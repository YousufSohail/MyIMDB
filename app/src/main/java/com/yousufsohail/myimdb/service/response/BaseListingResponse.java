package com.yousufsohail.myimdb.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yousuf on 28-Aug-17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseListingResponse extends BaseResponse {

    public int page;

    public int total_pages;

    public int total_results;
}
