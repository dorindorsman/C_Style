package com.dorin.c_style.Dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.dorin.c_style.Activities.NewItemActivity;
import com.dorin.c_style.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

import de.hdodenhof.circleimageview.CircleImageView;


public class ViewDialog_ColorPicker {

    private MaterialButton dialog_color_BTN_back, dialog_color_BTN_save;
    private ShapeableImageView dialog_color_IMG_picker,dialog_color_IMG_indicator;
    private String color;

    private Bitmap bitmap;

    public void showDialog(Activity activity, NewItemActivity.Callback_ViewDialogColorPicker callback_viewDialogColorPicker) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogbox_color_picker);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog_color_BTN_back = dialog.findViewById(R.id.dialog_color_BTN_back);
        dialog_color_BTN_save = dialog.findViewById(R.id.dialog_color_BTN_save);
        dialog_color_IMG_indicator = dialog.findViewById(R.id.dialog_color_IMG_indicator);
        dialog_color_IMG_picker = dialog.findViewById(R.id.dialog_color_IMG_picker);

        dialog_color_IMG_picker.setDrawingCacheEnabled(true);
        dialog_color_IMG_picker.buildDrawingCache(true);

        dialog_color_IMG_picker.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                    try {
                        bitmap = dialog_color_IMG_picker.getDrawingCache();
                        int pixels = bitmap.getPixel((int)event.getX(),(int) event.getY());
                        int red = Color.red(pixels);
                        int green = Color.green(pixels);
                        int blue = Color.blue(pixels);
                        String hex = "#" + Integer.toHexString(pixels);
                        color = hex;
                        dialog_color_IMG_indicator.setBackgroundColor(Color.rgb(red,green,blue));
                    } catch (Exception e){
                    }
                }
                return true;
            }
        });

        dialog_color_BTN_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (color == null){
                    color = "#0000FF";
                }
                callback_viewDialogColorPicker.done(color);
                dialog.cancel();
            }
        });

        dialog_color_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
