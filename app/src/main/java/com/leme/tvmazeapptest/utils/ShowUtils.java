package com.leme.tvmazeapptest.utils;

import android.util.DisplayMetrics;
import android.view.Display;

import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class ShowUtils {

    public static int calculateBestSpanCount(Display display) {
        int posterWidth = 500;

        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float screenWidth = outMetrics.widthPixels;
        return Math.round(screenWidth / posterWidth);
    }

    public static String appendGenresString(String[] genres) {
        String genresToView = "";
        StringBuilder builder = new StringBuilder();

        for (String string : genres) {
            builder.append(string + " - ");
        }
        if(genres.length >= 1) {
            genresToView = builder.substring(0, builder.length()-3);
        }

        return genresToView;
    }

    public static List<Show> setFavoriteShowInListByResponseList(List<UserResponse> responseList, List<Show> favoritedShows) {
        List<Show> listShowWithFavotire = new ArrayList<Show>();

        for (UserResponse response : responseList) {
            if(favoritedShows.size() == 0) {
                response.getShow().setFavorite(false);
            } else {
                for (Show show : favoritedShows) {
                    if(response.getShow().getId() == show.getId()) {
                        response.getShow().setFavorite(true);
                    }
                }
            }
            listShowWithFavotire.add(response.getShow());
        }

        return listShowWithFavotire;
    }

    public static List<Show> addShowInFavoritedList(Show showUpdate, List<Show> favoritedShows) {
        if(favoritedShows.contains(showUpdate)) {
            favoritedShows.remove(showUpdate);
        } else {
            favoritedShows.add(showUpdate);
        }

        return favoritedShows;
    }

}
