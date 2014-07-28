/*
Vanier College
DataBase Project
Health Care System
Medical Assistent Pane
Developed by Valerii Doroshenko
 */

package MedAssistPanes;

import hcsmain.*;
import hcsmain.*;
import hcssupport.Func;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class ActivePatient implements KeyListener, ListSelectionListener, ActionListener
{
    private JTabbedPane MAPanel;
    private JPanel patientPane;
    private JTextField findPatientTF;
    private DefaultListModel listModel;
    private DefaultListModel listModel2;
    private DefaultListModel listModel3;
    private Func function;
    private JList patientList;
    private Vector<StaffInfo> MAstaff;
    private JList testList;
    private JTextPane tpTestDisc,tpPresciption;
    private JTextField[] patientTF;
    private JScrollPane[] patientSP;
    private String[] labels;
    private JTextField findDoctorTF;
    private JTextField findNurseTF;
    private JList hospitalList;
    private JScrollPane calendarSP;
    private JTable table;
    private JButton btnEdit;
    private JButton btnNew;
    private JButton btnAppoint;
    private Vector<PatientInfo> actPatient;
    private int patientid;

    //Constructor
    public ActivePatient(JTabbedPane Panel) {
        this.MAPanel = Panel;
        this.init();
    }//end constructor

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

        this.function = new Func();
        listModel = new DefaultListModel();
        listModel2 = new DefaultListModel();
        listModel3 = new DefaultListModel();
        this.findPatientTF = new JTextField(25);
        this.findDoctorTF = new JTextField(25);
        this.findNurseTF = new JTextField(25);
        this.patientList = new JList(this.listModel);
        this.testList = new JList(this.listModel2);
        this.MAstaff = new Vector();
        this.function.fillStaff(MAstaff);
        this.tpTestDisc = new JTextPane();
        this.tpPresciption = new JTextPane();
        this.hospitalList = new JList(this.listModel3);
        table = new JTable(tabRows, tabHead);
        this.calendarSP = new JScrollPane(table);
        this.btnEdit = new JButton();
        this.btnNew = new JButton();
        this.btnAppoint = new JButton();
        this.actPatient = new Vector();
        this.function.fillSPatient(actPatient);

    }//end init


    public void ActivePatient(String[] labs, JPanel ActivePatientPane,int loggedId) {

        this.patientPane = ActivePatientPane;
        JScrollPane TestDiscSP = new JScrollPane(tpTestDisc);
        JScrollPane presciptionSP = new JScrollPane(tpPresciption);
        //Log in implementation
        StringBuffer loggedUser = new StringBuffer();
        JTextPane tpLoggedStaff = new JTextPane();

        // for welcome label
        for(int i = 0; i < MAstaff.size(); i++){
            if (MAstaff.get(i).getId() == loggedId){
                loggedUser.append("<b>"+MAstaff.get(i).getLname());
                loggedUser.append(", ");
                loggedUser.append(MAstaff.get(i).getFname()+"</b>");
                if(MAstaff.get(i).getPosition().equalsIgnoreCase("gp"))
                    loggedUser.append("  [logged as Dr.]");
                else if (MAstaff.get(i).getPosition().equalsIgnoreCase("ns"))
                    loggedUser.append("  [logged as R.N.]");
                else if (MAstaff.get(i).getPosition().equalsIgnoreCase("ma"))
                    loggedUser.append("  [logged as Med.As.]");
                else if (MAstaff.get(i).getPosition().equalsIgnoreCase("mo"))
                    loggedUser.append("  [logged as Med.Of.]");

            }
        }
        tpLoggedStaff.setContentType("text/html");
        tpLoggedStaff.setText(loggedUser.toString());
        this.function.setOpacity(tpLoggedStaff);
        tpLoggedStaff.setEditable(false);

        this.labels = labs;

        // patient list for auto filling
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(8);
        // test list for auto filling
        testList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testList.setSelectedIndex(0);
        testList.setVisibleRowCount(8);
        // hospital list for auto filling
        hospitalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hospitalList.setSelectedIndex(0);
        hospitalList.setVisibleRowCount(8);

        patientTF = new JTextField[labels.length];
        patientSP = new JScrollPane[patientTF.length];

        //buttons' text
        this.btnEdit.setText("Edit");
        this.btnNew.setText("New Profile");
        this.btnAppoint.setText("Make an Appointment");

        //Loop to insert labels into pane
        for (int i = 0; i < labels.length; i++) {
            patientTF[i] = new JTextField(15);
            this.patientSP[i] = new JScrollPane(patientTF[i]);
            this.function.makeElementWithBorder(patientSP[i], labels[i], Color.LIGHT_GRAY.darker(), false);
            patientTF[i].setBorder(null);
            this.function.setOpacity(patientSP[i]);
            patientSP[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            patientSP[i].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            patientTF[i].setOpaque(false);
            patientTF[i].setEditable(false);
        }

        // set title for TF fields
        function.makeElementWithBorder(findPatientTF, "Enter Patient's Name:", Color.DARK_GRAY, false);//
        findPatientTF.setOpaque(false);
        function.makeElementWithBorder(findDoctorTF, "Enter Doctor's Name:", Color.DARK_GRAY, false);//
        findDoctorTF.setOpaque(false);
        function.makeElementWithBorder(findNurseTF, "Enter Nurse's Name:", Color.DARK_GRAY, false);//
        findNurseTF.setOpaque(false);

        // set opacity for left panel
        this.function.setOpacity(tpTestDisc);
        this.function.setOpacity(TestDiscSP);
        // set opacity for middle panel
        this.function.setOpacity(tpPresciption);
        this.function.setOpacity(presciptionSP);

        //table
        this.function.setOpacity(calendarSP);
        this.table.setRowHeight(20);
        this.table.setEnabled(false);

         //set titles
        this.function.makeElementWithBorder(patientList, "Patients", null, false);
        this.function.makeElementWithBorder(testList, "Tests' List", null, false);
        this.function.makeElementWithBorder(hospitalList, "Hospitals", null, false);
        this.function.makeElementWithBorder(TestDiscSP, "Test's Discription", null, false);
        this.function.makeElementWithBorder(presciptionSP, "Prescription", null, false);
        this.function.makeElementWithBorder(calendarSP, "Schedule", null, false);

        //Timer implementation
        final JTextPane timeTP = new JTextPane();
        timeTP.setContentType("text/html");
        timeTP.setPreferredSize(new Dimension(200, 24));
        timeTP.setEditable(false);
        timeTP.setOpaque(false);
        timeTP.setBorder(null);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM d, yyyy");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");

        //scroll pane for patient list top left side
        JScrollPane dlistSP = new JScrollPane(patientList);
        this.function.setOpacity(dlistSP);
        //scroll pane for test list middle left side
        JScrollPane dlistSP2 = new JScrollPane(testList);
        this.function.setOpacity(dlistSP2);
        //scroll pane for hospital list middle left side
        JScrollPane dlistSP3 = new JScrollPane(hospitalList);
        this.function.setOpacity(dlistSP3);

        // building pane
        this.patientPane.add(timeTP, new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        this.patientPane.add(tpLoggedStaff, new GridBagConstraints(0, 1, 2, 1, 0, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        ActivePatientPane.add(findPatientTF, new GridBagConstraints(2, 0, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 0, 15, 15), 5, 5));
        this.patientPane.add(dlistSP, new GridBagConstraints(0, 2, 1, 4, 0.1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 5, 5));
        this.patientPane.add(dlistSP2, new GridBagConstraints(0, 6, 1, 4, 0.1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 5, 5));
        this.patientPane.add(TestDiscSP, new GridBagConstraints(0, 10, 2, 13, 0.7, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        this.patientPane.add(presciptionSP, new GridBagConstraints(1, 5, 4, 2, 0.2, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        this.patientPane.add(findDoctorTF, new GridBagConstraints(1, 7, 2, 1, 0.2, 0.1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        this.patientPane.add(findNurseTF, new GridBagConstraints(3, 7, 2, 1, 0.2, 0.1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        this.patientPane.add(dlistSP3, new GridBagConstraints(2, 8, 1, 4, 0.1, 0.2, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 5, 5));
        this.patientPane.add(calendarSP, new GridBagConstraints(3, 8, 2, 5, 0.1, 0.2, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        //buttons
        this.patientPane.add(this.btnEdit, new GridBagConstraints(2, 13, 1, 1, 0, 0, GridBagConstraints.LINE_END,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 40, 5));
        this.patientPane.add(this.btnNew, new GridBagConstraints(3, 13, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
        this.patientPane.add(this.btnAppoint, new GridBagConstraints(4, 13, 2, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));


        //for labels
        int pozY = 1, pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            this.patientPane.add(patientSP[i], new GridBagConstraints(++pozX, pozY, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 15, 15));
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }

        //time and date
        new javax.swing.Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Calendar date = Calendar.getInstance();
                timeTP.setText("<b>" + dateFormat.format(date.getTime()) +
                        "<br />" + timeFormat.format(date.getTime())+"</b>");
            }
        }).start();//end timer

        //filling the text box and lists
        findPatientTF.addKeyListener(this);
        findDoctorTF.addKeyListener(this);
        findNurseTF.addKeyListener(this);
        patientList.addListSelectionListener(this);
        testList.addListSelectionListener(this);
        hospitalList.addListSelectionListener(this);
        this.btnEdit.addActionListener(this);
        this.btnNew.addActionListener(this);
        this.btnAppoint.addActionListener(this);

    }//end active Patient

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
                 //fills the list according to the typed letters//patients//
            if (this.MAPanel.getSelectedIndex() == 0 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!patientTF[0].getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)))){

                function.patientList(actPatient, findPatientTF, listModel);

        }
    }
//
    public void actionPerformed(ActionEvent e){
//        if((JButton) e.getSource()==this.nextB){
//            this.PFPanel.setSelectedIndex(1);
//        }
    }
//
     public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
            if ((this.MAPanel.getSelectedIndex() == 0 || this.MAPanel.getSelectedIndex() == 1) &&
                    patientList.getSelectedIndex() >= 0 &&
                    e.getSource() == patientList) {
                String st = patientList.getSelectedValue().toString();
                function.patientInfoByItem(actPatient, st, patientTF, tpPresciption, null);
            }
        }

    }
}//end active patient
