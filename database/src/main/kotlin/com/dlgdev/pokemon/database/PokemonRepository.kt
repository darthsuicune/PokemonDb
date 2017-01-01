package com.dlgdev.pokemon.database

import com.dlgdev.utils.db.Select

import java.sql.ResultSet
import java.sql.SQLException

import javax.inject.Inject
import javax.sql.DataSource

class PokemonRepository
@Inject constructor(internal var source: DataSource) : PokemonProvider {

    override fun find(dexNumber: Int, form: Int): Pokemon {
        return Select(source)
                .from("pokemon")
                .where("dexNumber=?", arrayOf(Integer.toString(dexNumber)))
                .and("formNumber=?", arrayOf(Integer.toString(form)))
                .apply<Pokemon>({ this.loadPokemon(it) })
    }

    private fun loadPokemon(set: ResultSet): Pokemon {
        var dexNumber = 0
        var formNumber = 0
        var name = ""
        try {
            set.first()
            dexNumber = set.getInt("dexNumber")
            formNumber = set.getInt("formNumber")
            name = set.getString("name")
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        val pokemon = Pokemon(dexNumber, formNumber)
        pokemon.name = name
        return pokemon
    }
}
