package com.ingmicha.nextu.rappiapp.presenter;

import android.util.Log;

import com.ingmicha.nextu.rappiapp.data.model.Movie;
import com.ingmicha.nextu.rappiapp.data.model.Result;
import com.ingmicha.nextu.rappiapp.iteractor.MovieIterator;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/11/18.
 */

public class MoviePresenter extends Presenter<MoviePresenter.View> {

    private static final String TAG = MoviePresenter.class.getName();

    private MovieIterator iterator;

    public static final String TOP = "TOP";
    public static final String POPULAR = "POPULAR";
    public static final String UPCOMING = "UPCOMING";
    public MoviePresenter(MovieIterator iterator) {
        this.iterator = iterator;
    }

    public void onGetTopMovies(int page){
        try{
            getView().showLoading(TOP);

            Disposable disposable = iterator.getTopMovies(page).subscribe(
                    new Consumer<Result>(){
                        @Override
                        public void accept(Result result) throws Exception {
                            if (result != null){
                                getView().hideLoading(TOP);
                                getView().renderMovies(result,TOP);
                            }else{
                                getView().showNoResultsFound();
                            }
                        }
                    });
            addDisposableObserver(disposable);
        }catch (Exception e){
            Log.d(TAG, "onGetTopMovies: " + e.getMessage());
        }
    }

    public void onGetPopularMovies(int page){
        try{
            getView().showLoading(POPULAR);

            Disposable disposable = iterator.getPopularMovies(page).subscribe(
                    new Consumer<Result>(){
                        @Override
                        public void accept(Result result) throws Exception {
                            if (result != null){
                                getView().hideLoading(POPULAR);
                                getView().renderMovies(result,POPULAR);
                            }else{
                                getView().showNoResultsFound();
                            }
                        }
                    });
            addDisposableObserver(disposable);
        }catch (Exception e){
            Log.d(TAG, "onGetPopularMovies: " + e.getMessage());
        }

    }

    public void onGetUpcomingMovies(int page){
        try{
            getView().showLoading(UPCOMING);

            Disposable disposable = iterator.getupcomingMovies(page).subscribe(
                    new Consumer<Result>(){
                        @Override
                        public void accept(Result result) throws Exception {
                            if (result != null){
                                getView().hideLoading(UPCOMING);
                                getView().renderMovies(result,UPCOMING);
                            }else{
                                getView().showNoResultsFound();
                            }
                        }
                    });
            addDisposableObserver(disposable);
        }catch (Exception e){
            Log.d(TAG, "onGetUpcomingMovies: Exception "+ e.getMessage());
        }

    }

    public void launchMovieDetail(Movie movie){
        getView().launchMovieDetail(movie);
    }

    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public interface View extends Presenter.View {

        void showLoading(String option);

        void hideLoading(String option);

        void showNoResultsFound();

        void showConnectionErrorMessage();

        void showServiceError();

        void renderMovies(Result result,String option);

        void launchMovieDetail(Movie movie);

    }
}
