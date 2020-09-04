package com.view.fragment;


import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.data.local.entity.MusicEntity;
import com.data.remote.Status;
import com.music.app.R;
import com.music.app.databinding.FragmentListMusicBinding;
import com.view.adapter.MusicListAdapter;
import com.view.base.BaseFragment;
import com.view.callbacks.MusicListCallback;
import com.viewmodel.MusicListViewModel;

import java.util.Collections;


/**
 * The article list fragment which will list the popular articles
 */
public class MusicListFragment extends BaseFragment<MusicListViewModel, FragmentListMusicBinding> implements MusicListCallback {

    private MusicListAdapter musicAdapter;

    public static MusicListFragment newInstance() {
        Bundle args = new Bundle();
        MusicListFragment fragment = new MusicListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<MusicListViewModel> getViewModel() {
        return MusicListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list_music;
    }

    @Override
    public void onArticleClicked(MusicEntity articleEntity) {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        musicAdapter = new MusicListAdapter(this);
        dataBinding.recyclerView.setAdapter(musicAdapter);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        viewModel.getPopularArticles()
                .observe(this, listResource -> {
                    if (null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)) {
                        dataBinding.loginProgress.setVisibility(View.GONE);
                    }

                    if (listResource.data != null) {
                        Collections.sort(listResource.data);
                    }

                    dataBinding.setResource(listResource);

                    // If the cached data is already showing then no need to show the error
                    if (null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0) {
                        dataBinding.errorText.setVisibility(View.GONE);
                    }
                });

        dataBinding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] list = getResources().getStringArray(R.array.filter);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select a text size");
                builder.setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        musicAdapter.sortList(which);



                    }
                });
                builder.show();
            }
        });

    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (null == getActivity())
            return;

        SearchView searchView;
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();

        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                if (null != dataBinding.recyclerView.getAdapter()) {
                    ((MusicListAdapter) dataBinding.recyclerView.getAdapter()).getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                if (null != dataBinding.recyclerView.getAdapter()) {
                    ((MusicListAdapter) dataBinding.recyclerView.getAdapter()).getFilter().filter(query);
                }
                return false;
            }


        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
