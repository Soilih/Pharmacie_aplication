package com.example.wonono;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Espace_Client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espace__client);
    }
    //Méthode qui se déclenchera lorsque vous appuierez sur le bouton menu du téléphone
    public boolean onCreateOptionsMenu(Menu menu) {

        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menu , menu);
        return true;
    }

    //Méthode qui se déclenchera au clic sur un item
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.home:
                Toast.makeText(Espace_Client.this, "Option", Toast.LENGTH_SHORT).show();

            break;
            case R.id.Pharmacie:
                Toast.makeText(Espace_Client.this, "Liste des pharmacie", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext() , Accueil.class);
                startActivity(intent2);
            break;
            case R.id.Horaire:
                Toast.makeText(Espace_Client.this, "Disponibilite des pharmacie", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getApplicationContext() , Horaire.class);
                startActivity(intent3);
                break;

            case R.id.propos:
                Intent intent6 = new Intent(getApplicationContext(), Propos.class);
                startActivity(intent6);
                finish();

                break;
            case R.id.Annuaire:
                Intent intent = new Intent(getApplicationContext(), Contact.class);
                startActivity(intent);

                break;
            case R.id.quitter:
                //Pour fermer l'application il suffit de faire finish()
                finish();

            break;
        }
        return false;
    }

}
