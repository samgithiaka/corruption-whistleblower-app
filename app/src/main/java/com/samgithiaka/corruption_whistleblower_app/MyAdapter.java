package com.samgithiaka.corruption_whistleblower_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.samgithiaka.corruption_whistleblower_app.model.Post;
import com.samgithiaka.corruption_whistleblower_app.databinding.ReportListBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context c;
    ArrayList<Post> reports;
    public MyAdapter(Context c, ArrayList<Post> reports) {
        this.c = c;
        this.reports = reports;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReportListBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.report_list,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final  Post s=reports.get(position);
            holder.binding.setReports(s);


    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

}
