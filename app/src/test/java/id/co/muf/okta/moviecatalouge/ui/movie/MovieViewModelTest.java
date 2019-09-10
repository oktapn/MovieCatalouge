package id.co.muf.okta.moviecatalouge.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.utils.FakeDataDummy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel viewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);

    @Before
    public void setUp() {
        viewModel = new MovieViewModel(movieRepository);
    }

    @Test
    public void getMovie() {
        ArrayList<MovieEntity> dummyCourses = FakeDataDummy.generateDummyMovies();

        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyCourses);

        when(movieRepository.getAllMovies()).thenReturn(movies);

        Observer<List<MovieEntity>> observer = mock(Observer.class);

        viewModel.getMovies().observeForever(observer);

        verify(observer).onChanged(dummyCourses);
    }
}