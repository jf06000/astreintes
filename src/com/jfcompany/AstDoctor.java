package com.jfcompany;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jf on 24/05/2015.
 */
public class AstDoctor implements Comparable<AstDoctor>, Serializable {

    public float getDayPref(int day) {
        return dayPref[day];
    }

    static private enum State {WORKING, VACATION, ASSIGNED};


    private String name;
    private float score = 0;
    private float dayPref[];
    static public AstEvent currentEvent;
    private int lastDay = -1;

    public boolean getVacation(int day) {
        return calState[day] == State.VACATION;
    }

    public boolean getAstreinte(int day) {
        return calState[day] == State.ASSIGNED;
    }

    private State calState[];

    public AstDoctor(String name) {

        this.name = name;
        dayPref = new float[7];
        for (int i=0; i<7 ;i++) dayPref[i] = 0;
        calState = new State[367];
        for (int i=0; i<367 ;i++) calState[i] = State.WORKING;
    }

    public String getName() {
        return name;
    }

    public void inc() {
        System.out.println("astreinte " + currentEvent.getDayOfYear()+ " current score " + getCurrentScore());
        calState[currentEvent.getDayOfYear()] = State.ASSIGNED;
        lastDay = currentEvent.getDayOfYear();
        score += 1.0;
    }

    public void toggleVacation(int day){
        if (calState[day]==State.WORKING) calState[day] = State.VACATION;
        else if (calState[day]==State.VACATION) calState[day] = State.WORKING;
    }

    public float getScore(){
        return score;
    }

    public float getCurrentScore(){
        float ret;
        ret = score + dayPref[currentEvent.getDay()];
        if (lastDay != -1) ret += (1.0 / (currentEvent.getDayOfYear() - lastDay));
        if (calState[currentEvent.getDayOfYear()] == State.VACATION) ret += 20.0;
        System.out.println("CurrentScore " + getName() + " = " + ret);
        return ret;
    }

    private int getDayScore(){
        int total = 0;
        for (int i=0; i<7 ;i++) total += dayPref[i];
        return total;
    }

    public void incDayScore(int i){
        //if (getDayScore()<0) dayPref[i]++;
        //todo
        dayPref[i] = 20f;
    }

    public void decDayScore(int i){
        if (getDayScore()>-4) dayPref[i]--;
    }

    @Override
    public int compareTo(AstDoctor d) {

        return (int)((getCurrentScore() - d.getCurrentScore())*100.0);
    }

    public void clear() {
        lastDay = -1;
        score = 0;
        for (int i=0; i<367 ;i++) if (calState[i]==State.ASSIGNED) calState[i] = State.WORKING;
    }
}
