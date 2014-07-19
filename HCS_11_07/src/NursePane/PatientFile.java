package NursePane;

/**
 *
 * @author Nadine
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientFile {
    private DefaultListModel listMode;
    private Func function;
    private JTextPane patientTP;
    private JList patientList;
    private JTabbedPane PFPanel;
    private JTextField[] patient2TF;

    
    public PatientFile(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    private void init(){
        listMode = new DefaultListModel();
        this.function = new Func();
        this.patientTP = new JTextPane();
        this.patientList = new JList(this.listMode);
    }
    public void patientFile(JPanel ptPane){
        JScrollPane patientSP = new JScrollPane(patientTP);

        patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientList.setSelectedIndex(0);
        patientList.setVisibleRowCount(8);

        // set opacity for panel
        patientTP = new JTextPane();
        patientSP = new JScrollPane(patientTP);
        this.function.setOpacity(this.patientTP);
        this.function.setOpacity(patientSP);
        TitledBorder titlecSP = BorderFactory.createTitledBorder
                (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patient's General Information");
        titlecSP.setTitleJustification(TitledBorder.LEFT);
        patientSP.setBorder(titlecSP);
        patientSP.setVisible(true);

        //set Demensions
        patientList.setPreferredSize(new Dimension(20, 125));

        // set title for list
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Patients");
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

        patient2TF = new JTextField[lb.length];
        for (int i = 0; i < lb.length; i++) {
            patient2TF[i] = new JTextField(15);
            TitledBorder tl = BorderFactory.createTitledBorder
                    (BorderFactory.createLineBorder(Color.DARK_GRAY), lb[i]);
            tl.setTitleJustification(TitledBorder.LEFT);
            patient2TF[i].setBorder(tl);
            patient2TF[i].setOpaque(false);
        }
        
      String[] lbta = {"Patient's Anamnesis", "Patient's Diagnosis", "Patient's Prescriptions"};

        JTextArea[] patientTA = new JTextArea[lbta.length];
        JScrollPane[] taJS = new JScrollPane[lbta.length];

        for (int i = 0; i < lbta.length; i++) {
            patientTA[i] = new JTextArea();
            patientTA[i].setLineWrap(true);
            taJS[i] = new JScrollPane(patientTA[i]);
            TitledBorder titlePresc = BorderFactory.createTitledBorder
                (BorderFactory.createLineBorder(Color.DARK_GRAY), lbta[i]);
            titlePresc.setTitleJustification(TitledBorder.LEFT);
            taJS[i].setBorder(titlePresc);
            taJS[i].setVisible(true);
        }
        patient2TF[1].setPreferredSize(new Dimension(200, 40));

        //building pane
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patient2TF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patient2TF[1], new GridBagConstraints(3, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(patientList, new GridBagConstraints(4, 1, 1, 8, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 45, 15), 0, 0));
        ptPane.add(patientSP, new GridBagConstraints(0, 2, 4, 2, 0.1, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        int pozY = 4;
        for (int i = 0; i < lbta.length; i++) {
            ptPane.add(taJS[i], new GridBagConstraints
                    (0, pozY, 4, 2, 0.5, 0.5, GridBagConstraints.CENTER,
                    GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
            pozY+=2;
        }
        ptPane.add(new JButton("Submit"), new GridBagConstraints(4, 8, 1, 1, 0, 0.5,
                GridBagConstraints.SOUTH,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        
    }
}
