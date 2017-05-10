package com.dlgdev.pokemon.webservice.controllers

import com.dlgdev.pokemon.database.PokemonProvider
import com.dlgdev.pokemon.webservice.actions.BaseAction
import com.dlgdev.pokemon.webservice.controllers.BaseController
import com.dlgdev.pokemon.webservice.models.Output
import javax.inject.Inject

class GoController @Inject constructor(val provider: PokemonProvider): BaseController {
    override lateinit var action: BaseAction
    lateinit var parameters: GoControllerParameters

    override fun process(): Output {
        val mon = provider.find(1,1)
        println(mon)
        return object: Output {
            override lateinit var result: Any
            override var status = 200
        }
    }
}

class GoControllerParameters {
    class Builder {
        fun build(): GoControllerParameters {
            return GoControllerParameters()
        }
    }
}