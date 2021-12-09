package com.example.alovan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Notification.Main_Notification;
import com.example.myapplication.R;
import com.example.myapplication.data.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment {

    private String Menu;
    private String ID;
    private ArrayList<String> arrayMenuList=null;

    public HomeFragment(String Menu, String catID) {
        // Required empty public constructor
        this.Menu = Menu;
        this.ID = catID;

    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ListView listView = view.findViewById(R.id.listviewhome);
        ConnectDB connectDB = new ConnectDB();
        Connection conn = connectDB.CONN();

        String query = "Select * from Category where CatParent='"+ID+"' and CatShow = 1";
        Statement statement = null;
        try {
            arrayMenuList = new ArrayList<>();
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()==false){arrayMenuList.add("Home - Page");}

            while (rs.next()) {
                arrayMenuList.add(rs.getString("CatName"));
                Log.e("Sub",rs.getString("CatName"));
            }
            ArrayAdapter array = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_activated_1,arrayMenuList);
            listView.setAdapter(array);
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();}

        //set onlick item sub menu 3
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position = arrayMenuList.size();
                for(int j=0;j<position;j++){
                    if(i==j){
                        callMenulayout(i,arrayMenuList.get(i));
                    }
                }
            }
        });

            return view;
        }

    private void callMenulayout(int i, String callMenu) {
        Intent intent;
            switch (callMenu){
                case "Lệnh điều động":
                    intent = new Intent(getActivity(),taodonhang.class);
                    startActivity(intent);
                    break;
            }
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            //you can set the title for your toolbar here for different fragments different titles
            String title = "Home";
            requireActivity().setTitle(title);
        }



}
