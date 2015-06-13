package com.jfcompany;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class JastDay extends JLabel{

	//private JastCalendar jcal;

	static private Border blackline = BorderFactory.createLineBorder(Color.black);
    //static JastDayMouseListener mouseListener = new JastDayMouseListener();

    private int dayOfYear;
    private String info;
    private String strDate;
	
	public JastDay(JastCalendar jcal, Color color){
		super("");
		//this.jcal = jcal; 
		Calendar calendar = jcal.calendar;
        strDate = calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.SHORT,Locale.getDefault()).substring(0, 1).toUpperCase() + " " + calendar.get(Calendar.DAY_OF_MONTH);
		setText(strDate);
		setBorder(blackline);
		int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
		setBackground(color);
		setOpaque(true);
        dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        addMouseListener(jcal.getMouseListener());
	}

    public void setInfo(String info, Color color){
        this.info = info;
        setText(strDate + " " + info);
        setBackground(color);
    }

    public int getDayOfYear(){
        return dayOfYear;
    }

}
