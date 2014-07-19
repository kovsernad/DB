/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MedOfficerPanes;

import hcsmain.Staff;
import hcssupport.Func;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ContEd Student
 */
public class Doctors implements KeyListener, ListSelectionListener {

    private JTextPane doctorsTP;
    private JTextPane docHospTP;
    private JTextPane docinfoTP;
    private JTextField[] docTF;
    private JList hospDoctorList;
    private JList doctorList;
    private Func function;
    private JTextField findDoctorTF;
    private DefaultListModel listModel;
    private DefaultListModel listModelHosp;
    private Vector<Staff> doctor;
    private JTabbedPane MOPanel;

    public Doctors(JTabbedPane Panel) {
        this.MOPanel = Panel;
        this.init();
    }

    private void init() {
        listModel = new DefaultListModel();
        listModelHosp = new DefaultListModel();
        this.doctorsTP = new JTextPane();
        this.docHospTP = new JTextPane();
        this.docinfoTP = new JTextPane();
        this.findDoctorTF = new JTextField(25);
        this.doctorList = new JList(this.listModel);
        this.hospDoctorList = new JList(this.listModelHosp);
        this.function = new Func();
        this.doctor = new Vector();
        this.function.fillStaff(doctor,1);

    }

    public void doctor(String[] labels, JPanel doctorsPane) {

        JScrollPane doctorsSP = new JScrollPane(doctorsTP);
        JScrollPane docinfoSP = new JScrollPane(docinfoTP);
        JScrollPane docHospSP = new JScrollPane(docHospTP);

        // doctor list for auto filling
        doctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        doctorList.setSelectedIndex(0);
        doctorList.setVisibleRowCount(8);

        hospDoctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hospDoctorList.setSelectedIndex(0);
        hospDoctorList.setVisibleRowCount(8);

        docTF = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            docTF[i] = new JTextField(15);
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLUE), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            docTF[i].setBorder(ttl);
            docTF[i].setOpaque(false);
        }

        // set opacity for left panel
        this.function.setOpacity(doctorsTP);
        this.function.setOpacity(doctorsSP);

        //set opacity for info panel
        this.function.setOpacity(docinfoTP);
        this.function.setOpacity(docinfoSP);

        this.function.setOpacity(docHospTP);
        this.function.setOpacity(docHospSP);

        // set dimensions
        docinfoSP.setPreferredSize(new Dimension(400, 100));
        doctorsSP.setPreferredSize(new Dimension(200, 200));
        doctorList.setPreferredSize(new Dimension(150, 175));

        // set title for list
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Doctors");
        title.setTitleJustification(TitledBorder.LEFT);
        doctorList.setBorder(title);
        doctorList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) doctorList.getCellRenderer()).setOpaque(false);

        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Hospitals");
        title.setTitleJustification(TitledBorder.LEFT);
        hospDoctorList.setBorder(title);
        hospDoctorList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) hospDoctorList.getCellRenderer()).setOpaque(false);

        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Schedule");
        title.setTitleJustification(TitledBorder.LEFT);
        docHospSP.setBorder(title);
        docHospSP.setOpaque(false);

        final JTextPane timeTP = new JTextPane();
        timeTP.setContentType("text/html");
        timeTP.setPreferredSize(new Dimension(200, 24));
        timeTP.setEditable(false);
        timeTP.setOpaque(false);
        timeTP.setBorder(null);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM d, yyyy | h:mm:ss a");

        new javax.swing.Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Calendar date = Calendar.getInstance();
                timeTP.setText("<b>" + dateFormat.format(date.getTime()) + "</b>");
            }
        }).start();

        // building pane
        doctorsPane.add(timeTP, new GridBagConstraints(0, 0, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        doctorsPane.add(doctorsSP, new GridBagConstraints(0, 1, 2, 14, 0.5, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        doctorsPane.add(new JLabel("Doctor"), new GridBagConstraints(2, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 0, 15, 0), 0, 0));
        doctorsPane.add(doctorList, new GridBagConstraints(4, 0, 2, 4, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 5, 5));
        doctorsPane.add(findDoctorTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        doctorsPane.add(docinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        doctorsPane.add(hospDoctorList, new GridBagConstraints(2, 8, 1, 5, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        doctorsPane.add(docHospSP, new GridBagConstraints(3, 8, 2, 5, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        doctorsPane.add(new JButton("Add Doctor"), new GridBagConstraints(4, 13, 1, 1, 0.5, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
        doctorsPane.add(new JButton("Edit Doctor"), new GridBagConstraints(3, 13, 2, 1, 0.5, 0, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));

        int pozY = 4, pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            doctorsPane.add(docTF[i], new GridBagConstraints(++pozX, pozY, 1, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }

          // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                function.fillStaff(doctor,1);
                doctorsTP.setText(function.fillStaffInfo("gp"));

            }
        }).start();


        // filling left textbox
        doctorsTP.setText(this.function.fillStaffInfo("gp"));
        findDoctorTF.addKeyListener(this);
        doctorList.addListSelectionListener(this);
        this.hospDoctorList.addListSelectionListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.MOPanel.getSelectedIndex() == 0 && listModel.size() == 1) {
                String t = this.listModel.get(0).toString();
                System.out.println(t);
                function.staffPersonalInfo(doctor, t, docTF);
            }
        } else if (this.MOPanel.getSelectedIndex() == 0 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!findDoctorTF.getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)))) {
            function.staffListInfo(doctor, docinfoTP, findDoctorTF, listModel);
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (this.MOPanel.getSelectedIndex() == 0) {
                this.docHospTP.setText(null);
                this.listModelHosp.removeAllElements();
                docinfoTP.setText(null);
                listModel.removeAllElements();
                for (int i = 0; i < docTF.length; i++) {
                    docTF[i].setText(null);
                }
            }

        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (this.MOPanel.getSelectedIndex() == 0 &&
                    doctorList.getSelectedIndex() >= 0 &&
                    e.getSource() == doctorList) {
                String st = doctorList.getSelectedValue().toString();
                function.staffPersonalInfo(doctor, st, docTF);
                function.staffListHosp(doctor, st, this.listModelHosp);
            } else if (this.MOPanel.getSelectedIndex() == 0 &&
                    this.hospDoctorList.getSelectedIndex() >= 0 &&
                    e.getSource() == this.hospDoctorList) {
                String st = doctorList.getSelectedValue().toString();
                function.checkSchedule(doctor, st, this.docHospTP, this.hospDoctorList);
            }
        }
    }
}
