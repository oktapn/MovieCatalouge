package id.co.muf.okta.moviecatalouge.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.remote.response.MovieResponse;
import id.co.muf.okta.moviecatalouge.data.source.remote.response.TvshowResponse;
import id.co.muf.okta.moviecatalouge.utils.EspressoIdlingResource;
import id.co.muf.okta.moviecatalouge.utils.JsonHelper;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(helper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieResponse>>> getAllMoviesAsLiveData() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieResponse>>> resultCourse = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultCourse.setValue(ApiResponse.success(jsonHelper.loadMovies()));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultCourse;
    }

    public LiveData<ApiResponse<List<TvshowResponse>>> getAllTvshowsAsLiveData() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TvshowResponse>>> resultCourse = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultCourse.setValue(ApiResponse.success(jsonHelper.loadTvshows()));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultCourse;
    }

    public LiveData<ApiResponse<List<MovieResponse>>> getAllMovieDetailAsLiveData() {
        MutableLiveData<ApiResponse<List<MovieResponse>>> resultCourse = new MutableLiveData<>();

        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultCourse.setValue(ApiResponse.success(jsonHelper.loadMovies()));
//            callback.onAllMoviesReceived(jsonHelper.loadMovies());
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultCourse;
    }
}
