package com.example.weiwang.kamcordsample.Mvp;


import android.util.Log;

import com.example.weiwang.kamcordsample.Models.GroupModel;
import com.example.weiwang.kamcordsample.Models.ResponseModel;
import com.example.weiwang.kamcordsample.Retrofit.RetrofitInterface;
import com.example.weiwang.kamcordsample.Retrofit.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private Callback<ResponseModel> mCallback;
    private Callback<GroupModel> mPageCallback;

    private String mKey;
    private String mFeedId;

    public MainPresenter(final MainContract.View view) {
        this.mView = view;

    }


    @Override
    public void loadData(int count) {
        mCallback = new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                mView.dismissProgressDialog();
                if (response.code() == 200) {
                    ResponseModel responseModel = response.body();
                    if (responseModel.getGroups() != null)
                        mView.updateView(responseModel.getGroups().get(0));
                    if (responseModel.getGroups() != null && responseModel.getGroups().size() != 0) {
                        mKey = responseModel.getGroups().get(0).getNextPage();
                        mFeedId = responseModel.getGroups().get(0).getFeedId();
                    }
                }
            }


            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                mView.dismissProgressDialog();
                Log.d("result", t.toString());
            }
        };
        Call<ResponseModel> call = RetrofitUtil.getRetrofitInstance().getFeed(count);
        call.enqueue(mCallback);
    }

    @Override
    public void loadNextPage(int count) {
        mPageCallback = new Callback<GroupModel>() {
            @Override
            public void onResponse(Call<GroupModel> call, Response<GroupModel> response) {
                if (response.code() == 200) {
                    GroupModel groupModel = response.body();
                    if (groupModel.getCards() != null)
                        mView.updateView(groupModel);
                    if (groupModel != null) {
                        mKey = groupModel.getNextPage();
                        mFeedId = groupModel.getFeedId();
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupModel> call, Throwable t) {

            }
        };

        Call<GroupModel> call = RetrofitUtil.getRetrofitInstance().getPagenationFeed(mFeedId, count, mKey);
        call.enqueue(mPageCallback);
    }


}
