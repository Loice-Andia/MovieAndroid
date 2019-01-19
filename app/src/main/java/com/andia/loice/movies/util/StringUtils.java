package com.andia.loice.movies.util;

import android.content.Context;

import com.andia.loice.movies.model.data.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    private final Context context;

    public StringUtils(Context context) {
        this.context = context;
    }

    public static String getBackdropUrl(String imgUrl) {
        return "https://image.tmdb.org/t/p/" +
                "w780" +
                imgUrl;
    }

    public static String getPosterUrl(String imgUrl) {
        return "https://image.tmdb.org/t/p/" +
                "w185" +
                imgUrl;
    }

    public static String truncate(String input) {
        int amt = 100;
        if (input.length() < amt) {
            return input;
        }
        return input.substring(0, amt) + "...";
    }

    public String readFile(String fileName) throws IOException {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName), "UTF-8"));

        String content = "";
        String line;
        while ((line = reader.readLine()) != null) {
            content = content + line;
        }

        return content;

    }

    public List<Movie> getmovies() {
        String jsonFileContent = null;
        List<Movie> movies = new ArrayList<>();
        try {
            jsonFileContent = readFile("movie-test-data.json");
            JSONArray jsonArray = null;
            jsonArray = new JSONArray(jsonFileContent);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String title = jsonObj.getString("title");
                movies.add(new Movie(title));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;

    }
}
