package com.example.pr16;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextNom = findViewById(R.id.editTextNom);
        EditText editTextCognoms = findViewById(R.id.editTextCognoms);
        EditText editTextTfn = findViewById(R.id.editTextTfn);
        EditText editTextEmail = findViewById(R.id.editTextEmail);

        // Botó per guardar un nou contacte a l'arxiu 'contactes.txt'
        Button b1 = findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contacte = editTextNom.getText().toString() + "; " + editTextCognoms.getText().toString() + "; " + editTextTfn.getText().toString() + "; " + editTextEmail.getText().toString();
                try {
                    FileOutputStream fileOutputStream = getApplicationContext().openFileOutput("contactes.txt", Context.MODE_APPEND);
                    fileOutputStream.write(contacte.getBytes()); // Escrivim les dades d'un nou contacte
                    fileOutputStream.write(System.lineSeparator().getBytes()); // Afegeim un salt de línia
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(), "Dades del contacte guardades amb èxit.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error en guardar les dades del contacte.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botó per obrir la classe 'ListContactsActivity.java'
        Button b2 = findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListContactsActivity.class));
            }
        });
    }
}
