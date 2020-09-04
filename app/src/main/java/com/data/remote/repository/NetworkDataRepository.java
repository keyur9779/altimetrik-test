package com.data.remote.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.data.local.dao.MusicDao;
import com.data.local.entity.MusicEntity;
import com.data.remote.ApiService;
import com.data.remote.NetworkBoundResource;
import com.data.remote.Resource;
import com.data.remote.model.MusicModel;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

import static com.common.Constants.df;

/**
 * The article repository which has access to local and remote data fetching services
 */
public class NetworkDataRepository {

    private final MusicDao articleDao;
    private final ApiService apiService;

    @Inject
    NetworkDataRepository(MusicDao dao, ApiService service) {
        this.articleDao = dao;
        this.apiService = service;
    }

    /**
     * This method fetches the popular articles from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     *
     * @param howfarback index indicating how far back
     * @return List of articles
     */
    public LiveData<Resource<List<MusicEntity>>> loadPopularArticles(int howfarback) {
        return new NetworkBoundResource<List<MusicEntity>, MusicModel>() {

            @Override
            protected void saveCallResult(MusicModel item) {
                if (null != item) {
                    //Collections.sort(item.getResults(), new ShopDateComparator());

                    articleDao.saveArticles(item.getResults());
                }
            }

            @NonNull
            @Override
            protected LiveData<List<MusicEntity>> loadFromDb() {

                LiveData<List<MusicEntity>> data = articleDao.loadMusicList();




                return data;
            }

            @NonNull
            @Override
            protected Call<MusicModel> createCall() {
                return apiService.loadMusicList("all");
            }
        }.getAsLiveData();
    }


    class ShopDateComparator implements Comparator<MusicEntity> {
        @Override
        public int compare(MusicEntity data1, MusicEntity data2) {
            Log.d("keyur", "mesage ");

            if (data1.getReleaseDate() == null || data2.getReleaseDate() == null) {
                return 0;
            }

            try {
                Date dateLeft = df.parse(data1.getReleaseDate());
                Date dateRight = df.parse(data2.getReleaseDate());

                if (dateLeft.after(dateRight)) {
                    return 1;
                } else {
                    return 0;
                }

            } catch (ParseException e) {
                Log.d("keyur", "mesage " + e.getMessage());
                return 0;
            }


        }
    }


}

