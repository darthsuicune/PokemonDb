package com.dlgdev.pokemon.webservice.dagger;

import com.dlgdev.pokemon.database.PokemonRepository;
import com.dlgdev.pokemon.database.PokemonProvider;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
	private static final String DATABASE_NAME = "com/dlgdev/pokemon";
	private static final String DATABASE_USER_NAME = "com/dlgdev/pokemon";
	private static final String DATABASE_PASSWORD = "com/dlgdev/pokemon";

	@Provides PokemonProvider pokemonProvider(PokemonRepository db) {
		return db;
	}

	@Provides DataSource provideDataSource(MariaDbDataSource source) {
		initDatabase(source);
		return source;
	}

	private void initDatabase(DataSource source) {
		try (Connection connection = source.getConnection()) {
			if(tableExists("com/dlgdev/pokemon", "com/dlgdev/pokemon", connection)) {
				return;
			}
			System.out.println("Creating table");
			String sql = "CREATE TABLE IF NOT EXISTS pokemon(" +
					"dexNumber INTEGER, " +
					"formNumber INTEGER, " +
					"name VARCHAR(12), " +
					"PRIMARY KEY (dexNumber, formNumber));";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			String insert = "INSERT INTO pokemon(dexNumber, formNumber, name) " +
					"VALUES (1,1,'Bulbasaur'),(2,1,'Ivysaur');";
			statement = connection.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while creating com.dlgdev.pokemon.database", e);
		}
	}

	private boolean tableExists(String schemaName, String tableName, Connection connection)
			throws SQLException {
		String sql = "SELECT * FROM information_schema.tables WHERE table_schema=? AND table_name=? LIMIT 1;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, schemaName);
		statement.setString(2, tableName);
		ResultSet set = statement.executeQuery();
		return set.next();
	}

	@Provides MariaDbDataSource provideMariaDbDataSource() {
		MariaDbDataSource source;
		try {
			source = new MariaDbDataSource("localhost",3306,DATABASE_NAME);
			source.setUserName(DATABASE_USER_NAME);
			source.setPassword(DATABASE_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while connecting with com.dlgdev.pokemon.database", e);
		}
		return source;
	}
}
