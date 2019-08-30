package id.co.muf.okta.moviecatalouge.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import id.co.muf.okta.moviecatalouge.R;
import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.TvShowEntity;
import id.co.muf.okta.moviecatalouge.utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class DetailMovieActivityTest {
    private MovieEntity dummyMovie = FakeDataDummy.generateDummyMovies().get(0);
    private TvShowEntity dummyTvShow = FakeDataDummy.generateDummyTvShow().get(0);

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailMovieActivity.class);
            result.putExtra(DetailMovieActivity.EXTRA_MOVIES, dummyMovie.getImageId());
            return result;
        }
    };

    @Test
    public void loadMovie() {
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie.getTitle())));
        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
        onView(withId(R.id.text_date)).check(matches(withText(String.format("Released %s", dummyMovie.getDate()))));
    }

//    @Rule
//    public ActivityTestRule<DetailMovieActivity> activityRule2 = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
//        @Override
//        protected Intent getActivityIntent() {
//            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//            Intent result = new Intent(targetContext, DetailMovieActivity.class);
//            result.putExtra(DetailMovieActivity.EXTRA_MOVIES, dummyTvShow.getImageId());
//            return result;
//        }
//    };
//
//    @Test
//    public void loadTvShow() {
//        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
//        onView(withId(R.id.text_title)).check(matches(withText(dummyTvShow.getTitle())));
//        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
//        onView(withId(R.id.text_date)).check(matches(withText(String.format("Released %s", dummyTvShow.getDate()))));
//    }


}