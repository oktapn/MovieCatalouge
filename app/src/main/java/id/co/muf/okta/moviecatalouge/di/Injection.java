package id.co.muf.okta.moviecatalouge.di;

import android.app.Application;

import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.data.source.remote.RemoteRepository;
import id.co.muf.okta.moviecatalouge.utils.JsonHelper;

public class Injection {
    public static MovieRepository provideRepository(Application application) {

        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));

        return MovieRepository.getInstance(remoteRepository);
    }
}
