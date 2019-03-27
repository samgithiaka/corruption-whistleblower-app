package com.example.android.corruption_whistleblower_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView report= findViewById(R.id.report_corruption_text_view);
        report.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent rpt = new Intent(MainActivity.this, reportActivity.class);
                rpt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(rpt);
            }
        })
    ;
        TextView about= findViewById(R.id.about_text_view);
        about.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent abt = new Intent(MainActivity.this, aboutActivity.class);
                abt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(abt);
            }
        })
        ;

        TextView contact= findViewById(R.id.contact_us_text_view);
        contact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent cnt = new Intent(MainActivity.this, contact_us.class);
                cnt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(cnt);
            }
        })
        ;
        TextView postbox= findViewById(R.id.postbox_text_view);
        postbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent pstbox = new Intent(MainActivity.this, AnonymousAuthActivity.class);
                pstbox.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pstbox);
            }
        })
        ;
}
}
