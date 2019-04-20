package com.leme.tvmazeapptest.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leme.tvmazeapptest.R;
import com.leme.tvmazeapptest.contract.ShowDetailContract;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.presenter.ShowDetailPresenter;
import com.leme.tvmazeapptest.utils.AppValues;
import com.leme.tvmazeapptest.utils.ShowUtils;
import com.leme.tvmazeapptest.view.ShowDetailActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.leme.tvmazeapptest.utils.AppValues.NO_IMAGE_URL;
import static com.leme.tvmazeapptest.utils.AppValues.PARAMS_TO_FRAGMENT;

public class ShowDetailFragment extends Fragment implements ShowDetailContract.View {

    private ShowDetailPresenter mDetailPresenter;
    private boolean mShowUpdatedStatus;

    @BindView(R.id.view_detail_background_favorite)
    View mViewFavorite;

    @BindView(R.id.iv_detail_show_background_poster)
    ImageView mImageViewShowBackgroundPoster;

    @BindView(R.id.iv_detail_show_poster)
    ImageView mImageViewShowPoster;

    @BindView(R.id.iv_detail_icon_favorite_show)
    ImageView mImageViewIconFavorite;

    @BindView(R.id.tv_detail_show_name)
    TextView mTextViewShowName;

    @BindView(R.id.tv_detail_show_genres)
    TextView mTextViewShowGenres;

    @BindView(R.id.tv_detail_show_summary)
    TextView mTextViewShowSummary;

    @BindView(R.id.tv_detail_show_premiered)
    TextView mTextViewShowPremiered;

    public ShowDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_show, container, false);

        ButterKnife.bind(this, view);

        mDetailPresenter = new ShowDetailPresenter(this);

        mViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFavoriteShow();
            }
        });

        return view;
    }

    private void getBundleFragment() {

        Bundle params = getArguments();
        if(params != null) {
            ShowParcelable showParcelable = params.getParcelable(PARAMS_TO_FRAGMENT);
            mDetailPresenter.getFragmentBundle(showParcelable);
        }

    }

    @Override
    public void setShowDetailData(ShowParcelable show) {
        mDetailPresenter.getFragmentBundle(show);

        ShowParcelable.ImageParcelable image = show.getImage();
        String imageUrl = NO_IMAGE_URL;
        if(image != null) {
            imageUrl = image.getMedium();
        }

        Picasso.with(getContext())
                .load(imageUrl)
                .placeholder(R.drawable.vintage_tv_2)
                .error(R.drawable.vintage_tv_2)
                .into(mImageViewShowBackgroundPoster);

        Picasso.with(getContext())
                .load(imageUrl)
                .placeholder(R.drawable.vintage_tv_2)
                .error(R.drawable.vintage_tv_2)
                .into(mImageViewShowPoster);

        mTextViewShowName.setText(show.getName());
        mTextViewShowGenres.setText(ShowUtils.appendGenresString(show.getGenres()));
        mTextViewShowSummary.setText(Html.fromHtml(show.getSummary()));
        mTextViewShowPremiered.setText(show.getPremiered());

        setFavoriteIcon(show.isFavorite());
    }

    @Override
    public void favoriteShowIcon() {
        mImageViewIconFavorite.setImageResource(R.drawable.ic_favorite_on);
    }

    @Override
    public void disfavorShowIcon() {
        mImageViewIconFavorite.setImageResource(R.drawable.ic_favorite_off);
    }

    private void setFavoriteIcon(boolean isFavorite) {
        if(isFavorite) {
            favoriteShowIcon();
        } else {
            disfavorShowIcon();
        }
    }

    private void updateFavoriteShow() {
        class UpdateTask extends AsyncTask<Void, Void, Boolean> {

            @Override
            protected Boolean doInBackground(Void... voids) {
                return mDetailPresenter.favoriteShowFragment(ShowDetailFragment.this);
            }

            @Override
            protected void onPostExecute(Boolean favorite) {
                super.onPostExecute(favorite);
                setFavoriteIcon(favorite);
                mShowUpdatedStatus = true;
            }
        }

        UpdateTask updateTask = new UpdateTask();
        updateTask.execute();
    }

}
