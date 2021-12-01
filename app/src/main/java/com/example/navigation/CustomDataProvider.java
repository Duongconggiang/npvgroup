package com.example.navigation;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.data.ConnectDB;
import com.example.myapplication.data.model.getUserInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomDataProvider {


    private static final int MAX_LEVELS = 3;

    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;
    private static List<BaseItem> mMenu = new ArrayList<>();
    Context context;



    public static List<BaseItem> getInitialItems() {
        //return getSubItems(new GroupItem("root"));

        ConnectDB connectDB = new ConnectDB();
        Connection conn  = connectDB.CONN();

        //Get user name
        getUserInfo getuserInfo = new getUserInfo();
        String UserName = getuserInfo.getUserNamedata();

        List<BaseItem> rootMenu = new ArrayList<>();

        try {
            //Get menu
            String query = "Select * from Category where CatParent= 0 and CatShow = 1";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()){
                String Menu = rs.getString("CatName");
                String catID = rs.getString("CatID");

                // conect menu 1
                ConnectDB connectDB1 = new ConnectDB();
                Connection conn1  = connectDB1.CONN();

                String query1 = "Select * from Category where CatParent='"+ catID +"' and CatShow = 1";
                Statement stm1 = conn1.createStatement();
                ResultSet rs1 = stm1.executeQuery(query1);
                if(rs1.next()==true){
                    rootMenu.add(new GroupItem(rs.getString("CatName")));
                }else{
                    rootMenu.add(new Item(rs.getString("CatName")));}
                //Disconnect conn1
                conn1.close();
            }
            //Disconnect conn
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


//        rootMenu.add(new Item("Home", R.drawable.ic_home_black_24dp));
//        rootMenu.add(new GroupItem("Category", R.drawable.ic_widgets_black_24dp));
//        rootMenu.add(new GroupItem("Assignments", R.drawable.ic_assignment_black_24dp));
//        rootMenu.add(new Item("Help", R.drawable.ic_help_black_24dp));
//        rootMenu.add(new Item("About Us", R.drawable.ic_error_black_24dp));
        return rootMenu;
    }


    @Nullable
    public static List<BaseItem> getSubItems(BaseItem baseItem) {


        List<BaseItem> result = new ArrayList<>();
        int level = ((GroupItem) baseItem).getLevel() + 1;
        String menuItem = baseItem.getName();

        GroupItem groupItem = (GroupItem) baseItem;
        if (groupItem.getLevel() >= MAX_LEVELS) {
            return null;
        }

        if (level == LEVEL_1) {

            ConnectDB connectDB = new ConnectDB();
            Connection conn  = connectDB.CONN();
            try {
                String query = "Select * from Category where CatParent= 0 and CatShow = 1";
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()){
                    String Menu = rs.getString("CatName");
                    int catID = Integer.parseInt(rs.getString("catID"));
                    if(menuItem.equals(Menu)){
                    result = getListSubMenu(catID);
                    }
                } conn.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
//            switch (menuItem) {
//                case "Dịch vụ khách hàng":
//                    result = getListSubMenu(1);
//                    break;
//            }
        }
        return result;
    }

    @NonNull
    private static List<BaseItem> getListSubMenu(int id) {
        List<BaseItem> list = new ArrayList<>();

        ConnectDB connectDB = new ConnectDB();
        Connection conn  = connectDB.CONN();

        try {
            String query = "Select * from Category where CatParent='"+id+"' and CatShow = 1";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                list.add(new Item(rs.getString("CatName")));
            } conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public static boolean isExpandable(BaseItem baseItem) {
        return baseItem instanceof GroupItem;
    }
}
