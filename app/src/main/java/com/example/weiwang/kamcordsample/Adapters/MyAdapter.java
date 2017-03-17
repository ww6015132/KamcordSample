package com.example.weiwang.kamcordsample.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.weiwang.kamcordsample.Models.CardModel;
import com.example.weiwang.kamcordsample.Models.ErrorModel;
import com.example.weiwang.kamcordsample.Mvp.MainPresenter;
import com.example.weiwang.kamcordsample.R;
import com.example.weiwang.kamcordsample.Utils.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<CardModel> cardModels;
    private OnListItemClick mListener;

    public MyAdapter(Context context, List<CardModel> cardModels, MainPresenter mainPresenter) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.cardModels = cardModels;
        mListener = (OnListItemClick) context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = layoutInflater.inflate(R.layout.item_video, parent, false);      //Inflates the view for single item in the recycler view.
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, final int position) {
        Picasso.with(context).load(cardModels.get(position).getShotCardData().getShotThumbnail().getMedium()).into(itemViewHolder.mImageView);
        itemViewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position);
            }
        });

        if (cardModels.size() - position == 3) {
            //get booked trips.
            if (Util.isNetworkAvailable(context)) {
                mListener.loadNextPage();
            }
        }
    }

    @Override
    public int getItemCount() {
        return cardModels.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.thumbnail_item);
        }

    }

    public interface OnListItemClick {
        void onItemClick(int i);

        void loadNextPage();
    }
}
