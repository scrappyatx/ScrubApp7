package com.scrapdoodle.bryan.scrubapp7;

/**
 * Created by Bryan on 3/22/2017.
 */

public class Batch {
    private String orderOID;
    private Customer mCustomer;
    private Vehicle mAuto;
    private Driver mDriver;
    private ServiceProvider mServiceProvider;
    private Location pickupLocation;
    private Location serviceLocation;
    private Location returnLocation;
    private String targetPickupFromCustomer;
    private String targetArriveAtServiceLocation;
    private String targetServiceStart;
    private String targetServiceFinish;
    private String targetReturnToCustomer;
    private String serviceDescription;
    private batchState mBatchState;
    private batchStatus mBatchStatus;

    private enum batchState {assigned, unassigned};
    private enum batchStatus {none, pickupTransit, atPickup, pickupCompleted, serviceTransit, serviceArrived, serviceStarted,
        serviceComplete, returnTransit, atReturn, returnComplete}


    public String getOrderOID() {
        return orderOID;
    }

    public void setOrderOID(String orderOID) {
        this.orderOID = orderOID;
    }

    public Customer getmCustomer() {
        return mCustomer;
    }

    public void setmCustomer(Customer mCustomer) {
        this.mCustomer = mCustomer;
    }

    public Vehicle getmAuto() {
        return mAuto;
    }

    public void setmAuto(Vehicle mAuto) {
        this.mAuto = mAuto;
    }

    public Driver getmDriver() {
        return mDriver;
    }

    public void setmDriver(Driver mDriver) {
        this.mDriver = mDriver;
    }

    public ServiceProvider getmServiceProvider() {
        return mServiceProvider;
    }

    public void setmServiceProvider(ServiceProvider mServiceProvider) {
        this.mServiceProvider = mServiceProvider;
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

    public String getTargetPickupFromCustomer() {
        return targetPickupFromCustomer;
    }

    public void setTargetPickupFromCustomer(String targetPickupFromCustomer) {
        this.targetPickupFromCustomer = targetPickupFromCustomer;
    }

    public String getTargetArriveAtServiceLocation() {
        return targetArriveAtServiceLocation;
    }

    public void setTargetArriveAtServiceLocation(String targetArriveAtServiceLocation) {
        this.targetArriveAtServiceLocation = targetArriveAtServiceLocation;
    }

    public String getTargetServiceStart() {
        return targetServiceStart;
    }

    public void setTargetServiceStart(String targetServiceStart) {
        this.targetServiceStart = targetServiceStart;
    }

    public String getTargetServiceFinish() {
        return targetServiceFinish;
    }

    public void setTargetServiceFinish(String targetServiceFinish) {
        this.targetServiceFinish = targetServiceFinish;
    }

    public String getTargetReturnToCustomer() {
        return targetReturnToCustomer;
    }

    public void setTargetReturnToCustomer(String targetReturnToCustomer) {
        this.targetReturnToCustomer = targetReturnToCustomer;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public batchState getmBatchState() {
        return mBatchState;
    }

    public void setmBatchState(batchState mBatchState) {
        this.mBatchState = mBatchState;
    }

    public batchStatus getmBatchStatus() {
        return mBatchStatus;
    }

    public void setmBatchStatus(batchStatus mBatchStatus) {
        this.mBatchStatus = mBatchStatus;
    }
}
