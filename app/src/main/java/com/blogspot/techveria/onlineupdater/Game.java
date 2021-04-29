package com.blogspot.techveria.onlineupdater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Game extends AppCompatActivity {




    // Write a message to the database
    private  FirebaseAuth mAuth;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference myRef = db.getReference();

    DatabaseReference gameRef = myRef.child("game");


    TextView dName;

    TextView textView;
    Button rock,paper,scissor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textView = findViewById(R.id.displayText);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissor = findViewById(R.id.scissor);


        dName = findViewById(R.id.dName);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

//        gameRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String text = snapshot.getValue().toString();
//                textView.setText(text);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//                Log.i("TAG", "Something is wrong");
//            }
//        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dName.setText(snapshot.child(mAuth.getUid()).child("FirstName").getValue().toString()+ "\n"+ snapshot.child(mAuth.getUid()).child("LastName").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void Gameon(View view){


        switch(view.getId()){


            case R.id.rock:
                 gameRef.setValue("Rock");
                gameRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String text = snapshot.getValue().toString();
                        textView.setText(text);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Log.i("TAG", "Something is wrong");
                    }
                });
                break;
            case R.id.paper:
                 gameRef.setValue("Paper");
                gameRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String text = snapshot.getValue().toString();
                        textView.setText(text);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Log.i("TAG", "Something is wrong");
                    }
                });
                break;

            case R.id.scissor:
                 gameRef.setValue("Scissor");
                gameRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String text = snapshot.getValue().toString();
                        textView.setText(text);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Log.i("TAG", "Something is wrong");
                    }
                });
                break;

            default:
                Toast.makeText(this,"Something Went Wrong!",Toast.LENGTH_SHORT).show();

        }

    }


}