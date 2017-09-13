package com.dlgdev.pokemon

import com.dlgdev.pokemon.models.Pokemon
import javax.inject.Inject

class PokemonCache @Inject constructor(val mons: MutableMap<Int, MutableMap<Int, Pokemon>> = mutableMapOf()) {

    fun addToCache(poke: Pokemon) {
        if (mons.containsKey(poke.dexNumber)) {
            if (mons[poke.dexNumber]!!.containsKey(poke.formNumber))
                mons[poke.dexNumber]!![poke.formNumber] = poke
        } else {
            mons[poke.dexNumber] = mutableMapOf(Pair(poke.formNumber, poke))
        }
    }

    fun find(dexNumber: Int, formNumber: Int): Pokemon? {
        if (mons.containsKey(dexNumber) && mons[dexNumber]!!.containsKey(formNumber)) {
            return mons[dexNumber]!![formNumber]
        } else {
            return null
        }
    }
}