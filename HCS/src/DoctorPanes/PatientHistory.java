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

public class PatientHistory implements KeyListener, ListSelectionListener, ActionListener, ChangeListener {

    private Func function;
    private Vector<StaffInfo> staff;
    private JTabbedPane PFPanel;
    private JTextPane[] patientTP;
    private JTextField[] patientTF;
    private JList patientList;
    private JScrollPane patientJS;
    private String[] lbtp = {"Patient's General Medical History",
        "Patient's Illness History", "Patient's Medical History"};
    private Vector<PatientInfo> patient;
    private DefaultListModel listMode;
    private JButton backB;
    public static int phid;
    private PatientInfo temppatient;

    public PatientHistory(JTabbedPane Panel) {
        this.PFPanel = Panel;
        this.init();
    }

    private void init() {
        listMode = new DefaultListModel();
        this.function = new Func();
        this.patientTP = new JTextPane[3];
        this.patientList = new JList(this.listMode);
        this.patient = new Vector();
        this.staff = new Vector();
        this.function.fillStaff(staff);
        this.backB = new JButton();
        this.function.fillPatient(patient);
        this.temppatient = new PatientInfo();
        phid = -1;
    }

    public void PatientHistory(JPanel hPanel, int loggedId) {
        // for welcome label
        StringBuffer loggedUser = new StringBuffer();
        JTextPane tpLoggedStaff = new JTextPane();

        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).getId() == loggedId) {
                loggedUser.append("<b>" + staff.get(i).getLname());
                loggedUser.append(", ");
                loggedUser.append(staff.get(i).getFname() + "</b>");
                if (staff.get(i).getPosition().equalsIgnoreCase("gp")) {
                    loggedUser.append("  [logged as Dr.]");
                } else if (staff.get(i).getPosition().equalsIgnoreCase("ns")) {
                    loggedUser.append("  [logged as R.N.]");
                } else if (staff.get(i).getPosition().equalsIgnoreCase("ma")) {
                    loggedUser.append("  [logged as Med.As.]");
                } else if (staff.get(i).getPosition().equalsIgnoreCase("mo")) {
                    loggedUser.append("  [logged as Med.Of.]");
                }

            }
        }
        tpLoggedStaff.setContentType("text/html");
        tpLoggedStaff.setText(loggedUser.toString());
        this.function.setOpacity(tpLoggedStaff);
        tpLoggedStaff.setEditable(false);

        JScrollPane[] patientSP = new JScrollPane[patientTP.length];

        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(8);


        this.patientJS = new JScrollPane(this.patientList);
        this.function.setOpacity(this.patientJS);

        // set title and size for list
        patientList.setPreferredSize(new Dimension(20, 125));
        patientJS.setPreferredSize(new Dimension(20, 125));

        this.function.makeElementWithBorder(patientList, "Patients", null, true);
        patientList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) patientList.getCellRenderer()).setOpaque(false);


        for (int i = 0; i < patientTP.length; i++) {
            patientTP[i] = new JTextPane();
            patientTP[i].setPreferredSize(new Dimension(600, 100));
            patientSP[i] = new JScrollPane(patientTP[i]);
            this.function.setOpacity(this.patientTP[i]);
            this.function.setOpacity(patientSP[i]);
            function.makeElementWithBorder(patientSP[i], lbtp[i], Color.DARK_GRAY, false);
            patientSP[i].setVisible(true);
            patientSP[i].setPreferredSize(new Dimension(600, 100));
        }



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
        //textfields
        String[] lb = {"Patient", "Patient's birth date"};

        patientTF = new JTextField[lb.length];
        for (int i = 0; i < lb.length; i++) {
            patientTF[i] = new JTextField(15);
            function.makeElementWithBorder(patientTF[i], lb[i], Color.DARK_GRAY, false);
            patientTF[i].setOpaque(false);
        }
        patientTF[1].setPreferredSize(new Dimension(200, 40));


        //building pane
        hPanel.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        hPanel.add(tpLoggedStaff, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(50, 15, 0, 15), 0, 0));

        hPanel.add(patientTF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        hPanel.add(patientTF[1], new GridBagConstraints(3, 0, 2, 1, 0.3, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        hPanel.add(patientJS, new GridBagConstraints(4, 1, 1, 8, 0.2, 0.2,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 45, 15), 0, 0));

        int pozX = 0;

        for (int i = 0; i < lbtp.length; i++) {
            hPanel.add(patientSP[i], new GridBagConstraints(pozX, 1, 1, 8, 0.5, 0.5, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(15, 5, 15, 5), 0, 0));

            pozX++;
        }

        this.backB.setText("Back");
        this.backB.addActionListener(this);

        hPanel.add(backB, new GridBagConstraints(4, 8, 1, 1, 0, 0.5,
                GridBagConstraints.SOUTH,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));


        // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
//                function.fillSPatient(patient);
            }
        }).start();

        //filling the text box and list
        patientTF[0].addKeyListener(this);
        patientList.addListSelectionListener(this);
        this.PFPanel.addChangeListener(this);
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {

        //fills the list according to the typed letters
        if (this.PFPanel.getSelectedIndex() == 2 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!patientTF[0].getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)))) {

            function.patientList(patient, patientTF[0], listMode);

        }
    }

    public void actionPerformed(ActionEvent e) {
        if ((JButton) e.getSource() == this.backB) {
            this.PFPanel.setSelectedIndex(1);
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (this.PFPanel.getSelectedIndex() == 2 &&
                    patientList.getSelectedIndex() >= 0 &&
                    e.getSource() == patientList) {
                for (int i = 0; i < lbtp.length; i++) {
                    patientTP[i].setText("");
                }
                String st = patientList.getSelectedValue().toString();

                function.patientHisoryInfo(patient, st, patientTP);
                function.patientInfo(patient, st, patientTF, null, null);

            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        if (phid == -1) {
            phid = PatientActiveFile.ptid;
        if(phid == 0){
            patientTF[0].setText("");
            patientTF[1].setText("");
        }else{
        function.fillPatient(temppatient, phid);
        patientTF[0].setText(this.temppatient.getFname() + " " + this.temppatient.getLname());
        patientTF[1].setText("" + temppatient.getBdate());
        patientTP[0].setText(this.temppatient.getGmedhistory());
        patientTP[1].setText(this.temppatient.getIllnesshistory());
        patientTP[2].setText(this.temppatient.getMedspechistory());}
        }
    }
}
