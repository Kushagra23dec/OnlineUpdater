package com.blogspot.techveria.onlineupdater;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextView se;

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

        se = findViewById(R.id.server);



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
}