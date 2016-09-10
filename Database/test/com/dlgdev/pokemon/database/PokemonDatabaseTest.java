package com.dlgdev.pokemon.database;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PokemonDatabaseTest {
	PokemonDatabase db;
	@Before public void setUp() throws Exception {
		db = new PokemonDatabase(null);
	}

	@Test public void testFind() throws Exception {
		Pokemon mon = db.find(0,0);
		assertNotNull(mon);
	}

	@Test public void testFindFails() throws Exception {
		Pokemon mon = db.find(0, 0);
		assertNull(mon);
	}
}