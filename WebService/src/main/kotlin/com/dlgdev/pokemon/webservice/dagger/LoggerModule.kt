package com.dlgdev.pokemon.webservice.dagger

import dagger.Module
import dagger.Provides
import java.util.logging.Logger

@Module
class LoggerModule {
    @Provides internal fun provideLogger(): Logger {
        return Logger.getGlobal()
    }
}
