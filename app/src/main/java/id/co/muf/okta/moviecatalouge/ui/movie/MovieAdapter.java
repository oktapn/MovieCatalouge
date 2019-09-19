package id.co.muf.okta.moviecatalouge.ui.movie;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import id.co.muf.okta.moviecatalouge.R;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.ui.detail.DetailMovieActivity;
import id.co.muf.okta.moviecatalouge.utils.GlideApp;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final Activity activity;

    public MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<MovieEntity> mMovies = new ArrayList<>();

    private List<MovieEntity> getListMovies() {
        return mMovies;
    }

    void setListMovies(List<MovieEntity> listMovies) {
        if (listMovies == null) return;
        this.mMovies.clear();
        this.mMovies.addAll(listMovies);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.tvTitle.setText(getListMovies().get(position).getTitle());
        holder.tvDescription.setText(getListMovies().get(position).getDescription());
        holder.tvDate.setText(String.format("Released %s", getListMovies().get(position).getDate()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIES, getListMovies().get(position).getImageId());
            activity.startActivity(intent);
        });

        GlideApp.with(holder.itemView.getContext())
                .load(getListMovies().get(position).getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListMovies().size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        MovieViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description_tv);
            tvDate = itemView.findViewById(R.id.tv_item_date_tv);
        }
    }
}
