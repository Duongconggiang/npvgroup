package com.example.myapplication.data;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.NPVSharedPreference.LocalDataManager;
import com.example.myapplication.data.model.LoggedInUser;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    Connection conn;

    public Result<LoggedInUser> login(String username, String password) {

        try {
            ConnectDB  c = new ConnectDB();
            conn = c.CONN();

            // Check Database
            if(conn==null){
                // Fail to connect

            }else{
            // Connected --> Check user with Database

                String query = "Select * from users where UserName='"+ username +"'and Password='"+ password +"'";
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(query);
                while(rs.next()){
                    // Check succesed
                    //get ID
                    String Manv = username.substring(username.length()-4);
                    //New connect
                    query = "Select * from employees where code='" + Manv + "'";
                    stm = conn.createStatement();
                    rs = stm.executeQuery(query);

                    //get full name
                    while (rs.next()) {
                        String fullname = rs.getString(7);
                        // fix
                        //java.util.UUID.randomUUID().toString() = ID<maNV>)
                        //Set users information to local Data
                        Set<String> setUserName = new HashSet<>();
                        setUserName.add(username);
                        LocalDataManager.setUserName(setUserName);

                        LoggedInUser User =
                                new LoggedInUser(username, fullname);
                        return new Result.Success<>(User);
                    }
                }
            }conn.close();

            // TODO: handle loggedInUser authentication

            return new Result.Error(new IOException(username+" is not valid"));

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}