/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hcsmain;

import PatientPanes.*;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author ContEd Student
 */
public class StartPatient {

    private JFrame frame;
    private JTabbedPane PPanel;
    private int userId;
    private Patients patients;

    public StartPatient(int id) {
        this.userId = id;
        this.init();
    }

    private void init() {

        this.frame = new JFrame();
        this.PPanel = new JTabbedPane();
        this.patients = new Patients(this.PPanel);

        JPanel patientPane = new JPanel();

        this.PPanel.add("Patient's records", patientPane);

        // set layouts
        patientPane.setLayout(new GridBagLayout());

        String[] labels = {"First Name", "Last Name", "Date of birth",
            "Social Security Number", "e-mail", "Address", "ZIP",
            "Phone", "Medical Card", "Insurrance",
            "Last Prescription", "Last Anamnesis", "Last Diagnosis",};

        /*********************** Staff's record ****************************/
        this.patients.patient(labels, patientPane, userId);

        /******************* End of staff's record ************************/
        this.frame.setContentPane(this.PPanel);
        this.frame.setTitle("HEALTH CARE SYSTEM. Patient");
        this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 200,
                Toolkit.getDefaultToolkit().getScreenSize().height - 100);
        this.frame.setMinimumSize(new Dimension(900, 600));
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
