/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MedOfficerPanes;

import hcsmain.*;
import hcssupport.Func;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ContEd Student
 */
public class Doctors implements KeyListener, ListSelectionListener, ActionListener {

    private JTextPane tpPatiens;
    private JTextPane docinfoTP;
    private JTextField[] docTF;
    private JScrollPane[] docSP;
    private JList hospDoctorList;
    private JList doctorList;
    private JList positionList;
    private JList qualificationList;
    private Func function;
    private JTextField findDoctorTF;
    private DefaultListModel listModel;
    private DefaultListModel listModelHosp;
    private DefaultListModel listPosition;
    private DefaultListModel listQual;
    private Vector<Staff> doctor;
    private JTabbedPane MOPanel;
    private JButton btnAddDoctor;
    private JButton btnEditDoctor;
    private String[] labels;
    private JScrollPane docHospSP;
    private JScrollPane spPositionList;
    private JScrollPane spQualificationList;
    private JTable table;
    private JPanel doctorsPane;

    public Doctors(JTabbedPane Panel) {
        this.MOPanel = Panel;
        this.init();
    }

    private void init() {
        Object[] tabHead = {"Day of Week", "AM", "PM"};
        Object[][] tabRows = {  
            {"Sunday", "", ""},
            {"Monday", "", ""},
            {"Tuesday", "", ""},
            {"Wednesday", "", ""},
            {"Thursday", "", ""},
            {"Friday", "", ""},
            {"Saturday", "", ""}
        };
        listModel = new DefaultListModel();
        listModelHosp = new DefaultListModel();
        listPosition = new DefaultListModel();
        listQual = new DefaultListModel();
        this.tpPatiens = new JTextPane();
        this.docinfoTP = new JTextPane();
        this.findDoctorTF = new JTextField(25);
        this.doctorList = new JList(this.listModel);
        this.hospDoctorList = new JList(this.listModelHosp);
        this.positionList = new JList(this.listPosition);
        this.qualificationList = new JList(this.listQual);
        this.function = new Func();
        this.doctor = new Vector();
        this.function.fillStaff(doctor, 1);
        this.btnAddDoctor = new JButton();
        this.btnEditDoctor = new JButton();
        table = new JTable(tabRows, tabHead);
        this.docHospSP = new JScrollPane(table);


    }

    public void doctor(String[] lab, JPanel doctorsPane) {

        this.doctorsPane = doctorsPane;
        JScrollPane doctorsSP = new JScrollPane(tpPatiens);
        JScrollPane docinfoSP = new JScrollPane(docinfoTP);

        this.spQualificationList = new JScrollPane(this.qualificationList);
        this.qualificationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.qualificationList.setSelectedIndex(0);
        this.qualificationList.setVisibleRowCount(3);
        this.function.setOpacity(this.spQualificationList);
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Qualification");
        title.setTitleJustification(TitledBorder.LEFT);
        spQualificationList.setBorder(title);
        qualificationList.setOpaque(false);
//        ((javax.swing.DefaultListCellRenderer) qualificationList.getCellRenderer()).setOpaque(false);



        this.labels = lab;

        // doctor list for auto filling
        doctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        doctorList.setSelectedIndex(0);
        doctorList.setVisibleRowCount(8);

        hospDoctorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hospDoctorList.setSelectedIndex(0);
        hospDoctorList.setVisibleRowCount(8);

        this.positionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.positionList.setSelectedIndex(0);
        this.positionList.setVisibleRowCount(3);

        docTF = new JTextField[labels.length];
        docSP = new JScrollPane[docTF.length];

        this.btnAddDoctor.setText("Add Doctor");
        this.btnEditDoctor.setText("Edit Doctor");

        for (int i = 0; i < labels.length; i++) {
            docTF[i] = new JTextField(15);
            this.docSP[i] = new JScrollPane(docTF[i]);
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker()), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            docTF[i].setBorder(null);
            docSP[i].setBorder(ttl);
            this.function.setOpacity(docSP[i]);
            docSP[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            docSP[i].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            docTF[i].setOpaque(false);
            docTF[i].setEditable(false);
        }

        // set opacity for left panel
        this.function.setOpacity(tpPatiens);
        this.function.setOpacity(doctorsSP);

        //set opacity for info panel
        this.function.setOpacity(docinfoTP);
        this.function.setOpacity(docinfoSP);

        this.function.setOpacity(docHospSP);
        this.table.setRowHeight(20);
        this.table.setEnabled(false);

        // set title for list
         title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Doctors");
        title.setTitleJustification(TitledBorder.LEFT);
        doctorList.setBorder(title);
        doctorList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) doctorList.getCellRenderer()).setOpaque(false);

        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Hospitals");
        title.setTitleJustification(TitledBorder.LEFT);
        hospDoctorList.setBorder(title);
        hospDoctorList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) hospDoctorList.getCellRenderer()).setOpaque(false);


        this.spPositionList = new JScrollPane(this.positionList);
        this.function.setOpacity(this.spPositionList);
        title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), "Position");
        title.setTitleJustification(TitledBorder.LEFT);
        spPositionList.setBorder(title);
        positionList.setOpaque(false);
