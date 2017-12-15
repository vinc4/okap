package com.example.team_leader.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.mukeshsolanki.sociallogin.facebook.FacebookHelper;
import com.mukeshsolanki.sociallogin.facebook.FacebookListener;
import com.mukeshsolanki.sociallogin.google.GoogleHelper;
import com.mukeshsolanki.sociallogin.google.GoogleListener;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity implements FacebookListener, GoogleListener {

    FacebookHelper facebookHelper;
    GoogleHelper    mGoogleHelpler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize facebook sdk
        FacebookSdk.setApplicationId(getResources().getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(this);

        // intialize google firebase
        mGoogleHelpler = new GoogleHelper(this,this,null);

        facebookHelper = new FacebookHelper(this);

        //initialize Buttons
        Button facebook_login = (Button) findViewById(R.id.btnFBL);
        Button googl_login = (Button) findViewById(R.id.btnG);

        facebook_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebookHelper.performSignIn(MainActivity.this);
            }
        });


        googl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mGoogleHelpler.performSignIn(MainActivity.this);
            }
        });
    }

    @Override
    public void onFbSignInFail(String s) {

        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFbSignInSuccess(String s, String userid) {
        Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFBSignOut() {
        Toast.makeText(this, "Good bey !!", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebookHelper.onActivityResult(requestCode,resultCode,data);
        mGoogleHelpler.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onGoogleAuthSignIn(String s, String userid) {
        Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleAuthSignInFailed(String error) {
        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleAuthSignOut() {
        Toast.makeText(this, "Good bey !!", Toast.LENGTH_SHORT).show();
    }
}
