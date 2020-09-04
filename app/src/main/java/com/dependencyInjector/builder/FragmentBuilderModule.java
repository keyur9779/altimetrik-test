package com.dependencyInjector.builder;

import com.view.fragment.MusicListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This builder provides android injector service to fragments
 *
 *
 *
 *
 */
@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract MusicListFragment contributeArticleListFragment();


}
