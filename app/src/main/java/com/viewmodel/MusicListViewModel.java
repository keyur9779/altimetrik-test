package com.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.data.local.entity.MusicEntity;
import com.data.remote.Resource;
import com.data.remote.repository.NetworkDataRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Article List view model
 * <p>
 */
public class MusicListViewModel extends ViewModel {
    private final LiveData<Resource<List<MusicEntity>>> popularArticles;

    @Inject
    public MusicListViewModel(NetworkDataRepository articleRepository) {
        popularArticles = articleRepository.loadPopularArticles(7);
    }

    public LiveData<Resource<List<MusicEntity>>> getPopularArticles() {



        return popularArticles;
    }
}
