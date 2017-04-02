
package com.example.lenovo.photoviewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lenovo.photoviewer.R;
import com.example.lenovo.photoviewer.objects.Model_images;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;



public class GridViewAdapter extends ArrayAdapter<Model_images> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<Model_images> al_menu = new ArrayList<>();
    int int_position;


    public GridViewAdapter(Context context, ArrayList<Model_images> al_menu, int int_position) {
        super(context, R.layout.photos, al_menu);
        this.al_menu = al_menu;
        this.context = context;
        this.int_position = int_position;
    }

    @Override
    public int getCount() {

        Log.e("ADAPTER LIST SIZE", al_menu.get(int_position).getAl_imagepath().size() + "");
        return al_menu.get(int_position).getAl_imagepath().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (al_menu.get(int_position).getAl_imagepath().size() > 0) {
            return al_menu.get(int_position).getAl_imagepath().size();
        } else {
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photos, parent, false);
            viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.Share=(TextView) convertView.findViewById(R.id.tv_folder);
            viewHolder.card_view=(CardView) convertView.findViewById(R.id.card_view);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load("file://" + al_menu.get(int_position).getAl_imagepath().get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(viewHolder.iv_image);

        viewHolder.Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                     Glide.with(context).load(al_menu.get(int_position).getAl_imagepath().get(position)).asBitmap().into(new SimpleTarget<Bitmap>(){
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation){
                            Intent share=new Intent(Intent.ACTION_SEND);
                            share.setType("image/jpeg");
                            ByteArrayOutputStream bytes=new ByteArrayOutputStream();
                            resource.compress(Bitmap.CompressFormat.JPEG,100,bytes);
                            String path=MediaStore.Images.Media.insertImage(context.getContentResolver(),resource,"Title",null);
                            Uri imageUri=Uri.parse(path);
                            share.putExtra(Intent.EXTRA_STREAM,imageUri);
                            context.startActivity(Intent.createChooser(share,"Select"));
                        }
                     });
            }
        });

        return convertView;

    }

    public Bitmap StringToBitmap(String path){
        try{
            byte [] encodeByte=path.getBytes(Charset.forName("UTF-8"));
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    private static class ViewHolder {
        ImageView iv_image;
        TextView Share,View;
        CardView card_view;


    }
}