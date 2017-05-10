package com.dlgdev.pokemon.webservice.controllers

import com.dlgdev.pokemon.database.PokemonProvider
import com.dlgdev.pokemon.webservice.actions.BaseAction
import com.dlgdev.pokemon.webservice.models.Output
import javax.inject.Inject

class MainGamesController @Inject constructor(var provider: PokemonProvider) : BaseController {
    override fun process(): Output {
        var mon = provider.find(1,1)
        var result = object: Output {
            override lateinit var result: Any
            override var status = 200
        }
        result.result = mon
        return result
    }

    override lateinit var action: BaseAction
}