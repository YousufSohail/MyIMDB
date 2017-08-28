package com.yousufsohail.myimdb.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
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
import com.yousufsohail.myimdb.service.response.ResponseMovieDetails;
import com.yousufsohail.myimdb.ui.activities.MovieDetailActivity;
import com.yousufsohail.myimdb.ui.activities.MovieListActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yousufsohail.myimdb.constant.ApiConstants.IMDB_API_KEY;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_TITLE = "item_title";

    String movieId;
    TextView tvDetails;
    ProgressBar progressBar;
    private Movie mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            movieId = getArguments().getString(ARG_ITEM_ID);
            String movieTitle = "" + getArguments().getString(ARG_ITEM_TITLE);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(movieTitle);
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);
        tvDetails = (TextView) rootView.findViewById(R.id.movie_detail);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBarDetails);
        getMovieDetails(movieId);
        return rootView;
    }

    private void getMovieDetails(String movieId) {

        showProgressBar();

        ImdbService service = ServiceGenerator.getInstance().createService(ImdbService.class);
        Call<ResponseMovieDetails> getMovieDetails = service.getMovieDetails(movieId, IMDB_API_KEY);
        getMovieDetails.enqueue(new Callback<ResponseMovieDetails>() {
            @Override
            public void onResponse(Call<ResponseMovieDetails> call, Response<ResponseMovieDetails> response) {
                hideProgressBar();

                ResponseMovieDetails body = response.body();
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), R.string.server_error, Toast.LENGTH_SHORT).show();
                } else if (body != null) {
                    tvDetails.setText(body.overview);
                } else {
                    Toast.makeText(getActivity(), R.string.movie_not_found, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMovieDetails> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(getActivity(), R.string.went_wrong, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
