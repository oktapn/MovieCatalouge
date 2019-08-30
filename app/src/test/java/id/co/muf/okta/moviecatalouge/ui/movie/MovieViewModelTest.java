package id.co.muf.okta.moviecatalouge.ui.movie;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.MovieEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MovieViewModelTest {
    private MovieViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new MovieViewModel();
    }

    @Test
    public void getMovie() {
        List<MovieEntity> movieEntities = viewModel.getMovies();
        assertNotNull(movieEntities);
        assertEquals(10, movieEntities.size());
    }
}