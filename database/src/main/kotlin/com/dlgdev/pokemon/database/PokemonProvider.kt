package com.dlgdev.pokemon.database

import com.dlgdev.pokemon.database.models.Pokemon

interface PokemonProvider {
    fun find(dexNumber: Int, form: Int): Pokemon
    fun initialize()
}
