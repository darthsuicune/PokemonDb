package com.dlgdev.pokemon.database;

import javax.inject.Inject;
import javax.sql.DataSource;

public class PokemonDatabase implements PokemonProvider{
	DataSource source;

	@Inject public PokemonDatabase(DataSource source) {
		this.source = source;
	}

	@Override public Pokemon find(int dexNumber, int form) {
		return new Pokemon();
	}
}
