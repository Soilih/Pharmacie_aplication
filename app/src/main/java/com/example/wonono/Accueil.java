package com.example.wonono;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.wonono.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class Accueil extends AppCompatActivity  {
    //declaration des variables
    ListView listView;
    SearchView searchView;
    ArrayAdapter<String> adapter;

    //declaration d'une variable de type DatabaseHeleper
    DatabaseHelper databaseHelper;
    SQLiteOpenHelper openHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        listView =(ListView) findViewById(R.id.list_item);

        //Instance dun objet DatabaseHelper
        databaseHelper = new DatabaseHelper(this );
        ArrayList<String> list = new ArrayList<>();
        Cursor data = databaseHelper.List_Pharmacie();
        if(data.getCount()== 0){
            Toast.makeText(this, "la liste des pharmacie est vide " , Toast.LENGTH_SHORT).show();

        } else {
            while(data.moveToNext()){
                list.add(data.getString(1));
            }
            ListAdapter adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , list);
            listView.setAdapter(adapter);


        }





    }
    public  boolean onQueryTextSubmit(String query){
        return false;
    }

    public boolean onQueryTextChange(String newText){
        adapter.getFilter().filter(newText);
        return false;
    }


}

