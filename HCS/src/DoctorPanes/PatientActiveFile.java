package DoctorPanes;

/**
 *
 * @author Nadin
 */
import hcssupport.DB;
import hcsmain.*;
import java.awt.*;
import javax.swing.*;
import hcssupport.Func;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.event.*;

public class PatientActiveFile implements KeyListener, ListSelectionListener, ActionListener, ChangeListener{
    private Vector<PatientInfo> patient;
    private Vector<DrugInfo> drugs;
    private Vector<StaffInfo> staff;
    private DefaultListModel listMode;
    private DefaultListModel listModeDrug;
    private Func function;
    private JTextPane patientTP;
    private JList patientList;
    private JList drugList;
    private JTabbedPane PFPanel;
    private JButton submitB;
    private JTextField[] patient2TF;
    private JTextArea[] patientTA;
    private JScrollPane[] taJS;
    private JScrollPane drugJS;
    private JScrollPane patientJS;
    public static int ptid;
    private int drugId;
    private String drugstr = "|";
    private PatientInfo temppatient;
    private  String st, st1, st2;
    
    public PatientActiveFile(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init(); 
    }
    private void init(){
        listModeDrug = new DefaultListModel();
        listMode = new DefaultListModel();
        this.function = new Func();
        this.patientTP = new JTextPane();
        this.patientList = new JList(this.listMode);
        this.drugList = new JList(this.listModeDrug);
        this.patient = new Vector();
        this.drugs = new Vector();
        this.staff = new Vector();
        this.function.fillStaff(staff);
        this.function.fillPatient(patient);
        this.function.fillDrugs(drugs);
        this.submitB = new JButton();
        
    }
    public void PatientActiveFile(JPanel ptPane, int loggedId){
        // for welcome label
        StringBuffer loggedUser = new StringBuffer();
        JTextPane tpLoggedStaff = new JTextPane();

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
        tpLoggedStaff.setContentType("text/html");
        tpLoggedStaff.setText(loggedUser.toString());
        this.function.setOpacity(tpLoggedStaff);
        tpLoggedStaff.setEditable(false);
        
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(3);

        this.drugList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.drugList.setSelectedIndex(0);
        this.drugList.setVisibleRowCount(3);
        
        this.drugJS = new JScrollPane(this.drugList);
        this.function.setOpacity(this.drugJS);
        
        this.patientJS = new JScrollPane(this.patientList);
        this.function.setOpacity(this.patientJS);
        
        //set Demensions if not to do that for both list and Js they will jump and move during the search
        patientList.setPreferredSize(new Dimension(200, 125));
        drugList.setPreferredSize(new Dimension(200, 125)); 
        patientJS.setPreferredSize(new Dimension(200, 125));
        drugJS.setPreferredSize(new Dimension(200, 125));

         // set title for list
        this.function.makeElementWithBorder(patientList, "Patients", Color.GRAY, false);
        patientList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) patientList.getCellRenderer()).setOpaque(false);

        this.function.makeElementWithBorder(drugList, "Drugs", Color.GRAY, false);
        drugList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) drugList.getCellRenderer()).setOpaque(false);
       
        //patient pane
        JScrollPane patientSP = new JScrollPane(patientTP);
        patientTP = new JTextPane();
        patientSP = new JScrollPane(patientTP);
        
        this.function.setOpacity(this.patientTP);
        this.function.setOpacity(patientSP);
        function.makeElementWithBorder(patientSP, "Patient's General Information", Color.GRAY, false);
        patientSP.setVisible(true);
        patientSP.setPreferredSize(new Dimension(600, 100));


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

        //text fields with labels
        String[] lb = {"Patient", "Patient's birth date", "Drug"};

        patient2TF = new JTextField[lb.length];
        for (int i = 0; i < lb.length; i++) {
            patient2TF[i] = new JTextField(15);
            function.makeElementWithBorder(patient2TF[i], lb[i], Color.DARK_GRAY, false);
            patient2TF[i].setOpaque(false);
        }
        patient2TF[1].setPreferredSize(new Dimension(200, 40));
        patient2TF[2].setPreferredSize(new Dimension(200, 40));
        
        //text areas with labels
        String[] lbta = {"Patient's Anamnesis", "Patient's Diagnosis", "Patient's Prescriptions"};

        patientTA = new JTextArea[lbta.length];
        taJS = new JScrollPane[lbta.length];

        for (int i = 0; i < lbta.length; i++) {
            patientTA[i] = new JTextArea();
            patientTA[i].setLineWrap(true);
            taJS[i] = new JScrollPane(patientTA[i]);
            function.makeElementWithBorder(taJS[i], lbta[i], Color.DARK_GRAY, false);
            taJS[i].setVisible(true);
        }
        
        //building pane
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        ptPane.add(tpLoggedStaff, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(50, 15, 0, 15), 0, 0));
        
        ptPane.add(patient2TF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patient2TF[1], new GridBagConstraints(3, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patientJS, new GridBagConstraints(4, 1, 1, 5, 0.5, 0.5,
                GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        ptPane.add(patient2TF[2], new GridBagConstraints(4, 6, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        ptPane.add(drugJS, new GridBagConstraints(4, 6, 1, 4, 0.5, 0.5,
                GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(60, 5, 60, 5), 0, 0));
        ptPane.add(patientSP, new GridBagConstraints(0, 1, 4, 2, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

        int pozY = 4;
        for (int i = 0; i < lbta.length; i++) {
            ptPane.add(taJS[i], new GridBagConstraints
                    (0, pozY, 4, 2, 0.5, 0.5, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
            taJS[i].setPreferredSize(new Dimension(600, 100));
            pozY+=2;
        }
        ptPane.add(submitB, new GridBagConstraints(4, 9, 1, 1, 0, 0,
                GridBagConstraints.SOUTH,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));

         // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                function.fillPatient(patient);
                function.fillDrugs(drugs);

            }
        }).start();

         //filling the text box and list
        patient2TF[0].addKeyListener(this);
        patient2TF[2].addKeyListener(this);
        patientList.addListSelectionListener(this);
        drugList.addListSelectionListener(this);

        this.PFPanel.addChangeListener(this);
        this.submitB.setText("Submit");
        this.submitB.addActionListener(this);       
    }

    public void keyReleased(KeyEvent e) {
        if (e.getSource() == patient2TF[0]) {
            if ((/*this.PFPanel.getSelectedIndex() == 0 || */this.PFPanel.getSelectedIndex() == 1) &&
                    ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                    (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                    (!patient2TF[0].getText().isEmpty() &&
                    (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)))) {

                function.patientList(patient, patient2TF[0], listMode);
            }
        } else if (e.getSource() == patient2TF[2]) {
            if ((/*this.PFPanel.getSelectedIndex() == 0 || */this.PFPanel.getSelectedIndex() == 1) &&
                    ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                    (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                    (!patient2TF[2].getText().isEmpty() &&
                    (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)))) {

                function.drugsList(drugs, patient2TF[2], listModeDrug);
            }
        }
    }
    public void actionPerformed(ActionEvent e){
        if((JButton) e.getSource()==this.submitB){
            DB.db.diagAnamUpdate(st, st1, ptid);
            DB.db.prescInsert(st2, ptid, drugstr);
            JOptionPane.showMessageDialog(null, "The Diagnosis, Anamnesis and Prescription were submitted!");
        }
    }
    public void valueChanged(ListSelectionEvent e) {
               if (!e.getValueIsAdjusting()) {
            if ((this.PFPanel.getSelectedIndex() == 0 || this.PFPanel.getSelectedIndex() == 1) &&
                    patientList.getSelectedIndex() >= 0 &&
                    e.getSource() == patientList) {
                String st = patientList.getSelectedValue().toString();
                function.patientInfo(patient, st, patient2TF, patientTP, null);

                ptid = function.patientInfo(patient, st, patient2TF, patientTP, null);
            }
            else if ((this.PFPanel.getSelectedIndex() == 0 || this.PFPanel.getSelectedIndex() == 1) &&
                    drugList.getSelectedIndex() >= 0 &&
                    e.getSource() == drugList) {
                String st = drugList.getSelectedValue().toString();

                function.drugInfo(drugs, st, patient2TF[2],  null);

                this.drugId = function.drugInfo(drugs, st, patient2TF[2],  patientTA[2]);

                drugstr += String.valueOf(drugId).concat("|");
            }
        }
    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_ENTER && patientTA[0].isFocusOwner() == true){
            patientTA[0].append(".");
            st = patientTA[0].getText();
        }

         if(e.getKeyCode() == e.VK_ENTER && patientTA[1].isFocusOwner() == true){
            patientTA[1].append(".");
            st1 = patientTA[1].getText();
        }
        else if(e.getKeyCode() == e.VK_ENTER && patientTA[2].isFocusOwner() == true){
            patientTA[2].append(".");
            st2 = patientTA[2].getText();
             
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void stateChanged(ChangeEvent e) {
        ptid = PatientDocFile.id;
        if(ptid == 0){
          patient2TF[0].setText("");
          patient2TF[1].setText("");
        }else{
        temppatient = new PatientInfo();
        function.fillPatient(temppatient, ptid);
        patient2TF[0].setText(temppatient.getLname()+" "+this.temppatient.getFname());
        patient2TF[1].setText(""+temppatient.getBdate());
        function.patientPersonalInfo(temppatient, null, patientTP, null);}
    }
    
}
