package com.samgithiaka.corruption_whistleblower_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class contact_us extends Activity {
    private String subject = getString(R.string.email_head);
    private String body = getString(R.string.message);
    private String email = getString(R.string.email_address);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        ImageView location = findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("geo:0,0?q=Integrity Centre,Jakaya Kikwete/Valley Road Junction");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage(getString(R.string.MAPS_PACKAGE));
            }
        })
        ;

        ImageView email_us = findViewById(R.id.email_button);
        email_us.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + subject + "&body=" + body + "&to=" + email);
                intent.setData(data);
                startActivity(intent);
            }
        })
        ;
        ImageView call_us = findViewById(R.id.call_us);
        call_us.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0729 888881"));
                startActivity(intent);
            }
        })
        ;
    }
}