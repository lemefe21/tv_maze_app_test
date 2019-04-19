package com.leme.tvmazeapptest.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leme.tvmazeapptest.constants.JsonResponse;
import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.model.response.ApiResponse;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class MapperUtilsTest {

    private List<ApiResponse> response;

    @Before
    public void setUpShowParcelable() {
        Type collectionType = new TypeToken<Collection<ApiResponse>>(){}.getType();
        response = new Gson().fromJson(JsonResponse.TV_MAZE_API_JSON_RESPONSE, collectionType);
    }

    @Test
    public void showResponsesToParcelables() {
        List<ShowParcelable> showParcelables = MapperUtils.showResponsesToParcelables(response);
        ShowParcelable show = showParcelables.get(0);

        assertEquals(Long.valueOf(41734L), show.getId());
        assertEquals("GIRLS", show.getName());
        assertArrayEquals(new String[]{"Comedy"}, show.getGenres());
        assertEquals("", show.getPremiered());
        assertNull(show.getSummary());

        ShowParcelable.ImageParcelable image = show.getImage();
        assertEquals("http://static.tvmaze.com/uploads/images/medium_portrait/191/478539.jpg", image.getMedium());
        assertEquals("http://static.tvmaze.com/uploads/images/original_untouched/191/478539.jpg", image.getOriginal());
    }

    @Test
    public void showResponsesToParcelablesWithPremiered() {
        List<ShowParcelable> showParcelables = MapperUtils.showResponsesToParcelables(response);
        ShowParcelable show = showParcelables.get(1);

        assertEquals(Long.valueOf(139L), show.getId());
        assertEquals("15/04/2012", show.getPremiered());
    }

    @Test
    public void showParcelableToEntity() {
        List<ShowParcelable> showParcelables = MapperUtils.showResponsesToParcelables(response);

        ShowParcelable show = showParcelables.get(0);
        Show entity = MapperUtils.showParcelableToEntity(show);

        assertEquals(Long.valueOf(41734L), entity.getId());
    }
}