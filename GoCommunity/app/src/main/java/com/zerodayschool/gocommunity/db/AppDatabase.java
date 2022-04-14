package com.zerodayschool.gocommunity.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zerodayschool.gocommunity.clases.Slider;
import com.zerodayschool.gocommunity.dao.SliderDAO;

@Database(entities = {Slider.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract SliderDAO sliderDAO();

    private static com.zerodayschool.gocommunity.db.AppDatabase database;
    private static String DATABASE_NAME = "db_convertidor";

    public static com.zerodayschool.gocommunity.db.AppDatabase getInstance(final Context contexto)
    {
        if (database == null)
        {
            synchronized (com.zerodayschool.gocommunity.db.AppDatabase.class)
            {
                database = Room.databaseBuilder(contexto, com.zerodayschool.gocommunity.db.AppDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return database;
    }
}
