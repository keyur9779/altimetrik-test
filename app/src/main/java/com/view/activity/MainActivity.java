package com.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.music.app.R;
import com.music.app.databinding.ActivityMainBinding;
import com.utils.FragmentUtils;
import com.view.base.BaseActivity;
import com.view.fragment.MusicListFragment;

import static com.utils.FragmentUtils.TRANSITION_NONE;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, MusicListFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
