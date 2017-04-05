package com.scrapdoodle.bryan.scrubapp7;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bryan on 3/24/2017.
 */

public class Location implements Parcelable {
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String latitude;
    private String longitude;
    private String note;

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.street1);
        dest.writeString(this.street2);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.zip);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.note);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.street1 = in.readString();
        this.street2 = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.zip = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.note = in.readString();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
