package NursePane;

/**
 *
 * @author Nadine
 */
import hcsmain.PatientInfo;
import java.awt.*;
import hcssupport.*;
//import java.sql.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import hcssupport.Func;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PatientHistory implements KeyListener, ListSelectionListener, ActionListener{
    private Vector<PatientInfo> patient;
    private DefaultListModel listMode;
    private JList patientList;
    private Func function;
    private JTabbedPane PFPanel;
    private JButton nextB;
    private JTextArea[] historyTA;
    private String[] txtlable = {"The chronic diseases, if any:",
            "The Allergies, if any:", "The Drug reactions, if any:",
            "Describe any special features:"};
    
    private JTextField[] medhTF;

         //        Labels for text field
    private  String[] labels1 = {"Patient", "Patient's birth date", "Previous Doctor:",
            "Previous Medical Institution:", "Date of the Last Exam:",
            "The Reason of the Last Exam:","The Hepatitis virus, if any.",
            "The heart disease if any.", "The Tabacoo History, if any.",
            "The Alcohol History, if any."};
    private String gmedhistiry = "|";
    private int ptid;

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
    }
    public void patientHistory(JPanel ptPane){
        
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(8);
        patientList.setPreferredSize(new Dimension(220, 150));
        
        TitledBorder title = BorderFactory.createTitledBorder
		(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patients");
        title.setTitleJustification(TitledBorder.LEFT);
        patientList.setBorder(title);
        patientList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) patientList.getCellRenderer()).setOpaque(false);
        
        historyTA = new JTextArea[txtlable.length];
        JScrollPane[] taJS = new JScrollPane[txtlable.length];


        for(int i =0; i< txtlable.length; i++)
        {
            historyTA[i] = new JTextArea();
            historyTA[i].setLineWrap(true);
            taJS[i] = new JScrollPane(historyTA[i]);
            historyTA[i].setEditable(true);
            TitledBorder titlePresc = BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.DARK_GRAY), txtlable[i]);
            titlePresc.setTitleJustification(TitledBorder.LEFT);
            taJS[i].setBorder(titlePresc);
            taJS[i].setVisible(true);
            taJS[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }



        //text fields
        medhTF = new JTextField[labels1.length];

        //creating text fields with lables
        for (int i = 0; i < labels1.length; i++) {
            medhTF[i] = new JTextField();
            TitledBorder ttl = BorderFactory.createTitledBorder
                    (BorderFactory.createLineBorder(Color.DARK_GRAY), labels1[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            medhTF[i].setBorder(ttl);
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
        ///
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 0, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medhTF[0], new GridBagConstraints(1, 0, 3, 1, 1, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medhTF[1], new GridBagConstraints(4, 0, 1, 1, 0.1, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));



        int pozY = 1, pozX = 0;
        for (int i = 2; i < labels1.length; i++) {
            ptPane.add(medhTF[i], new GridBagConstraints
                    (pozX, pozY, 2, 1, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
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
                this.gmedhistiry +="|"+labels1[i]+"|"+medhTF[i].getText()+"|";

            }
            for(int j = 0; j<txtlable.length; j++){
                this.gmedhistiry+=txtlable[j]+"|"+historyTA[j].getText()+"|";               
            }
            DB.db.insertGmedHistory(gmedhistiry, ptid);
//            
//             System.out.println("gmedhistory 2 "+this.gmedhistiry);
//             System.out.println("id "+this.id);
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

              this.ptid = function.patientInfo(patient, st, medhTF, null, null);
            }
        }
        
    }
    
}
