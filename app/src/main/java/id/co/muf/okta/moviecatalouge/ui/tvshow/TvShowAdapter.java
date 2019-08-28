package id.co.muf.okta.moviecatalouge.ui.tvshow;

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
import id.co.muf.okta.moviecatalouge.data.TvShowEntity;
import id.co.muf.okta.moviecatalouge.ui.detail.DetailMovieActivity;
import id.co.muf.okta.moviecatalouge.utils.GlideApp;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private final Activity activity;

    public TvShowAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<TvShowEntity> tvShows = new ArrayList<>();

    private List<TvShowEntity> getListTvShow() {
        return tvShows;
    }

    void setListTvShow(List<TvShowEntity> listMovies) {
        if (listMovies == null) return;
        this.tvShows.clear();
        this.tvShows.addAll(listMovies);
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tvshow, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        holder.tvTitle.setText(getListTvShow().get(position).getTitle());
        holder.tvDescription.setText(getListTvShow().get(position).getDescription());
        holder.tvDate.setText(String.format("Released %s", getListTvShow().get(position).getDate()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIES, getListTvShow().get(position).getImageId());
            activity.startActivity(intent);
        });

        GlideApp.with(holder.itemView.getContext())
                .load(getListTvShow().get(position).getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListTvShow().size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        TvShowViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title_tv);
            imgPoster = itemView.findViewById(R.id.img_poster_tv);
            tvDescription = itemView.findViewById(R.id.tv_item_description_tv);
            tvDate = itemView.findViewById(R.id.tv_item_date_tv);
        }
    }

}
