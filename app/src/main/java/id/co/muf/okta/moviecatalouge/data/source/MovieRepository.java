package id.co.muf.okta.moviecatalouge.data.source;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.ArrayList;
import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.local.LocalRepository;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.data.source.remote.ApiResponse;
import id.co.muf.okta.moviecatalouge.data.source.remote.RemoteRepository;
import id.co.muf.okta.moviecatalouge.data.source.remote.response.MovieResponse;
import id.co.muf.okta.moviecatalouge.data.source.remote.response.TvshowResponse;
import id.co.muf.okta.moviecatalouge.utils.AppExecutors;
import id.co.muf.okta.moviecatalouge.vo.Resource;

public class MovieRepository implements MovieDataSource {

    private volatile static MovieRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;
    private final AppExecutors appExecutors;

    public MovieRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static MovieRepository getInstance(LocalRepository localRepository, RemoteRepository remoteData, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(localRepository, remoteData, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getAllMovies() {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            @Override
            public LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getAllMovies();
            }

            @Override
            public Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            public LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return remoteRepository.getAllMoviesAsLiveData();
            }

            @Override
            public void saveCallResult(List<MovieResponse> movieResponses) {
                List<MovieEntity> movieEntities = new ArrayList<>();

                for (MovieResponse movieResponse : movieResponses) {

                    movieEntities.add(new MovieEntity(movieResponse.getImageId(),
                            movieResponse.getTitle(),
                            movieResponse.getDescription(),
                            movieResponse.getDate(),
                            movieResponse.isFavorites(), movieResponse.getImagePath()));
                }

                localRepository.insertMovies(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovieDetail(final String imageId) {
        return new NetworkBoundResource<MovieEntity, List<MovieResponse>>(appExecutors) {
            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getDetailMovies(imageId);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity movieEntity) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> movieResponses) {

            }
        }.asLiveData();
    }


    @Override
    public LiveData<Resource<List<TvShowEntity>>> getAllTvShows() {
        return new NetworkBoundResource<List<TvShowEntity>, List<TvshowResponse>>(appExecutors) {
            @Override
            public LiveData<List<TvShowEntity>> loadFromDB() {
                return localRepository.getAllTvshows();
            }

            @Override
            public Boolean shouldFetch(List<TvShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            public LiveData<ApiResponse<List<TvshowResponse>>> createCall() {
                return remoteRepository.getAllTvshowsAsLiveData();
            }

            @Override
            public void saveCallResult(List<TvshowResponse> tvshowResponses) {
                List<TvShowEntity> tvShowEntities = new ArrayList<>();

                for (TvshowResponse tvshowResponse : tvshowResponses) {

                    tvShowEntities.add(new TvShowEntity(tvshowResponse.getImageId(),
                            tvshowResponse.getTitle(),
                            tvshowResponse.getDescription(),
                            tvshowResponse.getDate(),
                            tvshowResponse.isFavorites(), tvshowResponse.getImagePath()));
                }

                localRepository.insertTvshows(tvShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvShowEntity>> getTvshowDetail(final String imageId) {
        return new NetworkBoundResource<TvShowEntity, List<TvshowResponse>>(appExecutors) {
            @Override
            protected LiveData<TvShowEntity> loadFromDB() {
                return localRepository.getDetailTvshows(imageId);
            }

            @Override
            protected Boolean shouldFetch(TvShowEntity tvShowEntity) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TvshowResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TvshowResponse> tvshowResponses) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getFavoritesMovies() {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getFavoritesMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public void setFavoritesMovie(MovieEntity movie, boolean state) {
        Runnable runnable = () -> localRepository.setMovieFavorite(movie, state);

        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public LiveData<Resource<List<TvShowEntity>>> getFavoritesTvshow() {
        return new NetworkBoundResource<List<TvShowEntity>, List<TvshowResponse>>(appExecutors) {
            @Override
            protected LiveData<List<TvShowEntity>> loadFromDB() {
                return localRepository.getFavoritesTvshows();
            }

            @Override
            protected Boolean shouldFetch(List<TvShowEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TvshowResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TvshowResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public void setFavoritesTvshow(TvShowEntity tvShow, boolean state) {
        Runnable runnable = () -> localRepository.setTvshowFavorite(tvShow, state);

        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMoviesPaged() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavoriteMoviesPaged(),10).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvShowEntity>>> getFavoriteTvshowsPaged() {
        return new NetworkBoundResource<PagedList<TvShowEntity>, List<MovieResponse>>(appExecutors) {
            @Override
            protected LiveData<PagedList<TvShowEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getFavoriteTvshowsPaged(),10).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvShowEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieResponse>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {

            }
        }.asLiveData();
    }
}
