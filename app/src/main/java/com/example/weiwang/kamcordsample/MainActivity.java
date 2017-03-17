package com.example.weiwang.kamcordsample;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.weiwang.kamcordsample.Adapters.MyAdapter;
import com.example.weiwang.kamcordsample.Models.CardModel;
import com.example.weiwang.kamcordsample.Models.GroupModel;
import com.example.weiwang.kamcordsample.Models.ParcelModel;
import com.example.weiwang.kamcordsample.Models.ResponseModel;
import com.example.weiwang.kamcordsample.Mvp.MainContract;
import com.example.weiwang.kamcordsample.Mvp.MainPresenter;
import com.example.weiwang.kamcordsample.Utils.Util;

import java.util.ArrayList;
import java.util.List;


/**
Hi,
 In this project, I used MVP structure for this app.
 I use Retrofit 2.0 for the web service communication and picasso for the image cache.
I also implement pagination and I'm following the google material design for the UI part(image size, margin, padding, etc.).
On large screen it would show 5 items per row and on smaller screen it would show 3 items per row.

 I tried to make this app functions the same with the one on Play Store, Like video switching and some other small things.

I was about to use RxJava and Dagger 2. But on the specific it says "Refrain to use third party libraries" so I didn't use them.

 Thanks for taking the time to read my code! Have a nice day!

 -- Wei Wang

*/
public class MainActivity extends AppCompatActivity implements MainContract.View, MyAdapter.OnListItemClick {

    private MainPresenter mMainPresenter;
    private int mFeedCount = 20;
    private List<CardModel> mCardModels;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static final String INTENT_POSITION = "position";
    public static final String INTENT_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

        if (Util.isNetworkAvailable(MainActivity.this))
            mMainPresenter.loadData(mFeedCount);
    }

    @Override
    public void updateView(GroupModel groupModel) {
        mCardModels.addAll(groupModel.getCards());
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void dismissProgressDialog() {
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void playVideo(int position) {

        ArrayList<ParcelModel> parcelModels = new ArrayList<>();

        for (CardModel cardModel : mCardModels) {
            parcelModels.add(new ParcelModel(cardModel.getShotCardData().getHeartCount(), cardModel.getShotCardData().getPlay().getMp4()));
        }
        Intent mVideoIntent = new Intent(MainActivity.this, VideoActivity.class);
        mVideoIntent.putParcelableArrayListExtra(INTENT_DATA, parcelModels);
        mVideoIntent.putExtra(INTENT_POSITION, position);
        startActivity(mVideoIntent);
    }

    private void initial() {


        mRecyclerView = (RecyclerView) findViewById(R.id.rc_main);
        mCardModels = new ArrayList<>();
        mMainPresenter = new MainPresenter(MainActivity.this);

        mAdapter = new MyAdapter(MainActivity.this, mCardModels, mMainPresenter);

        mRecyclerView.setLayoutManager(Util.createLayoutManager(MainActivity.this));
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.sl_main);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCardModels.clear();
                mMainPresenter.loadData(mFeedCount);
            }
        });
    }

    @Override
    public void onItemClick(int i) {
        playVideo(i);
    }

    @Override
    public void loadNextPage() {
        if (Util.isNetworkAvailable(MainActivity.this))
            mMainPresenter.loadNextPage(mFeedCount);
    }
}
