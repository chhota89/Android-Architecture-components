package com.chhota.architecturesample;

import android.app.Application;

import com.chhota.architecturesample.dagger.component.DaggerPrefrenceComponent;
import com.chhota.architecturesample.dagger.component.PrefrenceComponent;
import com.chhota.architecturesample.dagger.module.AppModule;
import com.chhota.architecturesample.dagger.module.PreferenceModule;

import dagger.internal.DaggerCollections;

/**
 * Created by chhota89 on 19/9/17.
 */

public class AppController extends Application {

    private static PrefrenceComponent prefrenceComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        prefrenceComponent = DaggerPrefrenceComponent.builder()
                .appModule(new AppModule(this))
                .preferenceModule(new PreferenceModule())
                .build();
    }

    public static PrefrenceComponent getPrefrenceComponent(){
        return prefrenceComponent;
    }
}
