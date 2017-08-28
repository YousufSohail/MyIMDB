package com.yousufsohail.myimdb.service;

import com.yousufsohail.myimdb.service.response.ResponseMoviesTopRated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yousuf on 28-Aug-17.
 */

public interface ImdbService {

    @GET("movie/top_rated")
    Call<ResponseMoviesTopRated> getMoviesTop(@Query("api_key") String apiKey);

}