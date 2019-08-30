package id.co.muf.okta.moviecatalouge.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import id.co.muf.okta.moviecatalouge.R;
import id.co.muf.okta.moviecatalouge.data.MovieEntity;
import id.co.muf.okta.moviecatalouge.data.TvShowEntity;
import id.co.muf.okta.moviecatalouge.utils.GlideApp;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIES = "extra_course";
    private Button btnStart;
    private TextView textTitle;
    private TextView textDesc;
    private TextView textDate;
    private RecyclerView rvModule;
    //    private DetailCourseAdapter adapter;
    private ImageView imagePoster;
    private ProgressBar progressBar;
    private DetailMovieViewModel viewModel;
    private List<TvShowEntity> TVmodules;
    private TvShowEntity TVm;
    private List<MovieEntity> Moviemodules;
    private MovieEntity Mm;
    private String identifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        viewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);
        Toolbar toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

//        adapter = new DetailCourseAdapter();

        progressBar = findViewById(R.id.progress_bar);
//        btnStart = findViewById(R.id.btn_start);
        textTitle = findViewById(R.id.text_title);
        textDesc = findViewById(R.id.text_description);
        textDate = findViewById(R.id.text_date);
        rvModule = findViewById(R.id.rv_module);
        imagePoster = findViewById(R.id.image_poster);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String courseId = extras.getString(EXTRA_MOVIES);
            assert courseId != null;
            String identifier = courseId.substring(0, 1);
            switch (identifier) {
                case "t":
                    viewModel.setTvShowid(courseId);
                    TVm = viewModel.getTvShow();
                    if (viewModel.getTvShow() != null) {
                        populateTvShow(viewModel.getTvShow());
                    }
                    break;
                case "m":
                    viewModel.setMovieid(courseId);
                    Mm = viewModel.getMovie();
                    if (viewModel.getMovie() != null) {
                        populateMovie(viewModel.getMovie());
                    }
                    break;
            }
//                adapter.setModules(modules)}
        }

//        rvModule.setNestedScrollingEnabled(false);
//        rvModule.setLayoutManager(new LinearLayoutManager(this));
//        rvModule.setHasFixedSize(true);
//        rvModule.setAdapter(adapter);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvModule.getContext(), DividerItemDecoration.VERTICAL);
//        rvModule.addItemDecoration(dividerItemDecoration);
    }

    private void populateTvShow(TvShowEntity courseEntity) {
//        CourseEntity courseEntity = DataDummy.getCourse(courseId);
        textTitle.setText(courseEntity.getTitle());
        textDesc.setText(courseEntity.getDescription());
        textDate.setText(String.format("Released %s", courseEntity.getDate()));

        GlideApp.with(getApplicationContext())
                .load(courseEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);

//        btnStart.setOnClickListener(v -> {
//            Intent intent = new Intent(DetailCourseActivity.this, CourseReaderActivity.class);
//            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, viewModel.getCourseId());
//            v.getContext().startActivity(intent);
//        });
    }

    private void populateMovie(MovieEntity courseEntity) {
//        CourseEntity courseEntity = DataDummy.getCourse(courseId);
        textTitle.setText(courseEntity.getTitle());
        textDesc.setText(courseEntity.getDescription());
        textDate.setText(String.format("Released %s", courseEntity.getDate()));

        GlideApp.with(getApplicationContext())
                .load(courseEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);

//        btnStart.setOnClickListener(v -> {
//            Intent intent = new Intent(DetailCourseActivity.this, CourseReaderActivity.class);
//            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, viewModel.getCourseId());
//            v.getContext().startActivity(intent);
//        });
    }
}
