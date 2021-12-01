package com.example.myapplication.data.model;

import com.example.myapplication.NPVSharedPreference.LocalDataManager;
import com.example.myapplication.data.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class getUserInfo {

    private String fullnamedata;
    private String usernamedata;
    private String iddata;
    Connection conn;

    public getUserInfo() {
        this.fullnamedata=fullnamedata;
        this.usernamedata = usernamedata;
        this.iddata = iddata;
    }

    public String getUserNamedata() {
        Set<String> getUserName = LocalDataManager.getUserName();
        String[] User_Name = getUserName.toArray(new String[0]);
        String UserName = String.valueOf(User_Name[0]);
        return UserName;
    }
    public String getFullNamedata() throws SQLException {
        Set<String> getUserName = LocalDataManager.getUserName();
        String[] User_Name = getUserName.toArray(new String[0]);
        String UserName = String.valueOf(User_Name[0]);
        String ID =UserName.substring(UserName.length()-4);
        String Fullname = null;
        ConnectDB c = new ConnectDB();
        conn = c.CONN();
        String query = "Select * from employees where code='" + ID + "'";
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            Fullname = rs.getString(7);}conn.close();
        return Fullname;
    }
    public String getIDdata(){
        Set<String> getUserName = LocalDataManager.getUserName();
        String[] User_Name = getUserName.toArray(new String[0]);
        String UserName = String.valueOf(User_Name[0]);
        String ID =UserName.substring(UserName.length()-4);
        return ID;
    }


}
