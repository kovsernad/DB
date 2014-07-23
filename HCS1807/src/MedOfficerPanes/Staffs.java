/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MedOfficerPanes;

import hcssupport.DB;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ContEd Student
 */
public class Staffs implements KeyListener, ListSelectionListener, ActionListener {

    private JTextPane tpPatiens;
    private JTextPane staffinfoTP;
    private JTextField[] staffTF;
    private JScrollPane[] staffSP;
    private JList hospStaffList;
    private JList staffList;
    private JList positionList;
    private JList qualificationList;
    private Func function;
    private JTextField findStaffTF;
    private DefaultListModel listModel;
    private DefaultListModel listModelHosp;
    private DefaultListModel listPosition;
    private DefaultListModel listQual;
    private Vector<StaffInfo> staff;
    private JTabbedPane MOPanel;
    private JButton btnAddStaff;
    private JButton btnEditStaff;
    private String[] labels;
    private JScrollPane staffHospSP;
    private JScrollPane spPositionList;
    private JScrollPane spQualificationList;
    private JTable table;
    private JPanel staffPane;

    public Staffs(JTabbedPane Panel) {
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
        this.staffinfoTP = new JTextPane();
        this.findStaffTF = new JTextField(25);
        this.staffList = new JList(this.listModel);
        this.hospStaffList = new JList(this.listModelHosp);
        this.positionList = new JList(this.listPosition);
        this.qualificationList = new JList(this.listQual);
        this.function = new Func();
        this.staff = new Vector();
        this.function.fillStaff(staff);
        this.btnAddStaff = new JButton();
        this.btnEditStaff = new JButton();
        table = new JTable(tabRows, tabHead);
        this.staffHospSP = new JScrollPane(table);


    }

