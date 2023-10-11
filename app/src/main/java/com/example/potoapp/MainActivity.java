package com.example.potoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText servNumber, servAddress, servReference, servOBAC;
    Spinner comboBoxService;
    String[] servicios = new String[]{"Básico", "Fuego", "HazMat", "Medico"};
    ListView servicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Conexión de variables con UI
        servNumber = findViewById(R.id.servNumber);
        servAddress = findViewById(R.id.servAddres);
        servReference = findViewById(R.id.servReference);
        servOBAC = findViewById(R.id.servOBAC);
        comboBoxService = findViewById(R.id.comboBoxService);
        servicesList = findViewById(R.id.servicesList);

        // Poblar comboBox
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, servicios);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboBoxService.setAdapter(spinnerAdapter);

        // Cargar Lista
        CargarLista();
    }

    public void onClickAgregar(View view) {
        DataHelper dh = new DataHelper(this, "IRSptealto.db", null, 1);
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        reg.put("id", servNumber.getText().toString());
        reg.put("address", servAddress.getText().toString());
        reg.put("reference", servReference.getText().toString());
        reg.put("obac", servOBAC.getText().toString());
        reg.put("type", comboBoxService.getSelectedItem().toString());
        long resp = db.insert("servicio", null, reg);
        db.close();
        if (resp == -1) {
            Toast.makeText(this, "Error en registrar el servicio", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Servicio registrado con éxito", Toast.LENGTH_SHORT).show();
        }
        CargarLista();

    }

    public void CargarLista(){
        DataHelper dh = new DataHelper(this, "IRSptealto.db", null, 1);
        SQLiteDatabase db = dh.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT id, address, reference, type FROM servicio", null);
        String[] arr = new String[c.getCount()];
        if(c.moveToFirst()){
            int i = 0;
            do{
                String linea = "" + c.getInt(0) + " | " + c.getString(1) + " esq. " + c.getString(2) + " | " + c.getString(
                        3
                );
                arr[i] = linea;
                i++;
            }while(c.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_expandable_list_item_1, arr
        );
        servicesList.setAdapter(adapter);
        c.close();
    }
}