package com.leme.tvmazeapptest.model.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowParcelable implements Parcelable {

    private long id;
    private String name;
    private List<String> genres = null;
    private String premiered;
    private Image image;
    private String summary;
    private boolean isFavorite;

    public ShowParcelable() {
    }

    public ShowParcelable(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.genres = in.createStringArrayList();
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
        parcel.writeList(genres);
        parcel.writeString(premiered);
        parcel.writeParcelable(image, flags);
        parcel.writeString(summary);
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

    @Getter
    @Setter
    public static class Image implements Parcelable {

        private String medium;
        private String original;

        public Image() {
        }

        public Image(Parcel in) {
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
}
