package com.dorin.c_style.Fregments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dorin.c_style.Activities.NewOutfitActivity;
import com.dorin.c_style.Adapters.Adapter_Item;
import com.dorin.c_style.Adapters.Adapter_Outfit;
import com.dorin.c_style.Dialog.ViewDialog_Items;
import com.dorin.c_style.Managers.UserDataManager;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.Objects.Outfit;
import com.dorin.c_style.R;

import java.util.ArrayList;


public class OutfitsFragment extends Fragment {

    private RecyclerView outfits_LST_Outfits;
    private AppCompatActivity activity;
    private UserDataManager userDataManager;
    private LinearLayoutManager linearLayoutManager;


    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public OutfitsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_outfits, container, false);

        userDataManager = UserDataManager.getInstance();
        findViews(view);
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        outfits_LST_Outfits.setLayoutManager(linearLayoutManager);
        outfits_LST_Outfits.setHasFixedSize(true);
        outfits_LST_Outfits.setItemAnimator(new DefaultItemAnimator());
        setOutfits(userDataManager.getMyOutfits());
        return view;
    }

    private void findViews(View view) {
        outfits_LST_Outfits=view.findViewById(R.id.outfits_LST_Outfits);
    }


    private void setOutfits(ArrayList<Outfit> outfits){
        Adapter_Outfit adapter_outfit = new Adapter_Outfit(activity, outfits);
        outfits_LST_Outfits.setAdapter(adapter_outfit);

        adapter_outfit.setOutfitClickListener(new Adapter_Outfit.OutfitClickListener() {
            @Override
            public void outfitClicked(Outfit outfit) {
                    ArrayList<Item> items= userDataManager.getMyItems();
                    ArrayList<Item> myItems=new ArrayList<>();
                for (Item item:items) {
                    if(item.getId().equals(outfit.getBagID()) ||
                            item.getId().equals(outfit.getAccessoryID()) ||
                            item.getId().equals(outfit.getTopID()) ||
                            item.getId().equals(outfit.getBottomID()) ||
                            item.getId().equals(outfit.getShoesID()) ||
                            item.getId().equals(outfit.getCoatID()) ){
                        myItems.add(item);
                    }
                }

                ViewDialog_Items dialog = new ViewDialog_Items();
                dialog.showDialog(activity, outfit.getName(), myItems, new ViewDialog_Items.Callback_ViewDialog() {
                    @Override
                    public void itemClicked(String id, String urlImg, String category) {
                    }
                });


            }
        });


    }
}