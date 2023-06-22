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

public class AdapterListFood extends RecyclerView.Adapter<AdapterListFood.CategoryViewHolder> {
    private Context context;
    private ArrayList<Food> listFood;

    private ArrayList<Food> getListFood() { return listFood; }

    public void setListFood(ArrayList<Food> listFood) {
        this.listFood = listFood;
    }

    public AdapterListFood(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row_food, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.tvName.setText(getListFood().get(i).getName());
        categoryViewHolder.tvRemarks.setText(getListFood().get(i).getRemarks());

        Glide.with(context)
                .load(getListFood().get(i).getPhoto())
                .apply(new RequestOptions().override(110,110))
                .into(categoryViewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getListFood().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvRemarks;
        ImageView imgPhoto;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