//        ((javax.swing.DefaultListCellRenderer) positionList.getCellRenderer()).setOpaque(false);

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
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM d, yyyy");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");

        JScrollPane dlistSP = new JScrollPane(doctorList);
        this.function.setOpacity(dlistSP);
//        dlistSP.setBorder(null);

        JScrollPane hlistSP = new JScrollPane(hospDoctorList);
        this.function.setOpacity(hlistSP);
//        hlistSP.setBorder(null);



        
        // building pane
        this.doctorsPane.add(timeTP, new GridBagConstraints(0, 0, 2, 2, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        this.doctorsPane.add(doctorsSP, new GridBagConstraints(0, 1, 2, 14, 0.7, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        this.doctorsPane.add(new JLabel("Doctor"), new GridBagConstraints(2, 0, 2, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 0, 15, 0), 0, 0));
        this.doctorsPane.add(dlistSP, new GridBagConstraints(4, 0, 1, 4, 0.1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 5, 5));
        this.doctorsPane.add(findDoctorTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        this.doctorsPane.add(docinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.7, 0.1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        this.doctorsPane.add(hlistSP, new GridBagConstraints(2, 8, 1, 5, 0.1, 0.2, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        this.doctorsPane.add(docHospSP, new GridBagConstraints(3, 8, 2, 5, 0.1, 0.2, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        this.doctorsPane.add(this.btnAddDoctor, new GridBagConstraints(2, 13, 3, 1, 1, 0, GridBagConstraints.LINE_END,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
        this.doctorsPane.add(this.btnEditDoctor, new GridBagConstraints(3, 13, 2, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));

        int pozY = 4, pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            this.doctorsPane.add(docSP[i], new GridBagConstraints(++pozX, pozY, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 15, 15));
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }

        // fill the informations through the timer (
//        new javax.swing.Timer(500000, new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                function.fillStaff(doctor, 1);
//                doctorsTP.setText(function.fillStaffInfo("gp"));
//
//            }
//        }).start();

        //time and date
        new javax.swing.Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Calendar date = Calendar.getInstance();
                timeTP.setText("<b>" + dateFormat.format(date.getTime()) + 
                        "<br />" + timeFormat.format(date.getTime())+"</b>");
            }
        }).start();


        // filling left textbox  // 
        
        ResultSet rs = DB.db.position();
        try {
            Vector<StringBuffer> t = new Vector();
            this.listPosition.removeAllElements();
            while (rs.next()) {
                 StringBuffer tmp = new StringBuffer();
                 tmp.append(rs.getString("posdesc"));
                 t.add(tmp);
            }
            for (int i = 0; i < t.size(); i++)
                listPosition.add(i, t.get(i).toString());
        } catch (SQLException ex) {
            Logger.getLogger(Doctors.class.getName()).log(Level.SEVERE, null, ex);
        }


        rs = DB.db.qualification();
        try {
            Vector<StringBuffer> t = new Vector();
            this.listQual.removeAllElements();
            while (rs.next()) {
                 StringBuffer tmp = new StringBuffer();
                 tmp.append(rs.getString("qualdesc"));
                 t.add(tmp);
            }
            for (int i = 0; i < t.size(); i++)
                listQual.add(i, t.get(i).toString());
        } catch (SQLException ex) {
            Logger.getLogger(Doctors.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        findDoctorTF.addKeyListener(this);
        doctorList.addListSelectionListener(this);
        this.btnAddDoctor.addActionListener(this);
        this.btnEditDoctor.addActionListener(this);
        this.hospDoctorList.addListSelectionListener(this);
        this.positionList.addListSelectionListener(this);
        this.qualificationList.addListSelectionListener(this);
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
                this.listModelHosp.removeAllElements();
                docinfoTP.setText(null);
                this.tpPatiens.setText(null);
                listModel.removeAllElements();
                for (int i = 0; i < docTF.length; i++) {
                    docTF[i].setText(null);
                }
                for (int i = 0; i < 7; i++) {
                    for (int j = 1; j < 3; j++) {
                        this.table.setValueAt("", i, j);
                    }
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
                this.tpPatiens.setText(function.patientForDoctor(doctor, st));
            } else if (this.MOPanel.getSelectedIndex() == 0 &&
                    this.hospDoctorList.getSelectedIndex() >= 0 &&
                    e.getSource() == this.hospDoctorList) {
                String st = doctorList.getSelectedValue().toString();
                function.checkSchedule(doctor, st, this.table, this.hospDoctorList);
            } else if (this.MOPanel.getSelectedIndex() == 0 &&
                    this.positionList.getSelectedIndex() >= 0 &&
                    e.getSource() == this.positionList) {
                //TO DO
                docTF[8].setText((String) this.positionList.getSelectedValue());

            } else if (this.MOPanel.getSelectedIndex() == 0 &&
                    this.qualificationList.getSelectedIndex() >= 0 &&
                    e.getSource() == this.qualificationList) {
                //TO DO
                docTF[7].setText((String) this.qualificationList.getSelectedValue());

            }
        }
    }

    public void actionPerformed(ActionEvent e) {

        if ((JButton) e.getSource() == this.btnEditDoctor) {
            if (this.btnEditDoctor.getText().equalsIgnoreCase("Edit doctor")) {
                // Check if another button is pressed
                if (this.btnAddDoctor.getText().equalsIgnoreCase("Save changes")) {
                    this.btnAddDoctor.setText("Add doctor");
                    for (int i = 0; i < this.docTF.length; i++) {
                        docTF[i].setEditable(false);
                        docTF[i].setOpaque(false);
                        TitledBorder ttl = BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker()), labels[i]);
                        ttl.setTitleJustification(TitledBorder.LEFT);
                        docSP[i].setBorder(ttl);
                    }
                    this.table.setEnabled(false);
                }
                // prepare for changing
                this.btnEditDoctor.setText("Save changes");
                for (int i = 4; i < this.docTF.length; i++) {
                    docTF[i].setEditable(true);
                    docTF[i].setOpaque(true);
                    TitledBorder ttl = BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.RED), labels[i]);
                    ttl.setTitleJustification(TitledBorder.LEFT);
                    docSP[i].setBorder(ttl);
                }
                TitledBorder title = BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.RED), "Schedule");
                title.setTitleJustification(TitledBorder.LEFT);
                docHospSP.setBorder(title);
                this.table.setEnabled(true);

                // position
                doctorsPane.remove(docSP[8]);
                doctorsPane.add(this.spPositionList, new GridBagConstraints(4, 6, 1, 4, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 15), 0, 0));
                
                // qualif
                doctorsPane.remove(docSP[7]);
                doctorsPane.add(this.spQualificationList, new GridBagConstraints(3, 6, 1, 4, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 15), 0, 0));
                this.doctorsPane.revalidate();
                this.doctorsPane.repaint();


            } else if (this.btnEditDoctor.getText().equalsIgnoreCase("Save changes")) {
                this.btnEditDoctor.setText("Edit doctor");
                for (int i = 4; i < this.docTF.length; i++) {
                    docTF[i].setEditable(false);
                    docTF[i].setOpaque(false);
                    TitledBorder ttl = BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker()), labels[i]);
                    ttl.setTitleJustification(TitledBorder.LEFT);
                    docSP[i].setBorder(ttl);
                }
                TitledBorder title = BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Schedule");
                title.setTitleJustification(TitledBorder.LEFT);
                docHospSP.setBorder(title);
                this.table.setEnabled(false);


                //position
                doctorsPane.remove(this.spPositionList);
                doctorsPane.add(docSP[8], new GridBagConstraints(4, 6, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));

                // qualif
                doctorsPane.remove(this.spQualificationList);
                doctorsPane.add(docSP[7], new GridBagConstraints(3, 6, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
                this.doctorsPane.revalidate();
                this.doctorsPane.repaint();


            }
        } else if ((JButton) e.getSource() == this.btnAddDoctor) {
            if (this.btnAddDoctor.getText().equalsIgnoreCase("Add doctor")) {
                if (this.btnEditDoctor.getText().equalsIgnoreCase("Save changes")) {
                    this.btnEditDoctor.setText("Edit doctor");
                    for (int i = 4; i < this.docTF.length; i++) {
                        docTF[i].setEditable(false);
                        docTF[i].setOpaque(false);
                        TitledBorder ttl = BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker()), labels[i]);
                        ttl.setTitleJustification(TitledBorder.LEFT);
                        docSP[i].setBorder(ttl);
                    }
                    TitledBorder title = BorderFactory.createTitledBorder(
                            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Schedule");
                    title.setTitleJustification(TitledBorder.LEFT);
                    docHospSP.setBorder(title);
                    this.table.setEnabled(true);
                }
                this.btnAddDoctor.setText("Save changes");
                for (int i = 0; i < this.docTF.length; i++) {
                    docTF[i].setEditable(true);
                    docTF[i].setOpaque(true);
                    docTF[i].setText(null);
                    TitledBorder ttl = BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.RED), labels[i]);
                    ttl.setTitleJustification(TitledBorder.LEFT);
                    docSP[i].setBorder(ttl);
                }
                TitledBorder title = BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.RED), "Schedule");
                title.setTitleJustification(TitledBorder.LEFT);
                docHospSP.setBorder(title);
                this.listModel.removeAllElements();
                this.listModelHosp.removeAllElements();
                this.docinfoTP.setText(null);
                this.findDoctorTF.setText(null);
                this.table.setEnabled(true);

                // position
                doctorsPane.remove(docSP[8]);
                doctorsPane.add(this.spPositionList, new GridBagConstraints(4, 6, 1, 4, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 15), 0, 0));

                // qualif
                doctorsPane.remove(docSP[7]);
                doctorsPane.add(this.spQualificationList, new GridBagConstraints(3, 6, 1, 4, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 15), 0, 0));
                this.doctorsPane.revalidate();
                this.doctorsPane.repaint();

            } else if (this.btnAddDoctor.getText().equalsIgnoreCase("Save changes")) {
                this.btnAddDoctor.setText("Add doctor");
                for (int i = 0; i < this.docTF.length; i++) {
                    docTF[i].setEditable(false);
                    docTF[i].setOpaque(false);
                    TitledBorder ttl = BorderFactory.createTitledBorder(
                            BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker()), labels[i]);
                    ttl.setTitleJustification(TitledBorder.LEFT);
                    docSP[i].setBorder(ttl);
                }
                this.table.setEnabled(false);
                TitledBorder title = BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Schedule");
                title.setTitleJustification(TitledBorder.LEFT);
                docHospSP.setBorder(title);

                //position
                doctorsPane.remove(this.spPositionList);
                doctorsPane.add(docSP[8], new GridBagConstraints(4, 6, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));

                // qualif
                doctorsPane.remove(this.spQualificationList);
                doctorsPane.add(docSP[7], new GridBagConstraints(3, 6, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
                this.doctorsPane.revalidate();
                this.doctorsPane.repaint();

            }
        }


    }
}
