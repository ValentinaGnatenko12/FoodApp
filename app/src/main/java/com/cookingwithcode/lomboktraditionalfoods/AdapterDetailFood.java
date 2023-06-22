package com.cookingwithcode.lomboktraditionalfoods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AdapterDetailFood extends RecyclerView.Adapter<AdapterDetailFood.DetailViewHolder> {
    private Context context;
    private ArrayList<Food> list = new ArrayList<>();

    private ArrayList<Food> getListFoodDetail() {
        return list;
    }

    public void setListFoodDetail(ArrayList<Food> list) {
        this.list = list;
    }

    public AdapterDetailFood(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemDetail = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_detail_view, viewGroup, false);
        return new DetailViewHolder(itemDetail);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int i) {
        Food f = getListFoodDetail().get(i);
        Glide.with(context)
                .load(f.getPhoto())
                .apply(new RequestOptions())
                .into(detailViewHolder.imgPhoto);
        detailViewHolder.tvContent.setText(f.getContent());
    }

    @Override
    public int getItemCount() {
        return getListFoodDetail().size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvContent;
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvContent = itemView.findViewById(R.id.tv_item_content);
        }
    }
}
