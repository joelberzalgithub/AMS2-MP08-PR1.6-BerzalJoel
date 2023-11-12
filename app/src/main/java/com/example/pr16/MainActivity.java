package com.example.pr16;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextNom = findViewById(R.id.editTextNom);
        EditText editTextCognoms = findViewById(R.id.editTextCognoms);
        EditText editTextTfn = findViewById(R.id.editTextTfn);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = editTextNom.getText().toString();
                String cognoms = editTextCognoms.getText().toString();
                String tfn = editTextTfn.getText().toString();
                String email = editTextEmail.getText().toString();

                String contacte = nom + "; " + cognoms + "; " + tfn + "; " + email;

                try {
                    FileOutputStream fileOutputStream = getApplicationContext().openFileOutput("contactes.txt", Context.MODE_APPEND);
                    fileOutputStream.write(contacte.getBytes());
                    fileOutputStream.write(Objects.requireNonNull(System.getProperty("line.separator")).getBytes());  // Afegeim un salt de línia
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(), "Dades del contacte guardades amb èxit.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error en guardar les dades del contacte.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
