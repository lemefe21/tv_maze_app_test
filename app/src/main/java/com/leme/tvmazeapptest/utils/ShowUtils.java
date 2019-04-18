package com.leme.tvmazeapptest.utils;

import android.util.DisplayMetrics;
import android.view.Display;

import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;

import java.util.ArrayList;
import java.util.List;

import static com.leme.tvmazeapptest.utils.AppValues.UTIL_CLASS;
import static java.lang.Math.round;

public class ShowUtils {

    private ShowUtils() throws IllegalAccessException {
        throw new IllegalAccessException(UTIL_CLASS);
    }

    public static int calculateBestSpanCount(Display display) {
        int posterWidth = 500;

        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float screenWidth = outMetrics.widthPixels;
        return round(screenWidth / posterWidth);
    }

    public static String appendGenresString(List<String> genres) {
        StringBuilder builder = new StringBuilder();

        for (String string : genres) {
            builder.append(string)
                    .append(" - ");
        }

        return genres.size() >= 1 ? builder.substring(0, builder.length() - 3) : "";
    }

    /*public static List<Show> setFavoriteShowInListByResponseList(List<ShowParcelable> showList,
                                                                 List<Show> favoritedShows) {
        List<Show> listShowWithFavotire = new ArrayList<>();

        for (UserResponse response : responseList) {
            if(!favoritedShows.isEmpty()) {
                for (Show show : favoritedShows) {
                    if(response.getShow().equals(show)) {
                        response.getShow().setFavorite(true);
                    }
                }
            } else {
                response.getShow().setFavorite(false);
            }

            listShowWithFavotire.add(response.getShow());
        }

        return listShowWithFavotire;
    }*/

    /*public static List<ShowParcelable> addShowInFavoritedList(ShowParcelable showUpdate, List<Show> favoritedShows) {
        if(favoritedShows.contains(showUpdate)) {
            favoritedShows.remove(showUpdate);
        } else {
            favoritedShows.add(showUpdate);
        }

        return showUpdate;
    }*/

}
