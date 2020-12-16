package com.yulia.bonekamart;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();
    }

    public String getusename() {
        String usename = prefs.getString("usename", "");
        return usename;
    }

    public void setemail(String email) {
        prefs.edit().putString("email", email).commit();
    }

    public String getemail() {
        String usename = prefs.getString("email", "");
        return usename;
    }
}