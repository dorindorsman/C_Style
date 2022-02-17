package com.dorin.c_style.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.dorin.c_style.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Adapter_list extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface ItemClickListener {
        void itemClicked(int pos);
    }

    private Activity activity;
    private ArrayList<String> list;
    private ItemClickListener itemClickListener;

    public Adapter_list(Activity activity, ArrayList<String> list) {
        this.activity = activity;
        this.list = list;
    }

    public Adapter_list setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_category_item, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        myViewHolder ViewHolder = (myViewHolder) holder;
        String name = getItem(position);
        ViewHolder.global_LBL_text.setText(name);
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    private String getItem(int position) {
        return list.get(position);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public MaterialTextView global_LBL_text;

        public myViewHolder(final View itemView) {
            super(itemView);
            this.global_LBL_text = itemView.findViewById(R.id.global_LBL_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.itemClicked(getAdapterPosition());
                    }
                }
            });
        }
    }
}

