package id.co.muf.okta.moviecatalouge.ui.tvshow;

import androidx.lifecycle.ViewModel;

import java.util.List;

import id.co.muf.okta.moviecatalouge.data.TvShowEntity;
import id.co.muf.okta.moviecatalouge.utils.DataDummy;

public class TvShowViewModel extends ViewModel {
    public List<TvShowEntity> getTVshow() {
        return DataDummy.generateDummyTvShow();
    }

}
