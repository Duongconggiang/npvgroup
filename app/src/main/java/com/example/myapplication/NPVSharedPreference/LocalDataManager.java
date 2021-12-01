package com.example.myapplication.NPVSharedPreference;

import android.content.Context;

import java.util.Set;

public class LocalDataManager {
    private static final String PREFER_FIRST_INSTALL = "PREFER_FIRST_INSTALL";
    private static final String PREFER_USER_NAME = "PREFER_USER_NAME";
    private static LocalDataManager instance;
    private NPVSharedPreference npvSharedPreference;

    public static void init(Context context){
        instance = new LocalDataManager();
        instance.npvSharedPreference = new NPVSharedPreference(context);
    }

    public static LocalDataManager getInstance() {
        if(instance==null){
            instance = new LocalDataManager();
        }
        return instance;
    }

    //Check the first time install
    public static void setFirstinstall(boolean isFirst){
        LocalDataManager.getInstance().npvSharedPreference.putBooleanValue(PREFER_FIRST_INSTALL,isFirst);
    }
    public static boolean getFirstinstall(){
        return LocalDataManager.getInstance().npvSharedPreference.getBooleanValue(PREFER_FIRST_INSTALL);
    }
    //Set Users
    public static void setUserName(Set<String> userName){
        LocalDataManager.getInstance().npvSharedPreference.putStringSetValue(PREFER_USER_NAME,userName);
    }
    //get Users
    public static Set<String> getUserName(){
        return LocalDataManager.getInstance().npvSharedPreference.getSringSetValue(PREFER_USER_NAME);
    }
}
