package com.yousufsohail.myimdb.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yousufsohail.myimdb.entity.Genres;
import com.yousufsohail.myimdb.entity.ProductionCompanies;
import com.yousufsohail.myimdb.entity.ProductionCountries;
import com.yousufsohail.myimdb.entity.SpokenLanguages;

/**
 * Created by yousuf on 28-Aug-17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMovieDetails {
    public String budget;

    public String vote_average;

    public String backdrop_path;

    public Genres[] genres;

    public String status;

    public String runtime;

    public SpokenLanguages[] spoken_languages;

    public String adult;

    public String homepage;

    public String id;

    public ProductionCountries[] production_countries;

    public String title;

    public String original_language;

    public String overview;

    public ProductionCompanies[] production_companies;

    public String belongs_to_collection;

    public String imdb_id;

    public String release_date;

    public String original_title;

    public String vote_count;

    public String poster_path;

    public String video;

    public String tagline;

    public String revenue;

    public String popularity;

    @Override
    public String toString() {
        return "ClassPojo [budget = " + budget + ", vote_average = " + vote_average + ", backdrop_path = " + backdrop_path + ", genres = " + genres + ", status = " + status + ", runtime = " + runtime + ", spoken_languages = " + spoken_languages + ", adult = " + adult + ", homepage = " + homepage + ", id = " + id + ", production_countries = " + production_countries + ", title = " + title + ", original_language = " + original_language + ", overview = " + overview + ", production_companies = " + production_companies + ", belongs_to_collection = " + belongs_to_collection + ", imdb_id = " + imdb_id + ", release_date = " + release_date + ", original_title = " + original_title + ", vote_count = " + vote_count + ", poster_path = " + poster_path + ", video = " + video + ", tagline = " + tagline + ", revenue = " + revenue + ", popularity = " + popularity + "]";
    }
}