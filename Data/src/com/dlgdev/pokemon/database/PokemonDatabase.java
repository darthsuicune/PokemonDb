package com.dlgdev.pokemon.database;

import com.dlgdev.utils.db.Select;

import java.sql.ResultSet;
import java.sql.SQLException;

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
				.where("dexNumber=?", new String[]{Integer.toString(dexNumber)})
				.and("formNumber=?", new String[]{Integer.toString(form)})
				.apply(this::loadPokemon);
	}

	private Pokemon loadPokemon(ResultSet set) {
		int dexNumber = 0;
		int formNumber = 0;
		String name = "";
		try {
			set.first();
			dexNumber = set.getInt("dexNumber");
			formNumber = set.getInt("formNumber");
			name = set.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Pokemon pokemon = new Pokemon(dexNumber, formNumber);
		pokemon.name = name;
		return pokemon;
	}
}
