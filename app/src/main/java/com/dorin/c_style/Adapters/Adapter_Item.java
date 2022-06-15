package com.dorin.c_style.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;


public class Adapter_Item extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private ArrayList<Item> items;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void favoriteClicked(int pos, Item item);
        void itemClicked(Item item);
    }

    public Adapter_Item(Activity activity, ArrayList<Item> items) {
        this.activity = activity;
        this.items = items;
    }

    public Adapter_Item setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Item item = getItem(position);
        Log.d("pttt", "position= " + position);

        Glide
                .with(activity)
                .load(item.getPicture())
                .into(itemViewHolder.item_IMG);

        itemViewHolder.item_LBL_NameItem.setText(item.getName());
        itemViewHolder.item_LBL_CategoryItem.setText(item.getCategory());
        itemViewHolder.item_LBL_SizeItem.setText(item.getSize());
        itemViewHolder.item_TIL_ColorItem.getEndIconDrawable().setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_ATOP);



        if (item.isFavorite()) {
            itemViewHolder.item_IMG_favorite.setImageResource(R.drawable.ic_heart_filled);
        } else  {
            itemViewHolder.item_IMG_favorite.setImageResource(R.drawable.ic_heart_empty);
        }
    }

    public void setFilteredList(ArrayList<Item> filteredList){
        this.items = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }


    private Item getItem(int position) {
        return items.get(position);
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
                
        public AppCompatImageView item_IMG;
        public AppCompatImageView item_IMG_favorite;
        public MaterialTextView item_LBL_NameItem;
        public MaterialTextView item_LBL_CategoryItem;
        public MaterialTextView item_LBL_SizeItem;
        public TextInputLayout item_TIL_ColorItem;


        public ItemViewHolder(final View itemView) {
            super(itemView);
            this.item_IMG = itemView.findViewById(R.id.item_IMG);
            this.item_IMG_favorite = itemView.findViewById(R.id.item_IMG_favorite);
            this.item_LBL_NameItem = itemView.findViewById(R.id.item_LBL_NameItem);
            this.item_LBL_CategoryItem = itemView.findViewById(R.id.item_LBL_CategoryItem);
            this.item_LBL_SizeItem = itemView.findViewById(R.id.item_LBL_SizeItem);
            this.item_TIL_ColorItem = itemView.findViewById(R.id.item_TIL_ColorItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.itemClicked(getItem(getAdapterPosition()));
                }
            });

            item_IMG_favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.favoriteClicked(getAdapterPosition(),getItem(getAdapterPosition()));
                }
            });
        }
    }
}

