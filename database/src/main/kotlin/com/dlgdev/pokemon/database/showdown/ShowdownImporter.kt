package com.dlgdev.pokemon.database.showdown

import org.json.JSONObject

interface ShowdownImporter {
    fun importData(input: JSONObject)
}