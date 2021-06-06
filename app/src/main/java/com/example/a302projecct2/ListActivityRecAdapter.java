package com.example.a302projecct2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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


public class ListActivityRecAdapter extends RecyclerView.Adapter<ListActivityRecAdapter.ListActivityViewHolder> {

    private Context ctx;
    private ArrayList <ItemClass> items;

    public ListActivityRecAdapter(Context ctx, ArrayList<ItemClass> items){
        this.ctx = ctx;
        this.items = items;
    }


    /**
     * Inflating layout file for each category and creating viewholder
     */
    @NonNull
    @Override
    public ListActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ListActivityViewHolder(view);
    }

    /**
     * Binding data for all ItemClass to each item within inflated layout file
     */
    @Override
    public void onBindViewHolder(@NonNull ListActivityViewHolder holder, int position) {
            Animation animation = AnimationUtils.loadAnimation(ctx, R.anim.slide_in_right);
            holder.cvListItem.startAnimation(animation);

            holder.txtItemName.setText(items.get(position).getItemName());
            holder.txtItemPrice.setText(items.get(position).getItemPrice());
            Glide.with(this.ctx)
                    .load(items.get(position).getItemImages()[0])
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
                        //Creating intent to go viewItemPage
                        Intent intent = new Intent(ctx, viewItemPage.class);
                        intent.putExtra("itemName",items.get(position).getItemName());
                        intent.putExtra("itemPrice",items.get(position).getItemPrice());
                        intent.putExtra("itemDescription",items.get(position).getItemDescription());
                        intent.putExtra("itemImages", items.get(position).getItemImages());
                        intent.putExtra("prevPage", "List");
                        //Shared preference for the array of items
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
        return items.size();
    }

    //Connecting variables within here to components from the page
    public class ListActivityViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItemName, txtItemPrice;
        private ImageView imgListItem;
        private CardView cvListItem;

        public ListActivityViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            imgListItem = itemView.findViewById(R.id.img_list_item);
            cvListItem = itemView.findViewById(R.id.cvListItem);


        }
    }


}
