package com.samgithiaka.corruption_whistleblower_app;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samgithiaka.corruption_whistleblower_app.databinding.ReportListBinding;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
  protected ReportListBinding binding;

    public MyViewHolder(@NonNull ReportListBinding reportListBinding) {
        super(reportListBinding.getRoot());
this.binding = reportListBinding;
/*
        name= (TextView) itemView.findViewById(R.id.name);
         email= (TextView) itemView.findViewById(R.id.email);
         badgeNo= (TextView) itemView.findViewById(R.id.badgeNo);*/
    }

    @Override
    public void onClick(View v) {

    }
}
