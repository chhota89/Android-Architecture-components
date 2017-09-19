package com.chhota.architecturesample.dagger.component;

import com.chhota.architecturesample.UserRepository;
import com.chhota.architecturesample.dagger.module.AppModule;
import com.chhota.architecturesample.dagger.module.PreferenceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chhota89 on 19/9/17.
 */

@Singleton
@Component(modules = {AppModule.class, PreferenceModule.class})
public interface PrefrenceComponent {
    void inject(UserRepository userRepository);
}
