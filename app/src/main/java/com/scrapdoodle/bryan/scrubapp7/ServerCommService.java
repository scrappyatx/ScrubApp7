package com.scrapdoodle.bryan.scrubapp7;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import com.rollbar.android.Rollbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.CompletionListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ErrorInfo;
import io.ably.lib.types.Message;


/**
 * Created by Bryan on 4/2/2017.
 */

public class ServerCommService extends IntentService {
    private static final String TAG = "ServerCommService";
    private AblyRealtime mAblyRealTime;
    private Channel mAblyOnDutyChannel;
    private final static String ABLY_API_KEY = "lekciw.COtO7w:MtlsHxVAa7MVrgx6";
    private static final String ABLY_ONDUTY_CHANNEL = "onduty";


    private static final long UPDATE_INTERVAL_MS = TimeUnit.SECONDS.toMillis(5);




    @Override
    public void onCreate() {
        super.onCreate();

        // this gets called properly
        Log.d(TAG, "Service onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // this gets called properly
        Log.d(TAG, "Service onDestroy()");
    }

    //@Override
    //public int onStartCommand(Intent intent, int flags, int startId) {
    //    super.onStartCommand(intent, flags, startId);
    //    Log.d(TAG, "Service onStartCommand()");
    //    return START_STICKY;
    //}

    public static Intent newIntent(Context context) {
        return new Intent(context, ServerCommService.class);
    }

    public ServerCommService() {
        super(TAG);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Log.d(TAG,"Received an intent with no extras! " + intent);
        } else {
            String action = extras.getString("action");
            Log.d(TAG,"Received an intent with an action: " + action);
            processAction(action);
        }
        //Rollbar.reportMessage("Received an intent: " + intent, "info"); // default level is "info"
    }

    private void processAction(String action) {
        switch (action.toLowerCase()) {
            case "location":
                //do some location shit
                publishCurrentLocation();


                break;
        }
    }

    private void publishCurrentLocation() {
        JSONObject mJSON = new JSONObject();

        try {
            mJSON.put("driver", "Fizzle Sparkcrank");
            mJSON.put("zone", "Austin");
            mJSON.put("latitude", "XXX.XX");
            mJSON.put("longitude", "XXX.XX");

        } catch (JSONException e) {
            Log.d(TAG,"JSON exception while publishing message: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            initAbly();
            Log.d(TAG,"initAbly complete");
        } catch (AblyException e) {
            Log.d(TAG,"initAbly error");
            e.printStackTrace();
        }

        try {
            mAblyOnDutyChannel.publish("location", mJSON, new CompletionListener() {
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


    public static void setOnlineUpdates(Context context, boolean isOn) {
        Intent i = ServerCommService.newIntent(context);
        i.putExtra("action","location");
        PendingIntent pi = PendingIntent.getService(context, 0, i, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (isOn) {
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),UPDATE_INTERVAL_MS, pi);
            Log.d(TAG,"starting scheduled updates every " + Long.toString(UPDATE_INTERVAL_MS) + " ms");
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
            Log.d(TAG,"stopping scheduled updates" );
        }
    }

}
