package com.example.alovan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.NPVSharedPreference.LocalDataManager;
import com.example.myapplication.R;
import com.example.myapplication.data.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class notifiCationApp extends Fragment {


    Connection conn;
    String Manv;
    ArrayList<String> arrayList_title, arrayList_content;
    // private static final String CHANNEL_ID ="PRIORITY_DEFAULT";


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public notifiCationApp() {


    }



    // TODO: Customize parameter initialization


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nontificationlistview, container, false);

        TextView tvUer = (TextView) view.findViewById(R.id.textViewUser);
        TextView tvManv = (TextView) view.findViewById(R.id.textViewManv);
        ListView LvNotification = (ListView) view.findViewById(R.id.listView);



        Set<String> getUserName = LocalDataManager.getUserName();
        String[] User_Name = getUserName.toArray(new String[1]);
        String UserName = String.valueOf(User_Name[1]);
        tvUer.setText("Username: " + UserName.toUpperCase());
        Manv = UserName.substring(UserName.length() - 4);
        //tvManv.setText(Manv);

        try {
            ConnectDB c = new ConnectDB();
            conn = c.CONN();
            String query = "Select * from Notifications where NguoiNhan='" + Manv + "'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String title, content;
            arrayList_title = new ArrayList<>();
            arrayList_content = new ArrayList<>();

            while (rs.next()!=false) {
                title = rs.getString(2);
                title = reMovechar(title);
                content = rs.getString(3);
                content = reMovechar(content);
                //time = rs.getString(9);
                arrayList_title.add(new String(title));
                arrayList_content.add(new String(content));
            }conn.close();
            // Create adapter
            ArrayAdapter array = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1,arrayList_title);

            LvNotification.setAdapter(array);

            //Check Notification not to read
//            String Manv = null;
//            ConnectDB c_noti = new ConnectDB();
//            Connection conn = c_noti.CONN();
//            // Not Read =0
//
//            try {
//                String query_noti = "Select * from Notifications where NguoiNhan='" + Manv + "'and DaDoc=0";
//                Statement stm_noti = null;
//                stm_noti = conn.createStatement();
//                ResultSet rs_noti = stm_noti.executeQuery(query_noti);
//                String title_noti, content_noti;
//                while (rs_noti.next()!=false) {
//                    title_noti = rs_noti.getString(2);
//                    content_noti = rs_noti.getString(3);
//                    //send notification
//                    CustomSendNotification(title_noti,content_noti);
//                }conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return view;

    }


    // Send notification fun
    /*private void SendNotification(String title_send, String content_send) {
        //ring tone
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.notifications_npv);
        Notification notification = new NotificationCompat.Builder(this, myApplication.CHANNEL_ID_1 )
                .setContentTitle(title_send)
                .setContentText(content_send)
                .setSound(uri)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.notifications_npv)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content_send))
                .setLargeIcon(bitmap)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(),notification);

    }*/

    // count notification id
    private int getNotificationId(){
        return (int) new Date().getTime();
    }

    // custom send notification
//    private void CustomSendNotification(String title_send_2, String content_send_2){
//        // Defaul the sound
//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        // Click notification on Notifacation
//        // Create an Intent for the activity you want to start
//        Intent resultIntent = new Intent(this, Detail_Notification.class);
//        // Create the TaskStackBuilder and add the intent, which inflates the back stack
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addNextIntentWithParentStack(resultIntent);
//        // Get the PendingIntent containing the entire back stack
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(getNotificationId(), PendingIntent.FLAG_UPDATE_CURRENT);
//
//        // Call layout activity_custom_notification
//        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.activity_custom_notification);
//        notificationLayout.setTextViewText(R.id.tv_Title_Notification_custom,title_send_2);
//        notificationLayout.setTextViewText(R.id.tv_content_Notification_custom,content_send_2);
//        //get curren time
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
//        String StrDate = simpleDateFormat.format(new Date());
//        notificationLayout.setTextViewText(R.id.tv_time_Notification_custom,StrDate);
//
//
//        Notification notification = new NotificationCompat.Builder(this,Channel.CHANNEL_ID_2)
//                .setSound(uri)
//                .setContentIntent(resultPendingIntent)
//                .setCustomContentView(notificationLayout)
//                .setAutoCancel(true)
//                .setSmallIcon(R.drawable.notifications_npv)
//                .build();
//
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//        notificationManagerCompat.notify(getNotificationId(),notification);
//    }

    public static String reMovechar(String a){
        a = a.replace("<b>","");
        a = a.replace("</b>","");
        a = a.replace("<br>"," ");
        return a;

    }


}