package com.example.rohansingh.purpleautumn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInAndSignUpActivity extends AppCompatActivity {

    public ViewPager Slide;
    LinearLayout dotLayout;
    TextView[] mdots;
    Button gotoLoginPageButton;
    Button loginButton;
    EditText EmailEditText;
    EditText passwordEditText;
    FirebaseAuth mAuth;
    ProgressDialog loginDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_and_sign_up);
        // code for setting up the view pager
        Slide=(ViewPager)findViewById(R.id.LogInViewPager);
        dotLayout=(LinearLayout)findViewById(R.id.DotsForViewPagerLinearLayout);
        sliderAdapter adapter = new sliderAdapter(this);
        Slide.setAdapter(adapter); // setting the adapter
        AddDotsIndicator(0);// for the sliding dots
        Slide.addOnPageChangeListener(viewListener);

        mAuth= FirebaseAuth.getInstance();
        // code for the signup and login
        loginDialog=new ProgressDialog(LogInAndSignUpActivity.this);
        loginDialog.setTitle("Signing In");
        loginDialog.setMessage("wait while we sign you in");
        loginDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loginDialog.setCanceledOnTouchOutside(false);
        EmailEditText=(EditText)findViewById(R.id.usernameEditText);
        passwordEditText=(EditText)findViewById(R.id.passwordEditText);
        loginButton =(Button)findViewById(R.id.signINbutton);
        gotoLoginPageButton=(Button)findViewById(R.id.logInButton);
        gotoLoginPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout source = (LinearLayout)findViewById(R.id.loginHomeLayout);
                LinearLayout destination=(LinearLayout)findViewById(R.id.loginLayout);
                source.setVisibility(View.INVISIBLE);
                destination.setVisibility(View.VISIBLE);

            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDialog.show();
                String email = EmailEditText.getText().toString();
                String password= passwordEditText.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LogInAndSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            loginDialog.dismiss();
                        }
                        else{
                            Toast.makeText(LogInAndSignUpActivity.this, "login falied", Toast.LENGTH_SHORT).show();
                            loginDialog.dismiss();
                        }
                    }
                });
            }
        });

    }
    public void AddDotsIndicator(int position){

        mdots=new TextView[3];
        dotLayout.removeAllViews();
        for(int i=0;i<mdots.length;i++) {

            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(45);
            mdots[i].setTextColor(getResources().getColor(R.color.fadedWhite));

            dotLayout.addView(mdots[i]);
        }
            if(mdots.length>0){
                mdots[position].setTextColor(getResources().getColor(R.color.pureWhite));
            }

    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            AddDotsIndicator(position);
            //Toast.makeText(LogInAndSignUpActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
