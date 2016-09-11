package com.dlgdev.pokemon.database;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class PokemonDatabaseTest {
	@Mock DataSource source;
	@Mock Connection connection;
	@Mock PreparedStatement statement;
	@Mock ResultSet resultSet;
	PokemonDatabase db;
	@Before public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		db = new PokemonDatabase(source);

		when(source.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(anyString())).thenReturn(statement);
		doNothing().when(statement).setString(anyInt(), anyString());
		when(statement.executeQuery()).thenReturn(resultSet);
	}

	@Test public void testFind() throws Exception {
		int dexNumber = 1;
		when(resultSet.getInt("dexNumber")).thenReturn(dexNumber);
		Pokemon mon = db.find(1, 1);
		assertNotNull(mon);
		assertEquals(mon.dexNumber, 1);
	}
}