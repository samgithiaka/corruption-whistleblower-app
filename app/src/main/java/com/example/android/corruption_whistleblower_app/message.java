package com.example.android.corruption_whistleblower_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class message extends Activity{
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


          Button signout= findViewById(R.id.buttonAnonymousSignOut);
        signout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent main = new Intent(message.this, MainActivity.class);
                //main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                message.this.finish();
                //signOut();
            }

        })
        ;

    }
}