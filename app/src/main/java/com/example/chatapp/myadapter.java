package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myViewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public myadapter(@NonNull @NotNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull myadapter.myViewholder holder, int position, @NonNull @NotNull model model) {
    holder.name.setText(model.getName());
        holder.status.setText(model.getStatus());
        //Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);

    }

    @NonNull
    @NotNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
    return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{
        CircleImageView image;
       TextView name,status;
        public myViewholder(@NonNull @NotNull View itemView) {
            super(itemView);
          //  image=itemView.findViewById(R.id.profile_image);
            name=itemView.findViewById(R.id.textView5);
            status=itemView.findViewById(R.id.textView6);
        }
    }
}
