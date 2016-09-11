package com.dlgdev.pokemon.webservice.dagger;

import com.dlgdev.pokemon.database.PokemonDatabase;
import com.dlgdev.pokemon.database.PokemonProvider;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
	private static final String DATABASE_NAME = "pokemon";
	private static final String DATABASE_USER_NAME = "pokemon";
	private static final String DATABASE_PASSWORD = "pokemon";

	@Provides PokemonProvider pokemonProvider(PokemonDatabase db) {
		return db;
	}

	@Provides DataSource provideDataSource(MariaDbDataSource source) {
		initDatabase(source);
		return source;
	}

	private void initDatabase(DataSource source) {
		try (Connection connection = source.getConnection()) {
			String sql =
					"CREATE TABLE IF NOT EXISTS pokemon(dexNumber INTEGER PRIMARY KEY, formNumber INTEGER);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			String insert = "INSERT INTO pokemon(dexNumber, formNumber) VALUES ('asdf'),('asdfasdf');";
			statement = connection.prepareStatement(insert);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while creating database", e);
		}
	}

	@Provides MariaDbDataSource provideMariaDbDataSource() {
		MariaDbDataSource source;
		try {
			source = new MariaDbDataSource("localhost",3306,DATABASE_NAME);
			source.setUserName(DATABASE_USER_NAME);
			source.setPassword(DATABASE_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while connecting with database", e);
		}
		return source;
	}
}
