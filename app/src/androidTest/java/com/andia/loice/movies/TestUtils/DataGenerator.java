package com.andia.loice.movies.TestUtils;

import com.andia.loice.movies.model.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    /**
     * Generates a dummy visitor
     *
     * @return
     */
    public static Movie generateMovie() {
        Movie movie = new Movie();
        movie.setId((long) 348350);
        movie.setTitle("Solo: A Star Wars Story");
        movie.setOverview("Through a series of daring escapades deep within a dark and dangerous");
        movie.setPosterPath("/4oD6VEccFkorEBTEDXtpLAaz0Rl.jpg");
        return movie;
    }

    /**
     * Generates a list of Visitors with {count} items
     *
     * @param count - number of dummy visitors to generate
     */
    public static List<Movie> generateMovieList(int count) {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            movies.add(generateMovie());
        }
        return movies;
    }
}
