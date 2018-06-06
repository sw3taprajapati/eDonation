package com.example.sweta.edonation.adaptersandviewholders;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sweta.edonation.R;
import com.example.sweta.edonation.activities.DonorDetailsActivity;
import com.example.sweta.edonation.pojoclasses.Donor;

import java.util.List;

public class DonorAdapter extends RecyclerView.Adapter<DonorViewHolder> {
    Context context;
    List<Donor> donorList;
    public DonorAdapter(Context context, List<Donor> donorList) {
        this.context = context;
        this.donorList = donorList;
    }
    @Override
    public DonorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_view_donor, parent,
                false);
        DonorViewHolder donorViewHolder = new DonorViewHolder(view);
        return donorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DonorViewHolder holder, int position) {
        String foodString;
        String clothesString;
        String booksString;
        String stationeryString;
        final Donor donor = donorList.get(position);
        holder.donorName.setText(donor.getDonorName());
        holder.donorLocation.setText(donor.getDonorLocation());
        holder.organzationName.setText("Donated to : "+donor.getOrgName());
        boolean food = donor.getCurrentlyLooking().isFood();
        boolean clothes = donor.getCurrentlyLooking().isClothes();
        boolean books = donor.getCurrentlyLooking().isBooks();
        boolean stationery = donor.getCurrentlyLooking().isStationery();

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
        if(stationery==true){
            stationeryString="Stationery  ";
        }else {
            stationeryString="";
        }
        final String currentlyLooking=foodString+clothesString+booksString+stationeryString;
        if(books==false && clothes==false && books==false && stationery==false){
            holder.currentDonation.setText("We aren't accepting any donation at this time. " +
                    "Thank you!!!!!");
        }else {
            holder.currentDonation.setText("Currently Looking For : " + currentlyLooking);
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DonorDetailsActivity.class);
                intent.putExtra("donorName", donor.getDonorName());
                intent.putExtra("donorLocation", donor.getDonorLocation());
                intent.putExtra("donorEmail", donor.getDonorEmail());
                intent.putExtra("currentDonation",currentlyLooking);
                intent.putExtra("description", donor.getDescribeItems());
                intent.putExtra("phone", donor.getDonorPhone());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return donorList.size();
    }
}
