package com.leme.tvmazeapptest.utils;

import android.util.DisplayMetrics;
import android.view.Display;

public class ShowUtils {

    public static int calculateBestSpanCount(Display display) {
        int posterWidth = 500;
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float screenWidth = outMetrics.widthPixels;
        return Math.round(screenWidth / posterWidth);
    }

    public static String AppendGenresString(String[] genres) {

        String genresToView = "";
        StringBuilder builder = new StringBuilder();

        for (String string : genres) {
            builder.append(string + " - ");
        }
        if(genres.length > 1) {
            genresToView = builder.substring(0, builder.length()-3);
        }
        return genresToView;

    }
}
