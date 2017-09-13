package com.dlgdev.pokemon.database.showdown

import com.dlgdev.pokemon.database.daos.PokemonDao
import com.dlgdev.pokemon.models.*
import map
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import javax.inject.Inject

class Gen7ShowdownImporter @Inject constructor(val dao: PokemonDao) : ShowdownImporter {

    //Common to all mons
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
        val forms: MutableMap<Int, Int> = HashMap()
        input.keys().forEach { key ->
            val pokemon: JSONObject = input.getJSONObject(key as String?)
            val dexNumber: Int = pokemon.getInt(DEX_NUMBER)

            val formNumber: Int = if (pokemon.has(BASE_SPECIES)) {
                if (forms.containsKey(dexNumber)) {
                    forms[dexNumber] = forms[dexNumber]!! + 1
                } else {
                    forms[dexNumber] = 1
                }
                forms[dexNumber]!!
            } else {
                0
            }
            val poke: Pokemon = Pokemon(dexNumber, formNumber)
            //Available for all mons
            poke.name = pokemon.getString(SPECIES)
            poke.baseStats = baseStats(pokemon.getJSONObject(BASE_STATS))
            poke.types = typesFromArray(pokemon.getJSONArray(TYPES))
            poke.abilities = abilities(pokemon.getJSONObject(ABILITIES))
            poke.eggGroups = eggGroupsFromArray(pokemon.getJSONArray(EGG_GROUPS))
            poke.data = PokemonData(
                    pokemon.getString(HEIGHT),
                    pokemon.getString(WEIGHT),
                    if (pokemon.has(COLOR)) pokemon.getString(COLOR) else null,
                    if (pokemon.has(GENDER_RATIO)) {
                        genderRatio(pokemon.getJSONObject(GENDER_RATIO))
                    } else null
            )

            //Only available for some mons

            poke.evolutions = if (pokemon.has(EVOS)) evolutionsFromArray(pokemon.getJSONArray(EVOS)) else emptyArray()
//            poke.otherForms = if (pokemon.has(OTHER_FORMS)) {
//                stringsFromArray(pokemon.getJSONArray(OTHER_FORMS))
//            } else emptyArray()
//            poke.baseForm = if (pokemon.has(BASE_FORM)) pokemon.getString(BASE_FORM) else null
//            poke.formName = if (pokemon.has(FORM_NAME)) pokemon.getString(FORM_NAME) else null
//            poke.formLetter = if (pokemon.has(FORM_LETTER)) pokemon.getString(FORM_LETTER) else null
//            poke.evoLevel = if (pokemon.has(EVO_LEVEL)) pokemon.getInt(EVO_LEVEL) else -1
//            poke.gender = if (pokemon.has(GENDER)) pokemon.getString(GENDER) else null
//            poke.prevo = if (pokemon.has(PREVO)) pokemon.getString(PREVO) else null
            list.add(poke)
        }

        return list
    }

    private fun evolutionsFromArray(evosArray: JSONArray): Array<Evolution> {
        return evosArray.map { it -> Evolution(0, 0, "", "", false) }.toTypedArray()
    }

    private fun eggGroupsFromArray(eggGroupsArray: JSONArray): Array<EggGroup> {
        return eggGroupsArray.map { it -> EggGroup(it.getString("name")) }.toTypedArray()
    }

    private fun typesFromArray(typesArray: JSONArray): Array<Type> {
        return typesArray.map { it -> Type() }.toTypedArray()
    }

    private fun genderRatio(genderRatios: JSONObject): String {
        return genderRatios.getString("M")
    }

    private fun baseStats(baseStats: JSONObject): BaseStats {
        return BaseStats(baseStats.getInt("hp"),
                baseStats.getInt("atk"),
                baseStats.getInt("def"),
                baseStats.getInt("spa"),
                baseStats.getInt("spd"),
                baseStats.getInt("spe"))
    }

    private fun abilities(abilities: JSONObject): Array<Ability?> {
        val output: Array<Ability?> = arrayOfNulls(3)
        output[0] = Ability(abilities.getString("0"))
        output[1] = if (abilities.has("1")) Ability(abilities.getString("1")) else null
        output[2] = if (abilities.has("H")) Ability(abilities.getString("H")) else null
        return output
    }

    private fun stringsFromArray(array: JSONArray): Array<String?> {
        return Array(array.length(), { i -> array.getString(i) })
    }

    private fun write(pokemon: MutableList<Pokemon>) {
        dao.save(pokemon)
    }

}

