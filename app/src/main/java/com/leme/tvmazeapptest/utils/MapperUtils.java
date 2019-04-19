package com.leme.tvmazeapptest.utils;

import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable.ImageParcelable;
import com.leme.tvmazeapptest.model.response.ApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MapperUtils {

    private MapperUtils() throws IllegalAccessException {
        throw new IllegalAccessException(AppValues.UTIL_CLASS);
    }

    public static List<ShowParcelable> showResponsesToParcelables(List<ApiResponse> responses) {
        List<ShowParcelable> parcelableList = new ArrayList<>();

        for (ApiResponse response : responses) {
            ShowParcelable parcelable = new ShowParcelable();
            ApiResponse.Show show = response.getShow();
            ApiResponse.Image image = show.getImage();

            parcelable.setId(show.getId());
            parcelable.setName(show.getName());
            parcelable.setGenres(show.getGenres());
            parcelable.setPremiered(show.getPremiered());
            parcelable.setSummary(show.getSummary());

            if(image == null) {
                parcelable.setImage(null);
            } else {
                ImageParcelable parcelableImage = new ImageParcelable();
                parcelableImage.setMedium(image.getMedium());
                parcelableImage.setOriginal(image.getOriginal());
                parcelable.setImage(parcelableImage);
            }

            parcelableList.add(parcelable);
        }

        return parcelableList;
    }

    public static Show showParcelableToEntity(ShowParcelable parcelable) {
        return new Show(parcelable.getId());
    }

}
