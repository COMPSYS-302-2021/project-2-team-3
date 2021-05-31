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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a302projecct2.dataprovider.ItemClass;

import java.util.ArrayList;

public class SearchActivityAdapter extends RecyclerView.Adapter<SearchActivityAdapter.SearchViewHolder> {

    private Context ctx;
    private ArrayList<ItemClass> searchResults;

    public SearchActivityAdapter(Context ctx, ArrayList<ItemClass> searchResults){
        this.ctx = ctx;
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public SearchActivityAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new SearchActivityAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchActivityAdapter.SearchViewHolder holder, int position) {
        holder.txtItemName.setText(searchResults.get(position).getItemName());
        holder.txtItemPrice.setText(searchResults.get(position).getItemPrice());
        Glide.with(this.ctx)
                .load(searchResults.get(position).getItemImages()[0])
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgListItem);

        holder.cvListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isConnected()){
                    showNotConnectedDialog();
                }
                else{

                    Intent intent = new Intent(ctx, viewItemPage.class);
                    intent.putExtra("itemName",searchResults.get(position).getItemName());
                    intent.putExtra("itemPrice",searchResults.get(position).getItemPrice());
                    intent.putExtra("itemDescription",searchResults.get(position).getItemDescription());
                    intent.putExtra("itemImages", searchResults.get(position).getItemImages());
                    ctx.startActivity(intent);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItemName, txtItemPrice;
        private ImageView imgListItem;
        private CardView cvListItem;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            imgListItem = itemView.findViewById(R.id.img_list_item);
            cvListItem = itemView.findViewById(R.id.cvListItem);
        }
    }

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
