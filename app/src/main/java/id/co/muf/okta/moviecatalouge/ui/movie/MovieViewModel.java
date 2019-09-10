package id.co.muf.okta.moviecatalouge.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<List<MovieEntity>> getMovies() {
        return movieRepository.getAllMovies();
    }

}
