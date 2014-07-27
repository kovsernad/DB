package PatientPanes;

import hcsmain.PatientInfo;
import hcssupport.Func;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Leo Dubovyi
 * Vanier College
 *
 * Lab #
 * @Project  @Class Patients
 */
public class Patients implements ActionListener {

    private JTabbedPane PPanel;
    private PatientInfo patient;
    private Func function;
    private JPanel patientPane;
    private JTextField[] tfPatient;
    private JScrollPane[] spPatient;
    private JTextPane tpAnamnesis;
    private JScrollPane spAnamnesis;
    private JTextPane tpDiagnosis;
    private JScrollPane spDiagnosis;
    private JTextPane tpPrescriptions;
    private JScrollPane spPrescription;
    private JTable table;
    private JScrollPane spSchedual;

    public Patients(JTabbedPane Panel) {
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
        this.tpAnamnesis = new JTextPane();
        this.tpDiagnosis = new JTextPane();
        this.tpPrescriptions = new JTextPane();
        this.spAnamnesis = new JScrollPane(this.tpAnamnesis);
        this.spDiagnosis = new JScrollPane(this.tpDiagnosis);
        this.spPrescription = new JScrollPane(this.tpPrescriptions);
        table = new JTable(tabRows, tabHead);
        table.setEnabled(false);
        this.spSchedual = new JScrollPane(this.table);

    }

    public void patient(String[] lab, JPanel patientPane, int loggedId) {

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

        this.table.setRowHeight(20);
        tfPatient = new JTextField[lab.length - 3];
        this.spPatient = new JScrollPane[this.tfPatient.length];
        for (int i = 0; i < tfPatient.length; i++) {
            tfPatient[i] = new JTextField(15);
            this.spPatient[i] = new JScrollPane(tfPatient[i]);
            this.function.makeElementWithBorder(spPatient[i], lab[i], Color.LIGHT_GRAY.darker(), false);
            tfPatient[i].setBorder(null);
            this.function.setOpacity(spPatient[i]);
            spPatient[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            spPatient[i].setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            tfPatient[i].setOpaque(false);
            tfPatient[i].setEditable(false);
        }

        this.function.setOpacity(this.spAnamnesis);
        this.function.setOpacity(this.spDiagnosis);
        this.function.setOpacity(this.spPrescription);
        this.function.setOpacity(this.tpAnamnesis);
        this.function.setOpacity(this.tpDiagnosis);
        this.function.setOpacity(this.tpPrescriptions);
        this.function.setOpacity(this.spSchedual);

        this.function.makeElementWithBorder(spAnamnesis, lab[11], Color.LIGHT_GRAY.darker(), false);
        this.function.makeElementWithBorder(spPrescription, lab[10], Color.LIGHT_GRAY.darker(), false);
        this.function.makeElementWithBorder(spDiagnosis, lab[12], Color.LIGHT_GRAY.darker(), false);
        this.function.makeElementWithBorder(spSchedual, "Next Appointment", Color.LIGHT_GRAY.darker(), false);

        this.function.patientPersonalInfo(patient, tfPatient, null, null);
        this.tpAnamnesis.setText("<b><span style='font-size: 16pt'>" +
                patient.getAnam() + "</span></b><br /><br />");
        this.tpDiagnosis.setText("<b><span style='font-size: 16pt'>" +
                patient.getDiagn() + "</span></b><br /><br />");

        StringBuffer tmp = new StringBuffer();
        tmp.append("<b><span style='font-size: 16pt'>");
        tmp.append(patient.getPrescriptions());
        tmp.append("</span></b><br /><br />");
        for (int i = 0; i < patient.getDrugs().size(); i++) {
            tmp.append(patient.getDrugs().get(i));
            tmp.append("<br />");
        }

        this.tpPrescriptions.setText(tmp.toString());
        this.function.checkPatientSchedual(table, patient);


        //build pane
        this.patientPane.add(timeTP, new GridBagConstraints(0, 0, 2, 1, 0, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 0), 5, 5));
        this.patientPane.add(tpLoggedUser, new GridBagConstraints(0, 0, 2, 1, 0, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(70, 15, 0, 0), 0, 0));

        this.patientPane.add(spAnamnesis, new GridBagConstraints(2, 0, 2, 2, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        this.patientPane.add(spPrescription, new GridBagConstraints(2, 2, 3, 2, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 15, 0, 15), 0, 0));
        this.patientPane.add(spDiagnosis, new GridBagConstraints(4, 0, 1, 2, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        this.patientPane.add(spSchedual, new GridBagConstraints(2, 4, 4, 3, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 15, 50, 15), 0, 0));
        JPanel pPatientInfo = new JPanel();
        for (int i = 2; i < lab.length - 3; i++) {
            pPatientInfo.add(spPatient[i]);
        }

        this.patientPane.add(pPatientInfo, new GridBagConstraints(0, 0, 2, 7, 0.03, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(100, 15, 0, 0), 0, 0));
        this.patientPane.add(new JButton("Next Page"), new GridBagConstraints(4, 6, 1, 1, 0, 0, GridBagConstraints.LAST_LINE_END,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));


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
