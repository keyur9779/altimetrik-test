package com.dependencyInjector.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.viewmodel.MusicListViewModel;
import com.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Alloes us to inject dependencies via constructor injection
 * <p>
 *
 *
 *
 *
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MusicListViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsArticleListViewModel(MusicListViewModel articleListViewModel);



    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
