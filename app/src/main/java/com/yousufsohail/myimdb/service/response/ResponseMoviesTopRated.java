package com.yousufsohail.myimdb.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yousufsohail.myimdb.entity.Movie;

import java.util.ArrayList;

/**
 * Created by yousuf on 28-Aug-17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMoviesTopRated extends BaseListingResponse {

    private ArrayList<Movie> results;

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    public ArrayList<Movie> getMovies() {
        return results;
    }
}
