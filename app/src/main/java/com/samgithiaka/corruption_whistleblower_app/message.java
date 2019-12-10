package com.samgithiaka.corruption_whistleblower_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import io.smooch.core.Settings;
import io.smooch.core.Smooch;
import io.smooch.core.SmoochCallback;
import io.smooch.ui.ConversationActivity;

public class message extends Activity {
    String APP_ID = getString(R.string.APP_ID);
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Smooch.init(getApplication(), new Settings(APP_ID), new SmoochCallback() {
            @Override
            public void run(Response response) {
                // Handle init result
            }
        });
        setContentView(R.layout.activity_message);

        Button cht = findViewById(R.id.buttonChat);
        cht.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                ConversationActivity.show(message.this, Intent.FLAG_ACTIVITY_NEW_TASK);

            }
        });

        Button signout = findViewById(R.id.buttonAnonymousSignOut);
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