package com.jfcompany;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by jf on 24/05/2015.
 */
public class Main {
    private JTextArea textArea1;

    private AstControl control;

    public static void main(String[] args) {
        //UIManager.put("Label.font",new Font(Font.DIALOG, Font.PLAIN, 14));

        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
        new Main();

    }

    public Main() {
        control = new AstControl(2015);
        // JFrame frame = new JastFrame(control);

        /*
        // docteurs bidons
        AstDoctor doctor;
        astCalendar.addDoctor(new AstDoctor("PEPPER"));
        astCalendar.addDoctor(new AstDoctor("SALT"));
        doctor = new AstDoctor("MUTARDE");
        doctor.setNonAvailable(Calendar.MONDAY);
        astCalendar.addDoctor(doctor);

        astCalendar.AutomaticAffect();
        */
        control.update();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
