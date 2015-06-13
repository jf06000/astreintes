package com.jfcompany;

import java.io.Serializable;
import java.util.*;

/**
 * Created by jf on 24/05/2015.
 */
public class AstCalendar implements Serializable{

    private int year;

    public Calendar getCalendar() {
        return calendar;
    }
    public Collection<AstEvent> getEvents(){
        return events;
    }

    public AstEvent getEvent(int i){
        return events.get(i);
    }

    private GregorianCalendar calendar;
    private ArrayList<AstEvent> events;
    private ArrayList<AstDoctor> doctorsList;

    public AstCalendar(int year) {
        this.year = year;
        this.calendar = new GregorianCalendar(year, Calendar.JANUARY, 1);
        doctorsList = new ArrayList<AstDoctor>(100);
        events = new ArrayList<AstEvent>(31);
        AstEvent ev;
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        for (int i =0; i<maxDays; i++)
        {
            //System.out.println("coucou");
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            if ((day == Calendar.SATURDAY) || (day == Calendar.SUNDAY)) ev = new AstEvent(calendar.get(Calendar.DAY_OF_YEAR), true);
            else ev = new AstEvent(calendar.get(Calendar.DAY_OF_YEAR),day);
            events.add(ev);
            calendar.add(Calendar.DAY_OF_MONTH, 1);

            System.out.println(events.get(i).getInfo());
        }
        System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));

        calendar.set(year, Calendar.JANUARY, 1);
    }

    public void AutomaticAffect(){
        for (AstEvent ev: events){
            if (ev.booked()==false) {
                AstDoctor.currentEvent = ev;
                Collections.sort(doctorsList);
                ev.affect(doctorsList.get(0));
            }
        }
        //display();

        for (AstDoctor doctor: doctorsList) System.out.println(doctor.getName() + " score = " + doctor.getScore());
    }

    public void Erase(){
        for (AstEvent ev: events){
            ev.clear();
            }
        for (AstDoctor doctor: doctorsList){
            doctor.clear();
        }
        }


    public void addDoctor(AstDoctor doctor){
        doctorsList.add(doctor);
    }

    public void display(){
        for (AstEvent ev: events) {
            System.out.println(ev.getInfo());
        }
    }

    public AstDoctor getDoctor(String str) {
        for (AstDoctor doc: doctorsList)
        {
            if (doc.getName() == str) return doc;
        }
        return null;
    }

    public Collection<AstDoctor> getDoctors() {
        return doctorsList;
    }
}
