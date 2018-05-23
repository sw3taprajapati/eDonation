package com.example.sweta.edonation.adaptersandviewholders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sweta.edonation.R;
import com.example.sweta.edonation.pojoclasses.Organization;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

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

        final Organization organization = organizationList.get(position);

        holder.orgName.setText(organization.getOrgFullName());
        holder.orgLocation.setText(organization.getOrgLocation());
        holder.orgEmail.setText(organization.getOrgEmailID());
        holder.orgWebsite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(String.valueOf(organization.getOrgWebsite())));
                context.startActivity(intent);
            }
        });
        holder.orgCall.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel :" + String.valueOf(organization.getOrgPhone())));
                try {
                    context.startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return organizationList.size();
    }
}
