package com.chhota.architecturesample;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chhota89 on 18/9/17.
 */

public class PreferenceManager {

    private SharedPreferences pref;
    private static final String SHARED_PREFRENCE_NAME="CACHE_EXPIRATION";
    private static final String USER_CHACHE="user_cache";

    public PreferenceManager(Context context){
        pref = context.getSharedPreferences(SHARED_PREFRENCE_NAME, context.MODE_PRIVATE);
    }


    public long getUserSaveTime(){
        return pref.getLong(USER_CHACHE,0);
    }

    public void saveUserListTime(Long userListTime){
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(USER_CHACHE,userListTime);
        editor.commit();
    }
}
