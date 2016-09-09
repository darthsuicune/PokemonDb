package com.dlgdev.pokemon.database;

public interface PokemonProvider {
	Pokemon find(int dexNumber, int form);
}
