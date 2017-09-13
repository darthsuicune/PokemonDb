package com.dlgdev.pokemon.models

class Item(val game: Game, val type: ItemType)

enum class ItemType {
    HOLD, EVOLUTION, HEAL, MEGASTONE, ZSTONE, BALL, OVERWORLD,
}