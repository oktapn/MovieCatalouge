package id.co.muf.okta.moviecatalouge.ui.favorites.favoritedtvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.vo.Resource;

public class FavoritedTvshowViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public FavoritedTvshowViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<Resource<PagedList<TvShowEntity>>> getFavoriteTvshowsPaged() {
        return movieRepository.getFavoriteTvshowsPaged();
    }

    void setFavorite(TvShowEntity tvShowEntity) {
        final boolean newState = !tvShowEntity.isFavorites();
        movieRepository.setFavoritesTvshow(tvShowEntity, newState);
    }
}
