package id.co.muf.okta.moviecatalouge.ui.favorites.favoritedmovie;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.co.muf.okta.moviecatalouge.R;
import id.co.muf.okta.moviecatalouge.testing.SingleFragmentActivity;
import id.co.muf.okta.moviecatalouge.ui.movie.MovieFragment;
import id.co.muf.okta.moviecatalouge.utils.EspressoIdlingResource;
import id.co.muf.okta.moviecatalouge.utils.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class FavoritedMovieFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private FavoritedMovieFragment movieFragment = new FavoritedMovieFragment();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(movieFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadCourses() {
        onView(withId(R.id.rv_movie_fav)).check(matches(isDisplayed()));
    }
}