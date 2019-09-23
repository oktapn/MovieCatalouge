package id.co.muf.okta.moviecatalouge.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.vo.Resource;

public class TvShowViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public TvShowViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<Resource<List<TvShowEntity>>> getTvshow() {
        return movieRepository.getAllTvShows();
    }

}
