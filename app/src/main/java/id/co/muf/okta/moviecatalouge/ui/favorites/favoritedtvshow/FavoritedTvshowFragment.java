package id.co.muf.okta.moviecatalouge.ui.favorites.favoritedtvshow;


import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import id.co.muf.okta.moviecatalouge.R;
import id.co.muf.okta.moviecatalouge.data.source.local.entity.TvShowEntity;
import id.co.muf.okta.moviecatalouge.viewmodel.ViewModelFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritedTvshowFragment extends Fragment {

    private RecyclerView rvMovie;
    private ProgressBar progressBar;
    private TextView emptyView;
    private LinearLayout LLview;
    private FavoritedTvshowPagedAdapter adapter;
    private FavoritedTvshowViewModel viewModel;

    public FavoritedTvshowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorited_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_tvshow_fav);
        progressBar = view.findViewById(R.id.progress_bar);
        emptyView = view.findViewById(R.id.empty_view);
        LLview = view.findViewById(R.id.LLView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            viewModel = obtainViewModel(getActivity());
//            courses = viewModel.getBookmarks();

            adapter = new FavoritedTvshowPagedAdapter();

            viewModel.getFavoriteTvshowsPaged().observe(this, courses -> {
                if (courses != null) {
                    switch (courses.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            if (courses.data != null) {
                                viewdata(courses.data.size());
                            }
                            progressBar.setVisibility(View.GONE);
                            adapter.submitList(courses.data);
                            adapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovie.setHasFixedSize(true);
            rvMovie.setAdapter(adapter);
            itemTouchHelper.attachToRecyclerView(rvMovie);
        }

    }

    private void viewdata(int size) {
        if (size > 0) {
            rvMovie.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            LLview.setGravity(Gravity.TOP);
        } else {
            rvMovie.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            LLview.setGravity(Gravity.CENTER);
        }
    }

    @NonNull
    private static FavoritedTvshowViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavoritedTvshowViewModel.class);
    }

    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null) {
                int swipedPosition = viewHolder.getAdapterPosition();
                TvShowEntity tvShowEntity = adapter.getItemById(swipedPosition);
                viewModel.setFavorite(tvShowEntity);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> viewModel.setFavorite(tvShowEntity));
                snackbar.show();
            }
        }
    });

}
