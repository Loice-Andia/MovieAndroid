package com.andia.loice.movies.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.andia.loice.movies.R;
import com.andia.loice.movies.databinding.ActivityMovieListBinding;

public class MovieListActivity extends AppCompatActivity {
    ActivityMovieListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
    }
}
