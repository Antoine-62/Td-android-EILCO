package com.example.td5;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.time.Instant;
import java.time.temporal.TemporalAdjuster;
import java.util.List;

public class ContactsAdaptater extends RecyclerView.Adapter<ContactsAdaptater.ViewHolder> {

    private final List<Contact> mContact;
    private Context context;

    public ContactsAdaptater(@NonNull List<Contact> mContact, Context context) {
        this.mContact = mContact;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(contactView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = mContact.get(position);

        TextView firstNameTextView = holder.firstNameTextView;
        firstNameTextView.setText(contact.getPrenom());

        TextView lastnameTextView = holder.lastnameTextView;
        lastnameTextView.setText(contact.getNom());

        ImageView imageUrl = holder.imageurl;
        Glide.with(context).load(contact.getImageUrl()).into(imageUrl);

        ImageView imageUrl2 = holder.imageurl2;
        Glide.with(context).load(contact.getImageUrl()).into(imageUrl2);
    }

    @Override
    public int getItemCount() {
        return mContact.size();
    }

    public  static  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView firstNameTextView;
        public TextView lastnameTextView;
        public ImageView imageurl;
        public ImageView imageurl2;

        public ViewHolder(View itemView){
            super(itemView);

            firstNameTextView = (TextView) itemView.findViewById(R.id.prenom);
            lastnameTextView = (TextView) itemView.findViewById(R.id.nom);
            imageurl = (ImageView) itemView.findViewById(R.id.image);
            imageurl2 = (ImageView) itemView.findViewById(R.id.image2);
        }
    }
}
