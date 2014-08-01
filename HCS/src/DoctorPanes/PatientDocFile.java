package DoctorPanes;

/**
 *
 * @author Nadin
 */
import hcsmain.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.event.*;

public class PatientDocFile implements KeyListener, ListSelectionListener, ActionListener{
    private Vector<PatientInfo> patient;
    private Vector<TestInfo> tests;
    private Vector<StaffInfo> staff;
    private DefaultListModel listMode;
    private DefaultListModel testListModel;
    private Func function;
    private JTextPane[] patientTP;
    private JTextPane testTP;
    private JList patientList;
    private JList testList;
    private JScrollPane testListSP;
    private JScrollPane patientListSP;
    private JTabbedPane PFPanel;
    private JTextField[] patientTF;
    private JButton nextB;
    public static int id;
    private int staffid;
    
    public PatientDocFile(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    private void init(){
        listMode = new DefaultListModel();
        this.testListModel = new DefaultListModel();
        this.function = new Func();
        this.patientTP = new JTextPane[4];
        this.testTP = new JTextPane();
        this.patientList = new JList(this.listMode);
        this.testList = new JList(this.testListModel);
        this.patient = new Vector();
        this.tests = new Vector();
        this.staff = new Vector();
        this.function.fillStaff(staff);
        this.function.fillPatient(patient);
        this.nextB = new JButton();
    }
    
    public void PatientDocFile(JPanel ptPane, int loggedId){
// for welcome label
        staffid = loggedId;
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

        //set Demensions
        patientList.setPreferredSize((new Dimension(80, 40)));
        testList.setPreferredSize((new Dimension(80, 40)));
        
        // set title for list
        function.makeElementWithBorder(patientList, "Patients", Color.GRAY, false);
        patientList.setOpaque(false);
        
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(3);

        function.makeElementWithBorder(testList,  "Tests", Color.GRAY, false);
        testList.setOpaque(false);

        testList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testList.setSelectedIndex(0);
        testList.setVisibleRowCount(3);

        this.patientListSP = new JScrollPane(this.patientList);
        this.function.setOpacity(this.patientListSP);
        patientListSP.setPreferredSize((new Dimension(80, 40)));

        this.testListSP = new JScrollPane(this.testList);
        this.function.setOpacity(this.testListSP);
        testListSP.setPreferredSize((new Dimension(80, 40)));
       
        JScrollPane[] patientSP = new JScrollPane[patientTP.length];
        JScrollPane testSP = new JScrollPane(this.testTP);

        function.makeElementWithBorder(testSP, "Test Results", Color.DARK_GRAY, false);
        testSP.setVisible(true);
                
        this.function.setOpacity(testTP);
        this.function.setOpacity(testSP);
        testTP.setPreferredSize((new Dimension(80, 40)));
        testSP.setPreferredSize((new Dimension(80, 40)));
       
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

        //patient panes and labels
        String[] lb1 = {"Patient's General Information",
            "Patient's Prescriptions", "Patient's Anamnesis",
            "Patient's Diagnosis"};
        for (int i = 0; i < lb1.length; i++){
            patientTP[i] = new JTextPane();
            patientTP[i].setPreferredSize(new Dimension(600, 100));
            patientSP[i] = new JScrollPane(patientTP[i]);
            this.function.setOpacity(this.patientTP[i]);
            this.function.setOpacity(patientSP[i]);
            patientSP[i].setPreferredSize(new Dimension(600, 100));
            function.makeElementWithBorder(patientSP[i], lb1[i], Color.DARK_GRAY, false);
            patientSP[i].setVisible(true);
        }

        
        //text fields and labels
        String[] lb = {"Patient", "Patient's birth date"};

        patientTF = new JTextField[lb.length];
        for (int i = 0; i < lb.length; i++) {
            patientTF[i] = new JTextField(15);
            function.makeElementWithBorder(patientTF[i], lb[i], Color.DARK_GRAY, false);
            patientTF[i].setOpaque(false);
        }
        patientTF[1].setPreferredSize(new Dimension(80, 40));

        //building pane
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(tpLoggedStaff, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(50, 15, 0, 15), 0, 0));
        
        ptPane.add(patientTF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patientTF[1], new GridBagConstraints(3, 0, 2, 1, 0.2, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patientListSP, new GridBagConstraints(4, 1, 1, 3, 0.2, 0.2,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(testListSP, new GridBagConstraints(4, 4, 1, 1, 0.2, 0.2,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

        int pozY = 1;

        for (int i = 0; i < lb1.length; i++) {
            ptPane.add(patientSP[i], new GridBagConstraints
                    (0, pozY, 4, 2, 0.5, 0.5, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

            pozY+=2;
        }
        ptPane.add(testSP, new GridBagConstraints(4, 5, 1, 4, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 55, 15), 0, 0));

        this.nextB.setText("Next");
        this.nextB.addActionListener(this);

        ptPane.add(nextB, new GridBagConstraints(4, 8, 1, 1, 0, 0.5,
                GridBagConstraints.SOUTH,
                GridBagConstraints.NONE, new Insets(0, 15, 5, 15), 0, 0));
        
        // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                function.fillPatient(patient);
                //doctorsTP.setText(function.fillStaffInfo("gp"));
                patientTP[0].setText(null);
            }
        }).start();
        
        //filling the text box and list
        patientTF[0].addKeyListener(this);
        patientList.addListSelectionListener(this);
        testList.addListSelectionListener(this);

       
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
                function.patientInfo(patient, st, patientTF, null, patientTP);

                id = function.patientInfo(patient, st, patientTF, null, patientTP);
                function.fillTests(tests, id, staffid);
                function.testList(tests, testListModel, testTP);
                PatientActiveFile.ptid = PatientDocFile.id;
            }
        }
    }
}
