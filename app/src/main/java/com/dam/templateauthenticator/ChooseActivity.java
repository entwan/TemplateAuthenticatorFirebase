package com.dam.templateauthenticator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    // Var globales
    private Button btnLogin, btnRegister;


    private void initUI() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        initUI();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, LoginActivity.class);

                String titre = btnLogin.getText().toString();

                intent.putExtra("TitrePage", titre);

                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, RegisterActivity.class);

                String titre = btnRegister.getText().toString();

                intent.putExtra("TitrePage", titre);

                startActivity(intent);
            }
        });

    }
}