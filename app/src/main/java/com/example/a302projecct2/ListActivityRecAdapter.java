package com.example.a302projecct2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivityRecAdapter extends RecyclerView.Adapter<ListActivityRecAdapter.MyViewHolder> {

    Context ctx;

    public ListActivityRecAdapter(Context ctx){
        this.ctx = ctx;
    }


    //Inflating layout for each item within the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        //TODO: Create layout file for displaying each item
//        View view = inflater.inflate(R.layout.my_row, parent, false);
//        return new MyViewHolder(view);
        return null;
    }

    //Binds the data to the layout that will be used for each item
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    //Used to return the number of items that would be displayed
    public int getItemCount() {
        return 0;
    }

    //Connecting variables within here to components from the page
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
