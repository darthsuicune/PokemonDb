package com.dlgdev.pokemon.database.daos

import com.dlgdev.pokemon.database.DatabaseDefinition
import com.dlgdev.pokemon.database.DatabaseDefinition.*
import com.dlgdev.pokemon.models.*
import com.dlgdev.pokemon.models.Pokemon
import com.dlgdev.pokemon.models.PokemonData
import com.dlgdev.pokemon.query
import com.dlgdev.pokemon.use
import java.sql.ResultSet
import javax.inject.Inject
import javax.sql.DataSource

open class PokemonDao @Inject constructor(val db: DataSource, val language: String = "English") {

    fun load(dexNumber: Int, form: Int, game: Int): Pokemon {
        val id = loadMon(dexNumber, form, game)
        val poke = Pokemon(dexNumber, form, id)
        poke.name = loadMonName(id, game, language)
        poke.data = loadMonData(id)
        poke.evolutions = loadMonEvolutions(id, game)
        poke.baseStats = loadMonBaseStats(id, game)
        poke.types = loadMonTypes(id, game)
        poke.abilities = loadMonAbilities(id, game)
        poke.eggGroups = loadMonEggGroups(id, game)
        poke.levelAttacks = loadMonLevelAttacks(id, game)
        poke.tmAttacks = loadMonTmAttacks(id, game)
        poke.tutorAttacks = loadMonTutorAttacks(id, game)
        poke.eventAttacks = loadMonEventAttacks(id, game)

        return poke
    }

    private fun loadMonLevelAttacks(id: Long, game: Int): Array<Attack> {
        val query = "SELECT ${PokemonLevelAttacks.ATTACK_ID}," +
                "${AttackNames.ATTACK_NAME}," +
                "${AttackNames.DESCRIPTION}," +
                "${PokemonLevelAttacks.GAME}," +
                "${Attacks.BASE_POWER}," +
                "${Attacks.ACCURACY}," +
                "${Attacks.MODE}," +
                "${Attacks.ATTACK_TYPE}," +
                AttackEffects.ID +
                "FROM ${PokemonLevelAttacks.TABLE_NAME}" +
                "WHERE ${PokemonLevelAttacks.POKEMON_ID} = ?"
        return runQuery({ rs ->
            val atts: Array<Attack> = emptyArray()
            var i = 0
            rs.first()
            while (rs.next()) {
                atts[i] = rowAsAttack(rs)
                i++
            }
            atts
        }, query, id.toString())
    }

    private fun rowAsAttack(rs: ResultSet): Attack {
        return Attack(id = rs.getLong(0),
                name = rs.getString(1),
                description = rs.getString(2),
                game = rs.getLong(3),
                basePower = rs.getInt(4),
                accuracy = rs.getInt(5),
                mode = rs.getInt(6),
                type = rs.getInt(7),
                effect = rs.getLong(8))
    }

    private fun <T> runQuery(action: (ResultSet) -> T, query: String, vararg values: String): T {
        var result: T? = null
        db.use { conn ->
            val ps = conn.prepareStatement(query)
            var i = 0
            values.forEach { ps.setString(i, values[i]); i++ }
            ps.query { rs ->
                result = action(rs)
            }
        }
        return result!!
    }

    private fun loadMonTmAttacks(id: Long, game: Int): Array<Attack> {
        var query = ""
        return emptyArray()
    }

    private fun loadMonTutorAttacks(id: Long, game: Int): Array<Attack> {
        var query = ""
        return emptyArray()
    }

    private fun loadMonEventAttacks(id: Long, game: Int): Array<Attack> {
        val query = ""
        return emptyArray()
    }

    private fun loadMon(dexNumber: Int, form: Int, gen: Int): Long {
        val query = "SELECT ${DatabaseDefinition.Pokemon.ID} " +
                "FROM ${DatabaseDefinition.Pokemon.TABLE_NAME} " +
                "WHERE ${DatabaseDefinition.Pokemon.DEX_NUMBER} = $dexNumber " +
                "AND ${DatabaseDefinition.Pokemon.FORM} = $form"
        return 0
    }

