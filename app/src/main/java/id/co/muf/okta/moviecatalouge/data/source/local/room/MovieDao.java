package id.co.muf.okta.moviecatalouge.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;

@Dao
public interface MovieDao {
    @WorkerThread
    @Query("SELECT * FROM movieentities")
    LiveData<List<MovieEntity>> getMovies();

    @WorkerThread
    @Query("SELECT * FROM movieentities where favorites = 1")
    LiveData<List<MovieEntity>> getMoviesFavorite();

    @Transaction
    @Query("SELECT * FROM movieentities WHERE imageId = :imageId")
    LiveData<MovieEntity> getMovieWithDetailById(String imageId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovie(List<MovieEntity> movies);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateMovie(MovieEntity movies);

    @WorkerThread
    @Query("SELECT * FROM tvshowentities")
    LiveData<List<TvShowEntity>> getTvshows();

    @WorkerThread
    @Query("SELECT * FROM tvshowentities where favorites = 1")
    LiveData<List<TvShowEntity>> getTvshowsFavorite();

    @Transaction
    @Query("SELECT * FROM tvshowentities WHERE imageId = :imageId")
    LiveData<TvShowEntity> getTvshowsWithDetailById(String imageId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTvshow(List<TvShowEntity> tvShows);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateTvshow(TvShowEntity tvShows);

    @Query("SELECT * FROM movieentities where favorites = 1")
    DataSource.Factory<Integer, MovieEntity> getFavoriteMovieAsPaged();

    @Query("SELECT * FROM tvshowentities where favorites = 1")
    DataSource.Factory<Integer, TvShowEntity> getFavoriteTvshowAsPaged();

}
