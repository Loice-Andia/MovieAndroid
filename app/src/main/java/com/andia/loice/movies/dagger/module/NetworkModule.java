package com.andia.loice.movies.dagger.module;

import com.andia.loice.movies.model.db.api.ApiService;
import com.andia.loice.movies.model.db.api.ApiServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    static ApiService provideApiService() {
        return new ApiServiceFactory().providesApiService();
    }

}
