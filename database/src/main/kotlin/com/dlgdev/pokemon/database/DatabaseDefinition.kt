package com.dlgdev.pokemon.database

class DatabaseDefinition {

    object Pokemon {
        val TABLE_NAME = "pokemon"
        val ID = "pokemon_id"
        val DEX_NUMBER = "dex_number"
        val FORM = "form"

        val uniqueKey = "$DEX_NUMBER,$FORM"
    }

    object PokemonData {
        val TABLE_NAME = "pokemondata"
        val POKEMON_ID = "pokemon_id"
        val HEIGHT = "base_height"
        val WEIGHT = "base_weight"
        val COLOR = "color"
        val MALE_RATIO = "maleratio"
    }

    object Evolutions {
        val TABLE_NAME = "evolutions"
        val GAME = "game_id"
        val FROM = "from_id"
        val TO = "to_id"
        val EVOLUTION_METHOD = "evolution_method"
        val EVOLUTION_POINT = "evolution_point"
        val IS_MEGA = "is_mega"
    }

    object PokemonNames {
        val TABLE_NAME = "pokemonnames"
        val POKEMON_ID = "pokemon_id"
        val POKEMON_NAME = "name"
        val FORM_NAME = "form_name"
        val LANGUAGE = "language"
    }

    object PokemonBaseStats {
        val TABLE_NAME = "pokemonbasestats"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val HP = "hp"
        val ATK = "atk"
        val DEF = "def"
        val SPATK = "spatk"
        val SPDEF = "spdef"
        val SPEED = "speed"
    }

    object PokemonTypes {
        val TABLE_NAME = "pokemontypes"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val TYPE_1 = "type_1"
        val TYPE_2 = "type_2"

    }

    object PokemonAbilities {
        val TABLE_NAME = "pokemonabilities"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val ABILITY_1 = "ability_1"
        val ABILITY_2 = "ability_2"
        val ABILITY_HIDDEN = "ability_hidden"
    }

    object Abilities {
        val TABLE_NAME = "abilities"
        val ABILITY_ID = "ability_id"
        val GAME = "game_id"
        val NAME = "ability_name"
        val DESCRIPTION = "description"
        val LANGUAGE = "language"
    }

    object PokemonEggGroups {
        val TABLE_NAME = "pokemonegggroups"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val EGG_GROUP = "egg_group"
    }

    object Games {
        val TABLE_NAME = "games"
        val ID = "game_id"
        val NAME = "name"
        val GEN = "gen_id"
    }

    object Generations {
        val TABLE_NAME = "generations"
        val ID = "gen_id"
        val NAME = "name"
        val REPRESENTATION = "representation"
    }
    object Attacks {
        val TABLE_NAME = "attacks"
        val ID = "attack_id"
        val GEN = "gen_id"
        val BASE_POWER = "basepower"
        val ACCURACY = "accuracy"
        val ATTACK_TYPE = "attacktype"
        val MODE = "attackmode" //Special, Physical, Other
    }

    object AttackNames {
        val TABLE_NAME = "attacknames"
        val ATTACK_ID = "attack_id"
        val ATTACK_NAME = "attack_name"
        val DESCRIPTION = "attack_description"
        val LANGUAGE = "language"
    }
    
    object AttacksWithEffects {
        val TABLE_NAME = "attackswitheffects"
        val ATTACK_ID = "attack_id"
        val EFFECT_ID = "effect_id"
        val GEN = "gen_id"
        val CHANCE = "chance"
    }
    
    object AttackEffects {
        val TABLE_NAME = "attackeffects"
        val ID = "effect_id"
        val GAME = "game_id"
        val EFFECT_TYPE = "effect_type"
    }

    object PokemonLevelAttacks {
        val TABLE_NAME = "pokemonattackslevel"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val LEVEL = "level"
        val ATTACK_ID = "attack_id"
    }

    object PokemonTMAttacks {
        val TABLE_NAME = "pokemonattackstm"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val TM_NUMBER = "tm_number"
        val ATTACK_ID = "attack_id"
    }

    object PokemonTutorAttacks {
        val TABLE_NAME = "pokemonattackstutor"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val ATTACK_ID = "attack_id"
    }

    object PokemonEventAttacks {
        val TABLE_NAME = "pokemonattacksevent"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val ATTACK_ID = "attack_id"
    }

    object CatchRates {
        val TABLE_NAME = "catchrates"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val CATCH_RATE = "catch_rate"
    }
}
