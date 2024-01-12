package com.example.e_andshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.e_andshopping.Adapter.MyCartadapter;
import com.example.e_andshopping.Database.AppDatabase;
import com.example.e_andshopping.Database.Product;
import com.example.e_andshopping.Database.ProductDao;
import com.example.e_andshopping.Model.CategoryModelMens;

import java.util.List;

public class AddToCartPage extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView rateview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart_page);

        rateview = findViewById(R.id.rateview);
        getroomdata();
    }

    private void getroomdata() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"cart_db").allowMainThreadQueries().build();
        ProductDao productDao = db.ProductDao();
        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<CategoryModelMens> products = productDao.getallProduct();
        MyCartadapter myCartadapter = new MyCartadapter(products,rateview);
        recyclerView.setAdapter(myCartadapter);
    }
}