package id.co.muf.okta.moviecatalouge.ui.favorites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import id.co.muf.okta.moviecatalouge.R;
import id.co.muf.okta.moviecatalouge.ui.favorites.favoritedmovie.FavoritedMovieFragment;
import id.co.muf.okta.moviecatalouge.ui.favorites.favoritedtvshow.FavoritedTvshowFragment;

public class FavoritesActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        initialiseViews();
        setUpTabLayout();
        Toolbar mTopToolbar = findViewById(R.id.toolbar_favorites);
        setSupportActionBar(mTopToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setActionBarTitle("Favorited");
            mTopToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        }
    }

    public void initialiseViews() {
        tabLayout = findViewById(R.id.tab_layout_favorites);
        viewPager = findViewById(R.id.view_pager_favorites);
    }

    private void setUpTabLayout() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new FavoritedMovieFragment(), "Fav Movie");
        adapter.add(new FavoritedTvshowFragment(), "Fav TV Shows");
        viewPager.setAdapter(adapter);
        viewPager.setPageMargin((int) getResources().getDimension(R.dimen.view_pager_gap));
        viewPager.setPageMarginDrawable(R.color.background);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> titleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        void add(Fragment fragment, String title) {
            fragmentList.add(fragment);
            titleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
