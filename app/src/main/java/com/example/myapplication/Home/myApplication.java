package com.example.myapplication.Home;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.myapplication.NPVSharedPreference.LocalDataManager;
import com.example.myapplication.R;

public class myApplication extends Application {
    public static final String CHANNEL_ID_1 = "CHANNEL_1";
    public static final String CHANNEL_ID_2 = "CHANNEL_2";

    @Override
    public void onCreate() {
        super.onCreate();
        //call channel
        createNotificationChannel();
        //call localdatamanager
        LocalDataManager.init(getApplicationContext());
    }

    //channel
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Channel 1
            CharSequence name = getString(R.string.channel_name_1);
            String description = getString(R.string.channel_description_1);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_1, name, importance);
            channel.setDescription(description);

            //channel 2
            CharSequence name_2 = getString(R.string.channel_name_2 );
            String description_2 = getString(R.string.channel_description_2);
            int importance_2 = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel_2 = new NotificationChannel(CHANNEL_ID_2, name_2, importance_2);
            channel_2.setDescription(description_2);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager!=null){
                notificationManager.createNotificationChannel(channel);
                notificationManager.createNotificationChannel(channel_2);}
        }
    }


}
