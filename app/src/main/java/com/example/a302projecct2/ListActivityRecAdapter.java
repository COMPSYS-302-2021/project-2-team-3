package com.example.a302projecct2;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ListActivityRecAdapter extends RecyclerView.Adapter<ListActivityRecAdapter.MyViewHolder> {

    Context ctx;
    ItemClass[] items; 

    public ListActivityRecAdapter(Context ctx, ItemClass[] items){
        this.ctx = ctx;
        this.items = items;
    }


    //Inflating layout for each item within the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    //Binds the data to the layout that will be used for each item
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtItemName.setText(items[position].getItemName());
        holder.txtItemPrice.setText(items[position].getItemPrice());
        Glide.with(ctx)
                .load(items[position].getItemImages()[0])
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgListItem);

        holder.cvListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, viewItemPage.class);
                intent.putExtra("itemName",items[position].getItemName());
                intent.putExtra("itemPrice",items[position].getItemPrice());
                intent.putExtra("itemDescription",items[position].getItemDescription());
                ctx.startActivity(intent);
            }
        });



    }

    @Override
    //Used to return the number of items that would be displayed
    public int getItemCount() {
        return items.length;
    }

    //Connecting variables within here to components from the page
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtItemName, txtItemPrice;
        ImageView imgListItem;
        CardView cvListItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtItemPrice = itemView.findViewById(R.id.txtItemPrice);
            imgListItem = itemView.findViewById(R.id.img_list_item);
            cvListItem = itemView.findViewById(R.id.cvListItem);


        }
    }
}
