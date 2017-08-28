package com.yousufsohail.myimdb.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yousufsohail.myimdb.R;
import com.yousufsohail.myimdb.entity.Movie;
import com.yousufsohail.myimdb.service.ImdbService;
import com.yousufsohail.myimdb.service.ServiceGenerator;
import com.yousufsohail.myimdb.service.response.ResponseMoviesTopRated;
import com.yousufsohail.myimdb.ui.fragments.MovieDetailFragment;

import java.util.ArrayList;
import java.util.List;

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
    MovieListAdapter mAdapter;
    ProgressBar progressBar;
    List<Movie> movies = new ArrayList<>();
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

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView = (RecyclerView) findViewById(R.id.movie_list);
        assert recyclerView != null;
        setDataInAdapter(movies);

        if (findViewById(R.id.movie_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshMovieList();
    }

    private void setDataInAdapter(List<Movie> movies) {
        if (mAdapter == null) {
            mAdapter = new MovieListAdapter(movies);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.updateList(movies);
        }
    }

    private void refreshMovieList() {

        showProgressBar();

        ImdbService service = ServiceGenerator.getInstance().createService(ImdbService.class);
        Call<ResponseMoviesTopRated> getMovies = service.getMoviesTop(IMDB_API_KEY);
        getMovies.enqueue(new Callback<ResponseMoviesTopRated>() {
            @Override
            public void onResponse(Call<ResponseMoviesTopRated> call, Response<ResponseMoviesTopRated> response) {
                hideProgressBar();
                ResponseMoviesTopRated body = response.body();

                if (!response.isSuccessful()) {
                    Toast.makeText(MovieListActivity.this, R.string.server_error, Toast.LENGTH_SHORT).show();
                } else if (body != null && body.getMovies() != null && body.getMovies().size() > 0) {

                    movies.clear();
                    movies.addAll(body.getMovies());
                    setDataInAdapter(movies);

                } else {
                    Toast.makeText(MovieListActivity.this, R.string.movie_not_found, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMoviesTopRated> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(MovieListActivity.this, R.string.went_wrong, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    //TODO: Adapter class can be moved to its own class

    class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

        private List<Movie> mValues;

        public MovieListAdapter(List<Movie> movies) {
            mValues = movies;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_list_content, parent, false);
            return new MovieListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getId());
            holder.mContentView.setText(mValues.get(position).getTitle());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(MovieDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
                        MovieDetailFragment fragment = new MovieDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.movie_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra(MovieDetailFragment.ARG_ITEM_ID, holder.mItem.getId());

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public void updateList(List<Movie> modelArrayList) {
            this.mValues = modelArrayList;
            this.notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mContentView;
            Movie mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
