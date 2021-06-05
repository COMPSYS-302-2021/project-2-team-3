package com.example.a302projecct2;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.a302projecct2.dataprovider.ItemClass;

import java.util.ArrayList;
import java.util.HashSet;


public class CategoryItemRecAdapter extends RecyclerView.Adapter<CategoryItemRecAdapter.CategoryItemViewHolder>{

    private final Context ctx;
    private final ArrayList<ArrayList<ItemClass>> items;
    private final com.example.a302projecct2.dataprovider.CategoryClass[] categoryNames;

    public CategoryItemRecAdapter(Context ctx, ArrayList<ArrayList<ItemClass>> items, com.example.a302projecct2.dataprovider.CategoryClass[] categoryNames) {
        this.ctx = ctx;
        this.items = items;
        this.categoryNames = categoryNames;
    }

    /**
     * Inflating layout file for each category and creating viewholder
     */
    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new CategoryItemViewHolder(view);
    }

    /**
     * Binding data for all CategoryItems to each item within inflated layout file
     */
    @Override
    public void onBindViewHolder(@NonNull CategoryItemRecAdapter.CategoryItemViewHolder holder, int position) {

        holder.txtCuisine.setText(categoryNames[position].getCategoryName());

        Glide.with(this.ctx)
                .load(categoryNames[position].getImgFlag())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgFlag);


        holder.cvCategoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connectivity conn = new Connectivity(ctx);
                //Check if device is connected to internet before going to listActivity
                if(!conn.isConnected()){
                    Toast.makeText(ctx, "Please Connect To the Internet", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Creating intent to go to viewItemPage
                    Intent intent = new Intent(ctx, ListDishes.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("position", position);

                    //Storing name of category and its position in shared preferences to be used in viewItemPage
                    SharedPreferences SPref = ctx.getSharedPreferences("categoryName",Context.MODE_PRIVATE);
                    SharedPreferences.Editor Edited = SPref.edit();
                    Edited.putString("Name",categoryNames[position].getCategoryName());
                    Edited.putInt("cuisinePos", position);
                    Edited.apply();

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
        return categoryNames.length;
    }

    public static class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgFlag;
        private final TextView txtCuisine;
        private final CardView cvCategoryItem;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFlag = itemView.findViewById(R.id.imgFlag);
            txtCuisine = itemView.findViewById(R.id.txtCuisine);
            cvCategoryItem = itemView.findViewById(R.id.cvCategoryItem);
        }
    }
}
