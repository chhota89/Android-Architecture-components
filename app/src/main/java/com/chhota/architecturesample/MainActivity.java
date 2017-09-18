package com.chhota.architecturesample;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel mainViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView message = (TextView) findViewById(R.id.message);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getUser(false).observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                int a=0;
                Log.d(TAG, "onChanged() called with: users = [" + users + "]");
                message.setText(users.toString());
            }
        });

    }

    @Override
    public void onRefresh() {
        mainViewModel.forceRefreshData();
        swipeRefreshLayout.setRefreshing(false);
    }
}
