package com.ingmicha.nextu.rappiapp.data.api.client;

import com.ingmicha.nextu.rappiapp.data.model.Result;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/11/18.
 */

public interface MovieService {

    Observable<Result> getTopMovies(int page);
    Observable<Result> getPopularMovies(int page);
    Observable<Result> getUpcomingMovies(int page);
}
