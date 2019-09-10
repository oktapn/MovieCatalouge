package id.co.muf.okta.moviecatalouge.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.TvShowEntity;
import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;

public class DetailMovieViewModel extends ViewModel {
    private String Movieid;
    private String TvShowid;

    private MovieRepository movieRepository;

    public DetailMovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<MovieEntity> getMovie(){
        return movieRepository.getMovieDetail(Movieid);
    }

    public void setMovieid(String movieid) {
        Movieid = movieid;
    }

    LiveData<TvShowEntity> getTvShow(){
        return movieRepository.getTvshowDetail(TvShowid);

    }

    public void setTvShowid(String tvShowid) {
        TvShowid = tvShowid;
    }
}
