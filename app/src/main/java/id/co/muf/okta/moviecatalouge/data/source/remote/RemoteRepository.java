package id.co.muf.okta.moviecatalouge.data.source.remote;

import android.os.Handler;

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

    public void getAllMovies(LoadMoviesCallback callback) {
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            callback.onAllMoviesReceived(jsonHelper.loadMovies());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public void getAllTvshows(LoadTvshowsCallback callback) {
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            callback.onAllTvshowsReceived(jsonHelper.loadTvshows());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadMoviesCallback {
        void onAllMoviesReceived(List<MovieResponse> courseResponses);

        void onDataNotAvailable();
    }

    public interface LoadTvshowsCallback {
        void onAllTvshowsReceived(List<TvshowResponse> moduleResponses);

        void onDataNotAvailable();
    }

}
