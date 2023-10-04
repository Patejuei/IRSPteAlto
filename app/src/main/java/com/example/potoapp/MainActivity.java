package com.example.potoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

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
    }
}