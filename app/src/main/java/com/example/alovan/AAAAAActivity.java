package com.example.alovan;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Home.LocalService;
import com.example.myapplication.Home.myJobScheduler;
import com.example.myapplication.NPVSharedPreference.LocalDataManager;
import com.example.myapplication.R;
import com.example.myapplication.data.model.getUserInfo;
import com.example.navigation.BaseItem;
import com.example.navigation.CustomDataProvider;
import com.example.navigation.Item;
import com.example.view.LevelBeamView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Set;

import pl.openrnd.multilevellistview.ItemInfo;
import pl.openrnd.multilevellistview.MultiLevelListAdapter;
import pl.openrnd.multilevellistview.MultiLevelListView;
import pl.openrnd.multilevellistview.OnItemClickListener;

public class AAAAAActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String KEY_USERNAME = "Key_User";
    private static final int JOB_ID = 1;


    private MultiLevelListView multiLevelListView;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

        private void showItemDescription(Object object, ItemInfo itemInfo) {

            if (((BaseItem) object).getName().contains("Hồ sơ khách hàng")) {
                displaySelectedScreen("Dịch vụ khách hàng");
            }
            if (((BaseItem) object).getName().contains("Tiếp nhận TT đến")) {
                displaySelectedScreen("Dịch vụ khách hàng");
            }
            if (((BaseItem) object).getName().contains("Category-2")) { //submenu
                displaySelectedScreen("CATEGORY2");//root menu
            }
            if (((BaseItem) object).getName().contains("Category-3")) {
                displaySelectedScreen("CATEGORY3");
            }
            if (((BaseItem) object).getName().contains("Assignment-1")) {
                displaySelectedScreen("ASSIGNMENT1");
            }
            if (((BaseItem) object).getName().contains("Assignment-2")) {
                displaySelectedScreen("ASSIGNMENT2");
            }
            if (((BaseItem) object).getName().contains("Assignment-3")) {
                displaySelectedScreen("ASSIGNMENT3");
            }
            if (((BaseItem) object).getName().contains("Assignment-4")) {
                displaySelectedScreen("ASSIGNMENT4");
            }
            if (((BaseItem) object).getName().contains("Help")) {
                displaySelectedScreen("HELP");
            }
            if (((BaseItem) object).getName().contains("About Us")) {
                displaySelectedScreen("ABOUTUS");
            }



        }

        @Override
        public void onItemClicked(MultiLevelListView parent, View view, Object item, ItemInfo itemInfo) {
            showItemDescription(item, itemInfo);
        }

        @Override
        public void onGroupItemClicked(MultiLevelListView parent, View view, Object item, ItemInfo itemInfo) {
            showItemDescription(item, itemInfo);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        getUserInfo getuserInfo = new getUserInfo();
        String UserName = getuserInfo.getUserNamedata();

        StartJobScheduler();;


        confMenu();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen("HOME");
    }

    private void confMenu() {
        multiLevelListView = findViewById(R.id.multi_nav);
        // custom ListAdapter
        ListAdapter listAdapter = new ListAdapter();
        multiLevelListView.setAdapter(listAdapter);
        multiLevelListView.setOnItemClickListener(mOnItemClickListener);
        listAdapter.setDataItems(CustomDataProvider.getInitialItems());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, getString(R.string.m_web), Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(String itemName) {

        //creating fragment object
        Fragment fragment = null;
        //initializing the fragment object which is selected
        switch (itemName) {
            case "Dịch vụ khách hàng"://root menu
                fragment = new menu3_1();
                break;
            case "Tiếp nhận TT đến":
                fragment = new HomeFragment();
                break;
            case "CATEGORY2":
                fragment = new Category2Fragment();
                break;
            case "CATEGORY3":
                fragment = new Category3Fragment();
                break;
            case "ASSIGNMENT1":
                fragment = new Assignment1Fragment();
                break;
            case "ASSIGNMENT2":
                fragment = new Assignment2Fragment();
                break;
            case "ASSIGNMENT3":
                fragment = new Assignment3Fragment();
                break;
            case "ASSIGNMENT4":
                fragment = new Assignment4Fragment();
                break;
            case "HELP":
                startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                break;
            case "ABOUTUS":
                startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
                break;


        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method display selected screen and passing the id of selected menu
        displaySelectedScreen(String.valueOf(item.getItemId()));
        //make this method blank
        return true;
    }


    private class ListAdapter extends MultiLevelListAdapter {

        @Override
        public List<?> getSubObjects(Object object) {
            return CustomDataProvider.getSubItems((BaseItem) object);
        }

        @Override
        public boolean isExpandable(Object object) {
            return CustomDataProvider.isExpandable((BaseItem) object);
        }

        @SuppressLint("InflateParams")
        @Override
        public View getViewForObject(Object object, View convertView, ItemInfo itemInfo) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(AAAAAActivity.this).inflate(R.layout.data_item, null);
                viewHolder.nameView = convertView.findViewById(R.id.dataItemName);
                viewHolder.arrowView = convertView.findViewById(R.id.dataItemArrow);
                viewHolder.icon = convertView.findViewById(R.id.di_image);
                viewHolder.levelBeamView = convertView.findViewById(R.id.dataItemLevelBeam);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.nameView.setText(((BaseItem) object).getName());
            if (itemInfo.isExpandable()) {
                viewHolder.arrowView.setVisibility(View.VISIBLE);
                viewHolder.arrowView.setImageResource(itemInfo.isExpanded() ?
                        R.drawable.ic_bottom_arrow : R.drawable.ic_right_arrow);
            } else {
                viewHolder.arrowView.setVisibility(View.GONE);
            }
            viewHolder.icon.setImageResource(((BaseItem) object).getIcon());
            return convertView;
        }

        private class ViewHolder {
            TextView nameView;
            ImageView arrowView;
            ImageView icon;
            LevelBeamView levelBeamView;
        }
    }

    private void StartJobScheduler() {
        ComponentName componentName = new ComponentName(this, myJobScheduler.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID,componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                //.setPeriodic(30000)
                .build();
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }


}
