package id.co.muf.okta.moviecatalouge.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.vo.Resource;

public interface MovieDataSource {

    LiveData<Resource<List<MovieEntity>>> getAllMovies();

    LiveData<Resource<MovieEntity>> getMovieDetail(String imageId);

    LiveData<Resource<List<TvShowEntity>>> getAllTvShows();

    LiveData<Resource<TvShowEntity>> getTvshowDetail(String imageId);

    LiveData<Resource<List<MovieEntity>>> getFavoritesMovies();

    void setFavoritesMovie(MovieEntity movie, boolean state);

    LiveData<Resource<List<TvShowEntity>>> getFavoritesTvshow();

    void setFavoritesTvshow(TvShowEntity tvShow, boolean state);

    LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMoviesPaged();

    LiveData<Resource<PagedList<TvShowEntity>>> getFavoriteTvshowsPaged();


}
