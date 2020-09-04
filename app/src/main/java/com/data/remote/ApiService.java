package com.data.remote;

import com.data.remote.model.MusicModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The APIService interface which will contain the semantics of all the REST calls.
 *
 *
 *
 *
 */
public interface ApiService {

    @GET("search")
    Call<MusicModel> loadMusicList(@Query("term") String query);
}
