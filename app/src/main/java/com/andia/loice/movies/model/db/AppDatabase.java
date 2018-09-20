package com.andia.loice.movies.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.db.dao.MovieDao;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
}