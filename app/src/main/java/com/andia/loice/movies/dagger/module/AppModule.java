package com.andia.loice.movies.dagger.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.andia.loice.movies.BaseApp;
import com.andia.loice.movies.dagger.scheduler.IoScheduler;
import com.andia.loice.movies.dagger.scheduler.MainScheduler;
import com.andia.loice.movies.dagger.scheduler.SchedulerManager;
import com.andia.loice.movies.dagger.scheduler.SchedulerProvider;
import com.andia.loice.movies.model.db.AppDatabase;
import com.andia.loice.movies.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(BaseApp application) {
        return application.getApplicationContext();
    }

    @Provides
    @Reusable
    @MainScheduler
    Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Reusable
    @IoScheduler
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Reusable
    SchedulerManager provideSchedulerProvider(@NonNull @MainScheduler final Scheduler mainThreadScheduler,
                                              @NonNull @IoScheduler final Scheduler ioScheduler) {
        return new SchedulerProvider(mainThreadScheduler, ioScheduler);
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, Constants.DB_NAME)
                .allowMainThreadQueries()
                .build();
    }
}
