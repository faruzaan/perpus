package com.example.perpus;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name   = "crudpref";
    private static final String is_login    = "islogin";
    public static final String key_noid     = "key_noid";
    public static final String key_nama     = "key_nama";
    public static final String key_kelas     = "key_kelas";
    public static final String key_foto     = "key_foto";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    public void createSession(String id, String nama, String kelas, String foto){
        editor.putBoolean(is_login, true);
        editor.putString(key_noid, id);
        editor.putString(key_nama, nama);
        editor.putString(key_kelas, kelas);
        editor.putString(key_foto, foto);
        editor.commit();
    }

    public boolean is_login() {
        return pref.getBoolean(is_login, false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(pref_name, pref.getString(pref_name, null));
        user.put(key_noid, pref.getString(key_noid, null));
        user.put(key_nama, pref.getString(key_nama, null));
        user.put(key_kelas, pref.getString(key_kelas, null));
        user.put(key_foto, pref.getString(key_foto, null));
        return user;
    }
}

