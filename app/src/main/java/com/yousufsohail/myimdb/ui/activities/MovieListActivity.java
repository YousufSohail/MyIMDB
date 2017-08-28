package com.yousufsohail.myimdb.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yousufsohail.myimdb.R;
import com.yousufsohail.myimdb.service.ImdbService;
import com.yousufsohail.myimdb.service.ServiceGenerator;
import com.yousufsohail.myimdb.service.response.ResponseMoviesTopRated;
import com.yousufsohail.myimdb.ui.adapters.MovieListAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yousufsohail.myimdb.constant.ApiConstants.IMDB_API_KEY;

/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MovieListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        recyclerView = (RecyclerView) findViewById(R.id.movie_list);
        assert recyclerView != null;
        setupRecyclerView();

        if (findViewById(R.id.movie_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView() {
        fetchMoviesTop();
//        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
    }

    private void fetchMoviesTop() {
        ImdbService service = ServiceGenerator.getInstance().createService(ImdbService.class);
        Call<ResponseMoviesTopRated> movies = service.getMoviesTop(IMDB_API_KEY);
        movies.enqueue(new Callback<ResponseMoviesTopRated>() {
            @Override
            public void onResponse(Call<ResponseMoviesTopRated> call, Response<ResponseMoviesTopRated> response) {
                recyclerView.setAdapter(new MovieListAdapter(response.body().getMovies()));
            }

            @Override
            public void onFailure(Call<ResponseMoviesTopRated> call, Throwable t) {

            }
        });
    }
}
