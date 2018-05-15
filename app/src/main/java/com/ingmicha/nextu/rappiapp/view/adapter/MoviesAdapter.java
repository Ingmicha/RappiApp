package com.ingmicha.nextu.rappiapp.view.adapter;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ingmicha.nextu.rappiapp.R;
import com.ingmicha.nextu.rappiapp.data.model.Movie;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/12/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    public static final String TAG = MoviesAdapter.class.getName();

    private List<Movie> movies;
    private ItemClickListener itemClickListener;
    private int lastVisibleItem,totalItemCount;
    private boolean loadMore;
    private OnLoadMoreListener onLoadMoreListener;

    public MoviesAdapter(){
        this.movies = Collections.emptyList();
    }

    public MoviesAdapter(RecyclerView recyclerView){
       //this.movies.addAll(movies);
        this.movies = Collections.emptyList();
        Log.d(TAG, "MoviesAdapter: init");
        if (recyclerView.getLayoutManager() == null){
            Log.d(TAG, "MoviesAdapter: LayoutManager it's null");
        }
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager){
            Log.d(TAG, "MoviesAdapter: Enter");
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    Log.d(TAG, "onScrolled: " + totalItemCount + " "+lastVisibleItem +" "+loadMore);
                    if (!loadMore && totalItemCount-1 == lastVisibleItem){
                        if (onLoadMoreListener != null){
                            Log.d(TAG, "onScrolled: Load More Results");
                            onLoadMoreListener.onLoadMore();
                        }
                        loadMore = true;
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_movies,parent,false);
        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesAdapter.MoviesViewHolder holder, int position) {

        final Movie movie = movies.get(position);
        holder.movie = movie;
        holder.mTextViewTitle.setText(movie.getTitle());
        holder.mTextViewDate.setText(movie.getReleaseDate());
        holder.mTextViewDesc.setText(movie.getOverview());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500"+movie.getPosterPath())
                .into(holder.mImageViewCover);

        //TODO: IMAGE COVER

        holder.mButtonMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(movie,holder.getAdapterPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface ItemClickListener {
        void onItemClick(Movie movie, int position);
    }

    public void setMovies(List<Movie> movies) {
        if (this.movies != null && !this.movies.isEmpty()){
            Log.d(TAG, "setMovies: add More Results");
            this.movies.addAll(movies);
            this.loadMore = false;
        }else {
            Log.d(TAG, "setMovies: set results");
            this.movies = movies;
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_title)
        TextView mTextViewTitle;

        @BindView(R.id.text_view_date)
        TextView mTextViewDate;

        @BindView(R.id.text_view_desc)
        TextView mTextViewDesc;

        @BindView(R.id.image_view_cover)
        ImageView mImageViewCover;

        @BindView(R.id.button_more_info)
        Button mButtonMoreInfo;

        Movie movie;
        View itemView;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.itemView = itemView;

        }
    }


}
