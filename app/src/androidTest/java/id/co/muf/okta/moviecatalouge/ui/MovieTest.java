package id.co.muf.okta.moviecatalouge.ui;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import id.co.muf.okta.moviecatalouge.R;
import id.co.muf.okta.moviecatalouge.ui.home.HomeActivity;
import id.co.muf.okta.moviecatalouge.utils.EspressoIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class MovieTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void toDetailActivityMovieTest() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(withText("Alita")));
        onView(withId(R.id.action_favorites)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favorites)).perform(click());
    }

    @Test
    public void toDetailActivityTvshowTest() {
        onView(withText("TV Shows")).perform(click());
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(withText("Arrow")));
        onView(withId(R.id.action_favorites)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favorites)).perform(click());
    }

    @Test
    public void toDetailActivityFavoriteMovieTest() {
        onView(withId(R.id.action_list_favorites)).check(matches(isDisplayed()));
        onView(withId(R.id.action_list_favorites)).perform(click());
        if (getCountFromRecyclerView(R.id.rv_movie_fav) > 0) {
            onView(withId(R.id.rv_movie_fav)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void toDetailActivityFavoriteTvshowTest() {
        onView(withId(R.id.action_list_favorites)).check(matches(isDisplayed()));
        onView(withId(R.id.action_list_favorites)).perform(click());
        onView(withText("Fav TV Shows")).perform(click());
        if (getCountFromRecyclerView(R.id.rv_tvshow_fav) > 0) {
            onView(withId(R.id.rv_tvshow_fav)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        }
    }

    public static int getCountFromRecyclerView(@IdRes int RecyclerViewId) {
        final int[] COUNT = {0};
        Matcher matcher = new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                COUNT[0] = ((RecyclerView) item).getAdapter().getItemCount();
                return true;
            }
            @Override
            public void describeTo(Description description) {
            }
        };
        onView(allOf(withId(RecyclerViewId),isDisplayed())).check(matches(matcher));
        int result = COUNT[0];
        COUNT[0] = 0;
        return result;
    }
}
