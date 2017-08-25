package com.dlgdev.pokemon.database.showdown

import com.dlgdev.pokemon.database.models.*
import com.dlgdev.pokemon.database.models.Pokemon
import com.dlgdev.pokemon.database.models.PokemonData
import javax.inject.Inject
import javax.sql.DataSource

open class PokemonDao @Inject constructor(val db: DataSource, val language: String = "English") {

    fun load(dexNumber: Int, form: Int, gen: Int): Pokemon {
        val id = loadMon(dexNumber, form, gen)
        val poke = Pokemon(dexNumber, form, id)
        poke.name = loadMonName(id, gen, language)
        poke.data = loadMonData(id)
        poke.evolutions = loadMonEvolutions(id, gen)
        poke.baseStats = loadMonBaseStats(id, gen)
        poke.types = loadMonTypes(id, gen)
        poke.abilities = loadMonAbilities(id, gen)
        poke.eggGroups = loadMonEggGroups(id, gen)

        return poke
    }

    private fun loadMon(dexNumber: Int, form: Int, gen: Int): Long {
        /*SELECT ${Pokemon.ID}
        FROM ${Pokemon.TABLE_NAME}
        WHERE ${Pokemon.DEX_NUMBER} = dexNumber
            AND ${Pokemon.FORM} = form
         */
        return 0
    }

    private fun loadMonName(id: Long, gen: Int, language: String): String {
        /*
        SELECT ${PokemonNames.NAME}
        FROM ${PokemonNames.TABLE_NAME
        WHERE ${PokemonNames.POKEMON_ID} = id
         */
        return ""
    }

    private fun loadMonData(id: Long): PokemonData {
        /*SELECT ${PokemonData.HEIGHT}, ${PokemonData.WEIGHT}, ${PokemonData.COLOR}, ${PokemonData.MALE_RATIO}
        FROM ${PokemonData.TABLE_NAME}
        WHERE ${PokemonData.POKEMON_ID} = id
         */
        return PokemonData("0.0", "0.0", "black", "0.5")
    }

    private fun loadMonEvolutions(id: Long, gen: Int): Array<Evolution> {

        /* SELECT ${Evolutions.FROM}, ${Evolutions.TO}, ${Evolutions.EVOLUTION_METHOD}, ${Evolutions.EVOLUTION_POINT}, ${Evolutions.IS_MEGA}
        FROM ${Evolutions.TABLE_NAME}
        WHERE ${Evolutions.GEN} = gen
            AND (${Evolutions.FROM} = id
            OR ${Evolutions.TO} = id)

         */
        return emptyArray()
    }

    private fun loadMonBaseStats(id: Long, gen: Int): BaseStats {
        /* SELECT ${PokemonBaseStats.HP}, ${PokemonBaseStats.ATK}, ${PokemonBaseStats.DEF}, ${PokemonBaseStats.SPATK}, ${PokemonBaseStats.SPDEF}, ${PokemonBaseStats.SPEED}
        FROM ${PokemonBaseStats.TABLE_NAME}
        WHERE ${PokemonBaseStats.GEN} = gen
            AND ${PokemonBaseStats.POKEMON_ID} = id
        
         */
        return BaseStats(1, 1, 1, 1, 1, 1)
    }

    private fun loadMonEggGroups(id: Long, gen: Int): Array<EggGroup> {
        /* SELECT ${PokemonEggGroups.EGG_GROUP}
        FROM ${PokemonEggGroups.TABLE_NAME}
        WHERE ${PokemonEggGroups.GEN} = gen
            AND ${PokemonEggGroups.POKEMON_ID} = id
        
         */
        return emptyArray()
    }

    private fun loadMonTypes(id: Long, gen: Int): Array<Type> {
        /* SELECT ${PokemonTypes.TYPE_1}, ${PokemonTypes.TYPE_2}
        FROM ${PokemonTypes.TABLE_NAME}
        WHERE ${PokemonTypes.GEN} = gen
            AND ${PokemonTypes.POKEMON_ID} = id
        
         */
        return emptyArray()
    }

    private fun loadMonAbilities(id: Long, gen: Int): Array<Ability?> {
        /* SELECT ${PokemonAbilities.ABILITY_1},${PokemonAbilities.ABILITY_2},${PokemonAbilities.ABILITY_HIDDEN},
        FROM ${PokemonAbilities.TABLE_NAME}
        WHERE ${PokemonAbilities.GEN} = gen
            AND ${PokemonAbilities.POKEMON_ID} = id
        
         */
        return emptyArray()
    }

    fun save(pokemon: Pokemon) {
        val conn = db.getConnection("", "")
        val prep = conn.prepareStatement("")


    }

    fun save(mons: Collection<Pokemon>?) {

    }
}

