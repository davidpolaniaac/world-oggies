package com.app.mundo.oggies;

import java.util.ArrayList;

/**
 * Created by PC on 13/05/2016.
 */
public class Room {

     ArrayList<Integer> window;
     ArrayList<Integer> closer;
     ArrayList<Integer> trunk;
     ArrayList<Integer> bed;

    public Room(){

        this.window = new ArrayList<>();
        this.closer = new ArrayList<>();
        this.trunk = new ArrayList<>();
        this.bed = new ArrayList<>();
    }

    public ArrayList<Integer> getWindow() {
        return window;
    }

    public void setWindow(ArrayList<Integer> window) {
        this.window = window;
    }

    public ArrayList<Integer> getCloser() {
        return closer;
    }

    public void setCloser(ArrayList<Integer> closer) {
        this.closer = closer;
    }

    public ArrayList<Integer> getTrunk() {
        return trunk;
    }

    public void setTrunk(ArrayList<Integer> trunk) {
        this.trunk = trunk;
    }

    public ArrayList<Integer> getBed() {
        return bed;
    }

    public void setBed(ArrayList<Integer> bed) {
        this.bed = bed;
    }
}
