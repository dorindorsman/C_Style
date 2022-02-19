package com.dorin.c_style.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dorin.c_style.Adapters.Adapter_Item;
import com.dorin.c_style.Adapters.Adapter_list;
import com.dorin.c_style.Objects.Item;
import com.dorin.c_style.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class ViewDialog_Items {

    private MaterialTextView dialog_List_LBL_title;
    private RecyclerView dialog_LST_List;
    private MaterialButton dialog_List_BTN_back;
    private LinearLayoutManager linearLayoutManager;


    public interface Callback_ViewDialog{
        void itemClicked(String id,String urlImg,String category);
    }

    public void showDialog(Activity activity, String title , ArrayList<Item> list, Callback_ViewDialog callBack_viewDialogItem) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogbox_items);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        findViews(dialog);
        dialog_List_LBL_title.setText(title);
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        dialog_LST_List.setLayoutManager(linearLayoutManager);
        dialog_LST_List.setHasFixedSize(true);
        dialog_LST_List.setItemAnimator(new DefaultItemAnimator());



        Adapter_Item adapter_item = new Adapter_Item(activity, list);
        dialog_LST_List.setAdapter(adapter_item);

        adapter_item.setItemClickListener(new Adapter_Item.ItemClickListener() {
            @Override
            public void favoriteClicked(int pos, Item item) {

            }

            @Override
            public void itemClicked(Item item) {
                callBack_viewDialogItem.itemClicked(item.getId(),item.getPicture(),title);
                dialog.cancel();
            }
        });

        dialog_List_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void findViews(Dialog dialog) {

        dialog_List_LBL_title = dialog.findViewById(R.id.dialog_List_LBL_title);
        dialog_LST_List = dialog.findViewById(R.id.dialog_LST_category);
        dialog_List_BTN_back = dialog.findViewById(R.id.dialog_List_BTN_back);


    }


}
