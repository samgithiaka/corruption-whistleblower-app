package com.samgithiaka.corruption_whistleblower_app;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.samgithiaka.corruption_whistleblower_app.model.Post;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class reportActivity extends BaseActivity {

    private static final String REQUIRED = "Required";
    private final int PICK_IMAGE_REQUEST = 71;
    private TextView imageView;
    private double lon = 36.825358;
    private double lat = -1.291244;
    private String date;
    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    private EditText reportField;
    private EditText badgeNoField;
    private EditText fullName;
    private EditText emailAddress;
    private EditText locationIncident;
    private String imageString = "";

    ArrayList<Post> reports = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        if (getSupportActionBar() != null) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Report Corruption");
        }
        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference("Reports");
        // [END initialize_database_ref]

        reportField = findViewById(R.id.incidenceReport);
        badgeNoField = findViewById(R.id.badgeNo);
        imageView = findViewById(R.id.imageView);
        Button location2 = findViewById(R.id.locationButton);
        fullName = findViewById(R.id.fullName);
        emailAddress = findViewById(R.id.emailAddress);
        Button submitButton = findViewById(R.id.submitButton);
        Button attachImage = findViewById(R.id.buttonAttachImage);
        locationIncident = findViewById(R.id.locationIncident);


        location2.setOnClickListener(new View.OnClickListener() {


            //choosing the image
            @Override
            public void onClick(View arg0) {

                // Acquire a reference to the system Location Manager
                LocationManager locationManager =
                        (LocationManager) reportActivity.this.getSystemService(LOCATION_SERVICE);
                // Define a listener that responds to location updates
                LocationListener locationListener = new LocationListener() {
                    public void onLocationChanged(Location location) {
                        // Called when a new location is found by the network location provider.
                        lat = (location.getLatitude());
                        lon = (location.getLongitude());

                    }

                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    public void onProviderEnabled(String provider) {
                    }

                    public void onProviderDisabled(String provider) {
                    }

                };

                // Register the listener with the Location Manager to receive location updates
                if (ActivityCompat.checkSelfPermission(reportActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(reportActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                locationIncident.setText("https://maps.google.com/maps?q=" + lat + "," + lon);
            }

        });

        attachImage.setOnClickListener(new View.OnClickListener() {


            //choosing the image
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submitPost();
                return;

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.report_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            returnToPrevious();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        returnToPrevious();
    }

    public void returnToPrevious() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void submitPost() {
        final String report = reportField.getText().toString();
        final String badgeNo = badgeNoField.getText().toString();
        final String image = imageString;
        final String location = locationIncident.getText().toString();
        final String name = fullName.getText().toString();
        final String email = emailAddress.getText().toString();
        final String caseSerialNo = "key";
        final String date = Calendar.getInstance().getTime().toString();
        // Title is required
        if (TextUtils.isEmpty(report)) {
            reportField.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(badgeNo)) {
            badgeNoField.setError(REQUIRED);
            return;
        }
        // Body is required
        if (TextUtils.isEmpty(location)) {
            locationIncident.setError(REQUIRED);
            return;
        }


        mDatabase.child("Reports").addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
// Get Post object and use the values to update the UI
                        Post post = dataSnapshot.getValue(Post.class);
                        writeNewPost(report, badgeNo, image, location, name, email, caseSerialNo, date);
                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                        reports.add(post);
                        Log.w("hey", "getUser:onCancelled" + reports.toString());

                    }

                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("hey", "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    }


    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot){
        reports.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Post post = dataSnapshot.getValue(Post.class);
            reports.add(post);
        }
    }
    //READ THEN RETURN ARRAYLIST
    public ArrayList<Post> retrieve () {
        FirebaseDatabase.getInstance().getReference("Reports").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
            fetchData(dataSnapshot);
                Log.w("hey", "getData" + dataSnapshot.toString());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return reports;
    }

    private void setEditingEnabled(boolean enabled) {
        reportField.setEnabled(enabled);
        badgeNoField.setEnabled(enabled);
        imageView.setEnabled(enabled);
        locationIncident.setEnabled(enabled);
        fullName.setEnabled(enabled);
        emailAddress.setEnabled(enabled);
        if (enabled) {
        } else {
            mProgressDialog.hide();
        }

    }


    // [START write_fan_out]
    private void writeNewPost(String report, String badgeNo, String image, String location, String name, String email, String caseSerialNo, String date) {

        String key = mDatabase.push().getKey();
        caseSerialNo = key;

        Post post = new Post(report, badgeNo, image, location, name, email, caseSerialNo, date);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("" + key, postValues);
        mDatabase.child(key).setValue(post);

        //Alert Dialog

        TextView showText = new TextView(this);
        showText.setText("SUBMITED SUCCESFULLY.. LONG CLICK TO COPY TO CLIPBOARD AND SEND VIA CHAT CASE REF NO;" + key);
// Add the Listener
        showText.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                // Copy the Text to the clipboard
                ClipboardManager manager =
                        (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                TextView showTextParam = (TextView) v;
                manager.setText(showTextParam.getText());
                // Show a message:
                Toast.makeText(v.getContext(), "Text in clipboard",
                        Toast.LENGTH_SHORT)
                        .show();
                return true;
            }
        });
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        dialog.setView(showText);
        dialog.setTitle("PLEASE COPY REF NO");
        dialog.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        finish();

                    }
                });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }
    // [END write_fan_out]


    //set selected image filepath to string visible by user
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageView = findViewById(R.id.imageView);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setText(filePath.toString());
                //change the bitmap to string
                imageString = BitMapToString(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //convert bitmap to string
    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}
