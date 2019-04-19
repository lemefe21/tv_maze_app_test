package com.leme.tvmazeapptest.utils;

import android.util.DisplayMetrics;
import android.view.Display;

import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public static String appendGenresString(String[] genres) {
        StringBuilder builder = new StringBuilder();

        for (String string : genres) {
            builder.append(string)
                    .append(" - ");
        }

        return genres.length >= 1 ? builder.substring(0, builder.length() - 3) : "";
    }

    public static String parsePremieredDate(String premiered) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date date;
        try {
            date = df.parse(premiered);
        } catch (Exception e) {
            return "";
        }
        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy" );

        return df1.format(date);
    }

    public static List<ShowParcelable> setFavoritesInShowParcelableList(List<ShowParcelable> showParcelableList, List<Show> favoriteShows) {
        List<ShowParcelable> listShowWithFavorites = new ArrayList<>();

        for (ShowParcelable show : showParcelableList) {
            if(!favoriteShows.isEmpty()) {
                for (Show favoriteShow : favoriteShows) {
                    if(show.getId() == favoriteShow.getId()) {
                        show.setFavorite(true);
                    }
                }
            } else {
                show.setFavorite(false);
            }

            listShowWithFavorites.add(show);
        }

        return showParcelableList;
    }

    public static List<ShowParcelable> updateListWithFavoriteShow(ShowParcelable show, List<ShowParcelable> favoriteListShows) {
        if(favoriteListShows.contains(show)) {
            favoriteListShows.set(favoriteListShows.indexOf(show), show);
        }

        return favoriteListShows;
    }

}
