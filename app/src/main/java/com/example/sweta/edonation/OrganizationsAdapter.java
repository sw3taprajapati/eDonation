package com.example.sweta.edonation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class OrganizationsAdapter extends RecyclerView.Adapter<OrganizationsAdapter.OrganizationViewHolder>{

    Context context;
    List<Organization> organizationList;

    public OrganizationsAdapter(Context context, List<Organization> organizationList) {
        this.context = context;
        this.organizationList = organizationList;
    }

    @NonNull
    @Override
    public OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_view_admin, parent, false);
        OrganizationViewHolder organizationViewHolder = new OrganizationViewHolder(view);
        return organizationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrganizationViewHolder holder, int position) {

        Organization organization = organizationList.get(position);

        holder.textViewOrgName.setText(organization.getOrgFullName());
        holder.textViewPanNo.setText(String.valueOf(organization.getOrgPan()));

    }

    @Override
    public int getItemCount() {
        return organizationList.size();
    }

    class OrganizationViewHolder extends RecyclerView.ViewHolder{

        TextView textViewOrgName, textViewPanNo;

        public OrganizationViewHolder(View itemView) {
            super(itemView);

            textViewOrgName = itemView.findViewById(R.id.orgName);
            textViewPanNo = itemView.findViewById(R.id.orgnPan);
        }
    }
}