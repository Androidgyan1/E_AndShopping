package com.example.e_andshopping.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.e_andshopping.Model.CategoryModelMens;

import java.util.List;
@Dao
public  interface ProductDao {

    @Insert
    void insertrecord(CategoryModelMens product);

    @Query("SELECT EXISTS(SELECT * FROM Product WHERE pid =:pid)")
    Boolean is_exist(int pid);


    @Query("SELECT * FROM Cate")
    List<CategoryModelMens>  getallProduct();

    @Query("DELETE FROM Product WHERE pid=:category_id")
    void deleteById(int category_id);


}
