package hcsmain;

import MedOfficerPanes.Doctors;
import MedOfficerPanes.MedAssistant;
import MedOfficerPanes.Nurses;
import hcssupport.Func;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ContEd Student
 */
public class MedOfficer {

    private JFrame frame;
    private JTabbedPane MOPanel;
    
    private Doctors doctors;
    private Nurses nurses;
    private MedAssistant medAss;

    protected void init() {

        this.frame = new JFrame();
        this.MOPanel = new JTabbedPane();
        this.doctors = new Doctors(this.MOPanel);
        this.nurses = new Nurses(this.MOPanel);
        this.medAss = new MedAssistant(this.MOPanel);
    
        JPanel doctorsPane = new JPanel();
        JPanel nursesPane = new JPanel();
        JPanel medAssistPane = new JPanel();

        this.MOPanel.add("Doctor's records", doctorsPane);
        this.MOPanel.add("Nurse's records", nursesPane);
        this.MOPanel.add("Medical Assistens's records", medAssistPane);

        // set layouts
        doctorsPane.setLayout(new GridBagLayout());
        nursesPane.setLayout(new GridBagLayout());
        medAssistPane.setLayout(new GridBagLayout());

        String[] labels = {"First Name", "Last Name", "Date of birth", "e-mail",
            "Address", "ZIP", "Phone", "Social Security Number", "Position",
            "Qualification",};

        /*********************** Staff's record ****************************/
        this.doctors.doctor(labels, doctorsPane);
        this.nurses.nurse(labels, nursesPane);
        this.medAss.assistant(labels, medAssistPane);

        /******************* End of staff's record ************************/
        this.frame.setContentPane(this.MOPanel);
        this.frame.setTitle("HEALTH CARE SYSTEM. Medical Officer");
        this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 200,
                Toolkit.getDefaultToolkit().getScreenSize().height - 100);
        this.frame.setMinimumSize(new Dimension(900, 600));
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    

    private int checkTab() {
        //listener to trace the number of active pane
        final JTextField tmp = new JTextField();
        tmp.setText(String.valueOf(0));
        this.MOPanel.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                tmp.setText(String.valueOf(MOPanel.getSelectedIndex()));
            }
        });
        return Integer.parseInt(tmp.getText());
    }

   
}


