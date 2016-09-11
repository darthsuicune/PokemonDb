package com.dlgdev.pokemon.database;

import com.dlgdev.utils.db.Select;

import java.sql.ResultSet;

import javax.inject.Inject;
import javax.sql.DataSource;

public class PokemonDatabase implements PokemonProvider{
	DataSource source;

	@Inject public PokemonDatabase(DataSource source) {
		this.source = source;
	}

	@Override public Pokemon find(int dexNumber, int form) {
		return new Select(source)
				.from("pokemon")
				.where("dexNumber=?", new String[]{"200"})
				.execute(this::loadPokemon);
	}

	private Pokemon loadPokemon(ResultSet set) {
		return new Pokemon();
	}
}
