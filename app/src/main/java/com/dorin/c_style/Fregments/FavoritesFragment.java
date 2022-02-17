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

import com.dorin.c_style.Adapters.Adapter_Item;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.R;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {



    private AppCompatActivity activity;
    private RecyclerView favorites_LST_Clothes;
    private LinearLayoutManager linearLayoutManager;


    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }


    public FavoritesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_favorites, container, false);

        findViews(view);


        //test
        ArrayList<Item> itemsLST=new ArrayList<Item>();
        Item a=new Item().setName("dorin1").setCategory("Shirts").setFavorite(true).setColor("#fff001").setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vdVab7yNvgYEMd8shCfy2D6nTMu.jpg");
        Item b=new Item().setName("dorin2").setCategory("Shirts").setFavorite(true).setColor("#fff002").setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vdVab7yNvgYEMd8shCfy2D6nTMu.jpg");
        Item c=new Item().setName("dorin3").setCategory("Shirts").setFavorite(false).setColor("#fff030").setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vdVab7yNvgYEMd8shCfy2D6nTMu.jpg");
        Item d=new Item().setName("dorin4").setCategory("Shirts").setFavorite(true).setColor("#fff000").setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vdVab7yNvgYEMd8shCfy2D6nTMu.jpg");
        Item e=new Item().setName("dorin5").setCategory("pants").setFavorite(false).setColor("#fff000").setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vdVab7yNvgYEMd8shCfy2D6nTMu.jpg");
        Item g=new Item().setName("dorin6").setCategory("Shirts").setFavorite(true).setColor("#faf000").setPicture("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/vdVab7yNvgYEMd8shCfy2D6nTMu.jpg");
        itemsLST.add(a);
        itemsLST.add(b);
        itemsLST.add(c);
        itemsLST.add(d);
        itemsLST.add(e);
        itemsLST.add(g);


        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        favorites_LST_Clothes.setLayoutManager(linearLayoutManager);
        favorites_LST_Clothes.setHasFixedSize(true);
        favorites_LST_Clothes.setItemAnimator(new DefaultItemAnimator());

        Adapter_Item adapter_item = new Adapter_Item(activity, itemsLST);
        favorites_LST_Clothes.setAdapter(adapter_item);

        adapter_item.setItemClickListener(new Adapter_Item.ItemClickListener() {
            @Override
            public void favoriteClicked(int pos, Item item) {
                item.setFavorite(!item.isFavorite());
                favorites_LST_Clothes.getAdapter().notifyItemChanged(pos);
            }
        });


        return view;
    }

    private void findViews(View view) {
        favorites_LST_Clothes=view.findViewById(R.id.favorites_LST_Clothes);
    }
}