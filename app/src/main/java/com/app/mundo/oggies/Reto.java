package com.app.mundo.oggies;

import android.content.res.Resources;

/**
 * Created by Alejo_000 on 16/05/2016.
 */
public class Reto {

    private int ganador;
    private int perdedor;
    private String voz;

    public Reto(int ganador,int perdedor, String voz){

        this.ganador=ganador;
        this.perdedor=perdedor;
        this.voz=voz;
    }

    public int getGanador() {
        return ganador;
    }
    
    public int getPerdedor() {
        return perdedor;
    }

    public String getVoz() {
        return voz;
    }


}
