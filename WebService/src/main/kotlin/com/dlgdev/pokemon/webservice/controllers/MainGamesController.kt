package com.dlgdev.pokemon.webservice.controllers

import com.dlgdev.pokemon.webservice.actions.BaseAction
import com.dlgdev.pokemon.webservice.models.Output
import javax.inject.Inject

class MainGamesController @Inject constructor() : BaseController {
    override fun process(): Output {
        val actionResult = action.perform()
        val result = object: Output {
            override var page = action.page
            override lateinit var result: Any
            override var status = 200
        }
        result.result = actionResult
        return result
    }

    override lateinit var action: BaseAction
}