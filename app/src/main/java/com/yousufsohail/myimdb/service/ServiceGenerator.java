package com.yousufsohail.myimdb.service;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.yousufsohail.myimdb.constant.ApiConstants.API_BASE_URL;

/**
 * Created by yousuf on 28-Aug-17.
 */

public class ServiceGenerator {


    private static ServiceGenerator instance;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    public static ServiceGenerator getInstance() {
        if (instance == null) instance = new ServiceGenerator();
        return instance;
    }

    public <S> S createService(Class<S> serviceClass) {
        // introduce any interceptor here if needed
        return retrofit.create(serviceClass);
    }


}
