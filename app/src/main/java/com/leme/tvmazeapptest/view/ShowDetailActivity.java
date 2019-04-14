package com.leme.tvmazeapptest.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leme.tvmazeapptest.R;
import com.leme.tvmazeapptest.contract.ShowDetailContract;
import com.leme.tvmazeapptest.model.Image;
import com.leme.tvmazeapptest.model.Show;
import com.leme.tvmazeapptest.presenter.ShowDetailPresenter;
import com.leme.tvmazeapptest.utils.ShowUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDetailActivity extends AppCompatActivity implements ShowDetailContract.View {

    private ShowDetailPresenter mDetailPresenter;

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
                mDetailPresenter.favoriteShow(ShowDetailActivity.this);
            }
        });

    }

    @Override
    public void updateFavoriteIconState(int resource) {
        mImageViewIconFavorite.setImageResource(resource);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void setShowDetailData(Show show) {

        //mImageViewIconFavorite

        Image image = show.getImage();
        String imageUrl = "no_image";
        if(image != null) {
            imageUrl = image.getMedium();
        }

        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.vintage_tv_2)
                .error(R.drawable.vintage_tv_2)
                .into(mImageViewShowBackgroundPoster);

        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.vintage_tv_2)
                .error(R.drawable.vintage_tv_2)
                .into(mImageViewShowPoster);

        mTextViewShowName.setText(show.getName());
        mTextViewShowGenres.setText(ShowUtils.AppendGenresString(show.getGenres()));
        mTextViewShowSummary.setText(show.getSummary());
        mTextViewShowPremiered.setText(show.getPremiered());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDetailPresenter.onDestroy();
    }
}
