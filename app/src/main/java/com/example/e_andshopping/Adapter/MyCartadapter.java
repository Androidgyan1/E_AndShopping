package com.example.e_andshopping.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.e_andshopping.Database.AppDatabase;
import com.example.e_andshopping.Database.Product;
import com.example.e_andshopping.Database.ProductDao;
import com.example.e_andshopping.Model.CategoryModelMens;
import com.example.e_andshopping.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyCartadapter extends RecyclerView.Adapter<MyCartadapter.myviewholder>
{
    List<Product> products;
    TextView rateview;
    public MyCartadapter(List<CategoryModelMens> products, TextView rateview) {
        this.products = products;
        this.rateview= rateview;
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cart_item,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyCartadapter.myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.recid.setText(String.valueOf(products.get(position).getPid()));
        holder.recpname.setText(products.get(position).getPname());
        holder.recpprice.setText(String.valueOf(products.get(position).getPrice()));
        holder.delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(holder.recid.getContext(),
                        AppDatabase.class, "cart_db").allowMainThreadQueries().build();
                ProductDao productDao = db.ProductDao();

                productDao.deleteById(products.get(position).getPid());
                products.remove(position);
                notifyItemRemoved(position);
                updateprice();
            }
        });

       // holder.recqnt.setText(String.valueOf(products.get(position).getQnt()));

//        holder.delbtn.(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppDatabase db = Room.databaseBuilder(holder.recid.getContext(),
//                        AppDatabase.class, "cart_db").allowMainThreadQueries().build();
//                ProductDao productDao = db.ProductDao();
//
//                productDao.deleteById(products.get(position).getId());
//                products.remove(position);
//                notifyItemRemoved(position);
//                updateprice();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView recid,recpname,recqnt, recpprice;
        ImageButton delbtn;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            recid=itemView.findViewById(R.id.recid);
            recpname=itemView.findViewById(R.id.recpname);
            recpprice=itemView.findViewById(R.id.recpprice);
            //recqnt=itemView.findViewById(R.id.recqnt);
            delbtn=itemView.findViewById(R.id.delbtn);
        }
    }

    public void updateprice()
    {
        int sum=0,i;
        for(i=0;i< products.size();i++)
            sum= Integer.parseInt(String.valueOf(sum+(products.get(i).getPrice()*products.get(i).getPrice())));

        rateview.setText("Total Amount : INR "+sum);
    }

}
