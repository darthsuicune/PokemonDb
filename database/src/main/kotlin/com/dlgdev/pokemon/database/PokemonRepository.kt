package com.dlgdev.pokemon.database

import com.dlgdev.utils.db.Select
import java.sql.ResultSet
import java.sql.SQLException

import javax.inject.Inject
import javax.sql.DataSource

class PokemonRepository @Inject constructor(internal var source: DataSource) : PokemonProvider {

    override fun find(dexNumber: Int, form: Int): Pokemon {
        return Select(source)
                .from("pokemon")
                .where("dexNumber=?", arrayOf(Integer.toString(dexNumber)))
                .and("formNumber=?", arrayOf(Integer.toString(form)))
                .apply<Pokemon>({ this.loadPokemon(it) })
    }

    private fun loadPokemon(set: ResultSet): Pokemon {
        try {
            set.first()
            val dexNumber = set.getInt("dexNumber")
            val formNumber = set.getInt("formNumber")
            val name = set.getString("name")

            val pokemon = Pokemon(dexNumber, formNumber)
            pokemon.name = name
            return pokemon
        } catch (e: SQLException) {
            e.printStackTrace()
            throw RuntimeException("Pokemon not found")
        }
    }
}
