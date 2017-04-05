package com.scrapdoodle.bryan.scrubapp7;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bryan on 3/21/2017.
 */

public class ServiceProvider implements Parcelable {
    private String name;
    private String icon;
    private String contactPhone;
    private String contactText;
    private String contactName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactText() {
        return contactText;
    }

    public void setContactText(String contactText) {
        this.contactText = contactText;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.icon);
        dest.writeString(this.contactPhone);
        dest.writeString(this.contactText);
        dest.writeString(this.contactName);
    }

    public ServiceProvider() {
    }

    protected ServiceProvider(Parcel in) {
        this.name = in.readString();
        this.icon = in.readString();
        this.contactPhone = in.readString();
        this.contactText = in.readString();
        this.contactName = in.readString();
    }

    public static final Parcelable.Creator<ServiceProvider> CREATOR = new Parcelable.Creator<ServiceProvider>() {
        @Override
        public ServiceProvider createFromParcel(Parcel source) {
            return new ServiceProvider(source);
        }

        @Override
        public ServiceProvider[] newArray(int size) {
            return new ServiceProvider[size];
        }
    };
}
