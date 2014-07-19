package NursePane;

/**
 *
 * @author Nadine
 */
import NursePane.PatientFile;
import hcsmain.*;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.*;
//import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import hcssupport.Func;
//import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class PatientForm implements KeyListener, ListSelectionListener, ActionListener{
    private Vector<PatientInfo> patient;
    private DefaultListModel listMode;
    private Func function;
    private JTextPane[] patientcTP;
    private JList patientList;
    private JTabbedPane PFPanel;
    private JTextField[] patientTF;
    private JButton nextB;
    private int id;

    public PatientForm(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    
    private void init(){
        listMode = new DefaultListModel();
        this.function = new Func();
        this.patientcTP = new JTextPane[4];
        this.patientList = new JList(this.listMode);
        this.patient = new Vector();
        this.function.fillSPatient(patient);
        this.nextB = new JButton();
    }
    
    public void patientForm(JPanel ptPane){
        JScrollPane[] patientSP = new JScrollPane[patientcTP.length];
        
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(8);
        
        // set opacity for panel
        for(int i = 0; i < patientcTP.length; i++){
        patientcTP[i] = new JTextPane();
        patientSP[i] = new JScrollPane(patientcTP[i]);
        this.function.setOpacity(this.patientcTP[i]);
        this.function.setOpacity(patientSP[i]);
        }
        //set Demensions
        patientList.setPreferredSize(new Dimension(20, 125));
        
        // set title for list
        TitledBorder title = BorderFactory.createTitledBorder
		(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patients");
        title.setTitleJustification(TitledBorder.LEFT);
        patientList.setBorder(title);
        patientList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) patientList.getCellRenderer()).setOpaque(false);

        //timer
        final JTextPane timeTP = new JTextPane();
        timeTP.setContentType("text/html");
        timeTP.setPreferredSize(new Dimension(20, 15));
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
        
        String[] lb = {"Patient", "Patient's birth date"};

        patientTF = new JTextField[lb.length];
        for (int i = 0; i < lb.length; i++) {
            patientTF[i] = new JTextField(15);
            TitledBorder tl = BorderFactory.createTitledBorder
                    (BorderFactory.createLineBorder(Color.DARK_GRAY), lb[i]);
            tl.setTitleJustification(TitledBorder.LEFT);
            patientTF[i].setBorder(tl);
            patientTF[i].setOpaque(false);
        }
        patientTF[1].setPreferredSize(new Dimension(80, 40));

        //building pane
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patientTF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patientTF[1], new GridBagConstraints(3, 0, 2, 1, 0.2, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patientList, new GridBagConstraints(4, 1, 1, 8, 0.2, 0.2,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 45, 15), 0, 0));
        String[] lb1 = {"Patient's General Information",
            "Patient's Prescriptions", "Patient's Anamnesis", "Patient's Diagnosis"};
        int pozY = 1;

        for (int i = 0; i < lb1.length; i++) {
            ptPane.add(patientSP[i], new GridBagConstraints
                    (0, pozY, 4, 2, 0.5, 0.5, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

            TitledBorder titleSP = BorderFactory.createTitledBorder
                    (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), lb1[i]);
            titleSP.setTitleJustification(TitledBorder.LEFT);
            patientSP[i].setBorder(titleSP);
            patientSP[i].setVisible(true);

            pozY+=2;
        }
        this.nextB.setText("Next");
        this.nextB.addActionListener(this);

        ptPane.add(nextB, new GridBagConstraints(4, 8, 1, 1, 0, 0.5,
                GridBagConstraints.SOUTH,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        
        // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                function.fillSPatient(patient);
                //doctorsTP.setText(function.fillStaffInfo("gp"));
                patientcTP[0].setText(null);
            }
        }).start();
        
        //filling the text box and list
        patientTF[0].addKeyListener(this);
        patientList.addListSelectionListener(this);

       
    }
    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {
                //fills the list according to the typed letters
            if (this.PFPanel.getSelectedIndex() == 0 && 
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!patientTF[0].getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)))){

                function.patientList(patient, patientTF[0], listMode);
            
        }
    }
    public void actionPerformed(ActionEvent e){
        if((JButton) e.getSource()==this.nextB){
            this.PFPanel.setSelectedIndex(1);
        }
    }
    public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
            if (this.PFPanel.getSelectedIndex() == 0 &&
                    patientList.getSelectedIndex() >= 0 &&
                    e.getSource() == patientList) {
                String st = patientList.getSelectedValue().toString();
                function.patientInfo(patient, st, patientTF, patientcTP);

                this.id = function.patientInfo(patient, st, patientTF, patientcTP);
            }
        }
    }
}
