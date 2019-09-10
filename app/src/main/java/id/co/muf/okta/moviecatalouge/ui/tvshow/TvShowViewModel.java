package id.co.muf.okta.moviecatalouge.ui.tvshow;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.TvShowEntity;
import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;

public class TvShowViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public TvShowViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<List<TvShowEntity>> getTVshow() {
        return movieRepository.getAllTvShows();
    }

}
