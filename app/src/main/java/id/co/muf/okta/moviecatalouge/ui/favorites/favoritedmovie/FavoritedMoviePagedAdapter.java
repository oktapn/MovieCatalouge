package id.co.muf.okta.moviecatalouge.ui.favorites.favoritedmovie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;

import id.co.muf.okta.moviecatalouge.R;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.ui.detail.DetailMovieActivity;
import id.co.muf.okta.moviecatalouge.utils.GlideApp;

public class FavoritedMoviePagedAdapter extends PagedListAdapter<MovieEntity, FavoritedMoviePagedAdapter.FavoriteMovieViewHolder> {

    public FavoritedMoviePagedAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public FavoriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_movie, parent, false);
        return new FavoriteMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieViewHolder holder, int position) {
        final MovieEntity movie = getItem(position);
        if (movie != null) {
            holder.tvTitle.setText(movie.getTitle());
            holder.tvDescription.setText(movie.getDescription());
            holder.tvDate.setText(String.format("Released %s", movie.getDate()));
            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIES, movie.getImageId());
                context.startActivity(intent);
            });

            GlideApp.with(holder.itemView.getContext())
                    .load(movie.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(holder.imgPoster);
        }
    }

    private static DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.getImageId().equals(newItem.getImageId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    MovieEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    class FavoriteMovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        FavoriteMovieViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description_tv);
            tvDate = itemView.findViewById(R.id.tv_item_date_tv);
        }
    }
}
