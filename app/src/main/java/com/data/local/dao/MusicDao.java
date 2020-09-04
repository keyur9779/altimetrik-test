package com.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.data.local.entity.MusicEntity;

import java.util.List;

/**
 * File Description
 * <p>
 */
@Dao
public interface MusicDao {
    @Query("SELECT * FROM MusicEntity")
    LiveData<List<MusicEntity>> loadMusicList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveArticles(List<MusicEntity> articleEntities);

}
