package com.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.data.local.dao.MusicDao;
import com.data.local.entity.MusicEntity;

/**
 * File Description
 * <p>
 *
 *
 *
 *
 */
@Database(entities = {MusicEntity.class}, version = 1, exportSchema = false)
public abstract class MusicDatabase extends RoomDatabase {
    public abstract MusicDao articleDao();
}