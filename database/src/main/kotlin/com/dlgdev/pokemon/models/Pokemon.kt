package com.dlgdev.pokemon.models

class Pokemon(val dexNumber: Int, val formNumber: Int,val id: Long = -1) {
    var name: String = ""
    var data: PokemonData = PokemonData("0.0", "0.0", "None", "0.0")
    var evolutions = emptyArray<Evolution>()
    var baseStats = BaseStats(1, 1, 1, 1, 1, 1)
    var types = emptyArray<Type>()
    var abilities = emptyArray<Ability?>()
    var eggGroups = emptyArray<EggGroup>()
    var levelAttacks = emptyArray<Attack>()
    var tmAttacks = emptyArray<Attack>()
    var tutorAttacks = emptyArray<Attack>()
    var eventAttacks = emptyArray<Attack>()

    override fun toString(): String {
        return "Pokemon: $name"
    }

    fun height(): String {
        return data.height
    }

    fun weight(): String {
        return data.weight
    }

    fun color(): String {
        return data.color ?: "None"
    }

    fun maleRatio(): String {
        return data.maleRatio ?: "N/A"
    }

    fun femaleRatio(): String {
        if(data.maleRatio != null) {
            return (1.0 - data.maleRatio!!.toDouble()).toString()
        } else {
            return "N/A"
        }

    }
}

class BaseStats(val hp: Int, val atk: Int, val def: Int, val spatk: Int, val spdef: Int, val speed: Int)

class PokemonData(val height: String, val weight: String, val color: String?, val maleRatio: String?)

class Evolution(val from: Long, val to: Long, val evolutionMethod: String, val evolutionPoint: String,
                val isMega: Boolean)

class EggGroup(val name: String)