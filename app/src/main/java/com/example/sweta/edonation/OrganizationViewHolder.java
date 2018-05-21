package com.example.sweta.edonation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class OrganizationViewHolder extends RecyclerView.ViewHolder {

    View view;

    public OrganizationViewHolder(View itemView) {
        super(itemView);
        view=itemView;
    }

    public void setDetails(Context ctx, String userName, String userStatus, String userImage){

        TextView user_name = (TextView) view.findViewById(R.id.orgName);
        TextView user_status = (TextView) view.findViewById(R.id.location);
        TextView email = (TextView) view.findViewById(R.id.emailTxt);
        Button websiteBtn = view.findViewById(R.id.btnWebsite);
        Button callBtn = view.findViewById(R.id.callBtn);
    }
}
