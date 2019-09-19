package id.co.muf.okta.moviecatalouge.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.vo.Resource;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;

    private MutableLiveData<String> mLogin = new MutableLiveData<>();

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<Resource<List<MovieEntity>>> getMovie() {
        return movieRepository.getAllMovies();
    }

}
