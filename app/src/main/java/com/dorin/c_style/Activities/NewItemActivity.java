package com.dorin.c_style.Activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dorin.c_style.Dialog.ViewDialog_ColorPicker;
import com.dorin.c_style.Dialog.ViewDialog_List;
import com.dorin.c_style.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.util.ArrayList;

public class NewItemActivity extends AppCompatActivity {

    private ImageView newItem_ItemPicture;

    private TextInputLayout newItem_TIL_itemName;
    private TextInputEditText newItem_TIEL_itemName;

    private TextInputLayout newItem_TIL_Category;
    private TextInputEditText newItem_TIET_Category;

    private TextInputLayout newItem_TIL_Size;
    private TextInputEditText newItem_TIET_Size;

    private TextInputEditText newItem_TIET_Color;
    private TextInputLayout newItem_TIL_Color;

    private TextInputEditText newItem_TIET_Favorite;
    private TextInputLayout newItem_TIL_Favorite;

    private MaterialButton newItem_BTN_Save;
    private MaterialButton newItem_BTN_Back;

    private ArrayList<String> categoryList;
    private ArrayList<String> sizeClothesList;
    private ArrayList<String> sizeShoesList;
    private ArrayList<String> sizeAccList;
    private int categoryPick;
    private String color;
    private boolean isFavorite=false;

    public interface Callback_ViewDialogColorPicker {
        void done(String rgb);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        buildArrays();
        findViews();
        clickListener();


    }



    private void buildArrays() {
        categoryList = new ArrayList<String>();
        categoryList.add("Shirts");
        categoryList.add("T-Shirts");
        categoryList.add("knitwear");
        categoryList.add("Pants");
        categoryList.add("Sweatshirts");
        categoryList.add("Coats");
        categoryList.add("Shoes");
        categoryList.add("Bags");
        categoryList.add("Accessories");

        sizeClothesList = new ArrayList<String>();
        sizeClothesList.add("XS");
        sizeClothesList.add("S");
        sizeClothesList.add("M");
        sizeClothesList.add("L");
        sizeClothesList.add("XL");

        sizeShoesList = new ArrayList<String>();
        sizeShoesList.add("35");
        sizeShoesList.add("36");
        sizeShoesList.add("37");
        sizeShoesList.add("38");
        sizeShoesList.add("39");
        sizeShoesList.add("40");
        sizeShoesList.add("41");
        sizeShoesList.add("42");
        sizeShoesList.add("43");
        sizeShoesList.add("44");
        sizeShoesList.add("45");

        sizeAccList = new ArrayList<String>();
        sizeAccList.add("OS");

    }


    private void findViews() {
        newItem_ItemPicture=findViewById(R.id.newItem_ItemPicture);
        newItem_TIL_itemName=findViewById(R.id.newItem_TIL_itemName);
        newItem_TIEL_itemName=findViewById(R.id.newItem_TIEL_itemName);
        newItem_TIL_Category=findViewById(R.id.newItem_TIL_Category);
        newItem_TIET_Category = findViewById(R.id.newItem_TIET_Category);
        newItem_TIL_Size=findViewById(R.id.newItem_TIL_Size);
        newItem_TIET_Size = findViewById(R.id.newItem_TIET_Size);
        newItem_TIET_Color = findViewById(R.id.newItem_TIET_Color);
        newItem_TIL_Color = findViewById(R.id.newItem_TIL_Color);
        newItem_TIET_Favorite = findViewById(R.id.newItem_TIET_Favorite);
        newItem_TIL_Favorite = findViewById(R.id.newItem_TIL_Favorite);
        newItem_BTN_Save = findViewById(R.id.newItem_BTN_Save);
        newItem_BTN_Back = findViewById(R.id.newItem_BTN_Back);

    }

    private void clickListener() {

        newItem_TIET_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_List dialog = new ViewDialog_List();
                dialog.showDialog(NewItemActivity.this, "Category", categoryList, callBack_viewDialogCategory);
            }
        });


        newItem_TIET_Size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newItem_TIET_Category.getText().toString().equals("")) {
                    Toast.makeText(NewItemActivity.this, categoryPick+"", Toast.LENGTH_SHORT).show();
                    ViewDialog_List dialog = new ViewDialog_List();

                    switch (categoryPick) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            dialog.showDialog(NewItemActivity.this, "Size", sizeClothesList, callBack_viewDialogSize);
                            break;
                        case 6:
                            dialog.showDialog(NewItemActivity.this, "Size", sizeShoesList, callBack_viewDialogSize);
                            break;
                        case 7:
                        case 8:
                            dialog.showDialog(NewItemActivity.this, "Size", sizeAccList, callBack_viewDialogSize);
                            break;


                    }
                } else {
                    Toast.makeText(NewItemActivity.this, "Must Pick Categorty First", Toast.LENGTH_SHORT).show();
                }


            }
        });


        newItem_TIET_Color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog_ColorPicker dialog = new ViewDialog_ColorPicker();
                dialog.showDialog(NewItemActivity.this, callback_viewDialogColorPicker);
            }
        });

        newItem_ItemPicture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                ImagePicker.Companion.with(NewItemActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });

        newItem_TIET_Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isFavorite){
                    newItem_TIL_Favorite.setEndIconDrawable(R.drawable.ic_favorite);
                    isFavorite=true;
                }else{
                    newItem_TIL_Favorite.setEndIconDrawable(R.drawable.ic_favorite_border);
                    isFavorite=false;
                }

            }
        });

        newItem_BTN_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newItem_BTN_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2/16/2022  btnSaveNewItem
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri resultUri = data.getData();
        newItem_ItemPicture.setImageURI(resultUri);

    }


    ViewDialog_List.Callback_ViewDialog callBack_viewDialogCategory = new ViewDialog_List.Callback_ViewDialog() {
        @Override
        public void itemClicked(int pos) {
            // TODO: 2/14/2022  get pos-database
            newItem_TIET_Category.setText(categoryList.get(pos));
            categoryPick = pos;

        }
    };


    ViewDialog_List.Callback_ViewDialog callBack_viewDialogSize = new ViewDialog_List.Callback_ViewDialog() {
        @Override
        public void itemClicked(int pos) {
            // TODO: 2/14/2022  get pos-database
            switch (categoryPick) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    newItem_TIET_Size.setText(sizeClothesList.get(pos));
                    break;
                case 6:
                    newItem_TIET_Size.setText(sizeShoesList.get(pos));
                    break;
                case 7:
                case 8:
                    newItem_TIET_Size.setText(sizeAccList.get(0));
                    break;

            }


        }
    };

    Callback_ViewDialogColorPicker callback_viewDialogColorPicker=new Callback_ViewDialogColorPicker() {
        @Override
        public void done(String rgb) {
            color=rgb;
            newItem_TIL_Color.getEndIconDrawable().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_ATOP);
        }
    };


}