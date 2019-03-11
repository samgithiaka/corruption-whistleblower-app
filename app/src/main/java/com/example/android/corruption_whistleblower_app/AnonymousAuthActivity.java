package com.example.android.corruption_whistleblower_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Activity to demonstrate anonymous login and account linking (with an email/password account).
 */
public class AnonymousAuthActivity extends BaseActivity implements
        View.OnClickListener {

    private static final String TAG = "AnonymousAuth";

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    //private EditText mEmailField;
   // private EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postbox);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // Fields
       // mEmailField = findViewById(R.id.fieldEmail);
      //  mPasswordField = findViewById(R.id.fieldPassword);

        // Click listeners
        findViewById(R.id.buttonAnonymousSignIn).setOnClickListener(this);
        //findViewById(R.id.buttonAnonymousSignOut).setOnClickListener(this);
      //  findViewById(R.id.buttonLinkAccount).setOnClickListener(this);
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
      //  FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }
    // [END on_start_check_user]

    private void signInAnonymously() {
        showProgressDialog();
        // [START signin_anonymously]
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(AnonymousAuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END signin_anonymously]
    }

    public void signOut() {

        mAuth.signOut();
        updateUI(null);

    }




    public void updateUI(FirebaseUser user) {
        hideProgressDialog();

        TextView idView = findViewById(R.id.anonymousStatusId);
        //TextView emailView = findViewById(R.id.anonymousStatusEmail);
        boolean isSignedIn = (user != null);

        // Status text
        if (isSignedIn) {


                    Intent msg = new Intent(AnonymousAuthActivity.this, message.class);
                   msg.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(msg);

            //idView.setText(user.getUid());
            //emailView.setText(user.getEmail());
        } else {
            idView.setText("signed out");
           // emailView.setText(null);
        }

        // Button visibility
        findViewById(R.id.buttonAnonymousSignIn).setEnabled(!isSignedIn);
        findViewById(R.id.buttonAnonymousSignOut).setEnabled(isSignedIn);
      //  findViewById(R.id.buttonLinkAccount).setEnabled(isSignedIn);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.buttonAnonymousSignIn) {
            signInAnonymously();
        } else if (i == R.id.buttonAnonymousSignOut) {
            signOut();

    }
}}
