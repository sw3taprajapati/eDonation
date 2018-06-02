package com.example.sweta.edonation.adaptersandviewholders;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sweta.edonation.R;
import com.example.sweta.edonation.pojoclasses.Organization;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class OrganizationAdapter extends
        RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder> {

    Context context;
    List<Organization> organizationList;

    FirebaseAuth firebaseAuth;


    public OrganizationAdapter(Context context, List<Organization> organizationList) {
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

        final Organization organization = organizationList.get(position);

        holder.textViewOrgName.setText(organization.getOrgFullName());
        holder.textViewPanNo.setText(String.valueOf(organization.getOrgPan()));
        holder.btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = organization.getOrgId();
                int data = 1;
                DatabaseReference dbOrganization = FirebaseDatabase.getInstance().
                        getReference("OrganizationDetails");
                dbOrganization.child(id).child("status")
                        .setValue(1);

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + organization.getOrgEmailID())); // only email apps should handle this

                intent.putExtra(Intent.EXTRA_SUBJECT, "Confirmation");
                intent.putExtra(Intent.EXTRA_TEXT, "Your email is verfied");
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
            }
        });

        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = organization.getOrgId();

                //Delete from firebase database
                DatabaseReference dbOrganization = FirebaseDatabase.getInstance().
                        getReference("OrganizationDetails").child(id);
                dbOrganization.removeValue();


                //delete from Firebase Authentication
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //deleted
                        }

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return organizationList.size();
    }

    class OrganizationViewHolder extends RecyclerView.ViewHolder {

        TextView textViewOrgName, textViewPanNo;
        Button btnApprove, btnDecline;

        public OrganizationViewHolder(View itemView) {
            super(itemView);

            textViewOrgName = itemView.findViewById(R.id.orgName);
            textViewPanNo = itemView.findViewById(R.id.panNo);
            btnApprove = itemView.findViewById(R.id.approveBtn);
            btnDecline = itemView.findViewById(R.id.declineBtn);
        }
    }
}
