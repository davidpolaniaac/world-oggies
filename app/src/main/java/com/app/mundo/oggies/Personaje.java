package com.app.mundo.oggies;

import android.widget.ImageView;

/**
 * Created by PC on 12/05/2016.
 */
public class Personaje {

    int base;
    Estacion winter=new Estacion(0);
    Estacion spring=new Estacion(1);
    Estacion summer=new Estacion(2);
    Estacion autumn=new Estacion(3);

    public Personaje(){
        Jugador.marcos.clear();
        Jugador.marcos.add(R.drawable.marco_foto1);
        Jugador.marcos.add(R.drawable.marco_foto2);
        Jugador.marcos.add(R.drawable.marco_foto3n);
        Jugador.marcos.add(R.drawable.marco_foto4n);
        Jugador.marcos.add(R.drawable.marco_foto5n);
        Jugador.marcos.add(R.drawable.marco_foto6n);

    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public Estacion getWinter() {
        return winter;
    }

    public void setWinter(Estacion winter) {
        this.winter = winter;
    }

    public Estacion getSpring() {
        return spring;
    }

    public void setSpring(Estacion spring) {
        this.spring = spring;
    }

    public Estacion getSummer() {
        return summer;
    }

    public void setSummer(Estacion summer) {
        this.summer = summer;
    }

    public Estacion getAutumn() {
        return autumn;
    }

    public void setAutumn(Estacion autumn) {
        this.autumn = autumn;
    }

    public Estacion idEstacion(int id){

        if (id==0){
            return winter;
        }else if(id==1){
            return spring;
        }else if(id==2){
            return summer;
        }else {
            return autumn;
        }
    }
}
