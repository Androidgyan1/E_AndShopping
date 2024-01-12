package com.example.e_andshopping.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.e_andshopping.Model.CategoryModelMens;

@Database(entities = {CategoryModelMens.class},version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao ProductDao();


}
