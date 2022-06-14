package com.dam.templateauthenticator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";

    private EditText etStEmail, etStPassword;

    private FirebaseAuth mAuth;

    public void initUI() {
        etStEmail = findViewById(R.id.etStEmail);
        etStPassword = findViewById(R.id.etStPassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();

        Intent intent = getIntent();
        String titre = intent.getStringExtra("TitrePage");
        setTitle(titre);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            //reload();
        }
    }

    public void login(View view) {
        String email = etStEmail.getText().toString();
        String password = etStPassword.getText().toString();

//        Log.i(TAG, "register: email : " + email);
//        Log.i(TAG, "register: password : " + password);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            // on ferme l'activité
                            finish();
                        } else {
                            Log.w(TAG, "onComplete: signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
}