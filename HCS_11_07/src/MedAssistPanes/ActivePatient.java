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

public class ActivePatient implements KeyListener, ListSelectionListener
{        
    private JTextField[] patientTF;
    private JList patientList;
    private DefaultListModel listModel;
    private JTextField findPatientTF;
    private Vector<PatientInfo> actPatient;
    private JTabbedPane MAPanel;
    private Func function;

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
    }//end init


    public void ActivePatient(String[] labels, JPanel ActivePatientPane) {                

        // patient list for auto filling
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(8);

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
        //((javax.swing.DefaultListCellRenderer) findPatientTF.getCellRenderer()).setOpaque(false);

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
            //1 labels per row
            if (i > 10) {
                pozY++;
                pozX = 1;
                ActivePatientPane.add(patientTF[i], new GridBagConstraints(2, pozY, 3, 1, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 0, 0));
            }
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
            
    }//end active Patient

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        
    }

     public void valueChanged(ListSelectionEvent e) {
         
     }
}//end active patient
