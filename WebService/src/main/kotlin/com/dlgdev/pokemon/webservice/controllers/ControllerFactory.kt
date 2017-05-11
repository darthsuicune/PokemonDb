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

    private lateinit var controller: BaseController

    fun get(req: HttpServletRequest): BaseController {
        //Drop 1 for the initial / passed to the method
        val requestBits = req.requestURI.drop(1).split("/")
        val controllerIndex = getController(requestBits)
        val actionIndex = getAction(requestBits, controllerIndex + 1)
        if (requestBits.size > actionIndex + 1) {
            val parameters = requestBits.slice(actionIndex + 1..requestBits.size)
            controller.action.setParameters(parameters)
        }
        return controller
    }

    private fun getAction(requestBits: List<String>, i: Int): Int {
        val requestedAction = requestBits[i]
        if (requestedAction !in actions.keys) {
            throw RuntimeException("$requestedAction is not a valid action")
        }
        controller.action = actions[requestedAction]!!
        return i
    }

    private fun getController(requestBits: List<String>): Int {
        var index = 0
        val requestedController = requestBits[index]
        if (requestedController !in controllers.keys) {
            if (requestedController in actions.keys) {
                controller = controllers["mainGames"]!!
                return --index
            } else {
                throw RuntimeException("$requestedController is not a valid controller")
            }
        }
        controller = controllers[requestBits[index]]!!
        return index
    }
}

