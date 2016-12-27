package com.dlgdev.pokemon.database;

import java.util.Collections;
import java.util.List;

public class DatabaseDefinition {

	public static class Pokemon {
		public static final String TABLE_NAME = "pokemon";
		public static final String POKEMON_DEX_NUMBER = "pokemon_id";
		public static final String FORM_ID = "form_id";
		public static final String HEIGHT = "height";
		public static final String WEIGHT = "weight";
		public static final String COLOR = "color";
		public static final String MALE_RATIO = "";

		public static final String primaryKey = String.format("%s,%s", POKEMON_DEX_NUMBER, FORM_ID);

	}

	public static class Evolutions {
		public static final String TABLE_NAME = "evolutions";
		public static final String FROM = "pokemon_id";
		public static final String TO_DEX = "dex";
		public static final String TO_FORM = "form";

		public static final String primaryKey = String.format("%s,%s,%s", FROM, TO_DEX, TO_FORM);
		public static final List<String> indexes = Collections.emptyList();
		static {
			indexes.add(FROM);
			indexes.add(TO_DEX);
			indexes.add(TO_FORM);
		}
	}

	public static class PokemonNames {
		public static final String TABLE_NAME = "pokemonname";
		public static final String POKEMON_FORM_ID = "form_id";
		public static final String POKEMON_NAME = "pokemon_name";
		public static final String LANGUAGE = "language";

		public static final String primaryKey = String.format("%s,%s", POKEMON_FORM_ID, LANGUAGE);
		public static final List<String> indexes = Collections.emptyList();

		static {
			indexes.add(POKEMON_NAME);
		}
	}

	public static class PokemonBaseStats {
		public static final String TABLE_NAME = "pokemonbasestats";
		public static final String POKEMON_FORM_ID = "form_id";
		public static final String GEN = "gen";
		public static final String HP = "hp";
		public static final String ATK = "atk";
		public static final String DEF = "def";
		public static final String SPATK = "spatk";
		public static final String SPDEF = "spdef";
		public static final String SPEED = "speed";

		public static final String primaryKey = String.format("%s,%s", POKEMON_FORM_ID, GEN);
	}

	public static class PokemonTypes {
		public static final String TABLE_NAME = "pokemontypes";
		public static final String POKEMON_FORM_ID = "form_id";
		public static final String GEN = "gen";
		public static final String TYPE_1 = "type1";
		public static final String TYPE_2 = "type2";

		public static final String primaryKey = String.format("%s,%s", POKEMON_FORM_ID, GEN);
		public static final List<String> indexes = Collections.emptyList();

		static {
			indexes.add(TYPE_1);
			indexes.add(TYPE_2);
		}
	}

	public static class PokemonAbilities {
		public static final String TABLE_NAME = "pokemonabilities";
		public static final String POKEMON_FORM_ID = "form_id";
		public static final String GEN = "gen";
		public static final String ABILITY_1 = "ability1";
		public static final String ABILITY_2 = "ability2";
		public static final String ABILITY_HIDDEN = "hiddenAbility";

		public static final String primaryKey = String.format("%s,%s", POKEMON_FORM_ID, GEN);
		public static final List<String> indexes = Collections.emptyList();

		static {
			indexes.add(ABILITY_1);
			indexes.add(ABILITY_2);
			indexes.add(ABILITY_HIDDEN);
		}
	}

	public static class Abilities {
		public static final String TABLE_NAME = "abilities";
		public static final String ABILITY_ID = "ability_id";
		public static final String NAME = "ability_name";
		public static final String LANGUAGE = "language";

		public static final String primaryKey = String.format("%s,%s", ABILITY_ID, LANGUAGE);
	}

	public static class PokemonEggGroups {
		public static final String TABLE_NAME = "pokemonegggroups";
		public static final String POKEMON_ID = "pokemon_id";
		public static final String EGG_GROUP = "egg_group";

		public static final String primaryKey = String.format("%s,%s", POKEMON_ID, EGG_GROUP);
		public static final List<String> indexes = Collections.emptyList();

		static {
			indexes.add(EGG_GROUP);
		}
	}
}
