package com.example.weiwang.kamcordsample.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by weiwang on 3/15/17.
 */

public class ParcelModel implements Parcelable {
    private String url;
    private int count;

    public ParcelModel(int count, String url) {
        this.url = url;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ParcelModel(Parcel in) {
        url = in.readString();
        count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeInt(count);
    }

    public static final Creator<ParcelModel> CREATOR = new Creator<ParcelModel>() {
        public ParcelModel createFromParcel(Parcel in) {
            return new ParcelModel(in);
        }

        public ParcelModel[] newArray(int size) {
            return new ParcelModel[size];
        }
    };
}
