package com.chhota.architecturesample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chhota89 on 11/9/17.
 */

public interface WebInterface {

    @GET("1g1as5")
    Call<List<User>> getUser();
}
