package com.andia.loice.movies.util;

public class StringUtils {

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
}
