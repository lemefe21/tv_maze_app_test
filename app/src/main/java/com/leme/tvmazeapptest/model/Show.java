package com.leme.tvmazeapptest.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity(tableName = "show")
public class Show implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private long id;

    @Ignore
    @SerializedName("name")
    private String name;

    @Ignore
    @SerializedName("genres")
    private String[] genres;

    @Ignore
    @SerializedName("premiered")
    private String premiered;

    @Ignore
    @SerializedName("image")
    private Image image;

    @Ignore
    @SerializedName("summary")
    private String summary;

    @Ignore
    private boolean isFavorite;

    public Show(long id) {
        this.id = id;
    }

    @Ignore
    public Show(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.genres = in.createStringArray();
        this.premiered = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.summary = in.readString();
        this.isFavorite = in.readByte() != 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getPremiered() {
        return premiered;
    }

    public Image getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
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
        parcel.writeByte((byte) (isFavorite ? 1 : 0));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return id == show.id;
    }

}
