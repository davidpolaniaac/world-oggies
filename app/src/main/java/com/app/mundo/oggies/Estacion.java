package com.app.mundo.oggies;



import java.util.ArrayList;

/**
 * Created by PC on 12/05/2016.
 */
public class Estacion {

    private int idEstacion;
    private ArrayList<Integer> head;
    private ArrayList<Integer> body;
    private ArrayList<Integer> accessory;
    private int BackgroundResource;

    public Estacion(int idEstacion){

        this.idEstacion=idEstacion;
        head = new ArrayList<>();
        body = new ArrayList<>();
        accessory = new ArrayList<>();

        if(idEstacion==0){
            this.BackgroundResource=R.drawable.estacion_invierno;
        }else if(idEstacion==1){
            this.BackgroundResource=R.drawable.estacion_primavera;
        }else if(idEstacion==2){
            this.BackgroundResource=R.drawable.estacion_verano;
        }else{
            this.BackgroundResource=R.drawable.estacion_otono;
        }

    }

    public int getBackgroundResource() {
        return BackgroundResource;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public ArrayList<Integer> getHead() {
        return head;
    }

    public ArrayList<Integer> getBody() {
        return body;
    }

    public ArrayList<Integer> getAccessory() {
        return accessory;
    }

    public void setHead(ArrayList<Integer> head) {
        this.head = head;
    }

    public void setBody(ArrayList<Integer> body) {
        this.body = body;
    }

    public void setAccessory(ArrayList<Integer> accessory) {
        this.accessory = accessory;
    }
}