    public void staff(String[] lab, JPanel staffPane, int loggedId) {

        this.staffPane = staffPane;
        JScrollPane patientsSP = new JScrollPane(tpPatiens);
        JScrollPane staffinfoSP = new JScrollPane(staffinfoTP);
        StringBuffer loggedUser = new StringBuffer();
        JTextPane lblLoggedStaff = new JTextPane();

        this.spQualificationList = new JScrollPane(this.qualificationList);
        this.qualificationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.qualificationList.setSelectedIndex(0);
        this.qualificationList.setVisibleRowCount(3);
        this.function.setOpacity(this.spQualificationList);

// for welcome label
        for(int i = 0; i < staff.size(); i++){
            if (staff.get(i).getId() == loggedId){
                loggedUser.append("<b>"+staff.get(i).getLname());
                loggedUser.append(", ");
                loggedUser.append(staff.get(i).getFname()+"</b>");
                if(staff.get(i).getPosition().equalsIgnoreCase("gp"))
                    loggedUser.append("  [logged as Dr.]");
                else if (staff.get(i).getPosition().equalsIgnoreCase("ns"))
                    loggedUser.append("  [logged as R.N.]");
                else if (staff.get(i).getPosition().equalsIgnoreCase("ma"))
                    loggedUser.append("  [logged as Med.As.]");
                else if (staff.get(i).getPosition().equalsIgnoreCase("mo"))
                    loggedUser.append("  [logged as Med.Of.]");

            }
        }
        lblLoggedStaff.setContentType("text/html");
        lblLoggedStaff.setText(loggedUser.toString());
        this.function.setOpacity(lblLoggedStaff);
        lblLoggedStaff.setEditable(false);


        this.labels = lab;

        // staff list for auto filling
        staffList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        staffList.setSelectedIndex(0);
        staffList.setVisibleRowCount(8);

        hospStaffList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hospStaffList.setSelectedIndex(0);
        hospStaffList.setVisibleRowCount(8);

        this.positionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.positionList.setSelectedIndex(0);
        this.positionList.setVisibleRowCount(3);

        staffTF = new JTextField[labels.length];
        staffSP = new JScrollPane[staffTF.length];

        this.btnAddStaff.setText("Add Staff");
        this.btnEditStaff.setText("Edit Staff");

        for (int i = 0; i < labels.length; i++) {
            staffTF[i] = new JTextField(15);
            this.staffSP[i] = new JScrollPane(staffTF[i]);
            this.function.makeElementWithBorder(staffSP[i], labels[i], Color.LIGHT_GRAY.darker(), false);
            staffTF[i].setBorder(null);
            this.function.setOpacity(staffSP[i]);
            staffSP[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            staffSP[i].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            staffTF[i].setOpaque(false);
            staffTF[i].setEditable(false);
        }

        // set opacity for left panel
        this.function.setOpacity(tpPatiens);
        this.function.setOpacity(patientsSP);

        //set opacity for info panel
        this.function.setOpacity(staffinfoTP);
        this.function.setOpacity(staffinfoSP);

        this.function.setOpacity(staffHospSP);
        this.table.setRowHeight(20);
        this.table.setEnabled(false);

        this.spPositionList = new JScrollPane(this.positionList);
        this.function.setOpacity(this.spPositionList);


        // set title for list
        this.function.makeElementWithBorder(spQualificationList, "Qualification", Color.RED, true);
        this.function.makeElementWithBorder(staffList, "Staff", null, false);
        this.function.makeElementWithBorder(hospStaffList, "Hospitals", null, false);
        this.function.makeElementWithBorder(spPositionList, "Position", Color.RED, true);
        this.function.makeElementWithBorder(staffHospSP, "Schedule", null, false);

 
        final JTextPane timeTP = new JTextPane();
        timeTP.setContentType("text/html");
        timeTP.setPreferredSize(new Dimension(200, 24));
        timeTP.setEditable(false);
        timeTP.setOpaque(false);
        timeTP.setBorder(null);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM d, yyyy");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");

        JScrollPane dlistSP = new JScrollPane(staffList);
        this.function.setOpacity(dlistSP);

        JScrollPane hlistSP = new JScrollPane(hospStaffList);
        this.function.setOpacity(hlistSP);


        
        // building pane
        this.staffPane.add(timeTP, new GridBagConstraints(0, 0, 2, 2, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        this.staffPane.add(lblLoggedStaff, new GridBagConstraints(0, 1, 2, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        this.staffPane.add(patientsSP, new GridBagConstraints(0, 2, 2, 13, 0.7, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        this.staffPane.add(new JLabel("Staff"), new GridBagConstraints(2, 0, 2, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 0, 15, 0), 0, 0));
        this.staffPane.add(dlistSP, new GridBagConstraints(4, 0, 1, 4, 0.1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 5, 5));
        this.staffPane.add(findStaffTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        this.staffPane.add(staffinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.7, 0.1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        this.staffPane.add(hlistSP, new GridBagConstraints(2, 8, 1, 5, 0.1, 0.2, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        this.staffPane.add(staffHospSP, new GridBagConstraints(3, 8, 2, 5, 0.1, 0.2, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        this.staffPane.add(this.btnAddStaff, new GridBagConstraints(2, 13, 3, 1, 1, 0, GridBagConstraints.LINE_END,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
        this.staffPane.add(this.btnEditStaff, new GridBagConstraints(3, 13, 2, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));

        int pozY = 4, pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            this.staffPane.add(staffSP[i], new GridBagConstraints(++pozX, pozY, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
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
//                function.fillStaff(staff, 1);
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


        // filling lists  //

        this.function.setElementList(listPosition, true, false);
        this.function.setElementList(listQual, false, true);
       
        findStaffTF.addKeyListener(this);
        staffList.addListSelectionListener(this);
        this.btnAddStaff.addActionListener(this);
        this.btnEditStaff.addActionListener(this);
        this.hospStaffList.addListSelectionListener(this);
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
                function.staffPersonalInfo(staff, t, staffTF);
            }
        } else if (this.MOPanel.getSelectedIndex() == 0 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!findStaffTF.getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)))) {
            function.staffListInfo(staff, staffinfoTP, findStaffTF, listModel);
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (this.MOPanel.getSelectedIndex() == 0) {
                this.listModelHosp.removeAllElements();
                staffinfoTP.setText(null);
                this.tpPatiens.setText(null);
                listModel.removeAllElements();
                for (int i = 0; i < staffTF.length; i++) {
                    staffTF[i].setText(null);
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
                    staffList.getSelectedIndex() >= 0 &&
                    e.getSource() == staffList) {
                String st = staffList.getSelectedValue().toString();
                function.staffPersonalInfo(staff, st, staffTF);
                function.staffListHosp(staff, st, this.listModelHosp);
                this.tpPatiens.setText(function.patientForDoctor(staff, st));
            } else if (this.MOPanel.getSelectedIndex() == 0 &&
                    this.hospStaffList.getSelectedIndex() >= 0 &&
                    e.getSource() == this.hospStaffList) {
                String st = staffList.getSelectedValue().toString();
                function.checkSchedule(staff, st, this.table, this.hospStaffList);
            } else if (this.MOPanel.getSelectedIndex() == 0 &&
                    this.positionList.getSelectedIndex() >= 0 &&
                    e.getSource() == this.positionList) {
                //TO DO
                staffTF[8].setText((String) this.positionList.getSelectedValue());

            } else if (this.MOPanel.getSelectedIndex() == 0 &&
                    this.qualificationList.getSelectedIndex() >= 0 &&
                    e.getSource() == this.qualificationList) {
                //TO DO
                staffTF[7].setText((String) this.qualificationList.getSelectedValue());
            }
        }
    }

    public void actionPerformed(ActionEvent e) {

        if ((JButton) e.getSource() == this.btnEditStaff) {
            if (this.btnEditStaff.getText().equalsIgnoreCase("Edit staff")) {
                
                    // Check if another button is pressed
                    if (this.btnAddStaff.getText().equalsIgnoreCase("Save changes")) {
                        this.btnAddStaff.setText("Add staff");
                        for (int i = 0; i < this.staffTF.length; i++) {
                            staffTF[i].setEditable(false);
                            staffTF[i].setOpaque(false);
                            this.function.makeElementWithBorder(staffSP[i], labels[i], Color.LIGHT_GRAY.darker(), false);
                        }
                        this.table.setEnabled(false);
                    }
                    // prepare for changing
                    this.btnEditStaff.setText("Save changes");
                    for (int i = 4; i < this.staffTF.length; i++) {
                        staffTF[i].setEditable(true);
                        staffTF[i].setOpaque(true);
                        this.function.makeElementWithBorder(staffSP[i], labels[i], Color.RED, false);
                    }
                    this.function.makeElementWithBorder(staffHospSP, "Schedule", Color.RED, false);
                    this.table.setEnabled(true);
                    // position
                    staffPane.remove(staffSP[8]);
                    staffPane.add(this.spPositionList, new GridBagConstraints(4, 6, 1, 4, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 15), 0, 0));
                    // qualif
                    staffPane.remove(staffSP[7]);
                    staffPane.add(this.spQualificationList, new GridBagConstraints(3, 6, 1, 4, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 15), 0, 0));
                    this.staffPane.revalidate();
                    this.staffPane.repaint();
                    


            } else if (this.btnEditStaff.getText().equalsIgnoreCase("Save changes")) {
                try {
                    this.btnEditStaff.setText("Edit staff");
                    for (int i = 4; i < this.staffTF.length; i++) {
                        staffTF[i].setEditable(false);
                        staffTF[i].setOpaque(false);
                        this.function.makeElementWithBorder(staffSP[i], labels[i], Color.LIGHT_GRAY.darker(), false);
                    }
                    this.function.makeElementWithBorder(staffHospSP, "Schedule", null, false);
                    this.table.setEnabled(false);
                    //position
                    staffPane.remove(this.spPositionList);
                    staffPane.add(staffSP[8], new GridBagConstraints(4, 6, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
                    // qualif
                    staffPane.remove(this.spQualificationList);
                    staffPane.add(staffSP[7], new GridBagConstraints(3, 6, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
                    this.staffPane.revalidate();
                    this.staffPane.repaint();
                    // ----------------------
                    // edit staff
                    int id = -1;
                    int posId = -1;
                    int qualId = -1;
                    for (int i = 0; i < staff.size(); i++) {
                        if (staff.get(i).getLname().equals(staffTF[1].getText()) && staff.get(i).getbDate().toString().equals(staffTF[2].getText())) {
                            id = staff.get(i).getId();
                        }
                    }
                    ResultSet rs = DB.db.position(this.positionList.getSelectedValue().toString());
                    while (rs.next()) {
                        posId = rs.getInt("id");
                    }

                    rs = DB.db.qualification(this.qualificationList.getSelectedValue().toString());
                    while(rs.next()){
                        qualId = rs.getInt("id");
                    }
                    DB.db.staffUpdate(staffTF[4].getText(), staffTF[5].getText(), 
                            staffTF[6].getText(), staffTF[9].getText(), posId, qualId, id);


                    


                    staff.removeAllElements();
                    this.function.fillStaff(staff);
                } catch (SQLException ex) {
                    Logger.getLogger(Staffs.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        } else if ((JButton) e.getSource() == this.btnAddStaff) {
            if (this.btnAddStaff.getText().equalsIgnoreCase("Add staff")) {
                if (this.btnEditStaff.getText().equalsIgnoreCase("Save changes")) {
                    this.btnEditStaff.setText("Edit staff");
                    for (int i = 4; i < this.staffTF.length; i++) {
                        staffTF[i].setEditable(false);
                        staffTF[i].setOpaque(false);
                        this.function.makeElementWithBorder(staffSP[i], labels[i], Color.LIGHT_GRAY.darker(), false);
                    }
                    this.function.makeElementWithBorder(staffHospSP,  "Schedule", null, false);
                    this.table.setEnabled(true);
                }
                this.btnAddStaff.setText("Save changes");
                for (int i = 0; i < this.staffTF.length; i++) {
                    staffTF[i].setEditable(true);
                    staffTF[i].setOpaque(true);
                    staffTF[i].setText(null);
                    this.function.makeElementWithBorder(staffSP[i], labels[i], Color.RED, false);
                }
                this.function.makeElementWithBorder(staffHospSP,  "Schedule", Color.RED, false);
                this.listModel.removeAllElements();
                this.listModelHosp.removeAllElements();
                this.staffinfoTP.setText(null);
                this.findStaffTF.setText(null);
                this.table.setEnabled(true);

                // position
                staffPane.remove(staffSP[8]);
                staffPane.add(this.spPositionList, new GridBagConstraints(4, 6, 1, 4, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 15), 0, 0));

                // qualif
                staffPane.remove(staffSP[7]);
                staffPane.add(this.spQualificationList, new GridBagConstraints(3, 6, 1, 4, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 0, 15), 0, 0));
                this.staffPane.revalidate();
                this.staffPane.repaint();

            } else if (this.btnAddStaff.getText().equalsIgnoreCase("Save changes")) {
                this.btnAddStaff.setText("Add staff");
                for (int i = 0; i < this.staffTF.length; i++) {
                    staffTF[i].setEditable(false);
                    staffTF[i].setOpaque(false);
                    this.function.makeElementWithBorder(staffSP[i],  labels[i], Color.LIGHT_GRAY.darker(), false);
                }
                this.table.setEnabled(false);
                this.function.makeElementWithBorder(staffHospSP,  "Schedule", null, false);

                //position
                staffPane.remove(this.spPositionList);
                staffPane.add(staffSP[8], new GridBagConstraints(4, 6, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));

                // qualif
                staffPane.remove(this.spQualificationList);
                staffPane.add(staffSP[7], new GridBagConstraints(3, 6, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
                this.staffPane.revalidate();
                this.staffPane.repaint();

            }
        }


    }
}
