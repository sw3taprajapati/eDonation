package com.example.sweta.edonation.adaptersandviewholders;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sweta.edonation.R;

class ListViewHolder extends RecyclerView.ViewHolder {

    TextView orgName, orgLocation, currentRequirement;
    Button orgWebsite, orgCall;
    RelativeLayout relativeLayout;

    public ListViewHolder(View itemView) {
        super(itemView);

        orgName = itemView.findViewById(R.id.orgName);
        orgLocation = itemView.findViewById(R.id.location);
        currentRequirement = itemView.findViewById(R.id.needTxt);
        relativeLayout = itemView.findViewById(R.id.relativeLayout);
    }
}