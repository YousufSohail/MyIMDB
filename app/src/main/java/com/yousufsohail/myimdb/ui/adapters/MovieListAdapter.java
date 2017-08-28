package com.yousufsohail.myimdb.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yousufsohail.myimdb.R;
import com.yousufsohail.myimdb.dummy.DummyContent;
import com.yousufsohail.myimdb.entity.Movie;

import java.util.ArrayList;

/**
 * Created by yousuf on 28-Aug-17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private final ArrayList<Movie> mValues;

    public MovieListAdapter(ArrayList<Movie> movies) {
        mValues = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
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
