package com.andia.loice.movies.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andia.loice.movies.R;
import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.util.StringUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Movie> movies;

    public MovieListAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movieItem = movies.get(position);
        holder.movieTitle.setText(movieItem.getTitle());
        holder.movieTag.setText(StringUtils.truncate(movieItem.getOverview()));
        Picasso.get().load(StringUtils.getBackdropUrl(movieItem.getBackdropPath()))
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_text_view)
        TextView movieTitle;
        @BindView(R.id.poster_image_view)
        ImageView moviePoster;
        @BindView(R.id.overview_text_view)
        TextView movieTag;
        View view;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }

        private String getYearText(Movie movie) {
            String dateString = movie.getReleaseDate();
            String[] splits = dateString.split("-");
            return splits[0];
        }
    }
}