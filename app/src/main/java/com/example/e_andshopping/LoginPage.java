package com.example.e_andshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LoginPage extends AppCompatActivity {

    EditText edittext_usename,edittext_password;
    TextView token;
    String USERNAME,PASSWORD,TOKEN;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        edittext_usename = findViewById(R.id.edittext_email);
        edittext_password = findViewById(R.id.edittext_password);
        token = findViewById(R.id.token);

    }

    public void Login(View view) {

        USERNAME = edittext_usename.getText().toString().trim();////set in parameter of mac address
        PASSWORD = edittext_password.getText().toString().trim();////set in parameter of mac address

        //first getting the values
        RequestQueue queue = Volley.newRequestQueue(LoginPage.this);

        final JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("username", USERNAME);///scantext.toString()
            jsonObject.put("password", PASSWORD);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "https://fakestoreapi.com/auth/login", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // inside the on response method.
                // we are hiding our progress bar.


                // in below line we are making our card
                // view visible after we get all the data.
                Log.d("MSg", response.toString());
                // now we get our response from API in json object format.
                // in below line we are extracting a string with its key
                // value from our json object.
                // similarly we are extracting all the strings from our json object.

                                try {

                                    TOKEN = response.getString("token");
                                    // Format the date and time string according to your custom format
                                    token.setText(TOKEN);

                                    Intent intent = new Intent(LoginPage.this,Dashboard.class);
                                    intent.putExtra("TOKEN",TOKEN);
                                    startActivity(intent);
                                    finish();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    // Show an error message
                                    Toast.makeText(LoginPage.this, "User not found", Toast.LENGTH_SHORT).show();
                                    //login_progress.setVisibility(View.INVISIBLE);
                                }
                                //sring courseMode = response.getString("courseMode");
//                    String courseImageURL = response.getString("courseimg");

                            // after extracting all the data we are


            }
        }, new Response.ErrorListener() {
            // this is the error listener method which
            // we will call if we get any error from API.
            @Override
            public void onErrorResponse(VolleyError error) {
                // below line is use to display a toast message along with our error.
                Toast.makeText(LoginPage.this, "Use not found..", Toast.LENGTH_SHORT).show();
                //login_progress.setVisibility(View.INVISIBLE);
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }

        };
        // at last we are adding our json
        // object request to our request
        // queue to fetch all the json data.
        queue.add(jsonObjectRequest);

    }
}


