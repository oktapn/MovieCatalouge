package id.co.muf.okta.moviecatalouge.ui.favorites.favoritedtvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.MovieRepository;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.utils.FakeDataDummy;
import id.co.muf.okta.moviecatalouge.vo.Resource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoritedTvshowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FavoritedTvshowViewModel viewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);


    @Before
    public void setUp() {
        viewModel = new FavoritedTvshowViewModel(movieRepository);
    }

    @Test
    public void getTvShow() {

        MutableLiveData<Resource<PagedList<TvShowEntity>>> dummyTvshow = new MutableLiveData<>();
        PagedList<TvShowEntity> pagedList = mock(PagedList.class);

        dummyTvshow.setValue(Resource.success(pagedList));

        when(movieRepository.getFavoriteTvshowsPaged()).thenReturn(dummyTvshow);

        Observer<Resource<PagedList<TvShowEntity>>> observer = mock(Observer.class);

        viewModel.getFavoriteTvshowsPaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));
//        ArrayList<TvShowEntity> dummyTvshows = FakeDataDummy.generateDummyTvShow();
//
//        MutableLiveData<List<TvShowEntity>> tvshows = new MutableLiveData<>();
//        tvshows.setValue(dummyTvshows);
//
//        when(movieRepository.getAllTvShows()).thenReturn(tvshows);
//
//        Observer<List<TvShowEntity>> observer = mock(Observer.class);
//
//        viewModel.getTVshow().observeForever(observer);
//
//        verify(observer).onChanged(dummyTvshows);
    }
}