package com.cookingwithcode.lomboktraditionalfoods;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private String name, remarks, photo, content;

    public Food() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    protected Food(Parcel in) {
        name = in.readString();
        remarks = in.readString();
        photo = in.readString();
        content = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(remarks);
        dest.writeString(photo);
        dest.writeString(content);
    }
}