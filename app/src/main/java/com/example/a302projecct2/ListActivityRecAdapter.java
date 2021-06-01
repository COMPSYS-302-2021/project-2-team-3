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


public class ListActivityRecAdapter extends RecyclerView.Adapter<ListActivityRecAdapter.ListActivityViewHolder> {

    private Context ctx;
    private ArrayList <ItemClass> items;

    public ListActivityRecAdapter(Context ctx, ArrayList<ItemClass> items){
        this.ctx = ctx;
        this.items = items;
    }


    //Inflating layout for each item within the RecyclerView
    @NonNull
    @Override
    public ListActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ListActivityViewHolder(view);
    }

    //Binds the data to the layout that will be used for each item
    @Override
    public void onBindViewHolder(@NonNull ListActivityViewHolder holder, int position) {
        holder.txtItemName.setText(items.get(position).getItemName());
        holder.txtItemPrice.setText(items.get(position).getItemPrice());
        Glide.with(this.ctx)
                .load(items.get(position).getItemImages()[0])
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgListItem);

        holder.cvListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked");
                if(!isConnected()){
                    showNotConnectedDialog();
                }
                else{

                    SharedPreferences ShPref = ctx.getApplicationContext().getSharedPreferences("categoryName", Context.MODE_PRIVATE);
                    String categoryName = ShPref.getString("Name","");
                    int cuisinePos = ShPref.getInt("cuisinePos", 0);
                    JsonFuncs jsonFuncs = new JsonFuncs(ctx);
                    System.out.println("cuisinePos: " +cuisinePos+", position: "+ position);
                    jsonFuncs.incrementCount(cuisinePos,position);

                    Intent intent = new Intent(ctx, viewItemPage.class);
                    intent.putExtra("itemName",items.get(position).getItemName());
                    intent.putExtra("itemPrice",items.get(position).getItemPrice());
                    intent.putExtra("itemDescription",items.get(position).getItemDescription());
<<<<<<< Updated upstream
                    //intent.putExtra("itemImages", items.get(position).getItemImages());
=======
                    intent.putExtra("itemImages", items.get(position).getItemImages());
                    intent.putExtra("prevPage", "List");
>>>>>>> Stashed changes
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);

                }

            }
        });



    }

    @Override
    //Used to return the number of items that would be displayed
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
