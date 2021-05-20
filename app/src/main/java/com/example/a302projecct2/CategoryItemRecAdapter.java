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

import java.util.zip.Inflater;

public class CategoryItemRecAdapter extends RecyclerView.Adapter<CategoryItemRecAdapter.CategoryItemViewHolder>{

    private Context ctx;

    public CategoryItemRecAdapter(Context ctx) {
        this.ctx = ctx;
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
        //holder.txtCuisine.setText(/**Need to figure out how to have cateogry information and category items**/);


//        Glide.with(this.ctx)
//                .load(/**Same issue as above**/)
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(holder.imgFlag);

        holder.cvCategoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to listactivity once an category has been selected

                //Intent intent = new Intent(ctx, ListActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
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
}
