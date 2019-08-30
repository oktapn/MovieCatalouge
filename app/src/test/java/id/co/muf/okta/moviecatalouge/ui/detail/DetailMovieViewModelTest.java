package id.co.muf.okta.moviecatalouge.ui.detail;

import org.junit.Before;
import org.junit.Test;

import id.co.muf.okta.moviecatalouge.R;
import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.TvShowEntity;

import static org.junit.Assert.*;

public class DetailMovieViewModelTest {
    private DetailMovieViewModel viewModel;
    private MovieEntity dummyMovie;
    private TvShowEntity dummyTvshow;

    @Before
    public void setUp() throws Exception {
        viewModel = new DetailMovieViewModel();
        dummyMovie = new MovieEntity("m10",
                "Mortal Engines",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "2019",
                false,
                R.drawable.poster_mortal_engines);
        dummyTvshow = new TvShowEntity("t01",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "2012",
                false,
                R.drawable.poster_arrow);
    }

    @Test
    public void getMovie() {
        viewModel.setMovieid(dummyMovie.getImageId());
        MovieEntity courseEntity = viewModel.getMovie();
        assertNotNull(courseEntity);
        assertEquals(dummyMovie.getImageId(), courseEntity.getImageId());
        assertEquals(dummyMovie.getDate(), courseEntity.getDate());
        assertEquals(dummyMovie.getDescription(), courseEntity.getDescription());
        assertEquals(dummyMovie.getImagePath(), courseEntity.getImagePath());
        assertEquals(dummyMovie.getTitle(), courseEntity.getTitle());
    }


    @Test
    public void getTvShow() {
        viewModel.setTvShowid(dummyTvshow.getImageId());
        TvShowEntity tvShowEntity = viewModel.getTvShow();
        assertNotNull(tvShowEntity);
        assertEquals(dummyTvshow.getImageId(), tvShowEntity.getImageId());
        assertEquals(dummyTvshow.getDate(), tvShowEntity.getDate());
        assertEquals(dummyTvshow.getDescription(), tvShowEntity.getDescription());
        assertEquals(dummyTvshow.getImagePath(), tvShowEntity.getImagePath());
        assertEquals(dummyTvshow.getTitle(), tvShowEntity.getTitle());
    }


}