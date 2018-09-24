package com.andia.loice.movies.TestUtils;

import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.data.MovieResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    public static Movie generateMovie() {
        Movie movie = new Movie();
        movie.setId((long) 348350);
        movie.setTitle("Solo: A Star Wars Story");
        movie.setOverview("Through a series of daring escapades deep within a dark and dangerous");
        movie.setPosterPath("/4oD6VEccFkorEBTEDXtpLAaz0Rl.jpg");
        return movie;
    }

    public static List<Movie> generateMovieList(int count) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            movies.add(generateMovie());
        }
        return movies;
    }

    public MovieResponse getMovieTestData() {
        try {
            InputStream is = new FileInputStream("movie_test_data.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String textLine = bufferedReader.readLine();
            while (textLine != null) {
                builder.append(textLine);
                textLine = bufferedReader.readLine();
            }
            return new Gson().fromJson(builder.toString(), MovieResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
