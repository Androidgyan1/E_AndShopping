package com.example.e_andshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.e_andshopping.Database.AppDatabase;
import com.example.e_andshopping.Database.Product;
import com.example.e_andshopping.Database.ProductDao;
import com.example.e_andshopping.Model.CategoryModelMens;

public class DetailsActivity extends AppCompatActivity {

    public String title,image_url,category_id,descripation,price,category;

    TextView TITLE,DESCRIPATION,PRICE,CATEGORY;
    ImageView IMAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        title  = getIntent().getExtras().getString("title");
        price  = getIntent().getExtras().getString("price");
        descripation  = getIntent().getExtras().getString("description");
        category  = getIntent().getExtras().getString("id");
        image_url = getIntent().getExtras().getString("image") ;
       // category_id = getIntent().getExtras().getString("") ;

         TITLE = findViewById(R.id.detail_title);
         DESCRIPATION = findViewById(R.id.detail_descripation);
         PRICE = findViewById(R.id.detail_price);
         CATEGORY = findViewById(R.id.detail_category);
         IMAGE = findViewById(R.id.category_image);


        TITLE.setText(title);
        DESCRIPATION.setText(descripation);
        PRICE.setText(price);
        CATEGORY.setText(category);
        //TITLE.setText(title);

        // set image using Glide
        Glide.with(this).load(image_url).into(IMAGE);


    }

    public void AddtoCart(View view) {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"cart_db").allowMainThreadQueries().build();
        ProductDao productDao = db.ProductDao();
        Boolean check = productDao.is_exist(Integer.parseInt(CATEGORY.getText().toString()));
        if (check==false){
            String pid = CATEGORY.getText().toString();
            String Ptitle = TITLE.getText().toString();
            String Pprice =PRICE.getText().toString();
            productDao.insertrecord(new CategoryModelMens());
            Toast.makeText(this, "Inserted..", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Allready..", Toast.LENGTH_SHORT).show();
        }

    }
}