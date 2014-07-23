/*
Vanier College
DataBase Project
Health Care System
Medical Assistent Pane
Developed by Valerii Doroshenko
 */

package MedAssistPanes;

import hcsmain.PatientInfo;
import hcssupport.Func;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ActivePatient implements KeyListener, ListSelectionListener, ActionListener
{
    private JTextField[] patientTF;
    private JList patientList;
    private DefaultListModel listModel;
    private JTextField findPatientTF;
    private Vector<PatientInfo> actPatient;
    private JTabbedPane MAPanel;
    private Func function;
    private int id;
    private JTextPane[] patientinfTP;

    //Constructor
    public ActivePatient(JTabbedPane Panel) {
        this.MAPanel = Panel;
        this.init();
    }//end constructor

    private void init() {

        listModel = new DefaultListModel();
        this.patientList = new JList(this.listModel);
        this.findPatientTF = new JTextField(25);
        this.function = new Func();
        this.patientinfTP = new JTextPane[3];
        this.actPatient = new Vector();
        this.function.fillSPatient(actPatient);
    }//end init


    public void ActivePatient(String[] labels, JPanel ActivePatientPane) {

        JScrollPane[] patientSP = new JScrollPane[patientinfTP.length];

        // patient list for auto filling
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(8);

        // set opacity for panel
        for(int i = 0; i < patientinfTP.length; i++){
        patientinfTP[i] = new JTextPane();
        patientSP[i] = new JScrollPane(patientinfTP[i]);
        this.function.setOpacity(this.patientinfTP[i]);
        this.function.setOpacity(patientinfTP[i]);
        }

        //creating labels
        patientTF = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            patientTF[i] = new JTextField(15);
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.DARK_GRAY), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            patientTF[i].setBorder(ttl);
            //3 first notes can't be edited
            if (i<3 || i>7)
            {
                TitledBorder ttl2 = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.lightGray), labels[i]);
                    ttl2.setTitleJustification(TitledBorder.LEFT);
                    patientTF[i].setBorder(ttl2);
                patientTF[i].setEditable(false);
            }
            patientTF[i].setOpaque(false);

        }

        // set dimensions
        patientList.setPreferredSize(new Dimension(150, 175));

        // set title for Patients' list
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patients");
        title.setTitleJustification(TitledBorder.LEFT);
        patientList.setBorder(title);
        patientList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) patientList.getCellRenderer()).setOpaque(false);
        // set title for findpatient TF
        TitledBorder title2 = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                "Enter Patient's Name:");
        title.setTitleJustification(TitledBorder.LEFT);
        findPatientTF.setBorder(title2);
        findPatientTF.setOpaque(false);

        //Timer
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

        //Building pane
        ActivePatientPane.add(timeTP, new GridBagConstraints(0, 0, 2, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        ActivePatientPane.add(findPatientTF, new GridBagConstraints(2, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 0, 15, 15), 5, 5));
        ActivePatientPane.add(patientList, new GridBagConstraints(0, 1, 2, 14, 0.5, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

        //Labels for panes
        String[] lb1 = {"Anamnesis","Diagnose","Prescription"};


        //Placing labels into pane
        int pozY = 3, pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            ActivePatientPane.add(patientTF[i], new GridBagConstraints(++pozX, pozY, 1, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
            //3 labels per row
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }

        //Placing panes
        for (int i = 0; i < lb1.length; i++) {

            pozY++;
            pozX = 1;
             ActivePatientPane.add(patientSP[i], new GridBagConstraints
                    (2, pozY, 3, 1, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));

            TitledBorder titleSP = BorderFactory.createTitledBorder
                    (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), lb1[i]);
            titleSP.setTitleJustification(TitledBorder.LEFT);
            patientSP[i].setBorder(titleSP);
            patientSP[i].setVisible(true);
        }

        //Buttons
        ActivePatientPane.add(new JButton("Edit"), new GridBagConstraints(4, 13, 1, 1, 0.5, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
        ActivePatientPane.add(new JButton("Save Changes"), new GridBagConstraints(3, 13, 2, 1, 0.5, 0, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
        ActivePatientPane.add(new JButton("Make an Appointment"), new GridBagConstraints(2, 13, 1, 1, 0.5, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
        ActivePatientPane.add(new JButton("Create New Profile"), new GridBagConstraints(5, 13, 1, 1, 0.5, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));


        // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                function.fillSPatient(actPatient);
                //doctorsTP.setText(function.fillStaffInfo("gp"));
                patientinfTP[0].setText(null);
            }
        }).start();


        //filling the text box and list
        findPatientTF.addKeyListener(this);
        patientList.addListSelectionListener(this);



    }//end active Patient

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
                //fills the list according to the typed letters
            if (this.MAPanel.getSelectedIndex() == 0 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!patientTF[0].getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)))){

                function.patientList(actPatient, findPatientTF, listModel);

        }
    }

    public void actionPerformed(ActionEvent e){
//        if((JButton) e.getSource()==this.nextB){
//            this.PFPanel.setSelectedIndex(1);
//        }
    }

     public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
            if (this.MAPanel.getSelectedIndex() == 0 &&
                    patientList.getSelectedIndex() >= 0 &&
                    e.getSource() == patientList) {
                String st = patientList.getSelectedValue().toString();
                function.patientInfoMA(actPatient, st, patientTF, patientinfTP);

                this.id = function.patientInfoMA(actPatient, st, patientTF, patientinfTP);
            }
        }
    }
}//end active patient
