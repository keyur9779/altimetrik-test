package com.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.common.ShortCollections;
import com.data.local.entity.MusicEntity;
import com.music.app.R;
import com.music.app.databinding.ItemMusicBinding;
import com.view.base.BaseAdapter;
import com.view.callbacks.MusicListCallback;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.common.Constants.df;

/**
 * This class represents the Article list recyclerview adapter
 */
public class MusicListAdapter extends BaseAdapter<MusicListAdapter.MusicViewHolder, MusicEntity>
        implements Filterable {

    private List<MusicEntity> articleEntities;

    private List<MusicEntity> articleEntitiesFiltered;

    private final MusicListCallback articleListCallback;

    public MusicListAdapter(@NonNull MusicListCallback articleListCallback) {
        articleEntities = new ArrayList<>();
        articleEntitiesFiltered = new ArrayList<>();
        this.articleListCallback = articleListCallback;
    }

    @Override
    public void setData(List<MusicEntity> entities) {
        this.articleEntities = entities;
        this.articleEntitiesFiltered = entities;


        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return MusicViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, articleListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder viewHolder, int i) {
        viewHolder.onBind(articleEntitiesFiltered.get(i));
    }

    @Override
    public int getItemCount() {
        if (articleEntitiesFiltered == null) {
            return 0;
        }
        return articleEntitiesFiltered.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                Log.d("keyur ", "search result := " + charString);

                if (charString.isEmpty()) {
                    articleEntitiesFiltered = articleEntities;
                } else {
                    List<MusicEntity> filteredList = new ArrayList<>();
                    for (MusicEntity row : articleEntities) {

                        String artisName = row.getArtistName() == null ? "" : row.getArtistName().toLowerCase();
                        String CollectionName = row.getArtistName() == null ? "" : row.getArtistName().toLowerCase();
                        String TrackName = row.getArtistName() == null ? "" : row.getArtistName().toLowerCase();

                        if (artisName.contains(charString.toLowerCase())
                                || CollectionName.toLowerCase().contains(charString.toLowerCase())
                                || TrackName.toLowerCase().contains(charString.toLowerCase())) {

                            filteredList.add(row);
                        }
                    }

                    articleEntitiesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = articleEntitiesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                articleEntitiesFiltered = (ArrayList<MusicEntity>) filterResults.values;
                Log.d("keyur ", "search result := ");
                notifyDataSetChanged();
            }
        };
    }

    public void sortList(int which) {

        ShortCollections shortCollections = new ShortCollections();

        switch (which) {
            case 0:
                shortCollections.getCollectionNam(articleEntities);
                break;
            case 1:
                shortCollections.getTrackNam(articleEntities);
                break;
            case 2:
                shortCollections.getPrice(articleEntities);
                break;
            default:
                shortCollections.getArtistNam(articleEntities);

        }

        notifyDataSetChanged();
    }

    static class MusicViewHolder extends RecyclerView.ViewHolder {

        private static MusicViewHolder create(LayoutInflater inflater, ViewGroup parent, MusicListCallback callback) {
            ItemMusicBinding itemMovieListBinding = ItemMusicBinding.inflate(inflater, parent, false);
            return new MusicViewHolder(itemMovieListBinding, callback);
        }

        final ItemMusicBinding binding;

        private MusicViewHolder(ItemMusicBinding binding, MusicListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onArticleClicked(binding.getArticle()));
        }

        private void onBind(MusicEntity articleEntity) {
            binding.setArticle(articleEntity);

            binding.imageView.clearColorFilter();

            Glide
                    .with(binding.imageView)
                    .load(articleEntity.getArtworkUrl100())
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.imageView);


            binding.executePendingBindings();
        }
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
                    return 0;
                } else {
                    return 1;
                }

            } catch (ParseException e) {
                Log.d("keyur", "mesage " + e.getMessage());
                return 0;
            }


        }
    }

}
