package com.dlgdev.pokemon.database.models

class Pokemon(val id: Int, val dexNumber: Int, val formNumber: Int) {
    var name: String = ""
    var data: PokemonData = PokemonData("0.0", "0.0", "None", "0.0")
    var evolutions: Evolutions? = null
    var baseStats = BaseStats()
    var types = emptyArray<Type>()
    var abilities = emptyArray<Ability>()
    var eggGroups = emptyArray<EggGroup>()
    var evos = emptyArray<String?>()

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
        return data.color
    }

    fun maleRatio(): String {
        return data.maleRatio
    }

    fun femaleRatio(): String {
        return (1.0 - data.maleRatio.toDouble()).toString()
    }
}

class BaseStats

class PokemonData(val height: String, val weight: String, val color: String, val maleRatio: String)

class Evolutions

class EggGroup