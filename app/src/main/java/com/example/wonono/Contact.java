package com.example.wonono;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Contact<Chooser> extends AppCompatActivity {
    TextView textView;
    EditText phone;
    String numero_telephone;
    ImageButton btn_img_call;
    ImageButton btn_img_email;
    Chooser chooser= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        textView = (TextView) findViewById(R.id.textView5);
        btn_img_call = (ImageButton) findViewById(R.id.imageButton2);
        btn_img_email = (ImageButton) findViewById(R.id.imageButton3);

        btn_img_call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent(Intent.ACTION_CALL);
                numero_telephone = phone.getText().toString();
                //test si le champs est vide
                if (numero_telephone.trim().isEmpty()) {
                    intentCall.setData(Uri.parse("tel: "));
                    Toast.makeText(Contact.this, "sasir le numero ", Toast.LENGTH_SHORT).show();
                }
                else {
                    intentCall.setData(Uri.parse("tel: " + numero_telephone));
                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != getPackageManager().PERMISSION_GRANTED) {
                    Toast.makeText(Contact.this, "please grant permission", Toast.LENGTH_SHORT).show();
                    requestPermissions();
                } else {
                    startActivity(intentCall);
                }
            }
        });

        btn_img_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("SOILIH : "));
                String[] to ={"xxxx@gmail.com" ,"aaaaa@gmail.com"};
                intent.putExtra(intent.EXTRA_EMAIL , to);
                intent.putExtra(intent.EXTRA_SUBJECT , "je suis ravi");
                intent.putExtra(intent.EXTRA_TEXT , "MON projet m'a donné l'enve de continuer");
                intent.setType("message/rfc822");
                chooser = (Chooser) Intent.createChooser(intent , "send mail");
                startActivity((Intent) chooser);

            }
        });

        DatabaseHelper db = new DatabaseHelper(this);
        String data = db.getAnnuaire();
        textView.setText(data);
        textView.setMovementMethod(new ScrollingMovementMethod());

    }
        private void requestPermissions(){
            ActivityCompat.requestPermissions(Contact.this , new String[] {Manifest.permission.CALL_PHONE} , 1);
        }


 }

