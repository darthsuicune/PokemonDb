package com.dlgdev.pokemon.database.daos

import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import javax.sql.DataSource

class PokemonDaoTest {
    @Mock val db: DataSource? = null
    val dao: PokemonDao
    init {
        MockitoAnnotations.initMocks(this)
        dao = PokemonDao(db!!)
    }

    @Test
    fun testLoad() {

    }

    @Test
    fun testSave() {
    }

    @Test
    fun testSave1() {
    }

}