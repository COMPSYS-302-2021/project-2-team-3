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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a302projecct2.dataprovider.ItemClass;

import java.util.ArrayList;
import java.util.HashSet;


public class CategoryItemRecAdapter extends RecyclerView.Adapter<CategoryItemRecAdapter.CategoryItemViewHolder>{

    private Context ctx;
    private ArrayList<ArrayList<ItemClass>> items;
    private com.example.a302projecct2.dataprovider.CategoryClass[] categoryNames;

    public CategoryItemRecAdapter(Context ctx, ArrayList<ArrayList<ItemClass>> items, com.example.a302projecct2.dataprovider.CategoryClass[] categoryNames) {
        this.ctx = ctx;
        this.items = items;
        this.categoryNames = categoryNames;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemRecAdapter.CategoryItemViewHolder holder, int position) {

        //For data provider make it make two arrays, one for itemCLass and one for categoryItems which will contain a link and text
        holder.txtCuisine.setText(categoryNames[position].getCategoryName());


        Glide.with(this.ctx)
                .load(categoryNames[position].getImgFlag())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgFlag);

        holder.cvCategoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connectivity conn = new Connectivity(ctx);

                if(!conn.isConnected()){
                    Toast.makeText(ctx, "Please Connect To the Internet", Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent intent = new Intent(ctx, ListDishes.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("position", position);
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

    @Override
    public int getItemCount() {
        return categoryNames.length;
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFlag;
        private TextView txtCuisine;
        private CardView cvCategoryItem;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFlag = itemView.findViewById(R.id.imgFlag);
            txtCuisine = itemView.findViewById(R.id.txtCuisine);
            cvCategoryItem = itemView.findViewById(R.id.cvCategoryItem);
        }
    }


    private HashSet<ItemClass> conv2Set(ArrayList<ItemClass> dishes){

        HashSet<ItemClass> dishesHash = new HashSet<>();
        for(int i=0; i<dishes.size(); i++){
            dishesHash.add(dishes.get(i));
        }
        return dishesHash;

    }
}
