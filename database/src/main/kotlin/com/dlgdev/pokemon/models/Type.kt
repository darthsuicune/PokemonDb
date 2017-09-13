package com.dlgdev.pokemon.models

enum class Type {
    NORMAL, FIGHTING, FLYING, FIRE, WATER, GRASS, DRAGON, FAIRY, ELECTRIC, ICE, POISON, DARK, STEEL, GROUND, ROCK,
    PSYCHIC, GHOST, BUG;

    var weaknesses = emptySet<Type>()
    var resistances = emptySet<Type>()
    var immunities = emptySet<Type>()

    companion object {

        init {
            NORMAL.weaknesses = setOf(FIGHTING)
            NORMAL.immunities = setOf(GHOST)

            FIGHTING.weaknesses = setOf(FLYING, PSYCHIC, FAIRY)
            FIGHTING.resistances = setOf(DARK, ROCK, BUG)

            FLYING.weaknesses = setOf(ELECTRIC, ICE, ROCK)
            FLYING.resistances = setOf(FIGHTING, GRASS, BUG)
            FLYING.immunities = setOf(GROUND)

            FIRE.weaknesses = setOf(WATER, GROUND, ROCK)
            FIRE.resistances = setOf(FIRE, GRASS, FAIRY, ICE, STEEL, BUG)

            WATER.weaknesses = setOf(GRASS, ELECTRIC)
            WATER.resistances = setOf(FIRE, WATER, ICE, STEEL)

            GRASS.weaknesses = setOf(FLYING, FIRE, ICE, BUG, POISON)
            GRASS.resistances = setOf(WATER, GRASS, ELECTRIC, GROUND)

            DRAGON.weaknesses = setOf(DRAGON, ICE, FAIRY)
            DRAGON.resistances = setOf(FIRE, WATER, GRASS, ELECTRIC)

            FAIRY.weaknesses = setOf(POISON, STEEL)
            FAIRY.resistances = setOf(FIGHTING, DARK, BUG)
            FAIRY.immunities = setOf(DRAGON)

            ELECTRIC.weaknesses = setOf(GROUND)
            ELECTRIC.resistances = setOf(FLYING, ELECTRIC, STEEL)

            ICE.weaknesses = setOf(FIGHTING, FIRE, STEEL, ROCK)
            ICE.resistances = setOf(ICE)

            POISON.weaknesses = setOf(PSYCHIC, GROUND)
            POISON.resistances = setOf(FIGHTING, GRASS, FAIRY, POISON, BUG)

            DARK.weaknesses = setOf(FIGHTING, FAIRY, BUG)
            DARK.resistances = setOf(DARK, GHOST)
            DARK.immunities = setOf(PSYCHIC)

            STEEL.weaknesses = setOf(FIRE, GROUND, FIGHTING)
            STEEL.resistances = setOf(NORMAL, FLYING, GRASS, DRAGON, FAIRY, STEEL, ROCK, PSYCHIC, BUG, ICE)
            STEEL.immunities = setOf(POISON)

            GROUND.weaknesses = setOf(WATER, GRASS, ICE)
            GROUND.resistances = setOf(POISON, ROCK)
            GROUND.immunities = setOf(ELECTRIC)

            ROCK.weaknesses = setOf(FIGHTING, WATER, GRASS, STEEL, GROUND)
            ROCK.resistances = setOf(NORMAL, FIRE, POISON, FLYING)

            PSYCHIC.weaknesses = setOf(BUG, GHOST, DARK)
            PSYCHIC.resistances = setOf(FIGHTING, PSYCHIC)

            GHOST.weaknesses = setOf(GHOST, DARK)
            GHOST.resistances = setOf(POISON, BUG)
            GHOST.immunities = setOf(NORMAL, FIGHTING)

            BUG.weaknesses = setOf(FLYING, FIRE, ROCK)
            BUG.resistances = setOf(GRASS, FIGHTING, GROUND)
        }
    }
}
