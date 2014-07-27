package NursePane;

/**
 *
 * @author Nadine
 */
import hcsmain.*;
import java.awt.*;
import hcssupport.*;
import java.awt.event.*;
import javax.swing.*;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.event.*;

public class PatientHistory implements KeyListener, ListSelectionListener, ActionListener{
    private Vector<PatientInfo> patient;
    private Vector<StaffInfo> staff;
    private DefaultListModel listMode;
    private JList patientList;
    private JScrollPane ptlistSP;
    private Func function;
    private JTabbedPane PFPanel;
    private JButton nextB;
    private JTextArea[] historyTA;
    private String[] txtlable = {"The chronic diseases, if any:",
            "The Allergies, if any:", "The Drug reactions, if any:",
            "Describe any special features:"};
    
    private JTextField[] medhTF;

         //Labels for text field
    private  String[] labels1 = {"Patient", "Patient's birth date", "Previous Doctor:",
            "Previous Medical Institution:", "Date of the Last Exam:",
            "The Reason of the Last Exam:","The Hepatitis virus, if any.",
            "The heart disease if any.", "The Tabacoo History, if any.",
            "The Alcohol History, if any."};
    private String gmedhistiry = "|";
    public static int pthid;

    public PatientHistory(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    private void init(){
        this.function = new Func();
        this.nextB = new JButton();
        listMode = new DefaultListModel();
        this.patientList = new JList(this.listMode);
        this.patient = new Vector();
        this.function.fillSPatient(patient);
        this.staff = new Vector();
        this.function.fillStaff(staff);
    }
    public void patientHistory(JPanel ptPane, int loggedId){
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
        patientList.setVisibleRowCount(8);
        ptlistSP = new JScrollPane(this.patientList);
        function.setOpacity(ptlistSP);
        ptlistSP.setPreferredSize(new Dimension(220, 150));
        patientList.setPreferredSize(new Dimension(220, 150));
        function.makeElementWithBorder(patientList, "Patients", Color.LIGHT_GRAY, false);
        patientList.setOpaque(false);
        
        historyTA = new JTextArea[txtlable.length];
        JScrollPane[] taJS = new JScrollPane[txtlable.length];

        for(int i =0; i< txtlable.length; i++)
        {
            historyTA[i] = new JTextArea();
            historyTA[i].setLineWrap(true);
            taJS[i] = new JScrollPane(historyTA[i]);
            taJS[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            taJS[i].setPreferredSize(new Dimension(220, 150));
            function.makeElementWithBorder(taJS[i], txtlable[i], Color.DARK_GRAY, false);
            taJS[i].setVisible(true);

        }

        //text fields
        medhTF = new JTextField[labels1.length];

        //creating text fields with lables
        for (int i = 0; i < labels1.length; i++) {
            medhTF[i] = new JTextField();
            function.makeElementWithBorder(medhTF[i], labels1[i], Color.DARK_GRAY, false);
            medhTF[i].setOpaque(false);
            medhTF[i].setPreferredSize(new Dimension(2, 45));
            medhTF[i].setEditable(true);
        }

        //timer
        final JTextPane timeTP = new JTextPane();
        timeTP.setContentType("text/html");
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

        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, 
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(tpLoggedStaff, new GridBagConstraints(0, 0, 1, 1, 0, 0, 
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(50, 15, 0, 15), 0, 0));
        ptPane.add(medhTF[0], new GridBagConstraints(1, 0, 3, 1, 1, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medhTF[1], new GridBagConstraints(4, 0, 1, 1, 0.1, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

        int pozY = 1, pozX = 0;
        for (int i = 2; i < labels1.length; i++) {
            ptPane.add(medhTF[i], new GridBagConstraints
                    (pozX, pozY, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
            pozX += 2;

            if ((i + 1) % 2 == 0) {
                pozY++;
                pozX = 0;
            }
        }
         
        ptPane.add(patientList, new GridBagConstraints(4, 1, 1, 8, 0.1, 0.2,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 45, 15), 0, 0));
        pozY = 6;  pozX = 0;

        for (int i = 0; i < txtlable.length; i++) {
            ptPane.add(taJS[i], new GridBagConstraints
                    (pozX, pozY, 2, 2, 1, 1, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.BOTH, new Insets(5, 15, 5, 15), 0, 0));
            pozX += 2;

            if ((i + 1) % 2 == 0) {
                pozY+=2;
                pozX = 0;
            }
        }
               
        this.nextB.setText("Next");
        this.nextB.addActionListener(this);

        ptPane.add(this.nextB, new GridBagConstraints(4, 9, 1, 1, 0, 0,
                GridBagConstraints.NORTHEAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        
        //filling the text box and list
        medhTF[0].addKeyListener(this);
        patientList.addListSelectionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if((JButton) e.getSource()==this.nextB){
            this.PFPanel.setSelectedIndex(3);
            for(int i = 2; i<labels1.length; i++)
            {
                this.gmedhistiry +=labels1[i]+"|"+medhTF[i].getText()+"|";

            }
            for(int j = 0; j<txtlable.length; j++){
                this.gmedhistiry+=txtlable[j]+"|"+historyTA[j].getText()+"|";               
            }
            DB.db.insertGmedHistory(gmedhistiry, pthid);

        }
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
                (!medhTF[0].getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)))){

                function.patientList(patient, medhTF[0], listMode);    
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        
       if (!e.getValueIsAdjusting()) {
            if ((this.PFPanel.getSelectedIndex() == 0 || this.PFPanel.getSelectedIndex() == 1
                    || this.PFPanel.getSelectedIndex() == 2)&&
                    patientList.getSelectedIndex() >= 0 &&
                    e.getSource() == patientList) {
                String st = patientList.getSelectedValue().toString();
                function.patientInfo(patient, st, medhTF, null, null);

              pthid = function.patientInfo(patient, st, medhTF, null, null);
              PatientIllness.ptiid = PatientHistory.pthid;
            }
        }     
    }    
}
