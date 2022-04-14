package com.zerodayschool.gocommunity.clases;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "slider")
public class Slider {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_slider")
    private Integer idSlider;

    @ColumnInfo(name = "estado")
    @NonNull
    private boolean estado;

    public Slider() {
    }

    public Slider(Integer idSlider, boolean estado) {
        this.idSlider = idSlider;
        this.estado = estado;
    }

    public Integer getIdSlider() {
        return idSlider;
    }

    public void setIdSlider(Integer idSlider) {
        this.idSlider = idSlider;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
