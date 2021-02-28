package com.example.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Cell> galleryList;
    private Context context;

    public MyAdapter(Context context ,ArrayList<Cell> galleryList){
        Log.d("hello", "MyAdapter: ");
        this.galleryList=galleryList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell, viewGroup ,false);


        return new MyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setImageFromPath(galleryList.get(i).getPath(),viewHolder.img);
        viewHolder.textView.setText(galleryList.get(i).getTitle());
        viewHolder.img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String path=galleryList.get(i).getTitle();


                    if(checkType(path)==1) {
                        Intent intent = new Intent(context, FinalActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("image_url", galleryList.get(i).getPath());
                        intent.putExtra("title", galleryList.get(i).getTitle());
                        context.startActivity(intent);
                    }
                else if(checkType(path)==2){
                    Intent vintent = new Intent(context,VideoActivity.class);
                    vintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    vintent.putExtra("video_url",galleryList.get(i).getPath());
                    context.startActivity(vintent);
                    }
                else
                {
                    path=galleryList.get(i).getPath();
                    Intent i = new Intent(context,MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("folder_url",path);
                    context.startActivity(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView textView;
        public ViewHolder(View view){
            super(view);
            Log.d("hiii", "ViewHolder: ");
            img=(ImageView) view.findViewById(R.id.img);
            textView= (TextView) view.findViewById(R.id.title);
        }
    }

    private void setImageFromPath(String path, ImageView image){

        if(checkType(path)!=0)
        Glide.with(context).asBitmap().load(path).into(image);


    }

    private int checkType(String str){
        String[] img={".png",".jpg",".jpeg",".gif"};
        String[] vid={".mp4",".mkv",".3gp",".avi",".mp3"};
        if(!str.contains("."))
        return 0;
        else{
            for(String s:img){
                if(str.contains(s.toString()))
                    return 1;
            for(String t:vid)
                if(str.contains(t.toString()))
                    return 2;
            }
        }
        return 0;
    }

}



