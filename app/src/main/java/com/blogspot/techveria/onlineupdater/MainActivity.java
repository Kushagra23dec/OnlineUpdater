package com.blogspot.techveria.onlineupdater;

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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText email, password;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser !=null){
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
    }

//    public void onRegister(View view) {
//        final String myEmail = email.getText().toString();
//        final String myPass = password.getText().toString();
//
//        if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
//
//            email.setError("Email Address");
//            password.setError("Password");
//
//        } else if (password.getText().toString().isEmpty()) {
//
//            password.setError("Password");
//        }
//        else if (email.getText().toString().isEmpty() ) {
//
//            email.setError("Email Address");
//
//        }
//        else {
//
//            mAuth.createUserWithEmailAndPassword(myEmail, myPass)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                Log.i("TAG", "createUserWithEmail:success");
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                Toast.makeText(MainActivity.this, "Authentication Success.",
//                                        Toast.LENGTH_SHORT).show();
//
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Log.i("TAG", "createUserWithEmail:failure", task.getException());
//                                Toast.makeText(MainActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    });
//        }
//    }

    // Login code
    public void onLogin(View view) {
        final String myEmail = email.getText().toString();
        final String myPass = password.getText().toString();

        if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {

            email.setError("Email Address");
            password.setError("Password");

        } else if (password.getText().toString().isEmpty()) {

            password.setError("Password");
        } else if (email.getText().toString().isEmpty() ) {

            email.setError("Email Address");

        }
        else {
            mAuth.signInWithEmailAndPassword(myEmail, myPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.i("TAG", "sighInWithEmailAndPassword:Success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Login Successfully",
                                Toast.LENGTH_SHORT).show();
                        email.setText(null);
                        password.setText(null);
                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
                        startActivity(intent);

                    } else {
                        Log.i("TAG", "sighInWithEmailAndPassword: Failure", task.getException());
                        Toast.makeText(MainActivity.this, "No User Found",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    public void signupScreen(View view){
        Intent inte = new Intent(MainActivity.this, Signup.class);
        startActivity(inte);
    }


}