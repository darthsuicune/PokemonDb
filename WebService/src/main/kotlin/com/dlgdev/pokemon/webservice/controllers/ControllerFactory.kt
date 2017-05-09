package com.dlgdev.pokemon.webservice.controllers

import javax.inject.Inject
import javax.servlet.http.HttpServletRequest

class ControllerFactory @Inject constructor(
        go: GoController,
        mainGames: MainGamesController
){

    val controllers = mapOf(
            Pair("go", go),
            Pair("mainGames", mainGames)
    )

    fun get(req: HttpServletRequest): BaseController {
        val requestedController = "go"
        return controllers[requestedController]!!
    }
}