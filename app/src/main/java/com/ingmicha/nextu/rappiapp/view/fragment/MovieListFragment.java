package com.ingmicha.nextu.rappiapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ingmicha.nextu.rappiapp.R;
import com.ingmicha.nextu.rappiapp.data.api.client.MovieClient;
import com.ingmicha.nextu.rappiapp.data.model.Movie;
import com.ingmicha.nextu.rappiapp.data.model.Result;
import com.ingmicha.nextu.rappiapp.iteractor.MovieIterator;
import com.ingmicha.nextu.rappiapp.presenter.MoviePresenter;
import com.ingmicha.nextu.rappiapp.view.MovieDialog;
import com.ingmicha.nextu.rappiapp.view.adapter.MoviesAdapter;
import com.ingmicha.nextu.rappiapp.view.adapter.OnLoadMoreListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListFragment extends Fragment implements MoviePresenter.View{

    private static final String TAG = MovieListFragment.class.getName();

    @BindView(R.id.recycler_view_top_movie)
    RecyclerView mRecyclerViewTopMovies;
    @BindView(R.id.progress_bar_top_movies)
    ProgressBar mProgressBarTopMovies;

    @BindView(R.id.recycler_view_popular_movie)
    RecyclerView mRecyclerViewPopularMovies;
    @BindView(R.id.progress_bar_popular_movies)
    ProgressBar mProgressBarPopularMovies;

    @BindView(R.id.recycler_view_upcoming_movie)
    RecyclerView mRecyclerViewUpcomingMovies;
    @BindView(R.id.progress_bar_upcoming_movies)
    ProgressBar mProgressBarUpcomingMovies;

    private MoviePresenter moviePresenter;
    private int defaultPage,lastPage;
    private int defaultPagePopular,lastPagePopular;
    private int defaultPageUpcoming,lastPageUpcoming;
    private MoviesAdapter moviesAdapter,moviesAdapterPopular,moviesAdapterUpcoming;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviePresenter = new MoviePresenter(new MovieIterator(new MovieClient()));
        moviePresenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        setupRecyclerView();
        defaultPage = 1;
        defaultPagePopular = 1;
        defaultPageUpcoming = 1;
        moviePresenter.onGetTopMovies(defaultPage);
        moviePresenter.onGetPopularMovies(defaultPagePopular);
        moviePresenter.onGetUpcomingMovies(defaultPageUpcoming);
    }

    private void setupRecyclerView() {

        mRecyclerViewTopMovies.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerViewTopMovies.setHasFixedSize(true);

        mRecyclerViewPopularMovies.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerViewPopularMovies.setHasFixedSize(true);

        mRecyclerViewUpcomingMovies.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerViewUpcomingMovies.setHasFixedSize(true);

        moviesAdapter = new MoviesAdapter(mRecyclerViewTopMovies);
        moviesAdapter.setItemClickListener(new MoviesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                moviePresenter.launchMovieDetail(movie);
            }
        });


        moviesAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                lastPage = defaultPage+1;
                moviePresenter.onGetTopMovies(lastPage);
            }
        });
        mRecyclerViewTopMovies.setAdapter(moviesAdapter);

        moviesAdapterPopular = new MoviesAdapter(mRecyclerViewPopularMovies);
        moviesAdapterPopular.setItemClickListener(new MoviesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                moviePresenter.launchMovieDetail(movie);
            }
        });

        moviesAdapterPopular.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                lastPagePopular = defaultPagePopular+1;
                moviePresenter.onGetPopularMovies(lastPagePopular);
            }
        });
        mRecyclerViewPopularMovies.setAdapter(moviesAdapterPopular);

        moviesAdapterUpcoming = new MoviesAdapter(mRecyclerViewUpcomingMovies);
        moviesAdapterUpcoming.setItemClickListener(new MoviesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                moviePresenter.launchMovieDetail(movie);
            }
        });


        moviesAdapterUpcoming.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                lastPageUpcoming = defaultPageUpcoming+1;
                moviePresenter.onGetUpcomingMovies(lastPageUpcoming);
            }
        });
        mRecyclerViewUpcomingMovies.setAdapter(moviesAdapterUpcoming);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public Context getContext() {
        return getActivity();
    }


    @Override
    public Context context() {
        return null;
    }

    @Override
    public void showLoading(String option) {
        switch (option){
            case MoviePresenter.TOP:
                mProgressBarTopMovies.setVisibility(View.VISIBLE);
                break;
            case MoviePresenter.POPULAR:
                mProgressBarPopularMovies.setVisibility(View.VISIBLE);
                break;
            case MoviePresenter.UPCOMING:
                mProgressBarUpcomingMovies.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void hideLoading(String option) {
        switch (option){
            case MoviePresenter.TOP:
                mProgressBarTopMovies.setVisibility(View.GONE);
                break;
            case MoviePresenter.POPULAR:
                mProgressBarPopularMovies.setVisibility(View.GONE);
                break;
            case MoviePresenter.UPCOMING:
                mProgressBarUpcomingMovies.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void showNoResultsFound() {
        Toast.makeText(getActivity(),"No se encontraron mas resultados",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showConnectionErrorMessage() {
        Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showServiceError() {
        Toast.makeText(getActivity(),"Error al optener la informacion.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void renderMovies(Result result,String option) {
        switch (option){
            case MoviePresenter.TOP:
                moviesAdapter = (MoviesAdapter) mRecyclerViewTopMovies.getAdapter();
                moviesAdapter.setMovies(result.getResults());
                moviesAdapter.notifyDataSetChanged();
                mRecyclerViewTopMovies.setVisibility(View.VISIBLE);
                break;
            case MoviePresenter.POPULAR:
                moviesAdapterPopular = (MoviesAdapter) mRecyclerViewPopularMovies.getAdapter();
                moviesAdapterPopular.setMovies(result.getResults());
                moviesAdapterPopular.notifyDataSetChanged();
                mRecyclerViewPopularMovies.setVisibility(View.VISIBLE);
                break;
            case MoviePresenter.UPCOMING:
                moviesAdapterUpcoming = (MoviesAdapter) mRecyclerViewUpcomingMovies.getAdapter();
                moviesAdapterUpcoming.setMovies(result.getResults());
                moviesAdapterUpcoming.notifyDataSetChanged();
                mRecyclerViewUpcomingMovies.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void launchMovieDetail(Movie movie) {
        Log.d(TAG, "launchMovieDetail: " + movie.getTitle());

        MovieDialog movieDialog = MovieDialog.newInstance(movie);
        movieDialog.show(getActivity().getSupportFragmentManager(),"parking_dialog");

    }
}
