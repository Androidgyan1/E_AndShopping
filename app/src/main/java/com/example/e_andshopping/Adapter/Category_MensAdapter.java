package com.example.e_andshopping.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_andshopping.DetailsActivity;
import com.example.e_andshopping.Model.CategoryModelMens;
import com.example.e_andshopping.R;

import java.util.List;

public class Category_MensAdapter extends RecyclerView.Adapter<Category_MensAdapter.MyViewHolder> {

    RequestOptions options ;
    private Context mContext ;
    private List<CategoryModelMens> mData ;


    public Category_MensAdapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.mData = lst;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.category_item,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //ye h category ka click listner
                Intent i = new Intent(mContext, DetailsActivity.class);

                i.putExtra("id",mData.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("title",mData.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("price",mData.get(viewHolder.getAdapterPosition()).getPrice());

                i.putExtra("description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("category",mData.get(viewHolder.getAdapterPosition()).getCategory());
                i.putExtra("image",mData.get(viewHolder.getAdapterPosition()).getImage_url());

                i.putExtra("rate",mData.get(viewHolder.getAdapterPosition()).getRate());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });
        // click listener here
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvname.setText(mData.get(position).getTitle());
         holder.category_price.setText(mData.get(position).getPrice());
         holder.category_descripation.setText(mData.get(position).getDescription());
        holder.Id.setText(mData.get(position).getId());
        // holder.tvcat.setText(mData.get(position).getCategorie());

        // load image from the internet using Glide
        Glide.with(mContext).load(mData.get(position).getImage_url()).into(holder.AnimeThumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvname,category_price,category_descripation,Id;
        ImageView AnimeThumbnail;


        public MyViewHolder(View itemView) {
            super(itemView);
            AnimeThumbnail = itemView.findViewById(R.id.categoryimage);
            tvname = itemView.findViewById(R.id.category_name);
            category_price = itemView.findViewById(R.id.category_price);
            category_descripation = itemView.findViewById(R.id.category_descripation);
            Id = itemView.findViewById(R.id.category_Id);


        }
    }


}