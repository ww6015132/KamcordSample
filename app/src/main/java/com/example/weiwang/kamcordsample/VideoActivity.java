package com.example.weiwang.kamcordsample;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.weiwang.kamcordsample.Models.ParcelModel;
import com.example.weiwang.kamcordsample.Utils.SingleToast;
import com.example.weiwang.kamcordsample.Utils.Util;

import java.util.ArrayList;

/**
 * Created by weiwang on 3/16/17.
 */

public class VideoActivity extends AppCompatActivity implements MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    private VideoView mVideoView;
    private Uri mUri;
    private boolean mPrepared;
    private TextView mTextView;
    private int mPosition;
    private ArrayList<ParcelModel> mParcelModels;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mPosition = getIntent().getIntExtra(MainActivity.INTENT_POSITION, 0);
        mParcelModels = getIntent().getParcelableArrayListExtra(MainActivity.INTENT_DATA);

        initialVideoView();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mVideoView.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        this.finish();
        return false;
    }

    public void onStart() {
        super.onStart();

        // Play Video
        mVideoView.setVideoURI(mUri);

        if (mPrepared && !mVideoView.isPlaying() && Util.isNetworkAvailable(VideoActivity.this))
            mVideoView.start();

    }


    public void onPause() {
        mVideoView.stopPlayback();
        super.onPause();
    }

    private void initialVideoView() {
        mVideoView = (VideoView) findViewById(R.id.video_view);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnErrorListener(this);

        mUri = Uri.parse(mParcelModels.get(mPosition).getUrl());

        mTextView = (TextView) findViewById(R.id.heart_video);
        mTextView.setText(String.valueOf(mParcelModels.get(mPosition).getCount()));

        mFab = (FloatingActionButton) findViewById(R.id.fab_video);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleToast.show(VideoActivity.this, "I like it!", Toast.LENGTH_SHORT);
            }
        });

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mPrepared = true;
                if (!mVideoView.isPlaying() && Util.isNetworkAvailable(VideoActivity.this))
                    mVideoView.start();
            }
        });

        mVideoView.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mVideoView.stopPlayback();
                if (mPosition == mParcelModels.size() - 1) {
                    mPosition = 0;
                } else {
                    mPosition++;
                }
                Log.d("mPosition", mPosition + "");
                mTextView.setText(String.valueOf(mParcelModels.get(mPosition).getCount()));


                mUri = Uri.parse(mParcelModels.get(mPosition).getUrl());
                mVideoView.setVideoURI(mUri);
                if (Util.isNetworkAvailable(VideoActivity.this)) ;
                mVideoView.start();
                return false;
            }
        });
    }
}
