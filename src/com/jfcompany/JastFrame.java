package com.jfcompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

/**
 * Created by jf on 27/05/2015.
 */
public class JastFrame extends JFrame implements ActionListener {
    private AstControl control;
    private JComboBox<String> jDoctorCombo;

    public JMenu getJMenuDoctor() {
        return jMenuDoctor;
    }

    private JMenu jMenuDoctor;
    public JastFrame(AstControl control) throws HeadlessException {
        super("astreintes");
        this.control = control;
        initialize();


    }

    private void initialize() {
        JMenu menu;
        JMenuItem menuItem;
        setContentPane(control.getjCal());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("file");
        menuBar.add(menu);
        menuItem = new JMenuItem("save");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("save");
                control.save();
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("load");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("load");
                control.load();
            }
        });
        menu.add(menuItem);
        jMenuDoctor = new JMenu("docteurs");
        menuBar.add(jMenuDoctor);
        menuItem = new JMenuItem("ajout");
        menuItem.addActionListener(this);
        jMenuDoctor.add(menuItem);
        setJMenuBar(menuBar);

        jDoctorCombo = new JComboBox<String>();
        jDoctorCombo.addItem("calendrier global");
        jDoctorCombo.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("StateChanged event = " + e.getItem());
                    control.update((String)e.getItem());
                }
            }
        });
        menuBar.add(jDoctorCombo);
        JButton affectButton = new JButton("affectation");
        affectButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("affectation");
                control.affectation();
            }
        });
        menuBar.add(affectButton);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String s = (String)JOptionPane.showInputDialog(
                this,
                "entrez le nom",
                "ajout docteur",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "nom");
        System.out.println("coucou " + s);
    if (s != null) control.addDoctor(s);
    }

    public void addMenuDoctor(String name){
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doctor = ((JMenuItem)(e.getSource())).getText();
                int result = JOptionPane.showConfirmDialog(
                        null,
                        new JastDoctor(control, doctor),
                        "docteur " + doctor, JOptionPane.OK_CANCEL_OPTION);
                System.out.println("coucou " + result);
            }
        });

        jMenuDoctor.add(menuItem);

    }

    public void addDoctor(String doctor) {
        jDoctorCombo.addItem(doctor);
    }

    public String getDoctor(){
        return (String)jDoctorCombo.getSelectedItem();
    }
}
