package com.common;

import com.data.local.entity.MusicEntity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShortCollections {


    public List<MusicEntity> getCollectionNam(List<MusicEntity> list) {

        Collections.sort(list, new Comparator<MusicEntity>() {
            @Override
            public int compare(MusicEntity u1, MusicEntity u2) {
                String left = u1.getCollectionName() == null ? "" : u1.getCollectionName();

                String right = u2.getCollectionName() == null ? "" : u2.getCollectionName();

                return left.compareTo(right);

            }
        });

        return list;
    }

    public List<MusicEntity> getTrackNam(List<MusicEntity> list) {

        Collections.sort(list, new Comparator<MusicEntity>() {
            @Override
            public int compare(MusicEntity u1, MusicEntity u2) {
                return u1.getTrackName().compareTo(u2.getTrackName());
            }
        });

        return list;
    }

    public List<MusicEntity> getArtistNam(List<MusicEntity> list) {

        Collections.sort(list, new Comparator<MusicEntity>() {
            @Override
            public int compare(MusicEntity u1, MusicEntity u2) {
                return u1.getArtistName().compareTo(u2.getArtistName());
            }
        });

        return list;
    }


    public List<MusicEntity> getPrice(List<MusicEntity> list) {

        Collections.sort(list, new Comparator<MusicEntity>() {
            @Override
            public int compare(MusicEntity u1, MusicEntity u2) {
                return u1.getCollectionPrice().compareTo(u2.getCollectionPrice());
            }
        });

        return list;
    }

}
