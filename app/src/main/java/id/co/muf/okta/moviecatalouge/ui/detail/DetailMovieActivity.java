package id.co.muf.okta.moviecatalouge.ui.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;

import id.co.muf.okta.moviecatalouge.R;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.utils.GlideApp;
import id.co.muf.okta.moviecatalouge.viewmodel.ViewModelFactory;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIES = "extra_course";
    private TextView textTitle;
    private TextView textDesc;
    private TextView textDate;
    private ImageView imagePoster;
    private ProgressBar progressBar;
    private DetailMovieViewModel viewModel;
    private Menu menu;
    private String identifier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        viewModel = obtainViewModel(this);
        Toolbar toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        progressBar = findViewById(R.id.progress_bar);
        textTitle = findViewById(R.id.text_title);
        textDesc = findViewById(R.id.text_description);
        textDate = findViewById(R.id.text_date);
        imagePoster = findViewById(R.id.image_poster);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            progressBar.setVisibility(View.VISIBLE);
            String courseId = extras.getString(EXTRA_MOVIES);
            assert courseId != null;
            identifier = courseId.substring(0, 1);
            switch (identifier) {
                case "t":
                    viewModel.setTvshowId(courseId);
                    viewModel.tvshow.observe(this, tvShowEntityResource -> {
                        if (tvShowEntityResource != null) {

                            switch (tvShowEntityResource.status) {
                                case LOADING:
                                    progressBar.setVisibility(View.VISIBLE);
                                    break;
                                case SUCCESS:
                                    if (tvShowEntityResource.data != null) {
                                        progressBar.setVisibility(View.GONE);
                                        populateTvShow(tvShowEntityResource.data);
                                    }
                                    break;
                                case ERROR:
                                    progressBar.setVisibility(View.GONE);
                                    break;
                            }
                        }
                    });
                    break;
                case "m":
                    viewModel.setMovieId(courseId);
                    viewModel.movie.observe(this, movieEntityResource -> {
                        if (movieEntityResource != null) {

                            switch (movieEntityResource.status) {
                                case LOADING:
                                    progressBar.setVisibility(View.VISIBLE);
                                    break;
                                case SUCCESS:
                                    if (movieEntityResource.data != null) {
                                        progressBar.setVisibility(View.GONE);
                                        populateMovie(movieEntityResource.data);
                                    }
                                    break;
                                case ERROR:
                                    progressBar.setVisibility(View.GONE);
                                    break;
                            }
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        switch (identifier) {
            case "t":
                viewModel.tvshow.observe(this, tvShowEntityResource -> {
                    if (tvShowEntityResource != null) {
                        switch (tvShowEntityResource.status) {
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                if (tvShowEntityResource.data != null) {
                                    progressBar.setVisibility(View.GONE);
                                    boolean state = tvShowEntityResource.data.isFavorites();
                                    setBookmarkState(state);
                                }
                                break;
                            case ERROR:
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                break;
            case "m":
                viewModel.movie.observe(this, movieEntityResource -> {
                    if (movieEntityResource != null) {
                        switch (movieEntityResource.status) {
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                if (movieEntityResource.data != null) {
                                    progressBar.setVisibility(View.GONE);
                                    boolean state = movieEntityResource.data.isFavorites();
                                    setBookmarkState(state);
                                }
                                break;
                            case ERROR:
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorites) {
            switch (identifier) {
                case "t":
                    viewModel.setFavoritTvshow();
                    setBookmarkState(false);
                    break;
                case "m":
                    viewModel.setFavoriteMovie();
                    setBookmarkState(false);
                    break;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBookmarkState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_favorites);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorited));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite));
        }
    }

    private void populateTvShow(TvShowEntity courseEntity) {
        textTitle.setText(courseEntity.getTitle());
        textDesc.setText(courseEntity.getDescription());
        textDate.setText(String.format("Released %s", courseEntity.getDate()));

        GlideApp.with(getApplicationContext())
                .load(courseEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);
    }

    private void populateMovie(MovieEntity courseEntity) {
        textTitle.setText(courseEntity.getTitle());
        textDesc.setText(courseEntity.getDescription());
        textDate.setText(String.format("Released %s", courseEntity.getDate()));

        GlideApp.with(getApplicationContext())
                .load(courseEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);
    }

    @NonNull
    private static DetailMovieViewModel obtainViewModel(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(DetailMovieViewModel.class);
    }
}
