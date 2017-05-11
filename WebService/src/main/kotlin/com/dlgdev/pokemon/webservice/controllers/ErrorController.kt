package com.dlgdev.pokemon.webservice.controllers

import com.dlgdev.pokemon.webservice.actions.BaseAction
import com.dlgdev.pokemon.webservice.models.Output
import javax.inject.Inject

class ErrorController @Inject constructor() : BaseController {

    override lateinit var action: BaseAction

    var errorCode = 200

    override fun process(): Output {
        return object : Output {
            override var status = 400
            override var result = Any()
            override var page = "400"

        }
    }

}