package com.ingmicha.nextu.rappiapp.data.api.retrofit;

import com.ingmicha.nextu.rappiapp.data.api.Constants;
import com.ingmicha.nextu.rappiapp.data.model.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/11/18.
 */

public interface MovieRetrofitService {

    @GET(Constants.Endpoint.TOP_MOVIE)
    Observable<Result> getTopMovies(
            @Query("page")int page,
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET(Constants.Endpoint.POPULAR)
    Observable<Result> getPopularMovies(
            @Query("page")int page,
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET(Constants.Endpoint.UPCOMING)
    Observable<Result> getUpcomingMovies(
            @Query("page")int page,
            @Query("api_key") String api_key,
            @Query("language") String language
    );

}
