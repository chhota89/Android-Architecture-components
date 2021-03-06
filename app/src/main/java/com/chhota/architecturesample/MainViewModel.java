package com.chhota.architecturesample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

/**
 * Created by chhota89 on 11/9/17.
 */

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<User>> userList;
    private Context context;

    //TODO inject this using dagger
    private UserRepository userRepository;

    public MainViewModel(Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<User>> getUser(boolean forceRefresh){
        if(userList == null){
            loadUser();
        }
        return userList;
    }

    private void loadUser() {
        if(userRepository == null)
            userRepository = new UserRepository();

        userList = userRepository.getUserList(context);
    }

    public void forceRefreshData(){
        //TODO if this is injected no need to worry about this null check
        if(userRepository == null)
            userRepository = new UserRepository();

        userRepository.refreshData(context);
    }


}
