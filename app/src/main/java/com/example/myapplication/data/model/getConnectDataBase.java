package com.example.myapplication.data.model;

import com.example.myapplication.data.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getConnectDataBase {

    public String getConnectData(String query, String table) {

        String result = null;
        ConnectDB connectDB = new ConnectDB();
        Connection conn  = connectDB.CONN();
        try {
            String qr = "'" + query + "'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(qr);
            while (rs.next()) {
                result = rs.getString(table);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
