package id.co.muf.okta.moviecatalouge.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.utils.FakeDataDummy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailMovieViewModel viewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);
    private MovieEntity dummyMovie = FakeDataDummy.generateDummyMovies().get(0);
    private String imageId = dummyMovie.getImageId();


    @Before
    public void setUp() {
        viewModel = new DetailMovieViewModel(movieRepository);
        viewModel.setMovieid(imageId);
    }

    @Test
    public void getMovie() {
        MutableLiveData<MovieEntity> moviesEntities = new MutableLiveData<>();
        moviesEntities.setValue(dummyMovie);

        when(movieRepository.getMovieDetail(imageId)).thenReturn(moviesEntities);

        Observer<MovieEntity> observer = mock(Observer.class);

        viewModel.getMovie().observeForever(observer);

        verify(observer).onChanged(dummyMovie);

    }

}