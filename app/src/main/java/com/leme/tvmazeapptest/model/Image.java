package com.leme.tvmazeapptest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {

    @SerializedName("medium")
    private String medium;

    @SerializedName("original")
    private String original;

    public Image(Parcel in) {
        this.medium = in.readString();
        this.original = in.readString();
    }

    public String getMedium() {
        return medium;
    }

    public String getOriginal() {
        return original;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(medium);
        parcel.writeString(original);
    }

    static Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        @Override
        public Image[] newArray(int i) {
            return new Image[i];
        }
    };

}
