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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a302projecct2.dataprovider.ItemClass;

import java.util.ArrayList;


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
                if(!isConnected()){
                    showNotConnectedDialog();
                }
                else {

                    Intent intent = new Intent(ctx, ListDishes.class);
                    intent.putExtra("CategoryName", categoryNames[position].getCategoryName());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("position", position);
                    SharedPreferences SPref = ctx.getSharedPreferences("categoryName",Context.MODE_PRIVATE);
                    SharedPreferences.Editor Edited = SPref.edit();
                    Edited.putString("Name",categoryNames[position].getCategoryName());
                    Edited.commit();
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


    //Check if device is connected a network
    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        //Check if device is connected to internet
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())){
            return true;
        }
        else{
            return false;
        }

    }

    private void showNotConnectedDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage("Please connect to the internet to proceed")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ctx.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                });
        builder.show();


    }
}
