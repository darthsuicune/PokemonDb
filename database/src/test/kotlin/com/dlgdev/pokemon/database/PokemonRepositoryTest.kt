package com.dlgdev.pokemon.database

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.mockito.MockitoAnnotations
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import javax.sql.DataSource

class PokemonRepositoryTest {
    @Mock lateinit internal var source: DataSource
    @Mock lateinit internal var connection: Connection
    @Mock lateinit internal var statement: PreparedStatement
    @Mock lateinit internal var resultSet: ResultSet

    lateinit var repo: PokemonRepository

    @Before @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repo = PokemonRepository(source)

        `when`(source.connection).thenReturn(connection)
        `when`(connection.prepareStatement(anyString())).thenReturn(statement)
        doNothing().`when`<PreparedStatement>(statement).setString(anyInt(), anyString())
        `when`(statement.executeQuery()).thenReturn(resultSet)
    }

    @Test @Throws(Exception::class)
    fun testFind() {
        val dexNumber = 1
        `when`(resultSet.getInt("dexNumber")).thenReturn(dexNumber)
        `when`(resultSet.getInt("formNumber")).thenReturn(0)
        `when`(resultSet.getString("name")).thenReturn("asdf")
        val mon = repo.find(1, 1)
        assertNotNull(mon)
        assertEquals(mon.dexNumber.toLong(), 1)
    }
}