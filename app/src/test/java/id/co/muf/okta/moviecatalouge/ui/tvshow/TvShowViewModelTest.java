package id.co.muf.okta.moviecatalouge.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import id.co.muf.okta.moviecatalouge.data.TvShowEntity;
import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.utils.FakeDataDummy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TvShowViewModel viewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);


    @Before
    public void setUp() {
        viewModel = new TvShowViewModel(movieRepository);
    }

    @Test
    public void getTvShow() {
        ArrayList<TvShowEntity> dummyTvshows = FakeDataDummy.generateDummyTvShow();

        MutableLiveData<List<TvShowEntity>> tvshows = new MutableLiveData<>();
        tvshows.setValue(dummyTvshows);

        when(movieRepository.getAllTvShows()).thenReturn(tvshows);

        Observer<List<TvShowEntity>> observer = mock(Observer.class);

        viewModel.getTVshow().observeForever(observer);

        verify(observer).onChanged(dummyTvshows);
    }

}