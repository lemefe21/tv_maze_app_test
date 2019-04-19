package com.leme.tvmazeapptest.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leme.tvmazeapptest.constants.JsonResponse;
import com.leme.tvmazeapptest.model.response.ApiResponse;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DeserializeApiResponse {

    @Test
    public void deserializeJsonTvMazeToApiResponseClass() {
        Type collectionType = new TypeToken<Collection<ApiResponse>>(){}.getType();
        List<ApiResponse> response = new Gson().fromJson(JsonResponse.TV_MAZE_API_JSON_RESPONSE, collectionType);

        ApiResponse apiResponse = response.get(0);

        ApiResponse.Show show = apiResponse.getShow();
        assertEquals(Long.valueOf(41734L), show.getId());
        assertEquals("GIRLS", show.getName());
        assertArrayEquals(new String[]{"Comedy"}, show.getGenres());
        assertNull(show.getPremiered());
        assertNull(show.getSummary());

        ApiResponse.Image image = show.getImage();
        assertEquals("http://static.tvmaze.com/uploads/images/medium_portrait/191/478539.jpg", image.getMedium());
        assertEquals("http://static.tvmaze.com/uploads/images/original_untouched/191/478539.jpg", image.getOriginal());
    }

}
