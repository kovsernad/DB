package NursePane;

/**
 *
 * @author Nadine
 */
import hcssupport.DB;
import hcsmain.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import hcssupport.Func;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.event.*;
//import javax.swing.event.ListSelectionListener;

public class PatientFile implements KeyListener, ListSelectionListener, ActionListener {

    private Vector<PatientInfo> patient;
    private Vector<DrugInfo> drugs;
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

    public PatientFile() {
        this.init();
    }

    public int getId(){
        return ptid;
    }

    public PatientFile(JTabbedPane Panel) {
        this.PFPanel = Panel;
        this.init();
    }

    private void init() {
        listModeDrug = new DefaultListModel();
        listMode = new DefaultListModel();
        this.function = new Func();
        this.patientTP = new JTextPane();
        this.patientList = new JList(this.listMode);
        this.drugList = new JList(this.listModeDrug);
        this.patient = new Vector();
        this.drugs = new Vector();
        this.function.fillSPatient(patient);
        this.function.fillDrugs(drugs);
        this.submitB = new JButton();
        String[] lb = {"Patient", "Patient's birth date", "Drugs"};

        patient2TF = new JTextField[lb.length];
        for (int i = 0; i < lb.length; i++) {
            patient2TF[i] = new JTextField(15);
            TitledBorder tl = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), lb[i]);
            tl.setTitleJustification(TitledBorder.LEFT);
            patient2TF[i].setBorder(tl);
            patient2TF[i].setOpaque(false);
        }
        temppatient = new PatientInfo();

    }

    public void setPatient(){
        
        function.fillPatient(temppatient, ptid);
        patient2TF[0].setText(this.temppatient.getFname()+" "+this.temppatient.getLname());
        System.out.println(this.temppatient.getFname());

    }

    public void patientFile(JPanel ptPane) {
       
        patient2TF[0].revalidate();
        patient2TF[0].repaint();

        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(3);

        this.drugList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.drugList.setSelectedIndex(0);
        this.drugList.setVisibleRowCount(3);
        
        // set title for list
        this.function.makeElementWithBorder(patientList, "Patients", null, true);
        patientList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) patientList.getCellRenderer()).setOpaque(false);

        this.function.makeElementWithBorder(drugList, "Drugs", null, true);
        drugList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) drugList.getCellRenderer()).setOpaque(false);
        
        //set Demensions
        this.patientJS = new JScrollPane(this.patientList);
        this.function.setOpacity(this.patientJS);
        patientJS.setPreferredSize(new Dimension(20, 125));         
        patientList.setPreferredSize(new Dimension(20, 125));
        
        drugList.setPreferredSize(new Dimension(20, 125));
        this.drugJS = new JScrollPane(this.drugList);
        this.function.setOpacity(this.drugJS);
        drugJS.setPreferredSize(new Dimension(20, 125));

        // set opacity for panel
        JScrollPane patientSP = new JScrollPane(patientTP);
        patientTP = new JTextPane();
        patientSP = new JScrollPane(patientTP);
        this.function.setOpacity(this.patientTP);
        this.function.setOpacity(patientSP);
        TitledBorder titlecSP = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's General Information");
        titlecSP.setTitleJustification(TitledBorder.LEFT);
        patientSP.setBorder(titlecSP);
        patientSP.setVisible(true);
      
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

        
        //text areas with labels and scrolls
        String[] lbta = {"Patient's Anamnesis", "Patient's Diagnosis", "Patient's Prescriptions"};

        patientTA = new JTextArea[lbta.length];
        taJS = new JScrollPane[lbta.length];

        for (int i = 0; i < lbta.length; i++) {
            patientTA[i] = new JTextArea();
            patientTA[i].setLineWrap(true);
            taJS[i] = new JScrollPane(patientTA[i]);
            TitledBorder titlePresc = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), lbta[i]);
            titlePresc.setTitleJustification(TitledBorder.LEFT);
            taJS[i].setBorder(titlePresc);
            taJS[i].setVisible(true);
        }


        /****************/
//        System.out.println("ptid"+ptid);
//        temppatient = new PatientInfo();
//        function.fillPatient(temppatient, ptid);

        /******************/


        patient2TF[1].setPreferredSize(new Dimension(200, 40));
        patient2TF[2].setPreferredSize(new Dimension(200, 40));
        patientSP.setPreferredSize(new Dimension(600, 100));

        
//        patient2TF[0].setText(this.temppatient.getFname()+" "+this.temppatient.getLname());
        //building pane
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        ptPane.add(patient2TF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        ptPane.add(patient2TF[1], new GridBagConstraints(3, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
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
            ptPane.add(taJS[i], new GridBagConstraints(0, pozY, 4, 2, 0.5, 0.5, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
            taJS[i].setPreferredSize(new Dimension(600, 100));
            pozY += 2;
        }
        ptPane.add(submitB, new GridBagConstraints(4, 9, 1, 1, 0, 0,
                GridBagConstraints.SOUTH,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));

        // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                function.fillSPatient(patient);
                function.fillDrugs(drugs);

            }
        }).start();

        //filling the text box and list
        patient2TF[0].addKeyListener(this);
        patient2TF[2].addKeyListener(this);
        patientList.addListSelectionListener(this);
        drugList.addListSelectionListener(this);

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

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if ((this.PFPanel.getSelectedIndex() == 0 || this.PFPanel.getSelectedIndex() == 1) &&
                    patientList.getSelectedIndex() >= 0 &&
                    e.getSource() == patientList) {
                String st = patientList.getSelectedValue().toString();
                function.patientInfo(patient, st, patient2TF, patientTP, null);

                this.ptid = function.patientInfo(patient, st, patient2TF, patientTP, null);
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
    
     public void actionPerformed(ActionEvent e) {
        if ((JButton) e.getSource() == this.submitB) {
            DB.db.diagAnamUpdate(patientTA[0].getText(), patientTA[1].getText(), ptid);
            String tmp = patientTA[2].getText();
//            tmp = tmp.replaceAll("[(]", "|");
//            tmp = tmp.replaceAll("[)]", "");
            DB.db.prescInsert(tmp, ptid, drugstr);
            JOptionPane.showMessageDialog(null, "The Diagnosis, Anamnesis and Prescription were submitted!");
//             System.out.println("drug id "+drugstr);
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}
