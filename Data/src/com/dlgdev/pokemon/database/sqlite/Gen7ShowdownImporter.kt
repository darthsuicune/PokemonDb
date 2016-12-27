package com.dlgdev.pokemon.database.sqlite

import com.dlgdev.pokemon.database.Ability
import com.dlgdev.pokemon.database.Pokemon
import org.json.JSONObject
import java.util.*

const val DEX_NUMBER = "num"
const val SPECIES = "species"
const val BASE_SPECIES = "baseSpecies"
const val TYPES = "types"
const val GENDER_RATIO = "genderRatio"
const val BASE_STATS = "baseStats"
const val ABILITIES = "abilities"
const val HEIGHT = "heightm"
const val WEIGHT = "weightkg"
const val COLOR = "color"
const val EVOS = "evos"
const val EGG_GROUPS = "eggGroups"
const val EVO_LEVEL = "evoLevel"
const val PREVO = "prevo"

const val OTHER_FORMS = "otherFormes"

class Gen7ShowdownImporter : ShowdownImporter {

    override fun import(input: JSONObject) {
        val pokemon: MutableList<Pokemon> = read(input)
        write(pokemon)
    }

    fun read(input: JSONObject): MutableList<Pokemon> {
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
            poke.name = pokemon.getString(SPECIES)
            poke.names.put("English", poke.name)
            poke.baseStats = intArrayOf(0, 1, 2, 3, 4, 5)
            poke.types = arrayOf("", "", "")
            poke.abilities = arrayOf(Ability(""), Ability(""), Ability(""))
            poke.eggGroups = arrayOf("", "")
            poke.genderRatio = doubleArrayOf(0.0, 0.0)
            poke.height = pokemon.getDouble(HEIGHT)
            poke.weight = pokemon.getDouble(WEIGHT)
            poke.color = if (pokemon.has(COLOR)) pokemon.getString(COLOR) else null
            poke.evos = if (pokemon.has(EVOS)) arrayOf("") else null
            poke.prevo = if (pokemon.has(PREVO)) pokemon.getString(PREVO) else null
            poke.otherForms = if (pokemon.has(OTHER_FORMS)) arrayOf("") else null
            list.add(poke)
        }

        return list
    }

    private fun write(pokemon: MutableList<Pokemon>) {
    }

}

interface ShowdownImporter {
    fun import(input: JSONObject)
}

