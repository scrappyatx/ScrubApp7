package com.scrapdoodle.bryan.scrubapp7;

import java.util.ArrayList;

/**
 * Created by Bryan on 3/21/2017.
 */

public class Customer {
    private String customerOID;
    private String firstName;
    private String lastName;
    private String contactPhone;
    private String contactText;

    public String getCustomerOID() {
        return customerOID;
    }

    public void setCustomerOID(String customerOID) {
        this.customerOID = customerOID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
