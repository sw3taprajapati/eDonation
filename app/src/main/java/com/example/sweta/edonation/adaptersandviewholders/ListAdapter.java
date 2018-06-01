package com.example.sweta.edonation.adaptersandviewholders;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sweta.edonation.R;
import com.example.sweta.edonation.activities.OrganizationDetailActivity;
import com.example.sweta.edonation.pojoclasses.CurrentlyLooking;
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

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_view, parent,
                false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        String foodString;
        String clothesString;
        String booksString;
        String stationeryString;
        final Organization organization = organizationList.get(position);
        holder.orgName.setText(organization.getOrgFullName());
        holder.orgLocation.setText(organization.getOrgLocation());

        boolean food = organization.getCurrentlyLooking().isFood();
        boolean clothes = organization.getCurrentlyLooking().isClothes();
        boolean books = organization.getCurrentlyLooking().isBooks();
        boolean stationery = organization.getCurrentlyLooking().isStationery();

        if (food == true) {
            foodString = "Food  ";
        } else {
            foodString = "";
        }
        if (clothes == true) {
            clothesString = "Clothes  ";
        } else {
            clothesString = "";
        }
        if (books == true) {
            booksString = "Books  ";
        } else {
            booksString = "";
        }
        if (stationery == true) {
            stationeryString = "Stationery  ";
        } else {
            stationeryString = "";
        }
        final String currentlyLooking = foodString + clothesString + booksString + stationeryString;
        holder.currentRequirement.setText("Currently Looking For : " + currentlyLooking);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, OrganizationDetailActivity.class);
                intent.putExtra("orgName", organization.getOrgFullName());
                intent.putExtra("orgLocation", organization.getOrgLocation());
                intent.putExtra("orgEmail", organization.getOrgEmailID());
                intent.putExtra("currentRequirement", currentlyLooking);
                intent.putExtra("description", organization.getDescribeItems());
                intent.putExtra("website", organization.getOrgWebsite());
                intent.putExtra("phone", organization.getOrgPhone());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return organizationList.size();
    }
}
