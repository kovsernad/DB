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
public class Nurses implements KeyListener, ListSelectionListener{

    private JTextField findNurseTF;
    private DefaultListModel listModelN;
    private DefaultListModel listModelNHosp;
    private Vector<Staff> nurse;
    private Func function;
    private JTextField[] nurseTF;
    private JList hospNursesList;
    private JList nurseList;
    private JTextPane nurseTP;
    private JTextPane nurseHospTP;
    private JTextPane nurseinfoTP;
    private JTabbedPane MOPanel;

    public Nurses(JTabbedPane Panel) {
        this.MOPanel = Panel;
        this.init();
    }
    
    private void init() {
        listModelN = new DefaultListModel();
        listModelNHosp = new DefaultListModel();
        this.nurse = new Vector();
        this.findNurseTF = new JTextField(25);
        this.nurseTP = new JTextPane();
        this.nurseHospTP = new JTextPane();
        this.nurseinfoTP = new JTextPane();
        this.nurseList = new JList(this.listModelN);
        this.hospNursesList = new JList(this.listModelNHosp);
        this.function = new Func();
        this.function.fillStaff(this.nurse, 2);
    }

    public void nurse(String[] labels, JPanel nursesPane) {

        JScrollPane nurseSP = new JScrollPane(nurseTP);
        JScrollPane nurseinfoSP = new JScrollPane(nurseinfoTP);
        JScrollPane nurseHospSP = new JScrollPane(this.nurseHospTP);

        // nurse list for auto filling
        nurseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nurseList.setSelectedIndex(0);
        nurseList.setVisibleRowCount(8);

        hospNursesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hospNursesList.setSelectedIndex(0);
        hospNursesList.setVisibleRowCount(8);

        this.nurseTF = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            nurseTF[i] = new JTextField(15);
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLUE), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            nurseTF[i].setBorder(ttl);
            nurseTF[i].setOpaque(false);
        }

        // set opacity for left panel
        this.function.setOpacity(nurseTP);
        this.function.setOpacity(nurseSP);

        //set opacity for info panel
        this.function.setOpacity(nurseinfoTP);
        this.function.setOpacity(nurseinfoSP);

        this.function.setOpacity(this.nurseHospTP);
        this.function.setOpacity(nurseHospSP);

        // set dimensions
        nurseinfoSP.setPreferredSize(new Dimension(400, 100));
        nurseSP.setPreferredSize(new Dimension(200, 200));
        nurseList.setPreferredSize(new Dimension(150, 175));

        // set title for list
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Nurses");
        title.setTitleJustification(TitledBorder.LEFT);
        nurseList.setBorder(title);
        nurseList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) nurseList.getCellRenderer()).setOpaque(false);

        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Hospitals");
        title.setTitleJustification(TitledBorder.LEFT);
        hospNursesList.setBorder(title);
        hospNursesList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) hospNursesList.getCellRenderer()).setOpaque(false);

        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Schedule");
        title.setTitleJustification(TitledBorder.LEFT);
        nurseHospSP.setBorder(title);
        nurseHospSP.setOpaque(false);

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
        nursesPane.add(timeTP, new GridBagConstraints(0, 0, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        nursesPane.add(nurseSP, new GridBagConstraints(0, 1, 2, 14, 0.5, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        nursesPane.add(new JLabel("Nurse"), new GridBagConstraints(2, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 0, 15, 0), 0, 0));
        nursesPane.add(nurseList, new GridBagConstraints(4, 0, 2, 4, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 5, 5));
        nursesPane.add(findNurseTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        nursesPane.add(nurseinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));


        nursesPane.add(hospNursesList, new GridBagConstraints(2, 8, 1, 5, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        nursesPane.add(nurseHospSP, new GridBagConstraints(3, 8, 2, 5, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        nursesPane.add(new JButton("Add Nurse"), new GridBagConstraints(4, 13, 1, 1, 0.5, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
        nursesPane.add(new JButton("Edit Nurse"), new GridBagConstraints(3, 13, 2, 1, 0.5, 0, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));


        int pozY = 4;
        int pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            nursesPane.add(nurseTF[i], new GridBagConstraints(++pozX, pozY, 1, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 0, 0));
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }


        // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                function.fillStaff(nurse, 2);
                nurseTP.setText(function.fillStaffInfo("ns"));
            }
        }).start();

        // filling left textbox
        nurseTP.setText(this.function.fillStaffInfo("ns"));
        findNurseTF.addKeyListener(this);
        nurseList.addListSelectionListener(this);
        this.hospNursesList.addListSelectionListener(this);
    }

     @Override
    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
             if (this.MOPanel.getSelectedIndex() == 1 && listModelN.size() == 1){
                String t = this.listModelN.get(0).toString();
                function.staffPersonalInfo(nurse, t, nurseTF);
            }
        }
        else if (this.MOPanel.getSelectedIndex() == 1 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!findNurseTF.getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE))))
            function.staffListInfo(nurse, nurseinfoTP, findNurseTF, listModelN);

        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
             if (this.MOPanel.getSelectedIndex() == 1){
                nurseinfoTP.setText(null);
                listModelN.removeAllElements();
            }

        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting())
             if (this.MOPanel.getSelectedIndex() == 1 &&
                    nurseList.getSelectedIndex() >= 0) {
                String st = nurseList.getSelectedValue().toString();
                function.staffPersonalInfo(nurse, st, nurseTF);
            }
    }

}
