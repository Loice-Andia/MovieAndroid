package com.andia.loice.movies.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.andia.loice.movies.dagger.scheduler.SchedulerManager;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory{
    private Map<Class<? extends ViewModel>, Provider<ViewModel>> creatorMaps;
    private final SchedulerManager schedulerManager;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creatorMaps, SchedulerManager schedulerManager) {
        this.creatorMaps = creatorMaps;
        this.schedulerManager = schedulerManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> creatorMap = creatorMaps.get(modelClass);

        if (creatorMap == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry :
                    creatorMaps.entrySet()
                    ) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creatorMap = entry.getValue();
                    break;
                }
            }
        }
        if (creatorMap == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creatorMap.get();

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}