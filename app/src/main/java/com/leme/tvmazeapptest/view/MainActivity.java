package com.leme.tvmazeapptest.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leme.tvmazeapptest.R;
import com.leme.tvmazeapptest.contract.MainContract;
import com.leme.tvmazeapptest.model.Show;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecyclerView(List<Show> shows) {

    }

    @Override
    public void showError(int error) {

    }
}
