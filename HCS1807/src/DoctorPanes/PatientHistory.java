package DoctorPanes;

/**
 *
 * @author Nadin
 */
import hcsmain.PatientInfo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.border.*;

public class PatientHistory {
    private Func function;
    private JTabbedPane PFPanel;
    private JTextPane[] patientTP;
    private JTextField[] patientTF;
    private JList patientList;
    private Vector<PatientInfo> patient;
    private DefaultListModel listMode;
    private JButton backB;
    
    public PatientHistory(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    
    private void init(){
        listMode = new DefaultListModel();
        this.function = new Func();
        this.patientTP = new JTextPane[3];
        this.patientList = new JList(this.listMode);
        this.patient = new Vector();
        this.backB = new JButton();
        this.function.fillSPatient(patient);
    }
    public void PatientHistory(JPanel hPanel){
       JScrollPane[] patientSP = new JScrollPane[patientTP.length];
        
        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(8); 
        
        String[] lbtp = {"Patient's General Medical History",
            "Patient's Illness History", "Patient's Medical History"};
        
        for(int i = 0; i < patientTP.length; i++){
                patientTP[i] = new JTextPane();
                patientSP[i] = new JScrollPane(patientTP[i]);
                this.function.setOpacity(this.patientTP[i]);
                this.function.setOpacity(patientSP[i]);
                TitledBorder titleSP = BorderFactory.createTitledBorder
                (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), lbtp[i]);
                titleSP.setTitleJustification(TitledBorder.LEFT);
                patientSP[i].setBorder(titleSP);
                patientSP[i].setVisible(true);
                patientSP[i].setPreferredSize(new Dimension(600, 100));
           }       
                   
        // set title and size for list 
        patientList.setPreferredSize(new Dimension(20, 125));
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
        //textfields
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
        hPanel.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        hPanel.add(patientTF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        hPanel.add(patientTF[1], new GridBagConstraints(3, 0, 2, 1, 0.2, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        hPanel.add(patientList, new GridBagConstraints(4, 1, 1, 8, 0.2, 0.2,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 45, 15), 0, 0));
        
        int pozX = 1;

        for (int i = 0; i < lbtp.length; i++) {
            hPanel.add(patientSP[i], new GridBagConstraints
                    (pozX, 1, 1, 4, 0.5, 0.5, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

            pozX++;
        }
    }
    
}
