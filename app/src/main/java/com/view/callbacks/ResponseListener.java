package com.view.callbacks;

import com.data.local.entity.MusicEntity;

public interface ResponseListener {

    void onSuccess(MusicEntity data);
    void onFailure(String message);
}
