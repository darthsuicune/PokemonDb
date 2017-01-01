package com.dlgdev.pokemon.database

import java.util.*

class Pokemon(val dexNumber: Int, val formNumber: Int) {
    var name: String = ""
    var names: MutableMap<String, String> = HashMap()
    var baseStats = IntArray(6)
    var types = arrayOfNulls<String>(3)
    var genderRatio = DoubleArray(2)
    var abilities = arrayOfNulls<com.dlgdev.pokemon.database.Ability>(3)
    var eggGroups = arrayOfNulls<String>(2)
    var height: Double = 0.toDouble()
    var weight: Double = 0.toDouble()
    var color: String? = null
    var evos = emptyArray<String?>()
    var prevo: String? = null
    var otherForms = emptyArray<String?>()
    var gender: String? = null
    var evoLevel: Int = 0
    var baseForm: String? = null
    var formName: String? = null
    var formLetter: String? = null
}
