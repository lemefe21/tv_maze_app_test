package com.leme.tvmazeapptest.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leme.tvmazeapptest.R;
import com.leme.tvmazeapptest.contract.ShowDetailContract;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable.ImageParcelable;
import com.leme.tvmazeapptest.presenter.ShowDetailPresenter;
import com.leme.tvmazeapptest.utils.ShowUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.leme.tvmazeapptest.utils.AppValues.EXTRA_RESULT_DETAIL_KEY;
import static com.leme.tvmazeapptest.utils.AppValues.NO_IMAGE_URL;

public class ShowDetailActivity extends AppCompatActivity implements ShowDetailContract.View {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initUi();
    }

    private void initUi() {
        ButterKnife.bind(this);

        mDetailPresenter = new ShowDetailPresenter(this);
        mDetailPresenter.getIntentExtras(getIntent());

        mViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFavoriteShow();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void finish() {
        if(mShowUpdatedStatus) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(EXTRA_RESULT_DETAIL_KEY, mDetailPresenter.getShowUpdate());
            setResult(RESULT_OK, returnIntent);
        }
        super.finish();
    }

    @Override
    public void setShowDetailData(ShowParcelable show) {
        ImageParcelable image = show.getImage();
        String imageUrl = NO_IMAGE_URL;
        if(image != null) {
            imageUrl = image.getMedium();
        }

        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.default_poster)
                .error(R.drawable.default_poster)
                .into(mImageViewShowBackgroundPoster);

        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.default_poster)
                .error(R.drawable.default_poster)
                .into(mImageViewShowPoster);

        mTextViewShowName.setText(show.getName());
        mTextViewShowGenres.setText(ShowUtils.appendGenresString(show.getGenres()));
        mTextViewShowSummary.setText(Html.fromHtml(show.getSummary()));
        mTextViewShowPremiered.setText(show.getPremiered());

        setFavoriteIcon(show.isFavorite());
    }

    private void setFavoriteIcon(boolean isFavorite) {
        if(isFavorite) {
            favoriteShowIcon();
        } else {
            disfavorShowIcon();
        }
    }

    @Override
    public void favoriteShowIcon() {
        mImageViewIconFavorite.setImageResource(R.drawable.ic_favorite_on);
    }

    @Override
    public void disfavorShowIcon() {
        mImageViewIconFavorite.setImageResource(R.drawable.ic_favorite_off);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDetailPresenter.onDestroy();
    }

    private void updateFavoriteShow() {
        class UpdateTask extends AsyncTask<Void, Void, Boolean> {

            @Override
            protected Boolean doInBackground(Void... voids) {
                return mDetailPresenter.favoriteShow(ShowDetailActivity.this);
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
