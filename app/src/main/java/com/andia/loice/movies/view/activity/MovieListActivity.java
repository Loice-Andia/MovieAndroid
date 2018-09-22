package com.andia.loice.movies.view.activity;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andia.loice.movies.R;
import com.andia.loice.movies.databinding.ActivityMovieListBinding;
import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.view.adapter.MovieListAdapter;
import com.andia.loice.movies.viewmodel.MovieListViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MovieListActivity extends DaggerAppCompatActivity {
    ActivityMovieListBinding binding;


    private MovieListViewModel movieListViewModel;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ProgressDialog progressDialog;
    private MovieListAdapter movieListAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Movies");
        recyclerView = binding.movieListRcView;

        movieListViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel.class);

        fetchMovies();

    }

    private void fetchMovies() {
        progressDialog.show();
        final Observer<List<Movie>> moviesObserver = results -> {
            displayMovies(results);
            progressDialog.hide();
        };
        movieListViewModel.getMovies().observe(this, moviesObserver);
    }

    private void displayMovies(List<Movie> movies) {
        movieListAdapter = new MovieListAdapter(movies);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieListAdapter);
        movieListAdapter.notifyDataSetChanged();
        progressDialog.hide();

    }
}
