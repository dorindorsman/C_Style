package com.dorin.c_style.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dorin.c_style.Dialog.ViewDialog_Items;
import com.dorin.c_style.Dialog.ViewDialog_List;
import com.dorin.c_style.Managers.UserDataManager;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.R;
import com.dorin.c_style.Validator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class NewOutfitActivity extends AppCompatActivity {

    private MaterialButton newOutfit_BTN_Back;
    private MaterialTextView newOutfit_LBL_title;
    private TextInputLayout newOutfit_TIL_OutfitName;
    private TextInputEditText newOutfit_TIEL_OutfitName;
    private LinearLayout newOutfit_Layout_Acc;
    private ShapeableImageView newOutfit_ItemAcc;
    private LinearLayout newOutfit_Layout_Bag;
    private ShapeableImageView newOutfit_ItemBag;
    private LinearLayout newOutfit_Layout_Coat;
    private ShapeableImageView newOutfit_ItemCoat;
    private LinearLayout newOutfit_Layout_TopItem;
    private ShapeableImageView newOutfit_ItemTop;
    private LinearLayout newOutfit_Layout_BottomItem;
    private ShapeableImageView newOutfit_ItemBottom;
    private LinearLayout newOutfit_Layout_Shoes;
    private ShapeableImageView newOutfit_ItemShoes;
    private MaterialButton newOutfit_BTN_Save;

//    private boolean acc=true;
//    private boolean bag=true;
//    private boolean coat=true;
//    private boolean top=true;
//    private boolean bottom=true;
//    private boolean shoes=true;
    private final String urlImgEmpty="https://firebasestorage.googleapis.com/v0/b/c-style-e408e.appspot.com/o/ItemsPictures%2Fempty.JPG?alt=media&token=0655b1e4-ba15-4cf4-8457-5998ceff58f8";

    private String acc;
    private String bag;
    private String coat;
    private String top;
    private String bottom;
    private String shoes;

    Validator validatorOutfitName;

    private UserDataManager userDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_outfit);

        userDataManager = UserDataManager.getInstance();
        acc="";
        bag="";
        coat="";
        top="";
        bottom="";
        shoes="";
        findViews();
        initValidator();
        initButtons();


    }

    private void initValidator() {
        validatorOutfitName = Validator.Builder.make(newOutfit_TIL_OutfitName)
                .addWatcher(new Validator.Watcher_StringEmpty("Name Cannot Be Empty"))
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.add_outfit_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.addAccessory:
                return true;
            case R.id.deleteAccessory:
                return true;
            case R.id.addBag:
                return true;
            case R.id.deleteBag:
                return true;
            case R.id.addCoat:
                return true;
            case R.id.deleteCoat:
                return true;
            case R.id.addTop:
                return true;
            case R.id.deleteTop:
                return true;
            case R.id.addBottom:
                return true;
            case R.id.deleteBottom:
                return true;
            case R.id.addShoes:
                return true;
            case R.id.deleteShoes:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void findViews() {
        newOutfit_BTN_Back=findViewById(R.id.newOutfit_BTN_Back);
        newOutfit_LBL_title=findViewById(R.id.newOutfit_LBL_title);
        newOutfit_TIL_OutfitName=findViewById(R.id.newOutfit_TIL_OutfitName);
        newOutfit_TIEL_OutfitName=findViewById(R.id.newOutfit_TIEL_OutfitName);
        newOutfit_Layout_Acc=findViewById(R.id.newOutfit_Layout_Acc);
        newOutfit_ItemAcc=findViewById(R.id.newOutfit_ItemAcc);
        newOutfit_Layout_Bag=findViewById(R.id.newOutfit_Layout_Bag);
        newOutfit_ItemBag=findViewById(R.id.newOutfit_ItemBag);
        newOutfit_Layout_Coat=findViewById(R.id.newOutfit_Layout_Coat);
        newOutfit_ItemCoat=findViewById(R.id.newOutfit_ItemCoat);
        newOutfit_Layout_TopItem=findViewById(R.id.newOutfit_Layout_TopItem);
        newOutfit_ItemTop=findViewById(R.id.newOutfit_ItemTop);
        newOutfit_Layout_BottomItem=findViewById(R.id.newOutfit_Layout_BottomItem);
        newOutfit_ItemBottom=findViewById(R.id.newOutfit_ItemBottom);
        newOutfit_Layout_Shoes=findViewById(R.id.newOutfit_Layout_Shoes);
        newOutfit_ItemShoes=findViewById(R.id.newOutfit_ItemShoes);
        newOutfit_BTN_Save=findViewById(R.id.newOutfit_BTN_Save);
    }

    private ArrayList<Item> findItemByCategory(String category){
        ArrayList<Item> items=new ArrayList<Item>();
        ArrayList<Item> myItems=userDataManager.getMyItems();
        for (Item item:myItems) {
            if(item.getCategory().equals(category)){
                items.add(item);
            }
        }
        return items;
    }
    private Item getEmptyItem(){
        Item item=new Item()
                .setCategory("")
                .setName("Empty")
                .setColor("#ffffff")
                .setSize("")
                .setFavorite(false)
                .setPicture(urlImgEmpty);

        return item;
    }


    private void initButtons() {

        newOutfit_BTN_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newOutfit_BTN_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDataManager.addNewOutfit(newOutfit_TIEL_OutfitName.getText().toString(),bag,coat,top,bottom,shoes,acc);
                finish();

            }
        });

        newOutfit_ItemAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_Items dialog = new ViewDialog_Items();
                ArrayList<Item> items=findItemByCategory("Accessories");
                items.add(getEmptyItem().setCategory("Accessories"));
                dialog.showDialog(NewOutfitActivity.this, "Accessories", items, callback_viewDialog);

            }
        });

        newOutfit_ItemBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewDialog_Items dialog = new ViewDialog_Items();
                ArrayList<Item> items=findItemByCategory("Bags");
                items.add(getEmptyItem().setCategory("Bags"));
                dialog.showDialog(NewOutfitActivity.this, "Bags", items, callback_viewDialog);

            }
        });

        newOutfit_ItemCoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_Items dialog = new ViewDialog_Items();
                ArrayList<Item> items=findItemByCategory("Coats");
                items.add(getEmptyItem().setCategory("Coats"));
                dialog.showDialog(NewOutfitActivity.this, "Coats", items, callback_viewDialog);
            }
        });

        newOutfit_ItemTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_Items dialog = new ViewDialog_Items();
                ArrayList<Item> items=findItemByCategory("Shirts");
                items.addAll(findItemByCategory("T-Shirts"));
                items.addAll(findItemByCategory("knitwear"));
                items.addAll(findItemByCategory("Sweatshirts"));
                items.add(getEmptyItem().setCategory("Shirts"));
                dialog.showDialog(NewOutfitActivity.this, "Tops", items, callback_viewDialog);
            }
        });

        newOutfit_ItemBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_Items dialog = new ViewDialog_Items();
                ArrayList<Item> items=findItemByCategory("Pants");
                items.add(getEmptyItem().setCategory("Pants"));
                dialog.showDialog(NewOutfitActivity.this, "Bottom", items, callback_viewDialog);
            }
        });

        newOutfit_ItemShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_Items dialog = new ViewDialog_Items();
                ArrayList<Item> items=findItemByCategory("Shoes");
                items.add(getEmptyItem().setCategory("Shoes"));
                dialog.showDialog(NewOutfitActivity.this, "Shoes", items, callback_viewDialog);

            }
        });
    }

    ViewDialog_Items.Callback_ViewDialog callback_viewDialog=new ViewDialog_Items.Callback_ViewDialog() {
        @Override
        public void itemClicked(String id, String urlImg, String category) {


            if(category.equals("Accessories")){
                if(urlImg.equals(urlImgEmpty)){
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(R.drawable.emptyforoutfit)
                            .into(newOutfit_ItemAcc);
                    acc="";
                }else{
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(urlImg)
                            .into(newOutfit_ItemAcc);
                    acc=id;
                }
            }
            else if(category.equals("Bags")){
                if(urlImg.equals(urlImgEmpty)){
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(R.drawable.emptyforoutfit)
                            .into(newOutfit_ItemBag);
                    bag="";
                }else {
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(urlImg)
                            .into(newOutfit_ItemBag);
                    bag = id;
                }
            }
            else if(category.equals("Coats")){
                if(urlImg.equals(urlImgEmpty)){
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(R.drawable.emptyforoutfit)
                            .into(newOutfit_ItemCoat);
                    coat="";
                }else {
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(urlImg)
                            .into(newOutfit_ItemCoat);
                    coat = id;
                }
            }
            else if(category.equals("Tops")){
                if(urlImg.equals(urlImgEmpty)){
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(R.drawable.emptyforoutfit)
                            .into(newOutfit_ItemTop);
                    top="";
                }else {
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(urlImg)
                            .into(newOutfit_ItemTop);
                    top = id;
                }
            }
            else if(category.equals("Bottom")){
                if(urlImg.equals(urlImgEmpty)){
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(R.drawable.emptyforoutfit)
                            .into(newOutfit_ItemBottom);
                    bottom="";
                }else {
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(urlImg)
                            .into(newOutfit_ItemBottom);
                    bottom = id;
                }
            }
            else if(category.equals("Shoes")){
                if(urlImg.equals(urlImgEmpty)){
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(R.drawable.emptyforoutfit)
                            .into(newOutfit_ItemShoes);
                    shoes="";
                }else {
                    Glide
                            .with(NewOutfitActivity.this)
                            .load(urlImg)
                            .into(newOutfit_ItemShoes);
                    shoes = id;
                }
            }
        }
    };


}