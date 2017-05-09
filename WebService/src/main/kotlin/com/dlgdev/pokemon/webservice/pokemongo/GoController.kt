package com.dlgdev.pokemon.webservice.pokemongo

import com.dlgdev.pokemon.database.PokemonProvider
import com.dlgdev.pokemon.webservice.BaseController
import javax.inject.Inject

class GoController @Inject constructor(var provider: PokemonProvider): BaseController {
    override fun process() {

    }
}