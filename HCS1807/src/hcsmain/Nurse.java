/*
 Vanier College
 DataBase Design Project
 Health Care System
 Nurse face
 */
package hcsmain;

import NursePane.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*Nurse Console */
public class Nurse {
     //Creating references of frame and tabbed panels objects
    private JFrame frame;
    private JTabbedPane NSPanel;
    private int userID;
    private PatientForm ptform;
    private PatientFile ptfile;
    private PatientHistory pthistory;
    private PatientIllness ptillness;
    private PatientHistoryC pthistoryc;

        public Nurse(){
//        public Nurse(int id){
//        this.userID = id;
        this.init();
    }

    private void init() {
        //Creating objects of frame and tabbed panels objects
        this.frame = new JFrame();
        this.NSPanel = new JTabbedPane();
        this.ptform = new PatientForm(this.NSPanel);
        this.ptfile = new PatientFile(this.NSPanel);
        this.pthistory = new PatientHistory(this.NSPanel);
        this.ptillness = new PatientIllness(this.NSPanel);
        this.pthistoryc = new PatientHistoryC(this.NSPanel);

        //Creating pane objects
        JPanel patientPane = new JPanel();
        JPanel patientcPane = new JPanel();
        JPanel historygPane = new JPanel();
        JPanel historyilPane = new JPanel();
        JPanel historycPane = new JPanel();

        //Setting names of panes
        this.NSPanel.add("Patient Form", patientPane);
        this.NSPanel.add("Patient  File", patientcPane);
        this.NSPanel.add("Medical General History", historygPane);
        this.NSPanel.add("Medical Illness History", historyilPane);
        this.NSPanel.add("Medical History Specifications", historycPane);

        System.out.println("out "+this.checkTab());

        patientPane.setLayout(new GridBagLayout());
        patientcPane.setLayout(new GridBagLayout());
        historygPane.setLayout(new GridBagLayout());
        historyilPane.setLayout(new GridBagLayout());
        historycPane.setLayout(new GridBagLayout());

//       this.ptform.patientForm(patientPane, userID);
       this.ptform.patientForm(patientPane);
       this.ptfile.patientFile(patientcPane);
       this.pthistory.patientHistory(historygPane);
       this.ptillness.patientIllness(historyilPane);
       this.pthistoryc.patientHistoryC(historycPane);
       
       this.frame.setContentPane(this.NSPanel);
       this.frame.setTitle("HEALTH CARE SYSTEM. Nurse");
       this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 200,
                Toolkit.getDefaultToolkit().getScreenSize().height - 100);
       this.frame.setMinimumSize(new Dimension(900, 600));
       this.frame.setLocationRelativeTo(null);
       this.frame.setVisible(true);
       this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}
//
    private int checkTab() {
        //listener to trace the number of active pane
        final JTextField tmp = new JTextField();
        tmp.setText(String.valueOf(0));
        this.NSPanel.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                tmp.setText(String.valueOf(NSPanel.getSelectedIndex()));
                System.out.println(tmp.getText());
            }
        });
        System.out.println("outside "+ tmp.getText());
        return Integer.parseInt(tmp.getText());
    }

}
