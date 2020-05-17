package com.example.wonono;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wonono.DatabaseHelper;

public class Admin extends AppCompatActivity {
    //Recuperation des donnés
    EditText Edit_nom, Edit_telephone, Edit_email, Edit_detail, Edit_ville;
    Button inserer, update , btn_afficher,btn_delete , Acces_Accueil;

    //Initialisation de la base

    SQLiteOpenHelper openHelper;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        //recuperation des donneés dans fichier xml

        Edit_nom = (EditText) findViewById(R.id.nom);
        Edit_telephone = (EditText) findViewById(R.id.telephone);
        Edit_email = (EditText) findViewById(R.id.email);
        Edit_detail = (EditText) findViewById(R.id.detail);
        Edit_ville =(EditText) findViewById(R.id.ville);

        databaseHelper = new DatabaseHelper(this);

        inserer = (Button) findViewById(R.id.inserer);
        update = (Button) findViewById(R.id.update);
        btn_afficher = (Button) findViewById(R.id.lire);
        btn_delete = (Button) findViewById(R.id.delete);
        Acces_Accueil = (Button) findViewById(R.id.Accueil);
        //j'ai appeler la methode pour ajouter dans la base de donnes
        AddData();
        //j'appelle cette methode pour lister les pharmacie
        View_Pharmacie();
        //si on clique cette boutton on passse à l'activiter permettant de suprimer
        //une pharmacie
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this , Delete.class);
                startActivity(intent);
                finish();
            }
         });
        //si on clique cette boutton on passse à l'activité permettant de modifier une
        //une pharmacie
       update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this , Update.class);
                startActivity(intent);
                finish();
            }
        });
        //si on clique cette boutton on accede à l'espace client
        Acces_Accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this , Espace_Client.class);
               startActivity(intent);
                finish();
            }
        });




    }
    //Mehode d'ajout d'une pharmacie
    public void AddData() {
        inserer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = Edit_nom.getText().toString();

                String email = Edit_email.getText().toString();
                String telephone = Edit_telephone.getText().toString();
                String detail = Edit_detail.getText().toString();
                 String ville = Edit_ville.getText().toString();
                if((TextUtils.isEmpty(nom)) | (TextUtils.isEmpty(email)) | (TextUtils.isEmpty(telephone)) | (TextUtils.isEmpty(detail))){
                    Toast.makeText(Admin.this ,"veuillez renseigner tous les champs  " , Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean isInserted = databaseHelper.insertPharmacie(nom, email, telephone, detail,ville);
                    if (isInserted == true) {
                        Toast.makeText(Admin.this, " pharmacie est bien enregistre avec succeés ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    //methode qui permet d'afficher la liste des pharmacie

    public void View_Pharmacie(){
        btn_afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor resultat = databaseHelper.List_Pharmacie();
                if(resultat.getCount()==0) {
                    Toast.makeText(Admin.this , "le nombre de pharmacie est vide " , Toast.LENGTH_SHORT).show();

                }
                else{
                    StringBuffer buffer = new StringBuffer();
                    while (resultat.moveToNext()){
                        buffer.append(resultat.getString(0) +"\n");
                        buffer.append(resultat.getString(1)+"\n");
                        buffer.append(resultat.getString(3) +"\n");
                        buffer.append(resultat.getString(2) +"\n");
                        buffer.append(resultat.getString(4) +"\n" );
                        buffer.append(resultat.getString(5) +"\n" );
                    }
                    showMessage("Liste de pharmacie " , buffer.toString());

                }
            }
        });
    }
    //methode qui permet d'afficher un modal au clique du boutton lire plus et
    //appeler la methode View_Pharmacie()

    public   void showMessage(String title , String Message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}









