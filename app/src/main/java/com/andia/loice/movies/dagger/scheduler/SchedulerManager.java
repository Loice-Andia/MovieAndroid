package com.andia.loice.movies.dagger.scheduler;

import io.reactivex.Scheduler;

public interface SchedulerManager {
    Scheduler getIoScheduler();
    Scheduler getMainThreadScheduler();
}
