package com.dlgdev.pokemon.models

class Game(val id: Long, val gen: Generation, val name: String)

class Generation(val id: Long, val num: Int, val name: String)