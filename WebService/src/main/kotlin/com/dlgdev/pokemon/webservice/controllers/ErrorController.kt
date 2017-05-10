package com.dlgdev.pokemon.webservice.controllers

import com.dlgdev.pokemon.webservice.actions.BaseAction
import com.dlgdev.pokemon.webservice.models.Output
import javax.inject.Inject

class ErrorController @Inject constructor() : BaseController {

    override lateinit var action: BaseAction

    var errorCode = 200

    override fun process(): Output {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}