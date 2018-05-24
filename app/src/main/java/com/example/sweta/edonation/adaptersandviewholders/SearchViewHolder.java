package com.example.sweta.edonation.adaptersandviewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sweta.edonation.R;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView orgName;
    TextView location ;
    TextView email ;
    Button websiteBtn;
    Button callBtn;

    public SearchViewHolder(View itemView) {
        super(itemView);

        orgName =(TextView) view.findViewById(R.id.orgName);
        location = (TextView) view.findViewById(R.id.location);
        email = (TextView) view.findViewById(R.id.emailTxt);
        websiteBtn = view.findViewById(R.id.btnWebsite);
        callBtn = view.findViewById(R.id.callBtn);
    }
}
