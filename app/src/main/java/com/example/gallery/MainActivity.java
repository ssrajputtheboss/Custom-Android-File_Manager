package com.example.gallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Cell> allFilesPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE )
    != PackageManager.PERMISSION_GRANTED){
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1000);
    } else{
        if(!getIntent().hasExtra("folder_url")){

        showImages();}
        else{
            Toast.makeText(getApplicationContext(),getIntent().getStringExtra("folder_url"),Toast.LENGTH_SHORT).show();
            showImages(getIntent().getStringExtra("folder_url"));
        }
    }
}
private void showImages(){
    String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"";

    allFilesPath =new ArrayList<>();

    File file=new File(path);
    File[] files = file.listFiles();
    if(files != null){
        for(File f:files)
        {
            Cell cell = new Cell();
            cell.setTitle(f.getName());
            cell.setPath(f.getAbsolutePath());
            allFilesPath.add(cell);

        }
    }

    ArrayList<Cell> cells = prepareData();
    layout(cells);

}

    private ArrayList<Cell> prepareData(){

        ArrayList<Cell> allImages = new ArrayList<>();
        for(Cell c : allFilesPath)
        {
            Cell cell=new Cell();
            cell.setTitle(c.getTitle());
            cell.setPath(c.getPath());
            allImages.add(cell);
        }
        return allImages;
    }


    //@Override
    public void onRequestPerissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
      if(requestCode==1000){
          if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
              showImages();
          }else{
              Toast.makeText(this,"PERMISSION NOT GRANTED!",Toast.LENGTH_SHORT).show();
              finish();
          }
      }
    }

    public void layout(ArrayList<Cell> cells)
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(getApplicationContext(),4);


        MyAdapter adapter = new MyAdapter(getApplicationContext(),cells);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(layoutManager);
    }



    private void showImages(String newpath){
        String path = newpath;

        allFilesPath =new ArrayList<>();

        File file=new File(path);
        File[] files = file.listFiles();
        if(files != null){
            for(File f:files)
            {
                Cell cell = new Cell();
                cell.setTitle(f.getName());
                cell.setPath(f.getAbsolutePath());
                allFilesPath.add(cell);

            }
        }

        ArrayList<Cell> cells = prepareData();
        layout(cells);

    }
}

