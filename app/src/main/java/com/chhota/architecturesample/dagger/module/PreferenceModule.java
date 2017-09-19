package com.chhota.architecturesample.dagger.module;

import android.app.Application;

import com.chhota.architecturesample.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chhota89 on 19/9/17.
 */

@Module
public class PreferenceModule {

    @Provides
    @Singleton
    PreferenceManager providePreferenceManager(Application application){
        return new PreferenceManager(application);
    }
}
