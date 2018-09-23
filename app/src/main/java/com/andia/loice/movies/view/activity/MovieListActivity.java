package com.andia.loice.movies.view.activity;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andia.loice.movies.R;
import com.andia.loice.movies.databinding.ActivityMovieListBinding;
import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.view.adapter.MovieListAdapter;
import com.andia.loice.movies.viewmodel.MovieListViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Activity class to display the list of fetched movies.
 */
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

    /**
     * Supply an observer to the viewmodel observable soas to get results
     */
    private void fetchMovies() {
        progressDialog.show();
        if (!isNetworkAvailable(this)) {
            Snackbar.make(binding.movieListRcView, "No network Connection", Snackbar.LENGTH_LONG)
                    .show();
        }
        final Observer<List<Movie>> moviesObserver = results -> {
            displayMovies(results);
            progressDialog.hide();
        };
        movieListViewModel.getMovies().observe(this, moviesObserver);
    }


    /**
     * method to supply movies to the adapter and notify of data change
     *
     * @param movies
     */
    private void displayMovies(List<Movie> movies) {
        movieListAdapter = new MovieListAdapter(movies);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieListAdapter);
        movieListAdapter.notifyDataSetChanged();
        progressDialog.hide();

    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
