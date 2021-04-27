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

import java.util.SimpleTimeZone;

public class Signup extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference();
    DatabaseReference Fnref = ref.child("FirstName");
    DatabaseReference Lnref = ref.child("LastName");
    DatabaseReference Eref = ref.child("Email");
    DatabaseReference Pref = ref.child("Password");



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

//// Read from the database
//        rootref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.i("TAG", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.i("TAG", "Failed to read value.", error.toException());
//            }
//        });

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
                                
                                Fnref.setValue(fName.getText().toString());
                                Lnref.setValue(lName.getText().toString());
                                Eref.setValue(email.getText().toString());
                                Pref.setValue(password.getText().toString());

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

}