package id.co.muf.okta.moviecatalouge.ui.detail;

import androidx.lifecycle.ViewModel;

import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.TvShowEntity;
import id.co.muf.okta.moviecatalouge.utils.DataDummy;

public class DetailMovieViewModel extends ViewModel {
    private String Movieid;
    private MovieEntity movieEntity;

    private String TvShowid;
    private TvShowEntity tvShowEntity;

    public MovieEntity getMovie(){
        for (int i = 0; i < DataDummy.generateDummyMovies().size(); i++) {
            MovieEntity courseEntity = DataDummy.generateDummyMovies().get(i);
            if (courseEntity.getImageId().equals(Movieid)) {
                movieEntity = courseEntity;
            }
        }
        return movieEntity;
    }

    public String getMovieid() {
        return Movieid;
    }

    public void setMovieid(String movieid) {
        Movieid = movieid;
    }

    public TvShowEntity getTvShow(){
        for (int i = 0;i<DataDummy.generateDummyTvShow().size();i++){
            TvShowEntity tvShowEntitys = DataDummy.generateDummyTvShow().get(i);
            if (tvShowEntitys.getImageId().equals(TvShowid)){
                tvShowEntity = tvShowEntitys;
            }
        }
        return tvShowEntity;
    }

    public String getTvShowid() {
        return TvShowid;
    }

    public void setTvShowid(String tvShowid) {
        TvShowid = tvShowid;
    }
}
