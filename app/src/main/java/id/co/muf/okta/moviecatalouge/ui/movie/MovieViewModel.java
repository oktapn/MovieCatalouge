package id.co.muf.okta.moviecatalouge.ui.movie;

import androidx.lifecycle.ViewModel;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.utils.DataDummy;

public class MovieViewModel extends ViewModel {

    public List<MovieEntity> getMovies() {
        return DataDummy.generateDummyMovies();
    }

}
