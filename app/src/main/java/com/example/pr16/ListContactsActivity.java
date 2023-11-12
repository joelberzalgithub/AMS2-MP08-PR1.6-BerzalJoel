package com.example.pr16;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class ListContactsActivity extends AppCompatActivity {

    // Model = Taula de contactes: utilitzem ArrayList
    ArrayList<String> contacts;
    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);

        // Inicialitzem model
        contacts = new ArrayList<String>();

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

        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, contacts) {
            @NonNull
            public View getView(int pos, View convertView, @NonNull ViewGroup container) {

                // GetView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if (convertView == null) {
                    // Inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" els valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.contact)).setText(contacts.get(pos));
                return convertView;
            }
        };

        // Busquem la ListView i li endollem l'ArrayAdapter
        ListView lv = findViewById(R.id.contactsView);
        lv.setAdapter(adapter);
    }
}
