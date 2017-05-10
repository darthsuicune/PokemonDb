package com.dlgdev.pokemon.webservice.controllers

import com.dlgdev.pokemon.webservice.actions.DexAction
import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

class ControllerFactory @Inject constructor(
        go: GoController,
        mainGames: MainGamesController,
        errors: ErrorController,
        dexAction: DexAction
) {

    val controllers = mapOf(
            Pair("go", go),
            Pair("mainGames", mainGames)
    )

    val actions = mapOf(
            Pair("dex", dexAction)
    )

    fun get(req: HttpServletRequest): BaseController {
        val requestBits = req.requestURI.split("/")
        var controllerIndex = 1
        var requestedController = requestBits[controllerIndex]
        if (requestedController !in controllers.keys) {
            if (requestedController in actions.keys) {
                requestedController = "mainGames"
                controllerIndex--
            } else {
                throw RuntimeException("$requestedController is not a valid controller")
            }
        }
        val actionIndex = controllerIndex + 1
        val requestedAction = requestBits[actionIndex]
        if (requestedAction !in actions.keys) {
            throw RuntimeException("$requestedAction is not a valid action")
        }
        val controller = controllers[requestedController]!!
        controller.action = actions[requestedAction]!!
        return controller
    }
}

