package com.example.a302projecct2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a302projecct2.dataprovider.ItemClass;

import java.util.ArrayList;

public class SearchActivityAdapter extends RecyclerView.Adapter<SearchActivityAdapter.SearchViewHolder> {

    private final Context ctx;
    private final ArrayList<ItemClass> searchResults;

    public SearchActivityAdapter(Context ctx, ArrayList<ItemClass> searchResults){
        this.ctx = ctx;
        this.searchResults = searchResults;
    }

    /**
     * Inflating layout file for showing each item and creating a viewholder
     */
    @NonNull
    @Override
    public SearchActivityAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new SearchViewHolder(view);
    }

    /**
     * Binding data for all items relevant to search query to each item within inflated layout file
     */
    @Override
    public void onBindViewHolder(@NonNull SearchActivityAdapter.SearchViewHolder holder, int position) {
            Animation animation = AnimationUtils.loadAnimation(ctx, R.anim.slide_in_right);
            holder.cvListItem.startAnimation(animation);
            holder.txtItemName.setText(searchResults.get(position).getItemName());
            holder.txtItemPrice.setText(searchResults.get(position).getItemPrice());
            Glide.with(this.ctx)
                    .load(searchResults.get(position).getItemImages()[0])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.imgListItem);

            holder.cvListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Connectivity conn = new Connectivity(ctx);

                    //Check if device is connected to internet before going to viewItemPage
                    if(!conn.isConnected()){
                        Toast.makeText(ctx, "Please Connect To the Internet", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        //Creating intent to go to viewItemPage

                        Intent intent = new Intent(ctx, viewItemPage.class);
                        intent.putExtra("itemName",searchResults.get(position).getItemName());
                        intent.putExtra("itemPrice",searchResults.get(position).getItemPrice());
                        intent.putExtra("itemDescription",searchResults.get(position).getItemDescription());
                        intent.putExtra("itemImages", searchResults.get(position).getItemImages());
                        intent.putExtra("prevPage", "Search");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(intent);

                    }

                }
            });
    }


    /**
     *  Return number of items displayed in recycler view
     */
    @Override
    public int getItemCount() {
        return searchResults.size();
    }


    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtItemName;
        private final TextView txtItemPrice;
        private final ImageView imgListItem;
        private final CardView cvListItem;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            imgListItem = itemView.findViewById(R.id.img_list_item);
            cvListItem = itemView.findViewById(R.id.cvListItem);
        }
    }




}

