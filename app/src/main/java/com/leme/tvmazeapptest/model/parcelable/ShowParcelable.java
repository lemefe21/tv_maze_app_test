package com.leme.tvmazeapptest.model.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowParcelable implements Parcelable {

    private long id;
    private String name;
    private String[] genres;
    private String premiered;
    private ImageParcelable image;
    private String summary;
    private boolean isFavorite;

    public ShowParcelable() {
    }

    public ShowParcelable(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.genres = in.createStringArray();
        this.premiered = in.readString();
        this.image = in.readParcelable(ImageParcelable.class.getClassLoader());
        this.summary = in.readString();
        this.isFavorite = in.readByte() != 0;
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

    static Parcelable.Creator<ShowParcelable> CREATOR = new Parcelable.Creator<ShowParcelable>() {
        @Override
        public ShowParcelable createFromParcel(Parcel parcel) {
            return new ShowParcelable(parcel);
        }

        @Override
        public ShowParcelable[] newArray(int i) {
            return new ShowParcelable[i];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShowParcelable that = (ShowParcelable) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Getter
    @Setter
    public static class ImageParcelable implements Parcelable {

        private String medium;
        private String original;

        public ImageParcelable() {
        }

        public ImageParcelable(Parcel in) {
            this.medium = in.readString();
            this.original = in.readString();
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

        static Parcelable.Creator<ImageParcelable> CREATOR = new Parcelable.Creator<ImageParcelable>() {
            @Override
            public ImageParcelable createFromParcel(Parcel parcel) {
                return new ImageParcelable(parcel);
            }

            @Override
            public ImageParcelable[] newArray(int i) {
                return new ImageParcelable[i];
            }
        };
    }
}
