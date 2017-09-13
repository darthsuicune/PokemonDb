package com.dlgdev.pokemon.database

import com.dlgdev.utils.db.Create.*

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
        val HEIGHT = "height"
        val WEIGHT = "weight"
        val COLOR = "color"
        val MALE_RATIO = "maleratio"

        val fields = mapOf(Pair(POKEMON_ID, "INTEGER NOT NULL"),
                Pair(HEIGHT, "TEXT"),
                Pair(WEIGHT, "TEXT"),
                Pair(COLOR, "TEXT"),
                Pair(MALE_RATIO, "TEXT"))
        val primaryKey = POKEMON_ID
        val foreignKeys = listOf(ForeignKey(POKEMON_ID, Pokemon.TABLE_NAME,Pokemon.ID))
        val indexes = listOf(COLOR)

    }

    object Evolutions {
        val TABLE_NAME = "evolutions"
        val GAME = "game_id"
        val FROM = "pokemon_from"
        val TO = "pokemon_to"
        val EVOLUTION_METHOD = "evolutionmethod"
        val EVOLUTION_POINT = "evolutionpoint"
        val IS_MEGA = "ismega"

        val fields = mapOf(Pair(GAME, "INTEGER"),
                Pair(FROM, "INTEGER"),
                Pair(TO, "INTEGER"),
                Pair(EVOLUTION_METHOD, "TEXT"),
                Pair(EVOLUTION_POINT, "TEXT"),
                Pair(IS_MEGA, "BOOLEAN"))

        val primaryKey = "$FROM, $TO"
        val foreignKeys = listOf(ForeignKey(FROM,Pokemon.TABLE_NAME,Pokemon.ID),
                ForeignKey(TO,Pokemon.TABLE_NAME,Pokemon.ID),
                ForeignKey(GAME,Games.TABLE_NAME,Games.ID))
        val indexes = listOf(GAME, FROM, TO)
    }

    object PokemonNames {
        val TABLE_NAME = "pokemonname"
        val POKEMON_ID = "pokemon_id"
        val POKEMON_NAME = "pokemon_name"
        val FORM_NAME = "form_name"
        val LANGUAGE = "language"

        val primaryKey = "$POKEMON_ID,$LANGUAGE"
        val foreignKeys = listOf(ForeignKey(POKEMON_ID,Pokemon.TABLE_NAME,Pokemon.ID))
        val indexes = listOf(POKEMON_NAME, POKEMON_ID)
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

        val primaryKey = "$POKEMON_ID, $GAME"
        val foreignKeys = listOf(ForeignKey(POKEMON_ID,Pokemon.TABLE_NAME,Pokemon.ID),
                ForeignKey(GAME,Games.TABLE_NAME,Games.ID))
    }

    object PokemonTypes {
        val TABLE_NAME = "pokemontypes"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val TYPE_1 = "type1"
        val TYPE_2 = "type2"

        val primaryKey = "$POKEMON_ID, $GAME"
        val foreignKeys = listOf(ForeignKey(POKEMON_ID,Pokemon.TABLE_NAME,Pokemon.ID),
                ForeignKey(GAME,Games.TABLE_NAME,Games.ID))
        val indexes = listOf(TYPE_1, TYPE_2, GAME)

    }

    object PokemonAbilities {
        val TABLE_NAME = "pokemonabilities"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val ABILITY_1 = "ability1"
        val ABILITY_2 = "ability2"
        val ABILITY_HIDDEN = "ability3"

        val primaryKey = "$POKEMON_ID, $GAME"
        val foreignKeys = listOf(ForeignKey(POKEMON_ID,Pokemon.TABLE_NAME,Pokemon.ID),
                ForeignKey(GAME,Games.TABLE_NAME,Games.ID))
        val indexes = listOf(ABILITY_1, ABILITY_2, ABILITY_HIDDEN, GAME)
    }

    object Abilities {
        val TABLE_NAME = "abilities"
        val ABILITY_ID = "ability_id"
        val GAME = "game_id"
        val NAME = "ability_name"
        val DESCRIPTION = "description"
        val LANGUAGE = "language"

        val primaryKey = "$ABILITY_ID, $LANGUAGE"
        val foreignKeys = listOf(ForeignKey(GAME,Games.TABLE_NAME,Games.ID))
        val indexes = listOf(NAME, GAME)
    }

    object PokemonEggGroups {
        val TABLE_NAME = "pokemonegggroups"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val EGG_GROUP = "egg_group"

        val primaryKey = "$POKEMON_ID, $GAME, $EGG_GROUP"
        val foreignKeys = listOf(ForeignKey(GAME,Games.TABLE_NAME,Games.ID))
        val indexes = listOf(EGG_GROUP, GAME)
    }

    object Games {
        val TABLE_NAME = "games"
        val ID = "game_id"
        val NAME = "name"
        val GEN = "gen_id"

        val primaryKey = ID
        val foreignKeys = listOf(ForeignKey(GEN, Generations.TABLE_NAME, Generations.ID))
    }

    object Generations {
        val TABLE_NAME = "generations"
        val ID = "gen_id"
        val NAME = "name"
        val REPRESENTATION = "representation"

        val primaryKey = ID
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
    
    object AttacksWithEffects {
        val TABLE_NAME = "attackswitheffects"
        val ATTACK_ID = "attack_id"
        val EFFECT_ID = "effect_id"
        val GEN = "gen_id"
        val CHANCE = "chance"
        
        val primaryKey = "$ATTACK_ID, $EFFECT_ID, $GEN"
        val foreignKeys = listOf(ForeignKey(GEN,Generations.TABLE_NAME,Generations.ID),
                ForeignKey(EFFECT_ID,AttackEffects.TABLE_NAME,AttackEffects.ID),
                ForeignKey(ATTACK_ID,Attacks.TABLE_NAME,Attacks.ID))
    }
    
    object AttackEffects {
        val TABLE_NAME = "attackeffects"
        val ID = "effect_id"
        val GAME = "game_id"
        val EFFECT_TYPE = "effect_type"

        val primaryKey = ID
        val foreignKeys = listOf(ForeignKey(GAME,Games.TABLE_NAME,Games.ID))
    }

    object PokemonLevelAttacks {
        val TABLE_NAME = "pokemonlevelattacks"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val LEVEL = "level"
        val ATTACK_ID = "attack_id"

        val primarykey = "$POKEMON_ID,$GAME,$ATTACK_ID"
        val foreignKeys = listOf(ForeignKey(GAME,Games.TABLE_NAME,Games.ID),
                ForeignKey(POKEMON_ID,Pokemon.TABLE_NAME,Pokemon.ID),
                ForeignKey(ATTACK_ID,Attacks.TABLE_NAME,Attacks.ID))
    }

    object PokemonTMAttacks {
        val TABLE_NAME = "pokemontmattacks"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val TM_NUMBER = "tm_number"
        val ATTACK_ID = "attack_id"

        val primarykey = "$POKEMON_ID,$GAME,$ATTACK_ID"
        val foreignKeys = listOf(ForeignKey(GAME,Games.TABLE_NAME,Games.ID),
                ForeignKey(POKEMON_ID,Pokemon.TABLE_NAME,Pokemon.ID),
                ForeignKey(ATTACK_ID,Attacks.TABLE_NAME,Attacks.ID))
    }

    object PokemonTutorAttacks {
        val TABLE_NAME = "pokemontutorattacks"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val ATTACK_ID = "attack_id"

        val primarykey = "$POKEMON_ID,$GAME,$ATTACK_ID"
        val foreignKeys = listOf(ForeignKey(GAME,Games.TABLE_NAME,Games.ID),
                ForeignKey(POKEMON_ID,Pokemon.TABLE_NAME,Pokemon.ID),
                ForeignKey(ATTACK_ID,Attacks.TABLE_NAME,Attacks.ID))
    }

    object PokemonEventAttacks {
        val TABLE_NAME = "pokemoneventattacks"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val ATTACK_ID = "attack_id"

        val primarykey = "$POKEMON_ID,$GAME,$ATTACK_ID"
        val foreignKeys = listOf(ForeignKey(GAME,Games.TABLE_NAME,Games.ID),
                ForeignKey(POKEMON_ID,Pokemon.TABLE_NAME,Pokemon.ID),
                ForeignKey(ATTACK_ID,Attacks.TABLE_NAME,Attacks.ID))
    }

    object CatchRates {
        val TABLE_NAME = "catchrates"
        val POKEMON_ID = "pokemon_id"
        val GAME = "game_id"
        val CATCH_RATE = "catch_rate"

        val primaryKey = "$POKEMON_ID, $GAME"
        val foreignKeys = listOf(ForeignKey(GAME,Games.TABLE_NAME,Games.ID),
                ForeignKey(POKEMON_ID,Pokemon.TABLE_NAME,Pokemon.ID))
    }
}
