package com.dlgdev.pokemon.database.showdown

import com.dlgdev.pokemon.database.Pokemon
import javax.inject.Inject
import javax.sql.DataSource

open class PokemonDao @Inject constructor(val db: DataSource) {

    fun save(pokemon: Pokemon) {
        val conn = db.getConnection("", "")
        val prep = conn.prepareStatement("")
    }

    fun save(mons: Collection<Pokemon>?) {

    }
}

