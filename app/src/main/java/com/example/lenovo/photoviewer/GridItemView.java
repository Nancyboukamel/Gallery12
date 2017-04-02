package com.example.lenovo.photoviewer;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class GridItemView extends LinearLayout {
    private ImageView imageView;
    Context _context;

    public GridItemView(Context context) {
        super(context);
        _context=context;
        LayoutInflater.from(context).inflate(R.layout.photos, this);
        imageView = (ImageView) getRootView().findViewById(R.id.iv_image);
    }
    public void display(String text, boolean isSelected) {
        Glide.with(_context).load("file://" +text)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView);
        display(isSelected);
    }

    public void display(boolean isSelected) {
        if(isSelected){
            imageView.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
            imageView.invalidate();
        }
        else{
            imageView.getDrawable().clearColorFilter();
            imageView.invalidate();
        }
    }
}
