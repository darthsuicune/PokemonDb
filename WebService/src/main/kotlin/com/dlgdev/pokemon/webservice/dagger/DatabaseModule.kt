package com.dlgdev.pokemon.webservice.dagger

import com.dlgdev.pokemon.database.PokemonProvider
import com.dlgdev.pokemon.database.PokemonRepository
import dagger.Module
import dagger.Provides
import org.mariadb.jdbc.MariaDbDataSource
import java.sql.Connection
import java.sql.SQLException
import javax.sql.DataSource

@Module
class DatabaseModule {

    @Provides fun pokemonProvider(db: PokemonRepository): PokemonProvider {
        return db
    }

    @Provides fun provideDataSource(source: MariaDbDataSource): DataSource {
        initDatabase(source)
        return source
    }

    private fun initDatabase(source: DataSource) {
        try {
            source.connection.use { connection ->
                if (tableExists("pokemon", "pokemon", connection)) {
                    return
                }
                println("Creating table")
                val sql = "CREATE TABLE IF NOT EXISTS pokemon(" +
                        "dexNumber INTEGER, " +
                        "formNumber INTEGER, " +
                        "name VARCHAR(12), " +
                        "PRIMARY KEY (dexNumber, formNumber));"
                var statement = connection.prepareStatement(sql)
                statement.execute()
                val insert = "INSERT INTO pokemon(dexNumber, formNumber, name) " + "VALUES (1,1,'Bulbasaur'),(2,1,'Ivysaur');"
                statement = connection.prepareStatement(insert)
                statement.executeUpdate()
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            throw RuntimeException("Error while creating database", e)
        }

    }

    @Throws(SQLException::class)
    private fun tableExists(schemaName: String, tableName: String, connection: Connection): Boolean {
        val sql = "SELECT * FROM information_schema.tables WHERE table_schema=? AND table_name=? LIMIT 1;"
        val statement = connection.prepareStatement(sql)
        statement.setString(1, schemaName)
        statement.setString(2, tableName)
        val set = statement.executeQuery()
        return set.next()
    }

    @Provides internal fun provideMariaDbDataSource(): MariaDbDataSource {
        val source: MariaDbDataSource
        try {
            source = MariaDbDataSource("localhost", 3306, DATABASE_NAME)
            source.userName = DATABASE_USER_NAME
            source.setPassword(DATABASE_PASSWORD)
        } catch (e: SQLException) {
            e.printStackTrace()
            throw RuntimeException("Error while connecting with database", e)
        }

        return source
    }

    companion object {
        private val DATABASE_NAME = "pokemon"
        private val DATABASE_USER_NAME = "pokemon"
        private val DATABASE_PASSWORD = "pokemon"
    }
}
