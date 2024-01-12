package com.example.e_andshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_andshopping.Adapter.Category_MensAdapter;
import com.example.e_andshopping.Adapter.SlidingImage_Adapter;
import com.example.e_andshopping.Model.CategoryModelMens;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard extends AppCompatActivity {

    ProgressBar progress;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.banner_one,R.drawable.banner_two};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    TextView dash_token;

    private List<CategoryModelMens> lstAnimecategory = new ArrayList<>();
    private RecyclerView category_rv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dash_token = findViewById(R.id.dash_token);

        category_rv = findViewById(R.id.category_recycler);
        progress = findViewById(R.id.progress);

        jsoncallCategory();

        slider();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String TOKEN = extras.getString("TOKEN");

            dash_token.setText(TOKEN);

        }

    }

    private void slider() {

        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(Dashboard.this,ImagesArray));

        //indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
       // indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
    }


    private void jsoncallCategory() {
        final StringRequest JsonObject = new StringRequest(Request.Method.GET, "https://fakestoreapi.com/products",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion

                        progress.setVisibility(View.VISIBLE);



                        try {
                            //getting the whole json object from the response

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //now looping through all the elements of the json array
                            for (int i = 0; i < array.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = array.getJSONObject(i);

                                //creating a hero object and giving them the values from json object

                                CategoryModelMens categoryModel =  new CategoryModelMens();
                                categoryModel.setId(heroObject.getString("id"));
                                categoryModel.setImage_url(heroObject.getString("image"));
                                categoryModel.setTitle(heroObject.getString("title"));
                                categoryModel.setPrice(heroObject.getString("price"));
                                categoryModel.setDescription(heroObject.getString("description"));

                                //adding the hero to herolist
                                lstAnimecategory.add(categoryModel);

                                Log.e("sucess",response);

                            }

//                            final LinearLayoutManager managerbestsearch3 = new LinearLayoutManager(getApplicationContext());
//                            category_rv.setLayoutManager(managerbestsearch3);
//
//                            int numberOfColumns = 2;
//                            category_rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), numberOfColumns));
//
//                            Category_MensAdapter myAdaptercategory = new Category_MensAdapter(getApplicationContext(),lstAnimecategory) ;
//
//                            category_rv.setAdapter(myAdaptercategory);
                            setRvadapterCategory(lstAnimecategory);

                            progress.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        progress.setVisibility(View.GONE);
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //adding the string request to request queue
        requestQueue.add(JsonObject);
    }

    private void setRvadapterCategory(List<CategoryModelMens> lstAnimecategory) {
        final LinearLayoutManager manager1 = new LinearLayoutManager(getApplicationContext());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        category_rv.setLayoutManager(manager1);
        int numberOfColumns = 2;
        Category_MensAdapter myAdaptercategory = new Category_MensAdapter(getApplicationContext(),lstAnimecategory) ;
        category_rv.setAdapter(myAdaptercategory);

    }

}
