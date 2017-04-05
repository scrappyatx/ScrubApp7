package com.scrapdoodle.bryan.scrubapp7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rollbar.android.Rollbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.CompletionListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ErrorInfo;
import io.ably.lib.types.Message;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Switch mySwitch;
    private Switch testSwitch;
    private TextView myStatus;
    private RecyclerView mOfferView;
    private LinearLayoutManager mLinearLayoutManager;
    private OffersListAdapter mAdapter;
    private ArrayList<Offer> mOfferList;

    private AblyRealtime mAblyRealTime;
    private Channel mAblyOnDutyChannel;
    private final static String ABLY_API_KEY = "lekciw.COtO7w:MtlsHxVAa7MVrgx6";
    private static final String ABLY_ONDUTY_CHANNEL = "onduty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup rollbar
        Rollbar.init(this, "6489dbbc16e943beaebf5c0028ee588a", "production");


       //setup view
        mOfferView = (RecyclerView) findViewById(R.id.offersView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mOfferView.setLayoutManager(mLinearLayoutManager);


        mySwitch = (Switch) findViewById(R.id.online_switch);
        myStatus = (TextView) findViewById(R.id.status_text);
        testSwitch = (Switch) findViewById(R.id.test_switch);


        //set the switch to OFF
        mySwitch.setChecked(false);
        myStatus.setText("Go online to see current offers");
        testSwitch.setChecked(false);

        //initialize offer list
        mOfferList = new ArrayList<Offer>();

        Log.d(TAG,"mOfferList size before test data : " + Integer.toString(mOfferList.size()));
        generateTestData(mOfferList);
        Log.d(TAG,"mOfferList size after test data : " + Integer.toString(mOfferList.size()));


        mAdapter = new OffersListAdapter(mOfferList);
        mOfferView.setAdapter(mAdapter);


        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    mySwitch.setText("Online");
                    myStatus.setText("Looking for current offers");

                    //start service
                    Intent i = ServerCommService.newIntent(MainActivity.this);
                    i.putExtra("action","online");
                    MainActivity.this.startService(i);

                    //start sending updates
                    ServerCommService.setOnlineUpdates(MainActivity.this, true);

                    //send online message to ably
                    publishAbly("onduty");


                }else{
                    mySwitch.setText("Offline");
                    myStatus.setText("Go online to see current offers");

                    //stop sending updates
                    ServerCommService.setOnlineUpdates(MainActivity.this, false);

                    //send online message to ably
                    publishAbly("offduty");

                }
            }
        });

        testSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    testSwitch.setText("Real Offers");
                }else{
                    testSwitch.setText("Test Offers");
                }
            }
        });

        try {
            initAbly();
            Log.d(TAG,"initAbly complete");
        } catch (AblyException e) {
            Log.d(TAG,"initAbly error");
            e.printStackTrace();
        }
    }

    private void initAbly() throws AblyException {
        mAblyRealTime = new AblyRealtime(ABLY_API_KEY);

        //subscribe to location channel, and get notifed when message is received
        mAblyOnDutyChannel = mAblyRealTime.channels.get(ABLY_ONDUTY_CHANNEL);
        mAblyOnDutyChannel.subscribe(new Channel.MessageListener() {
            @Override
            public void onMessage(Message message) {
                Log.d(TAG,"message arrived in ably channel:" + message.data);
            }
        });
    }

    private void publishAbly(String data) {
        JSONObject mJSON = new JSONObject();

        try {
            mJSON.put("driver", "Fizzle Sparkcrank");
            mJSON.put("zone", "Austin");
            mJSON.put("status", data);
        } catch (JSONException e) {
            Log.d(TAG,"JSON exception while publishing message: " + e.getMessage());
            e.printStackTrace();
        }

        try {
                mAblyOnDutyChannel.publish("dutystatus", mJSON, new CompletionListener() {
                    @Override
                    public void onError(ErrorInfo reason) {
                        Log.d(TAG,"Unable to publish message; err = " + reason.message);
                    }

                    @Override
                    public void onSuccess() {
                         Log.d(TAG,"Message successfully sent");
                    }
                 });
        }
        catch (AblyException e) {
            Log.d(TAG,"initAbly publish error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void generateTestData(ArrayList<Offer> offerList) {
        Offer mOffer;



        // element 1
        mOffer = new Offer();
        mOffer.setOfferOID("X001");
        mOffer.setServiceProviderImage("jiffylube");
        mOffer.setOfferDate("01-April-2017");
        mOffer.setOfferTime("10:00 AM - 12:00 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$28");
        mOffer.setEstimatedEarningsExtra("+ tips");

        offerList.add(mOffer);

        //element 2
        mOffer = new Offer();
        mOffer.setOfferOID("X002");
        mOffer.setServiceProviderImage("kwikkar");
        mOffer.setOfferDate("02-April-2017");
        mOffer.setOfferTime("12:00 PM - 2:00 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$26");
        mOffer.setEstimatedEarningsExtra("+ tips");

        offerList.add(mOffer);

        //element 3
        mOffer = new Offer();
        mOffer.setOfferOID("X003");
        mOffer.setServiceProviderImage("kwikkar");
        mOffer.setOfferDate("02-April-2017");
        mOffer.setOfferTime("2:30 PM - 3:30 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$26");
        mOffer.setEstimatedEarningsExtra("+ tips");

        offerList.add(mOffer);

        //element 4
        mOffer = new Offer();
        mOffer.setOfferOID("X004");
        mOffer.setServiceProviderImage("kwikkar");
        mOffer.setOfferDate("02-April-2017");
        mOffer.setOfferTime("3:30 PM - 4:00 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$26");
        mOffer.setEstimatedEarningsExtra("+ tips");

        offerList.add(mOffer);

        //element 5
        mOffer = new Offer();
        mOffer.setOfferOID("X005");
        mOffer.setServiceProviderImage("kwikkar");
        mOffer.setOfferDate("02-April-2017");
        mOffer.setOfferTime("4:00 PM - 5:00 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$26");
        mOffer.setEstimatedEarningsExtra("+ tips");

        //mOfferList.add(mOffer);

        //element 6
        mOffer = new Offer();
        mOffer.setOfferOID("X006");
        mOffer.setServiceProviderImage("kwikkar");
        mOffer.setOfferDate("02-April-2017");
        mOffer.setOfferTime("5:00 PM - 6:00 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$26");
        mOffer.setEstimatedEarningsExtra("+ tips");

        //mOfferList.add(mOffer);

        //element 7
        mOffer = new Offer();
        mOffer.setOfferOID("X007");
        mOffer.setServiceProviderImage("kwikkar");
        mOffer.setOfferDate("02-April-2017");
        mOffer.setOfferTime("2:00 PM - 4:00 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$26");
        mOffer.setEstimatedEarningsExtra("+ tips");

        //mOfferList.add(mOffer);

        //element 8
        mOffer = new Offer();
        mOffer.setOfferOID("X008");
        mOffer.setServiceProviderImage("kwikkar");
        mOffer.setOfferDate("02-April-2017");
        mOffer.setOfferTime("2:00 PM - 4:00 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$26");
        mOffer.setEstimatedEarningsExtra("+ tips");

        //mOfferList.add(mOffer);

        //element 9
        mOffer = new Offer();
        mOffer.setOfferOID("X009");
        mOffer.setServiceProviderImage("kwikkar");
        mOffer.setOfferDate("02-April-2017");
        mOffer.setOfferTime("2:00 PM - 4:00 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$26");
        mOffer.setEstimatedEarningsExtra("+ tips");

        //mOfferList.add(mOffer);

        //element 10
        mOffer = new Offer();
        mOffer.setOfferOID("X010");
        mOffer.setServiceProviderImage("kwikkar");
        mOffer.setOfferDate("02-April-2017");
        mOffer.setOfferTime("2:30 PM - 3:45 PM");
        mOffer.setOfferDuration("2 hours");
        mOffer.setServiceDescription("Oil Change");
        mOffer.setEstimatedEarnings("$32");
        mOffer.setEstimatedEarningsExtra("+ tips");

        //mOfferList.add(mOffer);
    }

}

