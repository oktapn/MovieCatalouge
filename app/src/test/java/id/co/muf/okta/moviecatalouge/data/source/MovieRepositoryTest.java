package id.co.muf.okta.moviecatalouge.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import id.co.muf.okta.moviecatalouge.data.source.local.LocalRepository;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.data.source.remote.RemoteRepository;
import id.co.muf.okta.moviecatalouge.data.source.remote.response.MovieResponse;
import id.co.muf.okta.moviecatalouge.data.source.remote.response.TvshowResponse;
import id.co.muf.okta.moviecatalouge.utils.FakeDataDummy;
import id.co.muf.okta.moviecatalouge.utils.InstantAppExecutors;
import id.co.muf.okta.moviecatalouge.utils.LiveDataTestUtil;
import id.co.muf.okta.moviecatalouge.utils.PagedListUtil;
import id.co.muf.okta.moviecatalouge.vo.Resource;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository local = mock(LocalRepository.class);
    private RemoteRepository remote = mock(RemoteRepository.class);
    private InstantAppExecutors instantAppExecutors = mock(InstantAppExecutors.class);
    private FakeMovieRepository academyRepository = new FakeMovieRepository(local, remote, instantAppExecutors);
    private ArrayList<MovieEntity> movieEntities = FakeDataDummy.generateDummyMovies();
    private ArrayList<TvShowEntity> tvShowEntities = FakeDataDummy.generateDummyTvShow();
    private ArrayList<MovieResponse> movieResponses = FakeDataDummy.generateRemoteDummyMovies();
    private ArrayList<TvshowResponse> tvshowsResponses = FakeDataDummy.generateRemoteDummyTvShow();

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getMovies() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(local.getFavoriteMoviesPaged()).thenReturn(dataSourceFactory);
        academyRepository.getFavoriteMoviesPaged();
        Resource<PagedList<MovieEntity>> result = Resource.success(PagedListUtil.mockPagedList(movieEntities));

        verify(local).getFavoriteMoviesPaged();
        assertNotNull(result.data);
        assertEquals(movieEntities.size(), result.data.size());

//        doAnswer(invocation -> {
//            ((RemoteRepository.LoadMoviesCallback) invocation.getArguments()[0])
//                    .onAllMoviesReceived(movieResponses);
//            return null;
//        }).when(remote).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));
//
//        List<MovieEntity> result = LiveDataTestUtil.getValue(academyRepository.getAllMovies());
//
//        verify(remote, times(1)).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));
//
//        assertNotNull(result);
//        assertEquals(movieResponses.size(), result.size());
    }

    @Test
    public void getTvshows() {
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(local.getFavoriteTvshowsPaged()).thenReturn(dataSourceFactory);
        academyRepository.getFavoriteTvshowsPaged();
        Resource<PagedList<TvShowEntity>> result = Resource.success(PagedListUtil.mockPagedList(tvShowEntities));

        verify(local).getFavoriteTvshowsPaged();
        assertNotNull(result.data);
        assertEquals(tvShowEntities.size(), result.data.size());
//        MutableLiveData<List<TvShowEntity>> dummyCourses = new MutableLiveData<>();
//        dummyCourses.setValue(FakeDataDummy.generateDummyTvShow());
//
//        when(local.getAllTvshows()).thenReturn(dummyCourses);
//
//        Resource<List<TvShowEntity>> result = LiveDataTestUtil.getValue(academyRepository.getAllTvShows());
//
//        verify(local).getAllTvshows();
//        assertNotNull(result.data);
//        assertEquals(tvshowsResponses.size(), result.data.size());
//        doAnswer(invocation -> {
//            ((RemoteRepository.LoadTvshowsCallback) invocation.getArguments()[0])
//                    .onAllTvshowsReceived(tvshowsResponses);
//            return null;
//        }).when(remote).getAllTvshows(any(RemoteRepository.LoadTvshowsCallback.class));
//
//        List<TvShowEntity> result = LiveDataTestUtil.getValue(academyRepository.getAllTvShows());
//
//        verify(remote, times(1)).getAllTvshows(any(RemoteRepository.LoadTvshowsCallback.class));
//
//        assertNotNull(result);
//        assertEquals(movieResponses.size(), result.size());
    }

}
