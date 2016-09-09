package com.dlgdev.pokemon.webservice.dagger;

import java.util.logging.Logger;

import dagger.Module;
import dagger.Provides;

@Module
public class LoggerModule {
	@Provides Logger provideLogger() {
		return Logger.getGlobal();
	}
}
