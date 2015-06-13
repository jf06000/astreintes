package com.jfcompany;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jf on 24/05/2015.
 */
public class AstEvent implements Serializable {
    private int dayOfYear;
    private int day;
    private AstDoctor doctor;
    private boolean ferie = false;

    public AstEvent(int dayOfYear, int day) {
        this.doctor = null;
        this.dayOfYear = dayOfYear;
        this.day = day;
    }

    public AstEvent(int dayOfYear, boolean ferie) {
        this.doctor = null;
        this.dayOfYear = dayOfYear;
        this.ferie = ferie;
    }
    public String getInfo(){
        String str;
        if (ferie) str = "ferie";
        else if (doctor==null) str = "non affecté";
        else str = doctor.getName();

        return str;
    }

    public boolean booked() {
        if (ferie) return true;
        if (doctor==null) return false;
        return true;
    }

    public void affect(AstDoctor doctor) {
        this.doctor = doctor;
        doctor.inc();
    }

    public void toggleFerie(){
        ferie = !ferie;
    }

    public void clear() {
        this.doctor = null;
    }

    public int getDay() {
        return day;
    }

    public int getDayOfYear(){
        return dayOfYear;
    }


    public AstDoctor getDoctor() {
        return doctor;
    }

    public boolean getFerie() {
        return ferie;
    }
}
