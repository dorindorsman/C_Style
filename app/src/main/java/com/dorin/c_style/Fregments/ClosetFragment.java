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
import android.widget.Toast;

import com.dorin.c_style.Adapters.Adapter_Item;
import com.dorin.c_style.Adapters.Adapter_list;
import com.dorin.c_style.Managers.UserDataManager;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.Objects.User;
import com.dorin.c_style.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ClosetFragment extends Fragment {


    private TabLayout closet_TabCategories;
    private RecyclerView closet_LST_Clothes;

    private AppCompatActivity activity;
    private LinearLayoutManager linearLayoutManager;

    private UserDataManager userDataManager;




    public Fragment setActivity(AppCompatActivity activity){
        this.activity=activity;
        return this;
    }

    public ClosetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_closet, container, false);
        findViews(view);
        userDataManager = UserDataManager.getInstance();
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        closet_LST_Clothes.setLayoutManager(linearLayoutManager);
        closet_LST_Clothes.setHasFixedSize(true);
        closet_LST_Clothes.setItemAnimator(new DefaultItemAnimator());
        initButtons(view);
        setItems(userDataManager.getMyItems());

        return view;
    }

    private void setItems(ArrayList<Item> items){
        Adapter_Item adapter_item = new Adapter_Item(activity, items);
        closet_LST_Clothes.setAdapter(adapter_item);

        adapter_item.setItemClickListener(new Adapter_Item.ItemClickListener() {
            @Override
            public void favoriteClicked(int pos, Item item) {
                item.setFavorite(!item.isFavorite());
                closet_LST_Clothes.getAdapter().notifyItemChanged(pos);
            }

            @Override
            public void itemClicked(Item item) {

            }
        });
    }

    private void initButtons(View view) {

        closet_TabCategories.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getText().toString().equals("All")){
                    setItems(userDataManager.getMyItems());
                    return;
                }

                ArrayList<Item> items=userDataManager.getMyItems();
                ArrayList<Item> itemsLST=new ArrayList<>();

                for (Item item:items) {
                    if(item.getCategory().equals(tab.getText().toString())){
                        itemsLST.add(item);
                    }
                }

                setItems(itemsLST);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void findViews(View view) {

      closet_TabCategories=view.findViewById(R.id.closet_TabCategories);
      closet_LST_Clothes=view.findViewById(R.id.closet_LST_Clothes);


    }

}