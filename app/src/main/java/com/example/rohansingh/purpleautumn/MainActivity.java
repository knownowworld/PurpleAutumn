package com.example.rohansingh.purpleautumn;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthstateListner;
    BottomNavigationView NavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mAuthstateListner= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    Intent intent;
                    intent = new Intent(MainActivity.this,LogInAndSignUpActivity.class);
                    startActivity(intent);
                }
            }
        };

        NavigationBar=(BottomNavigationView)findViewById(R.id.navigationBar);
        NavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.logout){
                        FirebaseAuth.getInstance().signOut();
                        return false;

                }
                else return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthstateListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthstateListner!=null)
            mAuth.removeAuthStateListener(mAuthstateListner);
    }
}

