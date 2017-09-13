package com.dlgdev.pokemon.models

class Attack(val id: Long, val name: String, val description: String, val game: Game, val basePower: Int,
             val accuracy: Int, val mode: Mode, val type: Type, val effect: AttackEffect)

class AttackEffect(val attack: Attack, val effect: Effect, val chance: Int, val game: Game)

enum class Mode {
    PHYSICAL, SPECIAL, OTHER
}

enum class Effect {
    BURN, POISON, FREEZE, SLEEP, PARALYSIS, FLINCH
}