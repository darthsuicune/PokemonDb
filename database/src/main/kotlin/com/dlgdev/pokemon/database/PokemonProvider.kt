package com.dlgdev.pokemon.database

interface PokemonProvider {
    fun find(dexNumber: Int, form: Int): Pokemon
}
