package com.dlgdev.pokemon.webservice.actions

interface BaseAction {
    fun setParameters(parameters: List<String>)
    fun perform(): Any
    interface ActionParameters

    val page: String
}