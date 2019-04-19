package com.leme.tvmazeapptest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {

    @SerializedName("show")
    @Expose
    private Show show;

    @Getter
    @Setter
    public static class Show {

        @SerializedName("id")
        private long id;

        @SerializedName("name")
        private String name;

        @SerializedName("genres")
        private String[] genres;

        @SerializedName("premiered")
        private String premiered;

        @SerializedName("image")
        private Image image;

        @SerializedName("summary")
        private String summary;

    }

    @Getter
    @Setter
    public static class Image {

        @SerializedName("medium")
        private String medium;

        @SerializedName("original")
        private String original;

    }

}
