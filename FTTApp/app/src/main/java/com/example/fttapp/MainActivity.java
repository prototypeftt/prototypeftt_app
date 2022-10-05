package com.example.fttapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        changeActivity();
    }

    private void changeActivity(){
        Button LogInButton = (Button) findViewById(R.id.LogInButton);
        Button ToRegisterButton = (Button) findViewById(R.id.ToRegisterButton);
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainScreen.class));
            }
        });
        ToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterScreen.class));
            }
        });
    }
}