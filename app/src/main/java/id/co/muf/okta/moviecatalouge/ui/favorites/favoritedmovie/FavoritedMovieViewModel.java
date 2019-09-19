package id.co.muf.okta.moviecatalouge.ui.favorites.favoritedmovie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.vo.Resource;

public class FavoritedMovieViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public FavoritedMovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<Resource<PagedList<MovieEntity>>> getFAvoriteMoviesPaged(){
        return movieRepository.getFavoriteMoviesPaged();
    }

    void setFavorite(MovieEntity movieEntity) {
        final boolean newState = !movieEntity.isFavorites();
        movieRepository.setFavoritesMovie(movieEntity, newState);
    }
}
