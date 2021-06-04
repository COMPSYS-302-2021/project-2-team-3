package com.example.a302projecct2;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a302projecct2.dataprovider.*;

import java.util.ArrayList;

public class TopPicksRecAdapter extends RecyclerView.Adapter<TopPicksRecAdapter.TopPicksViewHolder> {

    private ArrayList<ItemClass> topPickItems;
    private Context ctx;

    public TopPicksRecAdapter(ArrayList<ItemClass> topPickItems, Context ctx) {
        this.topPickItems = topPickItems;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public TopPicksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View view = inflater.inflate(R.layout.top_picks, parent, false);
        return new TopPicksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopPicksRecAdapter.TopPicksViewHolder holder, int position) {
        holder.txtTopPicksName.setText(topPickItems.get(position).getItemName());
        Glide.with(this.ctx)
                .load(topPickItems.get(position).getItemImages()[0])
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgTopPicks);

        holder.cvTopPicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if device is connected to internet, if not show message else go to details
                //activity for the selected item
                Connectivity conn = new Connectivity(ctx);

                if(!conn.isConnected()){
                    Toast.makeText(ctx, "Please Connect To the Internet", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(ctx, viewItemPage.class);
                    intent.putExtra("itemName", topPickItems.get(position).getItemName());
                    intent.putExtra("itemPrice", topPickItems.get(position).getItemPrice());
                    intent.putExtra("itemDescription", topPickItems.get(position).getItemDescription());
                    intent.putExtra("itemImages", topPickItems.get(position).getItemImages());
                    intent.putExtra("prevPage", "Home");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return topPickItems.size();
    }

    public class TopPicksViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgTopPicks;
        private TextView txtTopPicksName;
        private CardView cvTopPicks;

        public TopPicksViewHolder(@NonNull  View itemView) {
            super(itemView);
            imgTopPicks = itemView.findViewById(R.id.img_top_picks);
            txtTopPicksName = itemView.findViewById(R.id.txtTopPicksName);
            cvTopPicks = itemView.findViewById(R.id.cvTopPicks);
        }
    }

}
