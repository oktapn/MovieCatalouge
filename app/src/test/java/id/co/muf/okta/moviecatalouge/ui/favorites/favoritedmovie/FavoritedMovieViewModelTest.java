package id.co.muf.okta.moviecatalouge.ui.favorites.favoritedmovie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.utils.FakeDataDummy;
import id.co.muf.okta.moviecatalouge.vo.Resource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoritedMovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FavoritedMovieViewModel viewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);

    @Before
    public void setUp() {
        viewModel = new FavoritedMovieViewModel(movieRepository);
    }

    @Test
    public void getFavoriteMovie() {
        MutableLiveData<Resource<PagedList<MovieEntity>>> dummyMovie = new MutableLiveData<>();
        PagedList<MovieEntity> pagedList = mock(PagedList.class);

        dummyMovie.setValue(Resource.success(pagedList));

        when(movieRepository.getFavoriteMoviesPaged()).thenReturn(dummyMovie);

        Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);

        viewModel.getFAvoriteMoviesPaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
//        ArrayList<MovieEntity> dummyCourses = FakeDataDummy.generateDummyMovies();
//
//        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
//        movies.setValue(dummyCourses);
//
//        when(movieRepository.getAllMovies()).thenReturn(movies);
//
//        Observer<List<MovieEntity>> observer = mock(Observer.class);
//
//        viewModel.getMovies().observeForever(observer);
//
//        verify(observer).onChanged(dummyCourses);
    }
}