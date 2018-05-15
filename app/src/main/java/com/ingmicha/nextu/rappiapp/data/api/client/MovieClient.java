package com.ingmicha.nextu.rappiapp.data.api.client;

import com.ingmicha.nextu.rappiapp.data.api.Constants;
import com.ingmicha.nextu.rappiapp.data.api.retrofit.MovieRetrofitClient;
import com.ingmicha.nextu.rappiapp.data.model.Result;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/11/18.
 */

public class MovieClient extends MovieRetrofitClient implements MovieService{

    @Override
    public Observable<Result> getTopMovies(int page) {
        return getMovieService().getTopMovies(page, Constants.ACCESS_TOKEN,"es")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Result> getPopularMovies(int page) {
        return getMovieService().getPopularMovies(page, Constants.ACCESS_TOKEN,"es")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Result> getUpcomingMovies(int page) {
        return getMovieService().getUpcomingMovies(page, Constants.ACCESS_TOKEN,"es")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
