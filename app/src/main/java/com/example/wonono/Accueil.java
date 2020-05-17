package com.example.wonono;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Message;
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

public class Accueil extends AppCompatActivity {
    //declaration des variables

    ListView listView;
   // SearchView searchView; //search
    ArrayAdapter adapter;

    ArrayList<String> listItem ; //list


    //declaration d'une variable de type DatabaseHeleper
    DatabaseHelper databaseHelper;
    SQLiteOpenHelper openHelper;
    private Object Message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        listView =(ListView) findViewById(R.id.list_item);

      //Instance dun objet DatabaseHelper
        databaseHelper = new DatabaseHelper(this );
         listItem  = new ArrayList<>();

        viewData();
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String text = listView.getItemAtPosition(position).toString();
               //fait appel à la methode search_pharmacie_Name
               //si l'utilisateur clique sur une pharmacie pour pouvoir visualiser les details
               Cursor res = databaseHelper.Search_pharmacie_Name(text);
               String data = null;
               if(res.moveToFirst()){

                   data =  "ID : " + res.getString(0)+"\n"+
                           "Nom  : " + res.getString(1)+"\n" + "\n"+
                           "tel :"  + res.getString(3)+"\n"+
                           "email : "  + res.getString(2)+"\n" + "\n"+
                           "se trouve à  : " + res.getString(5)+"\n" + "\n"+
                           "Adresse   : " + res.getString(4)+"\n";
               }

                //appel
               showMessage("la pharmacie : " , data );
               Toast.makeText(Accueil.this, ""+text, Toast.LENGTH_SHORT).show();
         }
       });
    }

    //methode qui permet d'afficher un modal au clique du boutton lire plus et
    //appeler la methode View_Pharmacie()
    public   void showMessage(String title , String Message )
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    public void viewData(){
            Cursor data = databaseHelper.List_Pharmacie();

            if(data.getCount()== 0){
                Toast.makeText(this, "la liste des pharmacie est vide " , Toast.LENGTH_SHORT).show();

            } else {
                while(data.moveToNext()){
                    listItem.add(data.getString(1));
                }
                ListAdapter adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , listItem);
                listView.setAdapter(adapter);
            }

        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search , menu );
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
         SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                ArrayList<String> listview1 = new ArrayList<>();
                for(String list_p : listItem){
                    if(list_p.toLowerCase().contains(newText.toLowerCase())){
                        listview1.add(list_p);

                    }

                }
               ArrayAdapter<String> adapter = new ArrayAdapter<String>(Accueil.this , android.R.layout.simple_list_item_1 , listview1 );
                listView.setAdapter(adapter);

                return true;
             }
         });

        return super.onCreateOptionsMenu(menu);
    }
}
