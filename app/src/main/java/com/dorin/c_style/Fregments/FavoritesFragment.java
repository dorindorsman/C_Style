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
import com.dorin.c_style.Managers.UserDataManager;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.R;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {



    private AppCompatActivity activity;
    private RecyclerView favorites_LST_Clothes;
    private LinearLayoutManager linearLayoutManager;
    private UserDataManager userDataManager;

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
        userDataManager = UserDataManager.getInstance();
        findViews(view);
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        favorites_LST_Clothes.setLayoutManager(linearLayoutManager);
        favorites_LST_Clothes.setHasFixedSize(true);
        favorites_LST_Clothes.setItemAnimator(new DefaultItemAnimator());


        ArrayList<Item> items=userDataManager.getMyItems();
        ArrayList<Item> itemsLST=new ArrayList<>();

        for (Item item:items) {
            if(item.isFavorite()){
                itemsLST.add(item);
            }
        }


        Adapter_Item adapter_item = new Adapter_Item(activity, itemsLST);
        favorites_LST_Clothes.setAdapter(adapter_item);

        adapter_item.setItemClickListener(new Adapter_Item.ItemClickListener() {
            @Override
            public void favoriteClicked(int pos, Item item) {
                item.setFavorite(!item.isFavorite());
                favorites_LST_Clothes.getAdapter().notifyItemChanged(pos);
            }

            @Override
            public void itemClicked(Item item) {

            }
        });


        return view;
    }

    private void findViews(View view) {
        favorites_LST_Clothes=view.findViewById(R.id.favorites_LST_Clothes);
    }
}