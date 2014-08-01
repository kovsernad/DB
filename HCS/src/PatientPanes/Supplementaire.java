package PatientPanes;

import hcsmain.PatientInfo;
import hcssupport.Func;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author ContEd Student
 */
public class Supplementaire implements ActionListener {

    private JTabbedPane PPanel;
    private PatientInfo patient;
    private Func function;
    private JPanel patientPane;
    private JTextField[] tfPatient;
    private JScrollPane[] spPatient;
    private JTextPane tpDoctorInHosp;
    private JScrollPane spDoctorsInHosp;
    private JTextPane tpEstimation;
    private JScrollPane spEstimation;
    private JTextPane tpPrescriptions;
    private JScrollPane spPrescription;
    private JTable table;
    private JScrollPane spSchedual;

    public Supplementaire(JTabbedPane Panel) {

        this.PPanel = Panel;
        this.init();

    }

    private void init() {
        Object[] tabHead = {"Day of Week", "Date", "Time", "Doctor/Nurse"};
        Object[][] tabRows = {
            {"Sunday", "", "", ""},
            {"Monday", "", "", ""},
            {"Tuesday", "", "", ""},
            {"Wednesday", "", "", ""},
            {"Thursday", "", "", ""},
            {"Friday", "", "", ""},
            {"Saturday", "", "", ""}
        };

        this.patient = new PatientInfo();
        this.function = new Func();
        this.tpDoctorInHosp = new JTextPane();
        this.tpEstimation = new JTextPane();
        this.tpPrescriptions = new JTextPane();
        this.spDoctorsInHosp = new JScrollPane(this.tpDoctorInHosp);
        this.spEstimation = new JScrollPane(this.tpEstimation);
        this.spPrescription = new JScrollPane(this.tpPrescriptions);
        table = new JTable(tabRows, tabHead);
        table.setEnabled(false);
        this.spSchedual = new JScrollPane(this.table);
    }

    public void supplementaire(String[] lab, JPanel patientPane, int loggedId){

        this.function.fillPatient(patient, loggedId);
        this.patientPane = patientPane;
        //welcome
        JTextPane tpLoggedUser = new JTextPane();

        StringBuffer loggedUser = new StringBuffer();
        loggedUser.append("<b>" + patient.getLname());
        loggedUser.append(", ");
        loggedUser.append(patient.getFname() + "</b>");

        tpLoggedUser.setContentType("text/html");
        tpLoggedUser.setText(loggedUser.toString());
        this.function.setOpacity(tpLoggedUser);
        tpLoggedUser.setEditable(false);

        final JTextPane timeTP = new JTextPane();
        timeTP.setContentType("text/html");
        timeTP.setEditable(false);
        timeTP.setOpaque(false);
        timeTP.setBorder(null);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM d, yyyy");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
        // end welcome


        this.function.setOpacity(this.spDoctorsInHosp);
        this.function.setOpacity(this.tpDoctorInHosp);

        this.function.setOpacity(this.spEstimation);
        this.function.setOpacity(this.tpEstimation);

        this.function.makeElementWithBorder(this.spDoctorsInHosp, "Information about next appointments", null, false);
        this.function.makeElementWithBorder(this.spEstimation, "Cost estimation for last prescription", null, false);
        
        this.function.checkPatientSchedual(patient, this.tpDoctorInHosp);
        this.function.drugEstimationForPatient(loggedId, this.tpEstimation);
        


        


        this.patientPane.add(timeTP, new GridBagConstraints(0, 0, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 0), 5, 5));
        this.patientPane.add(tpLoggedUser, new GridBagConstraints(0, 0, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(70, 15, 0, 0), 0, 0));

        this.patientPane.add(this.spDoctorsInHosp, new GridBagConstraints(0, 2, 1, 2, 1, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 15, 15, 0), 0, 0));
        this.patientPane.add(this.spEstimation, new GridBagConstraints(2, 2, 1, 2, 1, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 15, 15, 0), 0, 0));



        //time and date
        new javax.swing.Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Calendar date = Calendar.getInstance();
                timeTP.setText("<b>" + dateFormat.format(date.getTime()) +
                        "<br />" + timeFormat.format(date.getTime()) + "</b>");
            }
        }).start();

    }





    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
