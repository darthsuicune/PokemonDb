package com.dlgdev.pokemon.database.sqlite

import org.json.JSONObject

interface ShowdownImporter {
    fun importData(input: JSONObject) {}
}