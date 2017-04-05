package com.scrapdoodle.bryan.scrubapp7;

/**
 * Created by Bryan on 3/25/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class OffersListAdapter extends RecyclerView.Adapter<OffersListAdapter.OfferHolder> {
    private ArrayList<Offer> mOffers;
    private static final String TAG = "OffersListAdapter";

    public static class OfferHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mServiceProviderImage;
        private TextView mItemTime;
        private TextView mItemDuration;
        private TextView mItemDescription;
        private TextView mItemEarnings;
        private TextView mItemEarningsExtra;
        private ImageView mItemDistanceImage;
        private TextView mItemDistance;
        private Offer mOffer;


        private static final String OFFER_KEY = "OFFER";


        public OfferHolder(View v) {
            super(v);

            mServiceProviderImage = (ImageView) v.findViewById(R.id.serviceProvider_image);
            mItemTime = (TextView) v.findViewById(R.id.item_time);
            mItemDuration = (TextView) v.findViewById(R.id.item_duration);
            mItemDescription = (TextView) v.findViewById(R.id.item_description);
            mItemEarnings = (TextView) v.findViewById(R.id.item_earnings);
            mItemEarningsExtra = (TextView) v.findViewById(R.id.item_earnings_extra);
            mItemDistance = (TextView) v.findViewById(R.id.item_distance);
            mItemDistanceImage = (ImageView) v.findViewById(R.id.distance_image);

            v.setOnClickListener(this);
        }

        //5
        @Override
        public void onClick(View v) {
            Context context = itemView.getContext();
            Intent showOfferIntent = new Intent(context, MainActivity.class);
            showOfferIntent.putExtra(OFFER_KEY, mOffer);
            context.startActivity(showOfferIntent);
        }

        public void bindOffer(Offer offer) {

            Log.d(TAG,"staring bindOffer");
            mOffer = offer;

            Log.d(TAG,"---> OfferOID: " + mOffer.getOfferOID());
            mItemTime.setText(mOffer.getOfferTime());
            Log.d(TAG,"---> OfferTime: " + mOffer.getOfferTime());

            mItemDuration.setText(mOffer.getOfferDuration());


            mItemDescription.setText(mOffer.getServiceDescription());


            mItemEarnings.setText(mOffer.getEstimatedEarnings());


            mItemEarningsExtra.setText(mOffer.getEstimatedEarningsExtra());


            Log.i(TAG,"finishing bindOffer");
        }

    }

    public OffersListAdapter(ArrayList<Offer> offers) {
        mOffers = offers;
    }

    @Override
    public OffersListAdapter.OfferHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offersview_item_row, parent, false);
        return new OfferHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(OffersListAdapter.OfferHolder holder, int position) {
        Offer itemOffer = mOffers.get(position);
        holder.bindOffer(itemOffer);

        Log.i(TAG,"Binding offer : " + Integer.toString(position));
    }

    @Override
    public int getItemCount() {
        return mOffers.size();
    }
}
