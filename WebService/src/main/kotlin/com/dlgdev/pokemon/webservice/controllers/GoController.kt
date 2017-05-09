package com.dlgdev.pokemon.webservice.controllers

import com.dlgdev.pokemon.database.PokemonProvider
import com.dlgdev.pokemon.webservice.controllers.BaseController
import javax.inject.Inject

class GoController @Inject constructor(val provider: PokemonProvider): BaseController {
    lateinit var parameters: GoControllerParameters

    override fun process() {
        val mon = provider.find(1,1)
        println(mon)
    }
}

class GoControllerParameters {
    class Builder {
        fun build(): GoControllerParameters {
            return GoControllerParameters()
        }
    }
}