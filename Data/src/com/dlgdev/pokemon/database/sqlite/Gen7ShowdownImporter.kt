package com.dlgdev.pokemon.database.sqlite

import com.dlgdev.pokemon.database.Ability
import com.dlgdev.pokemon.database.Pokemon
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import javax.inject.Inject

class Gen7ShowdownImporter @Inject constructor(val dao: PokemonDao) : ShowdownImporter {

    //Common in all mons
    val DEX_NUMBER = "num"
    val SPECIES = "species"
    val TYPES = "types"
    val BASE_STATS = "baseStats"
    val ABILITIES = "abilities"
    val HEIGHT = "heightm"
    val WEIGHT = "weightkg"
    val COLOR = "color"
    val EGG_GROUPS = "eggGroups"

    //Only in some
    val BASE_SPECIES = "baseSpecies"
    val EVOS = "evos"
    val EVO_LEVEL = "evoLevel"
    val PREVO = "prevo"
    val FORM_NAME = "forme"
    val FORM_LETTER = "formeLetter"
    val OTHER_FORMS = "otherFormes"
    val BASE_FORM = "baseForme"
    val GENDER_RATIO = "genderRatio"
    val GENDER = "genderRatio"

    override fun importData(input: JSONObject) {
        val pokemon: MutableList<Pokemon> = read(input)
        write(pokemon)
    }

    private fun read(input: JSONObject): MutableList<Pokemon> {
        val list = ArrayList<Pokemon>()
        var forms: MutableMap<Int, Int> = HashMap()
        input.keys().forEach { key ->
            var pokemon: JSONObject = input.getJSONObject(key as String?)
            val dexNumber: Int = pokemon.getInt(DEX_NUMBER)

            var formNumber: Int = if (pokemon.has(BASE_SPECIES)) {
                if (forms.containsKey(dexNumber)) {
                    forms[dexNumber] = forms[dexNumber]!! + 1
                } else {
                    forms[dexNumber] = 1
                }
                forms[dexNumber]!!
            } else {
                0
            }
            var poke: Pokemon = Pokemon(dexNumber, formNumber)
            //Available for all mons
            poke.name = pokemon.getString(SPECIES)
            poke.names.put("English", poke.name)
            poke.baseStats = baseStats(pokemon.getJSONObject(BASE_STATS))
            poke.types = stringsFromArray(pokemon.getJSONArray(TYPES))
            poke.abilities = abilities(pokemon.getJSONObject(ABILITIES))
            poke.eggGroups = stringsFromArray(pokemon.getJSONArray(EGG_GROUPS))
            poke.height = pokemon.getDouble(HEIGHT)
            poke.weight = pokemon.getDouble(WEIGHT)
            poke.color = if (pokemon.has(COLOR)) pokemon.getString(COLOR) else null

            //Only available for some mons
            poke.genderRatio = if (pokemon.has(GENDER_RATIO)) {
                genderRatios(pokemon.getJSONObject(GENDER_RATIO))
            } else null
            poke.gender = if (pokemon.has(GENDER)) pokemon.getString(GENDER) else null
            poke.evos = if (pokemon.has(EVOS)) stringsFromArray(pokemon.getJSONArray(EVOS)) else null
            poke.prevo = if (pokemon.has(PREVO)) pokemon.getString(PREVO) else null
            poke.otherForms = if (pokemon.has(OTHER_FORMS)) {
                stringsFromArray(pokemon.getJSONArray(OTHER_FORMS))
            } else null
            poke.evoLevel = if(pokemon.has(EVO_LEVEL)) pokemon.getInt(EVO_LEVEL) else -1
            poke.baseForm = if(pokemon.has(BASE_FORM)) pokemon.getString(BASE_FORM) else null
            poke.formName = if(pokemon.has(FORM_NAME)) pokemon.getString(FORM_NAME) else null
            poke.formLetter = if(pokemon.has(FORM_LETTER)) pokemon.getString(FORM_LETTER) else null
            list.add(poke)
        }

        return list
    }

    private fun genderRatios(genderRatios: JSONObject): DoubleArray {
        return doubleArrayOf(genderRatios.getDouble("F"), genderRatios.getDouble("M"))
    }

    private fun baseStats(baseStats: JSONObject): IntArray {
        return intArrayOf(baseStats.getInt("hp"),
                baseStats.getInt("atk"),
                baseStats.getInt("def"),
                baseStats.getInt("spa"),
                baseStats.getInt("spd"),
                baseStats.getInt("spe"))
    }

    private fun abilities(abilities: JSONObject): Array<out Ability?> {
        val output: Array<Ability?> = arrayOfNulls(3)
        output[0] = Ability(abilities.getString("0"))
        output[1] = if (abilities.has("1")) Ability(abilities.getString("1")) else null
        output[2] = if (abilities.has("H")) Ability(abilities.getString("H")) else null
        return output
    }

    private fun stringsFromArray(array: JSONArray): Array<out String> {
        return Array(array.length(), { i -> array.getString(i) })
    }

    private fun write(pokemon: MutableList<Pokemon>) {
        dao.save(pokemon)
    }

}

