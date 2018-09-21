package com.andia.loice.movies.dagger.module;

import com.andia.loice.movies.model.db.ApiDao.ApiService;
import com.andia.loice.movies.model.db.ApiDao.ApiServiceFactory;
import com.andia.loice.movies.model.db.ApiDao.NetDataSource;
import com.andia.loice.movies.model.db.ApiDao.NetworkDataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    NetworkDataManager provideNetworkManager() {
        return new NetDataSource();
    }

    @Provides
    @Singleton
    ApiService provideApiService() {
        return new ApiServiceFactory().providesApiService();
    }

}
