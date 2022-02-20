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
import com.dorin.c_style.Managers.UserDataManager;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.Objects.Outfit;
import com.dorin.c_style.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class Adapter_Outfit extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private ArrayList<Outfit> outfits;
    private OutfitClickListener outfitClickListener;
    private UserDataManager userDataManager;


    public interface OutfitClickListener {
        void outfitClicked(Outfit outfit);
    }

    public Adapter_Outfit(Activity activity, ArrayList<Outfit> outfits) {
        this.activity = activity;
        this.outfits = outfits;
    }

    public Adapter_Outfit setOutfitClickListener(OutfitClickListener outfitClickListener) {
        this.outfitClickListener = outfitClickListener;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_outfit, viewGroup, false);
        userDataManager = UserDataManager.getInstance();
        return new OutfitViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        OutfitViewHolder outfitViewHolder = (OutfitViewHolder) holder;
        Outfit outfit = getOutfit(position);
        Log.d("pttt", "position= " + position);
        ArrayList<Item> items = userDataManager.getMyItems();


        if (!outfit.getAccessoryID().equals("")){
            for (Item item: items) {
                if(item.getId().equals(outfit.getAccessoryID())){
                    Glide
                            .with(activity)
                            .load(item.getPicture())
                            .into(outfitViewHolder.outfit_ItemAcc);
                    break;
                }
            }
        }


        if (!outfit.getBagID().equals("")){
            for (Item item: items) {
                if(item.getId().equals(outfit.getBagID())){
                    Glide
                            .with(activity)
                            .load(item.getPicture())
                            .into(outfitViewHolder.outfit_ItemBag);
                    break;
                }

            }
        }


        if (!outfit.getTopID().equals("")){
            for (Item item: items) {
                if(item.getId().equals(outfit.getTopID())){
                    Glide
                            .with(activity)
                            .load(item.getPicture())
                            .into(outfitViewHolder.outfit_ItemTop);
                    break;
                }
            }
        }


        if (!outfit.getBottomID().equals("")){
            for (Item item: items) {
                if(item.getId().equals(outfit.getBottomID())){
                    Glide
                            .with(activity)
                            .load(item.getPicture())
                            .into(outfitViewHolder.outfit_ItemBottom);
                    break;
                }
            }
        }


        if (!outfit.getShoesID().equals("")){
            for (Item item: items) {
                if(item.getId().equals(outfit.getShoesID())){
                    Glide
                            .with(activity)
                            .load(item.getPicture())
                            .into(outfitViewHolder.outfit_ItemShoes);
                    break;
                }
            }
        }


        if (!outfit.getCoatID().equals("")){
            for (Item item: items) {
                if(item.getId().equals(outfit.getCoatID())){
                    Glide
                            .with(activity)
                            .load(item.getPicture())
                            .into(outfitViewHolder.outfit_ItemCoat);
                    break;
                }
            }
        }


        outfitViewHolder.outfit_LBL_NameOutfit.setText(outfit.getName());
    }

    @Override
    public int getItemCount() {
        if (outfits == null) return 0;
        return outfits.size();
    }


    private Outfit getOutfit(int position) {
        return outfits.get(position);
    }


    public class OutfitViewHolder extends RecyclerView.ViewHolder {

        public MaterialTextView outfit_LBL_NameOutfit;
        public ShapeableImageView outfit_ItemAcc;
        public ShapeableImageView outfit_ItemTop;
        public ShapeableImageView outfit_ItemCoat;
        public ShapeableImageView outfit_ItemBag;
        public ShapeableImageView outfit_ItemBottom;
        public ShapeableImageView outfit_ItemShoes;


        public OutfitViewHolder(final View outfitView) {
            super(outfitView);
            this.outfit_LBL_NameOutfit = outfitView.findViewById(R.id.outfit_LBL_NameOutfit);
            this.outfit_ItemAcc = outfitView.findViewById(R.id.outfit_ItemAcc);
            this.outfit_ItemTop = outfitView.findViewById(R.id.outfit_ItemTop);
            this.outfit_ItemCoat = outfitView.findViewById(R.id.outfit_ItemCoat);
            this.outfit_ItemBag = outfitView.findViewById(R.id.outfit_ItemBag);
            this.outfit_ItemBottom = outfitView.findViewById(R.id.outfit_ItemBottom);
            this.outfit_ItemShoes = outfitView.findViewById(R.id.outfit_ItemShoes);

            outfitView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    outfitClickListener.outfitClicked(getOutfit(getAdapterPosition()));
                }
            });


        }
    }
}

