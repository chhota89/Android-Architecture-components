package com.chhota.architecturesample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chhota89 on 11/9/17.
 */

public class UserRepository {

    //TODO inject this using dagger
    private WebInterface apiService = ApiClient.getClient().create(WebInterface.class);
    private AppDatabase appDatabase;


    public LiveData<List<User>> getUserList(Context context){
        appDatabase = AppDatabase.getAppDatabase(context);
        refreshData();

        // return a LiveData directly from the database.
        return appDatabase.userDao().getAllSync();
    }

    private void refreshData() {

        //Check if the data is empty or not
        if(appDatabase.userDao().countOfUser() == 0){
            //There is no data in data base fetch data from network

            //TODO Expire the chache after some time
            Call<List<User>> call = apiService.getUser();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    List<User> userList = response.body();
                    appDatabase.userDao().insertAll(userList.toArray(new User[userList.size()]));
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    //TODO what to do in case of failure
                }
            });

        }
    }

}
