package com.dlgdev.pokemon.database

class DatabaseDefinition {

    object Pokemon {
        val TABLE_NAME = "pokemon"
        val POKEMON_DEX_NUMBER = "pokemon_id"
        val FORM_ID = "form_id"
        val HEIGHT = "height"
        val WEIGHT = "weight"
        val COLOR = "color"
        val MALE_RATIO = ""

        val primaryKey = String.format("%s,%s", POKEMON_DEX_NUMBER, FORM_ID)

    }

    object Evolutions {
        val TABLE_NAME = "evolutions"
        val FROM = "pokemon_id"
        val TO_DEX = "dex"
        val TO_FORM = "form"

        val primaryKey = String.format("%s,%s,%s", FROM, TO_DEX, TO_FORM)
        val indexes: MutableList<String> = mutableListOf()

        init {
            indexes.add(FROM)
            indexes.add(TO_DEX)
            indexes.add(TO_FORM)
        }
    }

    object PokemonNames {
        val TABLE_NAME = "pokemonname"
        val POKEMON_FORM_ID = "form_id"
        val POKEMON_NAME = "pokemon_name"
        val LANGUAGE = "language"

        val primaryKey = String.format("%s,%s", POKEMON_FORM_ID, LANGUAGE)
        val indexes: MutableList<String> = mutableListOf()

        init {
            indexes.add(POKEMON_NAME)
        }
    }

    object PokemonBaseStats {
        val TABLE_NAME = "pokemonbasestats"
        val POKEMON_FORM_ID = "form_id"
        val GEN = "gen"
        val HP = "hp"
        val ATK = "atk"
        val DEF = "def"
        val SPATK = "spatk"
        val SPDEF = "spdef"
        val SPEED = "speed"

        val primaryKey = String.format("%s,%s", POKEMON_FORM_ID, GEN)
    }

    object PokemonTypes {
        val TABLE_NAME = "pokemontypes"
        val POKEMON_FORM_ID = "form_id"
        val GEN = "gen"
        val TYPE_1 = "type1"
        val TYPE_2 = "type2"

        val primaryKey = String.format("%s,%s", POKEMON_FORM_ID, GEN)
        val indexes: MutableList<String> = mutableListOf()

        init {
            indexes.add(TYPE_1)
            indexes.add(TYPE_2)
        }
    }

    object PokemonAbilities {
        val TABLE_NAME = "pokemonabilities"
        val POKEMON_FORM_ID = "form_id"
        val GEN = "gen"
        val ABILITY_1 = "ability1"
        val ABILITY_2 = "ability2"
        val ABILITY_HIDDEN = "hiddenAbility"

        val primaryKey = String.format("%s,%s", POKEMON_FORM_ID, GEN)
        val indexes: MutableList<String> = mutableListOf<String>()

        init {
            indexes.add(ABILITY_1)
            indexes.add(ABILITY_2)
            indexes.add(ABILITY_HIDDEN)
        }
    }

    object Abilities {
        val TABLE_NAME = "abilities"
        val ABILITY_ID = "ability_id"
        val NAME = "ability_name"
        val LANGUAGE = "language"

        val primaryKey = String.format("%s,%s", ABILITY_ID, LANGUAGE)
    }

    object PokemonEggGroups {
        val TABLE_NAME = "pokemonegggroups"
        val POKEMON_ID = "pokemon_id"
        val EGG_GROUP = "egg_group"

        val primaryKey = String.format("%s,%s", POKEMON_ID, EGG_GROUP)
        val indexes: MutableList<String> = mutableListOf<String>()

        init {
            indexes.add(EGG_GROUP)
        }
    }
}
