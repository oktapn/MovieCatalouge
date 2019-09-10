package id.co.muf.okta.moviecatalouge.data.source;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.TvShowEntity;

public interface MovieDataSource {

    LiveData<List<MovieEntity>> getAllMovies();

    LiveData<MovieEntity> getMovieDetail(String imageId);

    LiveData<List<TvShowEntity>> getAllTvShows();

    LiveData<TvShowEntity> getTvshowDetail(String imageId);

}
