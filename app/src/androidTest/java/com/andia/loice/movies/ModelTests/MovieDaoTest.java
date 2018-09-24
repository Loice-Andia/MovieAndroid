package com.andia.loice.movies.ModelTests;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.andia.loice.movies.TestUtils.DataGenerator;
import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.db.AppDatabase;
import com.andia.loice.movies.model.db.dao.MovieDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MovieDaoTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDatabase mDatabase;

    private MovieDao movieDao;

    @Before
    public void setUp() throws Exception {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        movieDao = mDatabase.movieDao();
    }

    @After
    public void tearDown() throws Exception {
        mDatabase.close();
    }

    @Test
    public void onFetchingMovies_shouldGetEmptyList_IfTable_IsEmpty() throws InterruptedException {
        movieDao.getAllMovies().just(0)
                .test()
                .assertSubscribed()
                .assertValues(0)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void onInsertingMovie_checkIf_RowCountIsCorrect() throws InterruptedException {

        Movie movie = DataGenerator.generateMovie();
        movieDao.insertMovie(movie);

        movieDao.getAllMovies().just(1)
                .test()
                .assertSubscribed()
                .assertValues(1)
                .assertComplete()
                .assertNoErrors();
    }
}
