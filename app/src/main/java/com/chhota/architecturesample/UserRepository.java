package com.chhota.architecturesample;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chhota89 on 11/9/17.
 */

public class UserRepository {

    private static final String TAG = "UserRepository";

    private static final long cacheExpirationTime = 60 * 1000 * 1;
    //TODO inject this using dagger
    private WebInterface apiService = ApiClient.getClient().create(WebInterface.class);
    private AppDatabase appDatabase;


    public LiveData<List<User>> getUserList(Context context) {
        appDatabase = AppDatabase.getAppDatabase(context);

        if (isCacheExpire(context)) {
            Log.d(TAG, "getUserList: cache is expire");
            refreshData(context);
        } else {
            Log.d(TAG, "getUserList: cache is NOT expire");
        }

        //TODO don't query this on main thread
        // return a LiveData directly from the database.
        return appDatabase.userDao().getAllSync();
    }

    private boolean isCacheExpire(Context context) {
        PreferenceManager preferenceManager = new PreferenceManager(context);
        Long lastSaveTime = preferenceManager.getUserSaveTime();
        return System.currentTimeMillis() - lastSaveTime > cacheExpirationTime;
    }

    public void refreshData(final Context context) {

        //TODO remove this and directly use dagger for this
        if(appDatabase == null)
            appDatabase = AppDatabase.getAppDatabase(context);


        Call<List<User>> call = apiService.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                appDatabase.userDao().insertAll(userList.toArray(new User[userList.size()]));

                //this preference manager change from here when used dagger
                PreferenceManager preferenceManager = new PreferenceManager(context);
                preferenceManager.saveUserListTime(System.currentTimeMillis());

                Log.d(TAG, "onResponse: New data recived");

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                //TODO what to do in case of failure
            }
        });


    }

}
