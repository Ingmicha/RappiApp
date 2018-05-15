package com.ingmicha.nextu.rappiapp.data.api.retrofit;

import com.ingmicha.nextu.rappiapp.data.api.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/11/18.
 */

public class MovieRetrofitClient {

    private MovieRetrofitService movieRetrofitService;

    public MovieRetrofitClient() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = retrofitBuilder();
        movieRetrofitService = retrofit.create(getMovieServiceClass());
    }

    private Retrofit retrofitBuilder() {
        return new Retrofit.Builder().baseUrl(Constants.MOVIE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOKHttpCliente())
                .build();
    }

    private OkHttpClient getOKHttpCliente() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        ApiInterceptor apiInterceptor = new ApiInterceptor();
        client.addInterceptor(apiInterceptor);
        return client.build();
    }

    private Class<MovieRetrofitService> getMovieServiceClass(){
        return MovieRetrofitService.class;
    }

    protected MovieRetrofitService getMovieService(){
        return movieRetrofitService;
    }
}
