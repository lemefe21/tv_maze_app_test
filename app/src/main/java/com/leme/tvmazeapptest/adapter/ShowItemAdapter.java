package com.leme.tvmazeapptest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leme.tvmazeapptest.R;
import com.leme.tvmazeapptest.model.entity.Show;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable.ImageParcelable;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.leme.tvmazeapptest.utils.AppValues.NO_IMAGE_URL;
import static com.leme.tvmazeapptest.utils.ShowUtils.appendGenresString;
import static com.leme.tvmazeapptest.utils.ShowUtils.setFavoritesInShowParcelableList;

public class ShowItemAdapter extends RecyclerView.Adapter<ShowItemAdapter.ShowItemViewHolder> {

    private final ShowItemAdapterOnClickHandle mClickHandle;
    private List<ShowParcelable> mShowList;
    private Context mContext;

    public ShowItemAdapter(Context context, ShowItemAdapterOnClickHandle clickHandle) {
        mContext = context;
        mClickHandle = clickHandle;
    }

    public interface ShowItemAdapterOnClickHandle {
        void onClick(ShowParcelable showClicked);
    }

    @NonNull
    @Override
    public ShowItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.show_list_item, viewGroup, false);
        return new ShowItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowItemViewHolder showItemViewHolder, int position) {
        ShowParcelable show = mShowList.get(position);
        ImageParcelable image = show.getImage();
        String imageUrl = (image == null) ? NO_IMAGE_URL : image.getMedium();

        Picasso.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.vintage_tv_2)
                .error(R.drawable.vintage_tv_2)
                .into(showItemViewHolder.mImageViewShowPoster);

        showItemViewHolder.mTextViewShowName.setText(show.getName());
        showItemViewHolder.mTextViewShowGenres.setText(appendGenresString(show.getGenres()));

        if(show.isFavorite()) {
            showItemViewHolder.mImageViewFavoriteShowItem.setVisibility(VISIBLE);
        } else {
            showItemViewHolder.mImageViewFavoriteShowItem.setVisibility(INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mShowList == null ? 0 : mShowList.size();
    }

    public void setListData(List<ShowParcelable> showParcelables, List<Show> favoriteShows) {
        mShowList = setFavoritesInShowParcelableList(showParcelables, favoriteShows);
        notifyDataSetChanged();
    }

    public void setUpdateListData(List<ShowParcelable> showParcelables) {
        mShowList = showParcelables;
    }

    public class ShowItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_main_show_poster)
        ImageView mImageViewShowPoster;

        @BindView(R.id.tv_main_show_name)
        TextView mTextViewShowName;

        @BindView(R.id.tv_main_show_genres)
        TextView mTextViewShowGenres;

        @BindView(R.id.iv_main_favorite_show_item)
        ImageView mImageViewFavoriteShowItem;

        public ShowItemViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ShowParcelable showAtPosition = mShowList.get(getAdapterPosition());
            mClickHandle.onClick(showAtPosition);
        }

    }

}
