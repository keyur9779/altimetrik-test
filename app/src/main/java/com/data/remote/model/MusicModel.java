package com.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.data.local.entity.MusicEntity;

import java.util.List;

/**
 * The model class which holds the top popular articles data
 */
public class MusicModel {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<MusicEntity> results = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<MusicEntity> getResults() {
        return results;
    }

    public void setResults(List<MusicEntity> results) {
        this.results = results;
    }

}
