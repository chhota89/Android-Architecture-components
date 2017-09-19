package com.chhota.architecturesample.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chhota89 on 19/9/17.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication){
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return mApplication;
    }
}