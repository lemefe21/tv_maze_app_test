package com.leme.tvmazeapptest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Show implements Parcelable {

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

    public Show(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.genres = in.createStringArray();
        this.premiered = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.summary = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeStringArray(genres);
        parcel.writeString(premiered);
        parcel.writeParcelable(image, flags);
        parcel.writeString(summary);
    }

    static Parcelable.Creator<Show> CREATOR = new Parcelable.Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel parcel) {
            return new Show(parcel);
        }

        @Override
        public Show[] newArray(int i) {
            return new Show[i];
        }
    };

}
