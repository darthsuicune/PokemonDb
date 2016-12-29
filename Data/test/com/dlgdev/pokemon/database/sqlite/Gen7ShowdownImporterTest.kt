package com.dlgdev.pokemon.database.sqlite

import com.dlgdev.pokemon.database.Pokemon
import org.json.JSONObject
import org.junit.Test
import org.mockito.ArgumentMatchers.argThat
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class Gen7ShowdownImporterTest {
    val dao: PokemonDao = mock(PokemonDao::class.java)
    val importer = Gen7ShowdownImporter(dao)

    @Test fun testImport() {
        importer.importData(JSONObject("{" +
                "bulbasaur: {" +
                "num: 1," +
                "species: \"Bulbasaur\"," +
                "types: [\"Grass\", \"Poison\"]," +
                "genderRatio: {M: 0.875, F: 0.125}," +
                "baseStats: {hp: 45, atk: 49, def: 49, spa: 65, spd: 65, spe: 45}," +
                "abilities: {0: \"Overgrow\", H: \"Chlorophyll\"}," +
                "heightm: 0.7," +
                "weightkg: 6.9," +
                "color: \"Green\"," +
                "evos: [\"ivysaur\"]," +
                "eggGroups: [\"Monster\", \"Grass\"]," +
                "}," +
                "ivysaur: {" +
                "num: 2," +
                "species: \"Ivysaur\"," +
                "types: [\"Grass\", \"Poison\"]," +
                "genderRatio: {M: 0.875, F: 0.125}," +
                "baseStats: {hp: 60, atk: 62, def: 63, spa: 80, spd: 80, spe: 60}," +
                "abilities: {0: \"Overgrow\", H: \"Chlorophyll\"}," +
                "heightm: 1," +
                "weightkg: 13," +
                "color: \"Green\"," +
                "prevo: \"bulbasaur\"," +
                "evos: [\"venusaur\"]," +
                "evoLevel: 16," +
                "eggGroups: [\"Monster\", \"Grass\"]," +
                "}" + "}"))
        verify(dao).save(argThat { col: Collection<Pokemon> -> col.size == 2 })
    }
}