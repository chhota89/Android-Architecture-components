/*
 *
 * Knackter Softwares LLP
 *
 * [2016] - Till Date Knackter Softwares LLP
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of Knackter Softwares LLP.
 * The intellectual and technical concepts contained herein are proprietary to Knackter Softwares LLP are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained
 * from Knackter Softwares LLP.
 */

package com.chhota.architecturesample;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static final String TAG = ApiClient.class.getSimpleName();
    private static String DEVO = "https://api.myjson.com/bins/";


    public static String URL = DEVO;

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            buildRetrofit();
        }
        return retrofit;
    }

    private static void buildRetrofit(){
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build();
    }
}
