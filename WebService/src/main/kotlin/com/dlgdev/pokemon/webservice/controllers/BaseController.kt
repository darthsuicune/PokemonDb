package com.dlgdev.pokemon.webservice.controllers

import com.dlgdev.pokemon.webservice.actions.BaseAction
import com.dlgdev.pokemon.webservice.models.Output

interface BaseController {
    fun process(): Output
    var action: BaseAction
}