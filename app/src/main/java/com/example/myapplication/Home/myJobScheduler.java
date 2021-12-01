package com.example.myapplication.Home;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.NPVSharedPreference.LocalDataManager;
import com.example.myapplication.Notification.Detail_Notification;
import com.example.myapplication.R;
import com.example.myapplication.data.ConnectDB;
import com.example.myapplication.data.model.getUserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class myJobScheduler extends JobService {
    private boolean jobCancel;

    @Override
    public boolean onStartJob(JobParameters params) {

        // Start doInBackgroud
        doInBackgroudNotification(params);
        return true;
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        jobCancel = true;
        return true;
    }

    //To do some thing
    private void doInBackgroudNotification(final JobParameters params) {
        new Thread(new Runnable() {



            @Override
            public void run() {

                //run in background check on DataBase
                getUserInfo getuserInfo = new getUserInfo();
                String UserName = getuserInfo.getUserNamedata();
                String Manv = UserName.substring(UserName.length()-4);
                ConnectDB c_noti = new ConnectDB();
                Connection conn = c_noti.CONN();
                // Not Read =0

                try {
                    String query_noti = "Select * from Notifications where NguoiNhan='" + Manv + "'and DaDoc=0";
                    Statement stm_noti = null;
                    stm_noti = conn.createStatement();
                    ResultSet rs_noti = stm_noti.executeQuery(query_noti);
                    String title_noti, content_noti;

                        while (rs_noti.next() != false) {

                            title_noti = rs_noti.getString(2);
                            title_noti = reMovechar(title_noti);
                            content_noti = rs_noti.getString(3);
                            content_noti = reMovechar(content_noti);
                            int id = rs_noti.getInt(1);
                            //send notification
                            CustomSendNotification(title_noti, content_noti);
                            // put to server set received
                            Connection conn_put = ConnectDB.CONN();
                            String query_put = "Update Notifications set DaDoc=1 where ID=" + id;
                            PreparedStatement preparedStatement = conn_put.prepareStatement(query_put);
                            preparedStatement.executeUpdate();
                            Log.e(myJobScheduler.class.getName(), String.valueOf(id));
                        }
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                //Scheduler on Notification

            // Set true for daily check Notification on Database with 30s
            jobFinished(params,true);
            }
        }).start();
    }

    private void CustomSendNotification(String title_noti, String content_noti) {
        // Defaul the sound
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        // Click notification on Notifacation
        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(this, Detail_Notification.class);
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        // Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(getNotificationId(), PendingIntent.FLAG_UPDATE_CURRENT);

        // Call layout activity_custom_notification
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.activity_custom_notification);
        notificationLayout.setTextViewText(R.id.tv_Title_Notification_custom,title_noti);
        notificationLayout.setTextViewText(R.id.tv_content_Notification_custom,content_noti);
        //get curren time
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
        String StrDate = simpleDateFormat.format(new Date());
        notificationLayout.setTextViewText(R.id.tv_time_Notification_custom,StrDate);


        Notification notification = new NotificationCompat.Builder(this, myApplication.CHANNEL_ID_2)
                .setSound(uri)
                .setContentIntent(resultPendingIntent)
                .setCustomContentView(notificationLayout)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.notifications_npv)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(),notification);
    }
    // count notification id
    private int getNotificationId(){
        return (int) new Date().getTime();
    }

    public static String reMovechar(String a){
        a = a.replace("<b>","");
        a = a.replace("</b>","");
        a = a.replace("<br>"," ");
        return a;
    }
}
