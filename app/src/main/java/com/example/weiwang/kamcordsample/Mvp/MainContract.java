package com.example.weiwang.kamcordsample.Mvp;


import com.example.weiwang.kamcordsample.Models.ErrorModel;
import com.example.weiwang.kamcordsample.Models.GroupModel;
import com.example.weiwang.kamcordsample.Models.ResponseModel;

public class MainContract {
    public interface View {
        void updateView(GroupModel groupModel);


        void dismissProgressDialog();

        void playVideo(int position);
    }

    public interface Presenter {
        void loadData(int count);

        void loadNextPage(int count);
    }
}
