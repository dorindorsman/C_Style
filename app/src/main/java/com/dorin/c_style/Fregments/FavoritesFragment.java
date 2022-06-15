package com.dorin.c_style.Fregments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.dorin.c_style.Adapters.Adapter_Item;
import com.dorin.c_style.Managers.UserDataManager;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.R;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {



    private AppCompatActivity activity;
    private RecyclerView favorites_LST_Clothes;
    private SearchView favorites_search;
    private CheckBox favorites_checkBox;


    private LinearLayoutManager linearLayoutManager;
    private UserDataManager userDataManager;
    private ArrayList<Item> itemsLST;
    private Adapter_Item adapter_item;
    private Boolean checked=false;
    private String searchText="";

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
        InitButtons();
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        favorites_LST_Clothes.setLayoutManager(linearLayoutManager);
        favorites_LST_Clothes.setHasFixedSize(true);
        favorites_LST_Clothes.setItemAnimator(new DefaultItemAnimator());


        ArrayList<Item> items=userDataManager.getMyItems();
        itemsLST=new ArrayList<>();

        for (Item item:items) {
            if(item.isFavorite()){
                itemsLST.add(item);
            }
        }


        adapter_item = new Adapter_Item(activity, itemsLST);
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

    private void InitButtons() {

        favorites_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked = isChecked;
                filterList(searchText);
            }
        });


        favorites_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String newText) {
        ArrayList<Item> filteredList = new ArrayList<>();
        searchText=newText;
        for(Item item:itemsLST){
            if(checked){
                if(item.getName().toLowerCase().contains(searchText.toLowerCase())){
                    filteredList.add(item);
                }
            }else{
                if(item.getName().toLowerCase().startsWith(searchText.toLowerCase())){
                    filteredList.add(item);
                }
            }

        }

        if(filteredList.isEmpty()){
            Toast.makeText(this.activity,"No Cloth Found",Toast.LENGTH_SHORT).show();
            adapter_item.setFilteredList(itemsLST);
        }else{
            adapter_item.setFilteredList(filteredList);
        }
    }

    private void findViews(View view) {
        favorites_LST_Clothes=view.findViewById(R.id.favorites_LST_Clothes);
        favorites_checkBox=view.findViewById(R.id.favorites_checkBox);
        favorites_search=view.findViewById(R.id.favorites_search);
        favorites_search.clearFocus();
    }
}