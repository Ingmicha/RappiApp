package com.ingmicha.nextu.rappiapp.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ingmicha.nextu.rappiapp.R;
import com.ingmicha.nextu.rappiapp.data.model.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Michael Alejandro Ura A DesarrolloApps on 5/14/18.
 */

public class MovieDialog extends DialogFragment implements View.OnClickListener{


    @BindView(R.id.image_view_cover_dialog)
    ImageView mImageViewCover;
    @BindView(R.id.text_view_title_dialog)
    TextView mTextViewTitle;
    @BindView(R.id.text_view_date_dialog)
    TextView mTextViewDate;
    @BindView(R.id.text_view_desc_dialog)
    TextView mTextViewDesc;
    @BindView(R.id.button_close_dialog)
    Button mButtonClose;



    public static MovieDialog newInstance(Movie movie) {

        Bundle args = new Bundle();

        MovieDialog fragment = new MovieDialog();
        args.putString("movie", new Gson().toJson(movie));
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View dialog = View.inflate(getContext(), R.layout.dialog_detail_movie,null);
        ButterKnife.bind(this,dialog);
        builder.setView(dialog);

        if (getArguments() != null){
            Movie movie = new Gson().fromJson(getArguments().getString("movie"),Movie.class);

            assert movie != null;
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500"+movie.getPosterPath())
                    .into(mImageViewCover);

            mTextViewTitle.setText(movie.getTitle());
            mTextViewDesc.setText(movie.getOverview());
            mTextViewDate.setText(movie.getReleaseDate());
        }
        mButtonClose.setOnClickListener(this);

        return builder.create();

    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
