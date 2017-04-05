package com.scrapdoodle.bryan.scrubapp7;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Bryan on 3/21/2017.
 */

public class Offer implements Parcelable {
    private String offerOID;
    private ServiceProvider mServiceProvider;
    private String serviceProviderImage;
    private String offerDate;
    private String offerTime;
    private String offerDuration;
    private Location pickupLocation;
    private Location serviceLocation;
    private Location returnLocation;
    private String serviceDescription;
    private String estimatedEarnings;
    private String estimatedEarningsExtra;

    public String getOfferOID() {
        return offerOID;
    }

    public void setOfferOID(String offerOID) {
        this.offerOID = offerOID;
    }

    public ServiceProvider getServiceProvider() {
        return mServiceProvider;
    }

    public void setServiceProvider(ServiceProvider mServiceProvider) {
        this.mServiceProvider = mServiceProvider;
    }

    public String getServiceProviderImage() {
        return serviceProviderImage;
    }

    public void setServiceProviderImage(String serviceProviderImage) {
        this.serviceProviderImage = serviceProviderImage;
    }

    public String getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(String offerDate) {
        this.offerDate = offerDate;
    }

    public String getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(String offerTime) {
        this.offerTime = offerTime;
    }

    public String getOfferDuration() {
        return offerDuration;
    }

    public void setOfferDuration(String offerDuration) {
        this.offerDuration = offerDuration;
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Location getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(Location serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public Location getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(Location returnLocation) {
        this.returnLocation = returnLocation;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getEstimatedEarnings() {
        return estimatedEarnings;
    }

    public void setEstimatedEarnings(String estimatedEarnings) {
        this.estimatedEarnings = estimatedEarnings;
    }

    public String getEstimatedEarningsExtra() {
        return estimatedEarningsExtra;
    }

    public void setEstimatedEarningsExtra(String estimatedEarningsExtra) {
        this.estimatedEarningsExtra = estimatedEarningsExtra;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.offerOID);
        dest.writeParcelable(this.mServiceProvider, flags);
        dest.writeString(this.serviceProviderImage);
        dest.writeString(this.offerDate);
        dest.writeString(this.offerTime);
        dest.writeString(this.offerDuration);
        dest.writeParcelable(this.pickupLocation, flags);
        dest.writeParcelable(this.serviceLocation, flags);
        dest.writeParcelable(this.returnLocation, flags);
        dest.writeString(this.serviceDescription);
        dest.writeString(this.estimatedEarnings);
        dest.writeString(this.estimatedEarningsExtra);
    }

    public Offer() {
    }

    protected Offer(Parcel in) {
        this.offerOID = in.readString();
        this.mServiceProvider = in.readParcelable(ServiceProvider.class.getClassLoader());
        this.serviceProviderImage = in.readString();
        this.offerDate = in.readString();
        this.offerTime = in.readString();
        this.offerDuration = in.readString();
        this.pickupLocation = in.readParcelable(Location.class.getClassLoader());
        this.serviceLocation = in.readParcelable(Location.class.getClassLoader());
        this.returnLocation = in.readParcelable(Location.class.getClassLoader());
        this.serviceDescription = in.readString();
        this.estimatedEarnings = in.readString();
        this.estimatedEarningsExtra = in.readString();
    }

    public static final Parcelable.Creator<Offer> CREATOR = new Parcelable.Creator<Offer>() {
        @Override
        public Offer createFromParcel(Parcel source) {
            return new Offer(source);
        }

        @Override
        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    };
}
