package com.scrapdoodle.bryan.scrubapp7;

import java.util.ArrayList;

/**
 * Created by Bryan on 3/26/2017.
 */

public class OfferTest {

    public ArrayList<Offer> getTestList1() {
        ArrayList<Offer> mOfferList;
        Offer mOffer;

        mOfferList = new ArrayList<Offer>();
        mOffer = new Offer();

        // element 1
        mOffer.setOfferOID("X001");
        mOffer.setServiceProviderImage("jiffylube");
        mOffer.setOfferDate("01-April-2017");
        mOffer.setOfferTime("10:00 AM - 12:00 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$28");
        mOffer.setEstimatedEarningsExtra("+ tips");

        mOfferList.add(mOffer);

        //element 2
        mOffer.setOfferOID("X001");
        mOffer.setServiceProviderImage("kwikkar");
        mOffer.setOfferDate("02-April-2017");
        mOffer.setOfferTime("2:00 PM - 4:00 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$26");
        mOffer.setEstimatedEarningsExtra("+ tips");

        mOfferList.add(mOffer);


        return mOfferList;

    }
}
