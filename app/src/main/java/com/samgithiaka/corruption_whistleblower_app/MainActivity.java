package com.samgithiaka.corruption_whistleblower_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.samgithiaka.corruption_whistleblower_app.databinding.ActivityMainBinding;
import com.testfairy.TestFairy;

import java.util.Objects;

public class MainActivity extends BaseActivity {
    Toolbar myToolbar;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestFairy.begin(this, BuildConfig.TEST_FAIRY_KEY);
        setContentView(R.layout.activity_main);

      binding.reportCorruptionTextView.setOnClickListener(v -> proceedToReporting());
      binding.aboutTextView.setOnClickListener(v -> proceedToAbout());
      binding.contactUsTextView.setOnClickListener(v -> proceedToContactUs());
      binding.postboxTextView.setOnClickListener(v -> proceedToPostBox());
      binding.recView.setOnClickListener(v -> proceedToPastReports());
    }

    private void proceedToPastReports() {
        startActivity(new Intent(MainActivity.this, PastReports.class));
    }

    private void proceedToPostBox() {
        startActivity(new Intent(MainActivity.this, AnonymousAuthActivity.class));
    }

    private void proceedToContactUs() {
        startActivity(new Intent(MainActivity.this, contact_us.class));
    }

    public void proceedToReporting() {
         startActivity(new Intent(MainActivity.this, reportActivity.class));
    }

    public void proceedToAbout() {
        startActivity(new Intent(MainActivity.this, aboutActivity.class));
    }
    public void hideMainToolbar(boolean isHidden) {
        if (isHidden) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        } else {
            Objects.requireNonNull(getSupportActionBar()).isShowing();
            setSupportActionBar(myToolbar);
        }
    }
}
