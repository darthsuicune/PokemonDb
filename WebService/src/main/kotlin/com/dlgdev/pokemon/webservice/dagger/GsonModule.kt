package com.dlgdev.pokemon.webservice.dagger

import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class GsonModule {
    @Provides fun provideGson(): Gson {
        return Gson()
    }
}

