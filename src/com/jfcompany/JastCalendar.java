package com.jfcompany;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.Border;

class JastDayMouseListener implements MouseListener {
    private AstControl control;

    public JastDayMouseListener(AstControl control){
        super();
        this.control = control;
    }
	public void mouseClicked(MouseEvent e) {
		//System.out.println("Mouse Clicked: (" + e.getX() + ", " + e.getY() + ")");
		int s = ((JastDay)(e.getSource())).getDayOfYear();
		System.out.println("day = " + s);
		control.getDayClicked(s);
        //control.getCal().getEvent(s-1).toggleFerie();
        //control.update();
	}

	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}

public class JastCalendar extends JPanel {

	Calendar calendar;
	Border blackline;
	Color ferieBg;
    AstControl control;
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private ArrayList<JastDay> dayList;
	private JastDayMouseListener mouseListener = new JastDayMouseListener(control);

	public JastCalendar(AstControl control) {
		super(new BorderLayout());
        this.control = control;
	    initialize();
	 }
	
	private void initialize(){
		int days;
		JComponent jcomp;

        this.calendar = control.getCal().getCalendar();
        mouseListener = new JastDayMouseListener(control);
		JPanel pane;
		JPanel paneMonths = new JPanel(new GridLayout(0,12));
		Dimension dimension = new Dimension(300,20);
		blackline = BorderFactory.createLineBorder(Color.black);
		ferieBg = new Color(255,220,220);
		int dayWeek;
        dayList = new ArrayList<JastDay>(366);
        JastDay jday;
		for (int i=0;i<12;i++) 
			{
			pane = new JPanel();
			pane.setName("month " + i);
			pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
			//pane.setComponentOrientation(ComponentOrientation.);
			
			calendar.set(Calendar.MONTH, i);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			jcomp = new JLabel(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG,Locale.getDefault()).toUpperCase(), SwingConstants.CENTER);
			jcomp.setBackground(Color.blue);
			jcomp.setForeground(Color.white);
			jcomp.setOpaque(true);
			//jcomp.setAlignmentX(Component.CENTER_ALIGNMENT);
			jcomp.setMaximumSize(dimension);
			jcomp.setBorder(blackline);
			pane.add(jcomp);
			days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
			for (int j=1; j<=days; j++){
				calendar.set(Calendar.DAY_OF_MONTH, j);

				jday = new JastDay(this, control.getColor(calendar.get(Calendar.DAY_OF_YEAR)));
                dayList.add(jday);
                jcomp = jday;
				jcomp.setMaximumSize(dimension);
			    pane.add(jcomp);
			}
			paneMonths.add(pane);
			}
		add(paneMonths, BorderLayout.CENTER);
	}

    public JastDay getJastDay(int day) {
        return dayList.get(day-1);
    }

    public JastDayMouseListener getMouseListener() {
        return mouseListener;
    }


}
