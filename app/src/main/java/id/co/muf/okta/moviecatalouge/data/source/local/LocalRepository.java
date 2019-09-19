package id.co.muf.okta.moviecatalouge.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.data.source.local.room.MovieDao;

public class LocalRepository {
    private final MovieDao mMovieDao;

    private LocalRepository(MovieDao mMovieDao) {
        this.mMovieDao = mMovieDao;
    }

    private static LocalRepository INSTANCE;

    public static LocalRepository getInstance(MovieDao movieDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(movieDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getAllMovies() {
        return mMovieDao.getMovies();
    }

    public LiveData<List<MovieEntity>> getFavoritesMovies() {
        return mMovieDao.getMoviesFavorite();
    }

    public LiveData<MovieEntity> getDetailMovies(final String imageId) {
        return mMovieDao.getMovieWithDetailById(imageId);
    }

    public void insertMovies(List<MovieEntity> movies) {
        mMovieDao.insertMovie(movies);
    }

    public void setMovieFavorite(MovieEntity movie, boolean newState) {
        movie.setFavorites(newState);
        mMovieDao.updateMovie(movie);
    }

    public LiveData<List<TvShowEntity>> getAllTvshows() {
        return mMovieDao.getTvshows();
    }

    public LiveData<List<TvShowEntity>> getFavoritesTvshows() {
        return mMovieDao.getTvshowsFavorite();
    }

    public LiveData<TvShowEntity> getDetailTvshows(final String imageId) {
        return mMovieDao.getTvshowsWithDetailById(imageId);
    }

    public void insertTvshows(List<TvShowEntity> tvShowEntities) {
        mMovieDao.insertTvshow(tvShowEntities);
    }

    public void setTvshowFavorite(TvShowEntity tvshow, boolean newState) {
        tvshow.setFavorites(newState);
        mMovieDao.updateTvshow(tvshow);
    }

    public DataSource.Factory<Integer, MovieEntity> getFavoriteMoviesPaged() {
        return mMovieDao.getFavoriteMovieAsPaged();
    }

    public DataSource.Factory<Integer, TvShowEntity> getFavoriteTvshowsPaged() {
        return mMovieDao.getFavoriteTvshowAsPaged();
    }
}
