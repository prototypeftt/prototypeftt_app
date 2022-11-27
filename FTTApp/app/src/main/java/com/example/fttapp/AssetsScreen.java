package com.example.fttapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AssetsScreen extends AppCompatActivity {

    Spinner spinner;
    Query assetQuery;
    ArrayList<String> spinnerList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    RecyclerView mRecyclerView;
    DatabaseReference assetRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assets_screen);

        spinner = findViewById(R.id.assetTypeSpinner);
        adapter = new ArrayAdapter<String>(AssetsScreen.this, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        spinner.setAdapter(adapter);
        spinnerList.add("CRYPTO");
        spinnerList.add("STOCK");
        mRecyclerView = findViewById(R.id.recyclerAssets);
        assetRef = FirebaseDatabase.getInstance().getReference("assets");
        ShowData();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String selectedType;
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedType = spinner.getSelectedItem().toString();
                assetQuery = FirebaseDatabase.getInstance().getReference("assets/" + selectedType);
                new FirebaseDatabaseHelper(assetQuery).readAssets((assets, keys) -> new RecyclerViewConfig().setConfigAsset(mRecyclerView, AssetsScreen.this, assets, keys));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void ShowData(){
        assetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}