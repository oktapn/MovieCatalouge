package id.co.muf.okta.moviecatalouge.data.source;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.TvShowEntity;
import id.co.muf.okta.moviecatalouge.data.source.remote.RemoteRepository;
import id.co.muf.okta.moviecatalouge.data.source.remote.response.MovieResponse;
import id.co.muf.okta.moviecatalouge.data.source.remote.response.TvshowResponse;

public class MovieRepository implements MovieDataSource {

    private volatile static MovieRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;

    public MovieRepository(@NonNull RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static MovieRepository getInstance(RemoteRepository remoteData){
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public LiveData<List<MovieEntity>> getAllMovies() {
        MutableLiveData<List<MovieEntity>> tvshowResults = new MutableLiveData<>();

        remoteRepository.getAllMovies(new RemoteRepository.LoadMoviesCallback() {
            @Override
            public void onAllMoviesReceived(List<MovieResponse> courseResponses) {
                ArrayList<MovieEntity> movieList = new ArrayList<>();
                for (int i = 0; i < courseResponses.size(); i++) {
                    MovieResponse response = courseResponses.get(i);
                    MovieEntity movie = new MovieEntity(response.getImageId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getDate(),
                            false,
                            response.getImagePath());

                    movieList.add(movie);
                }
                tvshowResults.postValue(movieList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return tvshowResults;
    }

    @Override
    public LiveData<MovieEntity> getMovieDetail(String imageId) {
        MutableLiveData<MovieEntity> movieResult = new MutableLiveData<>();

        remoteRepository.getAllMovies(new RemoteRepository.LoadMoviesCallback() {
            @Override
            public void onAllMoviesReceived(List<MovieResponse> courseResponses) {
                for (int i = 0; i < courseResponses.size(); i++) {
                    MovieResponse response = courseResponses.get(i);
                    if (response.getImageId().equals(imageId)) {
                        MovieEntity movie = new MovieEntity(response.getImageId(),
                                response.getTitle(),
                                response.getDescription(),
                                response.getDate(),
                                false,
                                response.getImagePath());
                        movieResult.postValue(movie);
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return movieResult;
    }

    @Override
    public LiveData<List<TvShowEntity>> getAllTvShows() {
        MutableLiveData<List<TvShowEntity>> tvshowResults = new MutableLiveData<>();

        remoteRepository.getAllTvshows(new RemoteRepository.LoadTvshowsCallback() {
            @Override
            public void onAllTvshowsReceived(List<TvshowResponse> courseResponses) {
                ArrayList<TvShowEntity> tvshowList = new ArrayList<>();
                for (int i = 0; i < courseResponses.size(); i++) {
                    TvshowResponse response = courseResponses.get(i);
                    TvShowEntity tvshow = new TvShowEntity(response.getImageId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getDate(),
                            false,
                            response.getImagePath());

                    tvshowList.add(tvshow);
                }
                tvshowResults.postValue(tvshowList);
                Log.e("1258",String.valueOf(tvshowList.size()));
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return tvshowResults;
    }

    @Override
    public LiveData<TvShowEntity> getTvshowDetail(String imageId) {
        MutableLiveData<TvShowEntity> tvshowResult = new MutableLiveData<>();

        remoteRepository.getAllTvshows(new RemoteRepository.LoadTvshowsCallback() {
            @Override
            public void onAllTvshowsReceived(List<TvshowResponse> courseResponses) {
                for (int i = 0; i < courseResponses.size(); i++) {
                    TvshowResponse response = courseResponses.get(i);
                    if (response.getImageId().equals(imageId)) {
                        TvShowEntity tvshow = new TvShowEntity(response.getImageId(),
                                response.getTitle(),
                                response.getDescription(),
                                response.getDate(),
                                false,
                                response.getImagePath());
                        tvshowResult.postValue(tvshow);
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return tvshowResult;
    }
}
