package id.co.muf.okta.moviecatalouge.ui.tvshow;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.TvShowEntity;

import static org.junit.Assert.*;

public class TvShowViewModelTest {
    private TvShowViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new TvShowViewModel();
    }

    @Test
    public void getTvShow() {
        List<TvShowEntity> tvShowEntities = viewModel.getTVshow();
        assertNotNull(tvShowEntities);
        assertEquals(10, tvShowEntities.size());
    }

}