package com.example.a302projecct2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
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

public class TopPicksRecAdapter extends RecyclerView.Adapter<TopPicksRecAdapter.TopPicksViewHolder> {

    private ItemClass[] topPickItems;
    private Context ctx;

    public TopPicksRecAdapter(ItemClass[] topPickItems, Context ctx) {
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
        holder.txtTopPicksName.setText(topPickItems[position].getItemName());
        Glide.with(this.ctx)
                .load(topPickItems[position].getItemImages()[0])
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgTopPicks);

        holder.cvTopPicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if device is connected to internet, if not show message else go to details
                //activity for the selected item
                if(!isConnected()){
                    showNotConnectedDialog();
                }
                else{
                    Intent intent = new Intent(ctx, viewItemPage.class);
                    intent.putExtra("itemName", topPickItems[position].getItemName());
                    intent.putExtra("itemPrice", topPickItems[position].getItemPrice());
                    intent.putExtra("itemDescription", topPickItems[position].getItemDescription());
                    intent.putExtra("itemImages", topPickItems[position].getItemImages());
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return topPickItems.length;
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
