package com.samgithiaka.corruption_whistleblower_app;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.samgithiaka.corruption_whistleblower_app.databinding.ReportListBinding;

public class PastReports extends reportActivity {
    DatabaseReference mDatabase;
    reportActivity helper;
    MyAdapter adapter;
    RecyclerView rv;
    ReportListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_reports);

        //SETUP RECYCLER
        rv = (RecyclerView) findViewById(R.id.report_list);
        rv.setLayoutManager(new LinearLayoutManager(this));

        //INITIALIZE FIREBASE DB
        mDatabase= FirebaseDatabase.getInstance().getReference("Reports");
        helper=new reportActivity();

        //ADAPTER
        adapter=new MyAdapter(PastReports.this,helper.retrieve());
        rv.setAdapter(adapter);


    }
}
