package id.co.muf.okta.moviecatalouge.di;

import android.app.Application;

import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.data.source.local.LocalRepository;
import id.co.muf.okta.moviecatalouge.data.source.local.room.MovieDatabase;
import id.co.muf.okta.moviecatalouge.data.source.remote.RemoteRepository;
import id.co.muf.okta.moviecatalouge.utils.AppExecutors;
import id.co.muf.okta.moviecatalouge.utils.JsonHelper;

public class Injection {
    public static MovieRepository provideRepository(Application application) {

        MovieDatabase database = MovieDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(database.movieDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        AppExecutors appExecutors = new AppExecutors();

        return MovieRepository.getInstance(localRepository, remoteRepository,appExecutors);
    }
}
