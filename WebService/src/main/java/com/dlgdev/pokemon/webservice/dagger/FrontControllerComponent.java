package com.dlgdev.pokemon.webservice.dagger;

import com.dlgdev.pokemon.webservice.FrontController;

import dagger.Component;

@Component(modules = {LoggerModule.class, DatabaseModule.class})
public interface FrontControllerComponent {

	void inject(FrontController frontController);
}
