package com.andia.loice.movies.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.andia.loice.movies.model.data.Movie;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    Flowable<List<Movie>> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMoviesList(List<Movie> movieList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovieList(List<Movie> movieList);

    @Delete
    void deleteMovie(Movie movie);


}
