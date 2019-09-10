package id.co.muf.okta.moviecatalouge.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.TvShowEntity;
import id.co.muf.okta.moviecatalouge.data.source.remote.RemoteRepository;
import id.co.muf.okta.moviecatalouge.data.source.remote.response.MovieResponse;
import id.co.muf.okta.moviecatalouge.data.source.remote.response.TvshowResponse;
import id.co.muf.okta.moviecatalouge.utils.FakeDataDummy;
import id.co.muf.okta.moviecatalouge.utils.LiveDataTestUtil;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MovieRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remote = Mockito.mock(RemoteRepository.class);
    private FakeMovieRepository academyRepository = new FakeMovieRepository(remote);

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

        doAnswer(invocation -> {
            ((RemoteRepository.LoadMoviesCallback) invocation.getArguments()[0])
                    .onAllMoviesReceived(movieResponses);
            return null;
        }).when(remote).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));

        List<MovieEntity> result = LiveDataTestUtil.getValue(academyRepository.getAllMovies());

        verify(remote, times(1)).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));

        assertNotNull(result);
        assertEquals(movieResponses.size(), result.size());
    }

    @Test
    public void getTvshows() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTvshowsCallback) invocation.getArguments()[0])
                    .onAllTvshowsReceived(tvshowsResponses);
            return null;
        }).when(remote).getAllTvshows(any(RemoteRepository.LoadTvshowsCallback.class));

        List<TvShowEntity> result = LiveDataTestUtil.getValue(academyRepository.getAllTvShows());

        verify(remote, times(1)).getAllTvshows(any(RemoteRepository.LoadTvshowsCallback.class));

        assertNotNull(result);
        assertEquals(movieResponses.size(), result.size());
    }

}
