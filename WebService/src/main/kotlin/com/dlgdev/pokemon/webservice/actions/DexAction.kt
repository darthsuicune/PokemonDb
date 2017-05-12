package com.dlgdev.pokemon.webservice.actions

import com.dlgdev.pokemon.database.PokemonProvider
import javax.inject.Inject

class DexAction @Inject constructor(var provider: PokemonProvider) : BaseAction {
    override var page = "dex"

    override fun perform(): Any {
        if (parameters.requestedId > -1) {
            return provider.find(parameters.requestedId, 0)
        } else {
            page = "maindex"
            return Any()
        }
    }

    var parameters: DexAction.ActionParameters = ActionParameters()

    override fun setParameters(parameters: List<String>) {
        if(parameters.isNotEmpty()) {
            this.parameters.requestedId = Integer.parseInt(parameters[0])
        }
    }

    class ActionParameters {
        var requestedId = -1
    }
}

