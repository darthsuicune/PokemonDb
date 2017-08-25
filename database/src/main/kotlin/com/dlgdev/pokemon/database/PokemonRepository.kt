package com.dlgdev.pokemon.database

import com.dlgdev.pokemon.database.DatabaseDefinition.*
import com.dlgdev.pokemon.database.models.Pokemon
import com.dlgdev.utils.db.Select
import java.sql.ResultSet
import java.sql.SQLException

import javax.inject.Inject
import javax.sql.DataSource

class PokemonRepository @Inject constructor(internal var source: DataSource, var cache: PokemonCache)
    : PokemonProvider {

    override fun find(dexNumber: Int, form: Int): Pokemon {
        var mon = cache.find(dexNumber, form)
        if (mon == null) {
            Select(source)
                    .from("${DatabaseDefinition.Pokemon.TABLE_NAME} JOIN " +
                            "${DatabaseDefinition.PokemonNames.TABLE_NAME} ON " +
                            "${DatabaseDefinition.Pokemon.ID}=${PokemonNames.POKEMON_ID}")
                    .where("dexNumber=?", arrayOf(Integer.toString(dexNumber)))
                    .and("formNumber=?", arrayOf(Integer.toString(form)))
                    .apply<Pokemon>({ this.loadPokemon(it) })
        }
        return mon!!
    }

    private fun loadPokemon(set: ResultSet): Pokemon {
        try {
            set.first()
            val id = set.getLong(DatabaseDefinition.Pokemon.ID);
            val dexNumber = set.getInt(DatabaseDefinition.Pokemon.DEX_NUMBER)
            val formNumber = set.getInt(DatabaseDefinition.Pokemon.FORM)
            val name = set.getString("pokemon_name")

            val pokemon = Pokemon(dexNumber, formNumber, id)
            pokemon.name = name
            return pokemon
        } catch (e: SQLException) {
            e.printStackTrace()
            throw RuntimeException("Pokemon not found")
        }
    }
}
