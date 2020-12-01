package com.example.td6;

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

import java.util.List;

import retrofit2.Callback;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder>{

    private final List<Repo> mRepo;
    private Context context;

    public RepoAdapter(@NonNull List<Repo> mRepo, Context context) {
        this.mRepo = mRepo;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(contactView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repo contact = mRepo.get(position);

        TextView firstNameTextView = holder.firstNameTextView;
        firstNameTextView.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return mRepo.size();
    }

    public  static  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView firstNameTextView;

        public ViewHolder(View itemView){
            super(itemView);

            firstNameTextView = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
