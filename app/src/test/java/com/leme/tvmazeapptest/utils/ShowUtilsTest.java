package com.leme.tvmazeapptest.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leme.tvmazeapptest.constants.JsonResponse;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.model.response.ApiResponse;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ShowUtilsTest {

    private List<ShowParcelable> showParcelables;

    @Before
    public void setUpShowParcelable() {
        Type collectionType = new TypeToken<Collection<ApiResponse>>(){}.getType();
        List<ApiResponse> response = new Gson().fromJson(JsonResponse.TV_MAZE_API_JSON_RESPONSE, collectionType);

        showParcelables = MapperUtils.showResponsesToParcelables(response);
    }

    @Test
    public void appendGenreString() {
        ShowParcelable showParcelable = showParcelables.get(0);
        String genresString = ShowUtils.appendGenresString(showParcelable.getGenres());
        assertEquals("Comedy", genresString);
    }

    @Test
    public void appendGenresArrayString() {
        ShowParcelable showParcelable = showParcelables.get(1);
        String genresString = ShowUtils.appendGenresString(showParcelable.getGenres());
        assertEquals("Drama - Romance", genresString);
    }

    @Test
    public void appendGenresEmptyArrayString() {
        ShowParcelable showParcelable = showParcelables.get(5);
        String genresString = ShowUtils.appendGenresString(showParcelable.getGenres());
        assertEquals("", genresString);
    }

    @Test
    public void parsePremieredDate() {
        ShowParcelable showParcelable = showParcelables.get(1);
        String premieredDate = ShowUtils.parsePremieredDate(showParcelable.getPremiered());
        assertEquals("15/04/2012", premieredDate);
    }

    @Test
    public void parsePremieredDateWithException() {
        String premieredDate = ShowUtils.parsePremieredDate("no_date");
        assertEquals("", premieredDate);
    }

    @Test
    public void parseNullPremieredDate() {
        ShowParcelable showParcelable = showParcelables.get(0);
        String premieredDate = ShowUtils.parsePremieredDate(showParcelable.getPremiered());
        assertEquals("", premieredDate);
    }

}