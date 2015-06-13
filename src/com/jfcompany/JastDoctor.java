package com.jfcompany;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by jf on 06/06/2015.
 */

class JastComboDoc extends JComboBox<String>{

    static private String prefStr[] = {"indifferent", "a eviter", "prefere"};

    private int value;
    private String doctor;
    private AstControl control;

    public JastComboDoc(AstControl control, String doctor, int value) {
        super(prefStr);
        this.value = value;
        this.control = control;
        this.doctor = doctor;
        addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JastComboDoc combo = (JastComboDoc)e.getSource();
                    System.out.println("Changed event = " + combo.doctor + ", " +combo.value + "; " + e.getItem());
                    combo.control.updateDocDayPref(combo.doctor, combo.value, (String)e.getItem());
                    //control.update((String)e.getItem());
                }
            }
        });
    }
}

public class JastDoctor extends JPanel {
    static private enum PrefDay {INDIF, DISLIKED, LIKED};

    private int prefs[];

    static private String daysStr[] = {"LUN","MAR","MER","JEU","VEN"};


    public JastDoctor(AstControl control, String doctor) {
        super();
        prefs = new int[5];
        JastComboDoc jComboBox;

        for (int i=0;i<5;i++){
            add(new JLabel(daysStr[i]));
            jComboBox = new JastComboDoc(control, doctor, i);
            add(jComboBox);


        }
    }


}
