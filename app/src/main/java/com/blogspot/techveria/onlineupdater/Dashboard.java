package com.blogspot.techveria.onlineupdater;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    private FirebaseAuth mAuth;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dref = db.getReference();


    TextView dEmail,dFirst,dLast;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dEmail = findViewById(R.id.dEmail);
        dFirst = findViewById(R.id.dFirst);
        dLast = findViewById(R.id.dLast);


        DisplayDetails();

        mAuth = FirebaseAuth.getInstance();

        //se.setText(" " +FirebaseAuth.getInstance().getUid());

        // se.setText(" "+FirebaseAuth.getInstance().getCurrentUser()+"  -> "+FirebaseAuth.getInstance().getUid()+" -> IOjtAvytiMS5zn9YRyGRHLHnRWU2 ");
    }

    public void logout (View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(Dashboard.this, "Bye!!!",
                Toast.LENGTH_SHORT).show();

        Intent in = new Intent(Dashboard.this, MainActivity.class);

         startActivity(in);

    }

    public void onGame(View view){
        Intent i = new Intent(Dashboard.this, Game.class);
        startActivity(i);
    }


    private void DisplayDetails(){


        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                dFirst.setText("FirstName: "+ snapshot.child(mAuth.getUid()).child("FirstName").getValue().toString());
                dLast.setText("LastName: "+ snapshot.child(mAuth.getUid()).child("LastName").getValue().toString());
                dEmail.setText("Email: "+ snapshot.child(mAuth.getUid()).child("Email").getValue().toString());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("TAG", "Something is wrong");
            }
        });


    }


}