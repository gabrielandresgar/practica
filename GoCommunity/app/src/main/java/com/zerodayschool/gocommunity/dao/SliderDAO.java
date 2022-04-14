package com.zerodayschool.gocommunity.dao;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zerodayschool.gocommunity.clases.Slider;

import java.util.List;


@Dao
public interface SliderDAO {


    @Query("SELECT * FROM Slider")
    List<Slider> consultar();

    @Insert
     void registrar( Slider e);

    @Update
     void actualizar(Slider e);

    @Update
     void desactivar(Slider e);
}
