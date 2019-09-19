package id.co.muf.okta.moviecatalouge.ui.favorites.favoritedtvshow;

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
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.ui.detail.DetailMovieActivity;
import id.co.muf.okta.moviecatalouge.utils.GlideApp;

public class FavoritedTvshowPagedAdapter extends PagedListAdapter<TvShowEntity, FavoritedTvshowPagedAdapter.FavoriteTvshowViewHolder> {

    public FavoritedTvshowPagedAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public FavoriteTvshowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tvshow, parent, false);
        return new FavoriteTvshowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTvshowViewHolder holder, int position) {
        final TvShowEntity tvShowEntity = getItem(position);
        if (tvShowEntity != null) {
            holder.tvTitle.setText(tvShowEntity.getTitle());
            holder.tvDescription.setText(tvShowEntity.getDescription());
            holder.tvDate.setText(String.format("Released %s", tvShowEntity.getDate()));
            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIES, tvShowEntity.getImageId());
                context.startActivity(intent);
            });

            GlideApp.with(holder.itemView.getContext())
                    .load(tvShowEntity.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(holder.imgPoster);
        }
    }

    private static DiffUtil.ItemCallback<TvShowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.getImageId().equals(newItem.getImageId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    TvShowEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    class FavoriteTvshowViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        FavoriteTvshowViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title_tv);
            imgPoster = itemView.findViewById(R.id.img_poster_tv);
            tvDescription = itemView.findViewById(R.id.tv_item_description_tv);
            tvDate = itemView.findViewById(R.id.tv_item_date_tv);
        }
    }
}
