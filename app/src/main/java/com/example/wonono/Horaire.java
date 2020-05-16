package com.example.wonono;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Horaire extends AppCompatActivity {
    TextView textview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire);
        textview1 =(TextView) findViewById(R.id.textview3);

        DatabaseHelper db = new DatabaseHelper(this);
        String data = db.getHoraire();

        textview1.setText(data);
        textview1.setMovementMethod(new ScrollingMovementMethod());

    }
}
