package com.leme.tvmazeapptest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leme.tvmazeapptest.R;
import com.leme.tvmazeapptest.model.Image;
import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.model.UserResponse;
import com.leme.tvmazeapptest.utils.ShowUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowItemAdapter extends RecyclerView.Adapter<ShowItemAdapter.ShowItemViewHolder> {

    private List<UserResponse> mShowList;
    private List<Show> mFavoritedShows;
    private final ShowItemAdapterOnClickHandle mClickHandle;
    private Context mContext;

    public ShowItemAdapter(Context context, ShowItemAdapterOnClickHandle clickHandle) {
        mContext = context;
        mClickHandle = clickHandle;
    }

    public interface ShowItemAdapterOnClickHandle {
        void onClick(Show showClicked);
    }

    @NonNull
    @Override
    public ShowItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_list_item, viewGroup, false);
        return new ShowItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowItemViewHolder showItemViewHolder, int position) {
        Log.i("mFavoritedShowsList", String.valueOf(mFavoritedShows.size()));

        Show show = mShowList.get(position).getShow();
        Image image = show.getImage();

        String imageUrl = "no_image";
        if(image != null) {
            imageUrl = image.getMedium();
        }

        Picasso.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.vintage_tv_2)
                .error(R.drawable.vintage_tv_2)
                .into(showItemViewHolder.mImageViewShowPoster);

        showItemViewHolder.mTextViewShowName.setText(show.getName());
        showItemViewHolder.mTextViewShowGenres.setText(ShowUtils.AppendGenresString(show.getGenres()));
    }

    @Override
    public int getItemCount() {
        if(mShowList == null) return 0;
        return mShowList.size();
    }

    public void setListData(List<UserResponse> response, List<Show> favoritedShows) {
        mShowList = response;
        mFavoritedShows = favoritedShows;
        notifyDataSetChanged();
    }

    public class ShowItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_main_show_poster)
        ImageView mImageViewShowPoster;

        @BindView(R.id.tv_main_show_name)
        TextView mTextViewShowName;

        @BindView(R.id.tv_main_show_genres)
        TextView mTextViewShowGenres;

        public ShowItemViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Show showAtPosition = mShowList.get(getAdapterPosition()).getShow();
            mClickHandle.onClick(showAtPosition);
        }

    }

}
