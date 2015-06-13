package com.jfcompany;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

/**
 * Created by jf on 27/05/2015.
 */
public class AstControl {

    static private Color colBg = Color.white;
    static private Color colBgFerie = new Color(255,220,220);
    static private Color colVacation = new Color(200,255,200);
    static private Color colNotPrefered = new Color(230,255,230);
    static private Color colAstreinte = new Color(255,255,220);

    private AstCalendar cal;
    private JastCalendar jCal;
    private JastFrame frame;


    public AstCalendar getCal() {
        return cal;
    }


    public JastCalendar getjCal() {
        return jCal;
    }


    public AstControl(int year) {
        this.cal = new AstCalendar(year);
        this.jCal = new JastCalendar(this);
        this.frame = new JastFrame(this);

    }

    public void update(){
        int day;
        JastDay jDay;
        AstDoctor doctor;
        for (AstEvent ev: cal.getEvents()) {
            //System.out.println(ev.getInfo());
            day = ev.getDayOfYear();
            jDay = jCal.getJastDay(day);
            Color color;
            if (ev.getFerie()) color = colBgFerie; else color =colBg;
            jDay.setInfo(ev.getInfo(),color);
            }
    }

    public void updateDoctorCalendar(AstDoctor doctor){
        int day;
        JastDay jDay;
        String strNull = "";
        String strAst = "astreinte";
        String str;
        for (AstEvent ev: cal.getEvents()) {
            str = strNull;
            //System.out.println(ev.getInfo());
            day = ev.getDayOfYear();
            jDay = jCal.getJastDay(day);
            Color color = colBg;
            if (doctor.getDayPref(ev.getDay())>0) color = colNotPrefered;
            if (ev.getFerie()) color = colBgFerie;
            if (doctor.getVacation(day)) {
                color = colVacation;
            }
            if (doctor.getAstreinte(day)) {
                color = colAstreinte;
                str = strAst;
            }
            jDay.setInfo(str,color);
        }
    }

    public void addDoctor(String s) {

        frame.addMenuDoctor(s);
        AstDoctor doctor = new AstDoctor(s);
        cal.addDoctor(doctor);
        frame.addDoctor(doctor.getName());
        //cal.Erase();
        //cal.AutomaticAffect();
        //update(frame.getDoctor());
        //updateDoctorCalendar(doctor);
    }

    public Color getColor(int day){
        Color color = colBg;
        if (getCal().getEvent(day - 1).getFerie()) color = colBgFerie;
        return color;
    }

    public void update(String str){
        AstDoctor doctor = cal.getDoctor(str);
        if (doctor == null) update();
        else updateDoctorCalendar(doctor);
    }

    public void getDayClicked(int day) {
        String str = frame.getDoctor();
        AstDoctor doctor = cal.getDoctor(str);
        if (doctor == null) {
            getCal().getEvent(day-1).toggleFerie();
            update();
        }
        else
        {
            doctor.toggleVacation(day);
            updateDoctorCalendar(doctor);
        }

    }

    public void affectation() {
        cal.Erase();
        cal.AutomaticAffect();
        update(frame.getDoctor());
    }

    public void save() {
        try{
            FileOutputStream fout = new FileOutputStream("test.ast");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(cal);
            oos.close();
            fout.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void load() {
        try{
            FileInputStream inputFileStream = new FileInputStream("test.ast");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
            cal = (AstCalendar)objectInputStream.readObject();
            objectInputStream.close();
            inputFileStream.close();

            for (AstDoctor doc:cal.getDoctors()){
                frame.addMenuDoctor(doc.getName());
                frame.addDoctor(doc.getName());
            }
            update();
    } catch (Exception e) {

        e.printStackTrace();
    }
    }

    public void updateDocDayPref(String str, int value, String item) {
        AstDoctor doctor = cal.getDoctor(str);
        if (doctor != null){
            // value   : 0 is for monday
            if (item.equals("a eviter")) doctor.incDayScore(value+Calendar.MONDAY);
            //update(doctor.getName());
            }

    }
}
