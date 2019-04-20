package com.leme.tvmazeapptest.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.leme.tvmazeapptest.R;
import com.leme.tvmazeapptest.model.parcelable.ShowParcelable;
import com.leme.tvmazeapptest.view.fragment.ListShowFragment;
import com.leme.tvmazeapptest.view.fragment.ShowDetailFragment;

import static com.leme.tvmazeapptest.utils.AppValues.PARAMS_TO_FRAGMENT;

public class MainFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.initial_frame, new ListShowFragment());
        tx.replace(R.id.detail_frame, new ShowDetailFragment());
        tx.commit();
    }

    public void getShowClicked(ShowParcelable showClicked) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ShowDetailFragment detailFragment =
                (ShowDetailFragment) fragmentManager.findFragmentById(R.id.detail_frame);

        detailFragment.setShowDetailData(showClicked);
    }
}
