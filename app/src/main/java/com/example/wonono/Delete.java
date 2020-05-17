package com.example.wonono;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.wonono.Admin;
import com.example.wonono.DatabaseHelper;

public class Delete extends AppCompatActivity {
    //delaration des variable

    SQLiteOpenHelper openHelper;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Switch aSwitch;
    EditText Edit_id_pharmacie ;
    Button btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        databaseHelper = new DatabaseHelper(this);
        aSwitch =(Switch) findViewById(R.id.switch1);
        Edit_id_pharmacie  =(EditText) findViewById(R.id.id_pharmacie);
        btn_delete =(Button) findViewById(R.id.button);

        DeletePharmacie();

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch.isChecked()){

                    Intent intent = new Intent(Delete.this , Admin.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }
    public void DeletePharmacie(){
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_p = Edit_id_pharmacie.getText().toString();
                if(TextUtils.isEmpty(id_p)){
                    Toast.makeText(Delete.this , "saisir le id " , Toast.LENGTH_SHORT).show();
                    return;

                }
                Integer deletedRows = databaseHelper.delete_Pharmacie(Edit_id_pharmacie.getText().toString());
                if(deletedRows>0) {
                    Toast.makeText(Delete.this , "Pharmacie est bien suprimé avec succés " , Toast.LENGTH_LONG).show();
                    Edit_id_pharmacie.setText("");
                }
                else{
                    Toast.makeText(Delete.this , "ce pharmacie n'existe pas " , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
