package com.andia.loice.movies.dagger.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public class SchedulerProvider{

    private final Scheduler mainThreadScheduler;
    private final Scheduler ioScheduler;

    public SchedulerProvider(@NonNull final Scheduler mainThreadScheduler,
                             @NonNull final Scheduler ioScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioScheduler = ioScheduler;
    }

    public Scheduler getIoScheduler() {
        return ioScheduler;
    }

    public Scheduler getMainThreadScheduler() {
        return mainThreadScheduler;
    }
}