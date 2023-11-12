package com.example.pr16;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class EditContactsActivity extends AppCompatActivity {

    // Model = Taula de contactes: utilitzem ArrayList
    ArrayList<String> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextNom = findViewById(R.id.editTextNom);
        EditText editTextCognoms = findViewById(R.id.editTextCognoms);
        EditText editTextTfn = findViewById(R.id.editTextTfn);
        EditText editTextEmail = findViewById(R.id.editTextEmail);

        // Obtenim la posició del contacte que volem editar
        int receivedContacte = getIntent().getIntExtra("contacte", 0);

        // Inicialitzem model
        contacts = new ArrayList<String>();

        // Botó per guardar un nou contacte a l'arxiu 'contactes.txt'
        Button b1 = findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contacte = editTextNom.getText().toString() + "; " + editTextCognoms.getText().toString() + "; " + editTextTfn.getText().toString() + "; " + editTextEmail.getText().toString();
                try {
                    // Obrim l'arxiu 'contactes.txt' des del directori Assets
                    InputStream inputStream = openFileInput("contactes.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        // Afegim cada línia de l'arxiu a l'ArrayList de contactes
                        contacts.add(line);
                    }

                    inputStream.close();
                    inputStreamReader.close();
                    bufferedReader.close();

                    // Canviem les dades del contacte antigües per les noves
                    contacts.set(receivedContacte, contacte);

                    // Reescrivim l'arxiu 'contactes.txt'
                    FileOutputStream fileOutputStream = getApplicationContext().openFileOutput("contactes.txt", Context.MODE_PRIVATE);
                    for (int i=0; i < contacts.size(); i++) {
                        if (receivedContacte == i) {
                            fileOutputStream.write(contacte.getBytes());
                        } else {
                            fileOutputStream.write(contacts.get(i).getBytes());
                        }
                        fileOutputStream.write(System.lineSeparator().getBytes()); // Afegeim un salt de línia
                    }

                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(), "Dades del contacte actualitzades amb èxit.", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error en actualitzar les dades del contacte.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Botó per obrir la classe 'ListContactsActivity.java'
        Button b2 = findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditContactsActivity.this, ListContactsActivity.class));
            }
        });
    }
}
