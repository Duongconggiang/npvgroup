package com.example.myapplication.NPVSharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;


public class NPVSharedPreference {
    private static final String NPV_SHARED_PREFERENCE = "NPV_SHARED_PREFERENCE";
    private Context mContext;

    public NPVSharedPreference(Context mContext) {
        this.mContext = mContext;
    }
    // Put shared BooleanValue
    public void putBooleanValue(String key,boolean value){
        SharedPreferences sharedPreference = mContext.getSharedPreferences(NPV_SHARED_PREFERENCE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    //get
    public boolean getBooleanValue(String key){
        SharedPreferences sharedPreference = mContext.getSharedPreferences(NPV_SHARED_PREFERENCE,Context.MODE_PRIVATE);
        return sharedPreference.getBoolean(key,false);
    }
    //Put String set value
    public void putStringSetValue(String key, Set<String> values){
        SharedPreferences sharedPreference = mContext.getSharedPreferences(NPV_SHARED_PREFERENCE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putStringSet(key, values);
        editor.apply();
    }
    //get
    public Set<String> getSringSetValue(String key){
        SharedPreferences sharedPreference = mContext.getSharedPreferences(NPV_SHARED_PREFERENCE,Context.MODE_PRIVATE);
        Set<String> valuesDefault = new HashSet<>();
        return sharedPreference.getStringSet(key,valuesDefault);
    }
}
