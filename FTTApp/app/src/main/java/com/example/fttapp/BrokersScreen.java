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

public class BrokersScreen extends AppCompatActivity {

    Spinner spinner;
    DatabaseReference institutionRef;
    Query brokersRef;
    ArrayList<String> spinnerList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brokers_screen);

        spinner = findViewById(R.id.brokersSpinner);
        institutionRef = FirebaseDatabase.getInstance().getReference("institutions");
        adapter = new ArrayAdapter<String>(BrokersScreen.this, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        spinner.setAdapter(adapter);
        ShowData();
        mRecyclerView = findViewById(R.id.recyclerBrokers);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String selectedInstitution;
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedInstitution = spinner.getSelectedItem().toString();
                brokersRef = FirebaseDatabase.getInstance().getReference("brokers").
                        orderByChild("institution").equalTo(selectedInstitution);
                new FirebaseDatabaseHelper(brokersRef).readBrokers((brokers, keys) -> new RecyclerViewConfig().setConfigBroker(mRecyclerView, BrokersScreen.this, brokers, keys));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void ShowData(){
        institutionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item: snapshot.getChildren()){
                    spinnerList.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}