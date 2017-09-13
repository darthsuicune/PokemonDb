package com.dlgdev.pokemon

import com.dlgdev.pokemon.models.Pokemon

interface PokemonProvider {
    fun find(dexNumber: Int, form: Int): Pokemon
}
