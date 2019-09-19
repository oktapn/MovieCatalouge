package id.co.muf.okta.moviecatalouge.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.vo.Resource;

public class DetailMovieViewModel extends ViewModel {
    private MutableLiveData<String> movieId = new MutableLiveData<>();
    private MutableLiveData<String> tvshowId = new MutableLiveData<>();

    private MovieRepository movieRepository;

    public DetailMovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String getMovieId() {
        if (movieId.getValue() == null) return null;
        return movieId.getValue();
    }

    public String getTvShowId() {
        if (tvshowId.getValue() == null) return null;
        return tvshowId.getValue();
    }

    void setFavoriteMovie() {
        if (movie.getValue() != null) {
            MovieEntity movieEntity = movie.getValue().data;
            // Kode di bawah menggunakan tanda seru (!),
            // karena akan mengganti status dari apakah sudah di bookmark atau tidak menjadi apakah sudah siap dibookmark atau tidak
            final boolean newState = !movieEntity.isFavorites();
            movieRepository.setFavoritesMovie(movieEntity, newState);

        }
    }

    void setFavoritTvshow() {
        if (tvshow.getValue() != null) {
            TvShowEntity tvShowEntity = tvshow.getValue().data;
            // Kode di bawah menggunakan tanda seru (!),
            // karena akan mengganti status dari apakah sudah di bookmark atau tidak menjadi apakah sudah siap dibookmark atau tidak
            final boolean newState = !tvShowEntity.isFavorites();
            movieRepository.setFavoritesTvshow(tvShowEntity, newState);

        }
    }

    public LiveData<Resource<MovieEntity>> movie = Transformations.switchMap(movieId,
            imageId -> movieRepository.getMovieDetail(imageId));

    public void setMovieId(String movieId) {
        this.movieId.setValue(movieId);
    }

    public LiveData<Resource<TvShowEntity>> tvshow = Transformations.switchMap(tvshowId,
            imageId -> movieRepository.getTvshowDetail(imageId));

    public void setTvshowId(String tvshowId) {
        this.tvshowId.setValue(tvshowId);
    }
}
