package com.example.wonono;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Update extends AppCompatActivity implements View.OnClickListener {
    //recuperation des widget
    EditText u_nom , u_email , u_detail , u_telephone , u_id , u_ville;
    Button btn_update , btn_search;
    Switch aSwitch2;


    String id , email , nom , telephone , detail , ville;

    //recuperation de la base de donneés
    SQLiteOpenHelper openHelper;
    DatabaseHelper databaseHelper;
    Pharmacie pharmacie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        u_nom = (EditText) findViewById(R.id.update_nom);
        u_telephone = (EditText) findViewById(R.id.update_phone);
        u_email = (EditText) findViewById(R.id.update_email);
        u_detail = (EditText) findViewById(R.id.update_detail);
        u_id = (EditText) findViewById(R.id.id_mod);
        u_ville = (EditText) findViewById(R.id.ville);

        //
        btn_search = (Button) findViewById(R.id.recherche);
        btn_update = (Button) findViewById(R.id.update_pharmacie);
        aSwitch2 = (Switch) findViewById(R.id.switch2);

        btn_update.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        //initialisation de la base de donnes
        databaseHelper = new DatabaseHelper(this);



        //methode d'activation de switch pour quitter cette page
        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch2.isChecked()){

                    Intent intent = new Intent(Update.this , Admin.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_pharmacie:
                id = u_id.getText().toString().trim();
                nom = u_nom.getText().toString();
                email = u_email.getText().toString();
                telephone = u_telephone.getText().toString();
                detail = u_detail.getText().toString();
                ville= u_ville.getText().toString();
                //je teste si les champs sont vides
                if (id.equals("") | nom.equals("") | telephone.equals("") | email.equals("") | detail.equals("")) {
                    Toast.makeText(this, "reseigner les champs ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Long l = Long.parseLong(id);
                    databaseHelper.Update_pharmacie(l, nom, email, telephone, detail,ville);
                    u_nom.setText(" ");
                    u_email.setText(" ");
                    u_telephone.setText("");
                    u_detail.setText("");
                    u_ville.setText("");
                    Toast.makeText(this, "modification est bien reussi  ", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.recherche:
                id = u_id.getText().toString().trim();
                if (id.equals("")) { //si le champs id est vide
                    Toast.makeText(this, "veuillez saisir le champs id ", Toast.LENGTH_SHORT).show();
                } else
                {
                    try {
                        long l1 = Long.parseLong(id);
                        nom = databaseHelper.getNom(l1);
                        email = databaseHelper.getEmail(l1);
                        telephone = databaseHelper.getTel(l1);
                        detail = databaseHelper.getDetail(l1);
                        ville = databaseHelper.getville(l1);

                        u_nom.setText(nom);
                        u_telephone.setText(telephone);
                        u_email.setText(email);
                        u_detail.setText(detail);
                        u_ville.setText(ville);

                        Toast.makeText(this , "seach est bien fait " , Toast.LENGTH_SHORT).show();


                    } catch (Exception e){
                        Toast.makeText(this, "is not Avaible" , Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    public   void showMessage(String title , String Message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}

