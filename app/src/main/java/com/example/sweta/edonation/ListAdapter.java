package com.example.sweta.edonation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> implements View.OnClickListener {

    Context context;
    List<Organization> organizationList;

    public ListAdapter(Context context, List<Organization> organizationList) {
        this.context = context;
        this.organizationList = organizationList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_view, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        Organization organization = organizationList.get(position);

        holder.orgName.setText(organization.getOrgFullName());
        holder.orgLocation.setText(organization.getOrgLocation());
        holder.orgEmail.setText(organization.getOrgEmailID());
        holder.orgWebsite.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return organizationList.size();
    }

    @Override
    public void onClick(View v) {

    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView orgName,orgLocation, orgEmail;
        Button orgWebsite, orgCall;

        public ListViewHolder(View itemView) {
            super(itemView);

            orgName = itemView.findViewById(R.id.orgName);
            orgLocation=itemView.findViewById(R.id.location);
            orgEmail=itemView.findViewById(R.id.emailTxt);
            orgWebsite=itemView.findViewById(R.id.btnWebsite);
            orgCall=itemView.findViewById(R.id.callBtn);
        }
    }
}
