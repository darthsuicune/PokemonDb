package com.dlgdev.pokemon.webservice.actions

import com.dlgdev.pokemon.database.PokemonProvider
import javax.inject.Inject

class DexAction @Inject constructor(var provider: PokemonProvider) : BaseAction {
    override val page = "dex"

    override fun perform(): Any {
        return provider.find(parameters.requestedId, 0)
    }

    var parameters: DexAction.ActionParameters = ActionParameters()

    override fun setParameters(parameters: List<String>) {
        this.parameters.requestedId = Integer.parseInt(parameters[0])
    }

    class ActionParameters {
        var requestedId = 0
    }
}

