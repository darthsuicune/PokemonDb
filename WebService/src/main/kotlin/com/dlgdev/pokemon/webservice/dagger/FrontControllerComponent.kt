package com.dlgdev.pokemon.webservice.dagger

import com.dlgdev.pokemon.webservice.FrontController

import dagger.Component

@Component(modules = arrayOf(LoggerModule::class, DatabaseModule::class))
interface FrontControllerComponent {

    fun inject(frontController: FrontController)
}
