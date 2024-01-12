package com.example.e_andshopping.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int pid;

    @ColumnInfo(name = "title")
    public String pname;

    @ColumnInfo(name = "price")
    public int price;

    public Product(int pid, String pname, int price) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