    private fun loadMonName(id: Long, gen: Int, language: String): String {
        val query = "SELECT ${PokemonNames.POKEMON_NAME} " +
                "FROM ${PokemonNames.TABLE_NAME} " +
                "WHERE ${PokemonNames.POKEMON_ID} = id"
        return ""
    }

    private fun loadMonData(id: Long): PokemonData {
        val q = "SELECT ${DatabaseDefinition.PokemonData.HEIGHT}, ${DatabaseDefinition.PokemonData.WEIGHT}, " +
                "${DatabaseDefinition.PokemonData.COLOR}, ${DatabaseDefinition.PokemonData.MALE_RATIO} " +
                "FROM ${DatabaseDefinition.PokemonData.TABLE_NAME} " +
                "WHERE ${DatabaseDefinition.PokemonData.POKEMON_ID} = id"
        return PokemonData("0.0", "0.0", "black", "0.5")
    }

    private fun loadMonEvolutions(id: Long, gen: Int): Array<Evolution> {

        val query = "SELECT ${Evolutions.FROM}, ${Evolutions.TO}, ${Evolutions.EVOLUTION_METHOD}, " +
                "${Evolutions.EVOLUTION_POINT}, ${Evolutions.IS_MEGA} " +
                "FROM ${Evolutions.TABLE_NAME} " +
                "WHERE ${Evolutions.GAME} = gen " +
                "AND (${Evolutions.FROM} = id " +
                "OR ${Evolutions.TO} = id)"
        return emptyArray()
    }

    private fun loadMonBaseStats(id: Long, gen: Int): BaseStats {
        val query = "SELECT ${PokemonBaseStats.HP}, ${PokemonBaseStats.ATK}, ${PokemonBaseStats.DEF}, " +
                "${PokemonBaseStats.SPATK}, ${PokemonBaseStats.SPDEF}, ${PokemonBaseStats.SPEED} " +
                "FROM ${PokemonBaseStats.TABLE_NAME} " +
                "WHERE ${PokemonBaseStats.GAME} = gen " +
                "AND ${PokemonBaseStats.POKEMON_ID} = id"
        return BaseStats(1, 1, 1, 1, 1, 1)
    }

    private fun loadMonEggGroups(id: Long, gen: Int): Array<EggGroup> {
        val query = "SELECT ${PokemonEggGroups.EGG_GROUP} " +
                "FROM ${PokemonEggGroups.TABLE_NAME} " +
                "WHERE ${PokemonEggGroups.GAME} = gen " +
                "AND ${PokemonEggGroups.POKEMON_ID} = id"
        return emptyArray()
    }

    private fun loadMonTypes(id: Long, gen: Int): Array<Type> {
        val query = "SELECT ${PokemonTypes.TYPE_1}, ${PokemonTypes.TYPE_2} " +
                "FROM ${PokemonTypes.TABLE_NAME} " +
                "WHERE ${PokemonTypes.GAME} = gen " +
                "AND ${PokemonTypes.POKEMON_ID} = id"
        return emptyArray()
    }

    private fun loadMonAbilities(id: Long, gen: Int): Array<Ability?> {
        val query = "SELECT ${PokemonAbilities.ABILITY_1},${PokemonAbilities.ABILITY_2}," +
                "${PokemonAbilities.ABILITY_HIDDEN}, " +
                "FROM ${PokemonAbilities.TABLE_NAME} " +
                "WHERE ${PokemonAbilities.GAME} = gen " +
                "AND ${PokemonAbilities.POKEMON_ID} = id"
        return emptyArray()
    }

    fun save(pokemon: Pokemon) {
        val conn = db.getConnection("", "")
        val prep = conn.prepareStatement("")


    }

    fun save(mons: Collection<Pokemon>?) {

    }
}

