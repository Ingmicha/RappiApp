package com.ingmicha.nextu.rappiapp.iteractor;

import com.ingmicha.nextu.rappiapp.data.api.client.MovieService;
import com.ingmicha.nextu.rappiapp.data.api.retrofit.MovieRetrofitService;
import com.ingmicha.nextu.rappiapp.data.model.Result;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/11/18.
 */

public class MovieIterator {


    private MovieService movieService;

    public MovieIterator(MovieService movieService){
        this.movieService = movieService;
    }

    public Observable<Result> getTopMovies(int page){
        return movieService.getTopMovies(page);
    }
    public Observable<Result> getPopularMovies(int page){
        return movieService.getPopularMovies(page);
    }
    public Observable<Result> getupcomingMovies(int page){
        return movieService.getUpcomingMovies(page);
    }

}
