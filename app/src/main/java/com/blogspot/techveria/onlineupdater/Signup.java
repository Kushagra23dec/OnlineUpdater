package com.blogspot.techveria.onlineupdater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

public class Signup extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference();

    private  FirebaseAuth mAuth;

    EditText email, password, fName, lName;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);

        mAuth = FirebaseAuth.getInstance();

    }

    public void onRegister(View view) {
        final String myEmail = email.getText().toString();
        final String myPass = password.getText().toString();

        if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty() && fName.getText().toString().isEmpty() && lName.getText().toString().isEmpty()) {

            email.setError("Email Address");
            password.setError("Password");
            fName.setError("Firstname");
            lName.setError("Lastname");

        }
        else if(fName.getText().toString().isEmpty()){
            fName.setError("Firstname");
        }
        else if(lName.getText().toString().isEmpty()){
            lName.setError("Lastname");
        }

        else if (password.getText().toString().isEmpty()) {

            password.setError("Password");
        }
        else if (email.getText().toString().isEmpty() ) {

            email.setError("Email Address");

        }
        else {

            mAuth.createUserWithEmailAndPassword(myEmail, myPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.i("TAG", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                // Update Details
                                UpdateDetails();



                                Toast.makeText(Signup.this, "Authentication Success.",
                                        Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Signup.this, MainActivity.class);
                                startActivity(i);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.i("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Signup.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }

    public void loginScreen(View view){
        Intent i = new Intent(Signup.this, MainActivity.class);
        startActivity(i);
    }

    private void UpdateDetails(){

        Map<String, String> userData = new HashMap<String, String>();

        userData.put("Email", email.getText().toString());
        userData.put("Password", password.getText().toString());
        userData.put("FirstName", fName.getText().toString());
        userData.put( "LastName" ,lName.getText().toString());

        ref.child(mAuth.getUid()).setValue(userData);


    }
}