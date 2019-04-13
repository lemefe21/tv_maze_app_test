package com.leme.tvmazeapptest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserResponse implements Parcelable {

    @SerializedName("show")
    private Show show;

    public UserResponse(Parcel in) {
        this.show = in.readParcelable(Show.class.getClassLoader());
    }

    public Show getShow() {
        return show;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(show, flags);
    }

    static Parcelable.Creator<UserResponse> CREATOR = new Parcelable.Creator<UserResponse>() {
        @Override
        public UserResponse createFromParcel(Parcel parcel) {
            return new UserResponse(parcel);
        }

        @Override
        public UserResponse[] newArray(int i) {
            return new UserResponse[i];
        }
    };
}
