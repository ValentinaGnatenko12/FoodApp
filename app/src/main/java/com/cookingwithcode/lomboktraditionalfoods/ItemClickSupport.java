package com.cookingwithcode.lomboktraditionalfoods;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemClickSupport {
    private final RecyclerView xRecyclerView;
    private OnItemClickListener xOnItemClickListener;
    private OnItemLongClickListener xOnItemLongClickListener;
    private View.OnClickListener xOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (xOnItemClickListener != null){
                RecyclerView.ViewHolder holder = xRecyclerView.getChildViewHolder(v);
                xOnItemClickListener.onItemClicked(xRecyclerView, holder.getAdapterPosition(), v);
            }
        }
    };

    private View.OnLongClickListener xOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (xOnItemLongClickListener != null){
                RecyclerView.ViewHolder holder = xRecyclerView.getChildViewHolder(v);
                return xOnItemLongClickListener.onItemLongClicked(xRecyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };

    private RecyclerView.OnChildAttachStateChangeListener xAttachListener = new RecyclerView
            .OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(@NonNull View view) {
            if (xOnClickListener != null){
                view.setOnClickListener(xOnClickListener);
            }
            if (xOnLongClickListener != null){
                view.setOnLongClickListener(xOnLongClickListener);
            }
        }
        @Override
        public void onChildViewDetachedFromWindow(@NonNull View view) {
        }
    };

    private ItemClickSupport(RecyclerView recyclerView){
        xRecyclerView = recyclerView;
        xRecyclerView.setTag(R.id.item_click_support, this);
        xRecyclerView.addOnChildAttachStateChangeListener(xAttachListener);
    }

    public static ItemClickSupport addTo(RecyclerView view){
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support == null){
            support = new ItemClickSupport(view);
        }
        return support;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        xOnItemClickListener = listener;
    }

    public void setOnitemLongClickListener(OnItemLongClickListener listener){
        xOnItemLongClickListener = listener;
    }

    private void detach(RecyclerView view){
        view.removeOnChildAttachStateChangeListener(xAttachListener);
        view.setTag(R.id.item_click_support, null);
    }

    public interface OnItemClickListener{
        void onItemClicked(RecyclerView recyclerView, int position, View view);
    }
    public interface OnItemLongClickListener{
        boolean onItemLongClicked(RecyclerView recyclerView, int position, View view);
    }
}

