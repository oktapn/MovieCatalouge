package id.co.muf.okta.moviecatalouge.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.di.Injection;
import id.co.muf.okta.moviecatalouge.ui.detail.DetailMovieViewModel;
import id.co.muf.okta.moviecatalouge.ui.favorites.favoritedmovie.FavoritedMovieViewModel;
import id.co.muf.okta.moviecatalouge.ui.favorites.favoritedtvshow.FavoritedTvshowViewModel;
import id.co.muf.okta.moviecatalouge.ui.movie.MovieViewModel;
import id.co.muf.okta.moviecatalouge.ui.tvshow.TvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final MovieRepository movieRepository;

    private ViewModelFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(DetailMovieViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailMovieViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
            //noinspection unchecked
            return (T) new TvShowViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(FavoritedMovieViewModel.class)) {
            //noinspection unchecked
            return (T) new FavoritedMovieViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(FavoritedTvshowViewModel.class)) {
            //noinspection unchecked
            return (T) new FavoritedTvshowViewModel(movieRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
