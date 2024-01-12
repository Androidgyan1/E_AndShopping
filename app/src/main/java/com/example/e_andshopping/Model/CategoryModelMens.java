package com.example.e_andshopping.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CategoryModelMens {
    String  id,title,price,description,category,image_url,rate,count;


    @PrimaryKey(autoGenerate = true)
    public String id;



//    public CategoryModelMens(String id, String title, String price, String description, String category, String image_url, String rate, String count) {
//        this.id = id;
//        this.title = title;
//        this.price = price;
////        this.description = description;
//        this.category = category;
//        this.image_url = image_url;
//        this.rate = rate;
//        this.count = count;
  //  }


    public String getId() {
        return id;
    }

    public void setId(String pid) {
        this.id = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
