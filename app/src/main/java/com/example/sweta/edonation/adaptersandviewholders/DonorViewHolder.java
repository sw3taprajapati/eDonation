package com.example.sweta.edonation.adaptersandviewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sweta.edonation.R;

public class DonorViewHolder extends RecyclerView.ViewHolder {

    TextView donorName, donorLocation, currentDonation;
    Button orgWebsite, orgCall;
    RelativeLayout relativeLayout;
    public DonorViewHolder(View itemView) {
        super(itemView);
        donorName = itemView.findViewById(R.id.donorName);
        donorLocation = itemView.findViewById(R.id.location);
        currentDonation = itemView.findViewById(R.id.donatedItems);
        relativeLayout = itemView.findViewById(R.id.relativeLayoutDonor);
    }
}
