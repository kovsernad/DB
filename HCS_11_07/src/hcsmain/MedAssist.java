/*
 Vanier College
 DataBase Design Project
 Health Care System
 Medical Assitant face
 */
package hcsmain;

import MedAssistPanes.ActivePatient;
//import MedAssistPanes.Appointment;
//import MedAssistPanes.NewPatient;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*Medical Assistant Console */
public class MedAssist {

    //Creating references of frame and tabbed panels objects
    private JFrame frame;
    private JTabbedPane MAPanel;

    private ActivePatient actPatient;
//    private Appointment appoint;
//    private NewPatient nPatient;


    public MedAssist(){
        this.init();
    }

    private void init(){

        //Creating objects of frame and tabbed panels objects
        this.frame = new JFrame();
        this.MAPanel = new JTabbedPane();
        this.actPatient=new ActivePatient (this.MAPanel);
//        this.appoint=new Appointment (this.MAPanel);
//        this.nPatient=new NewPatient (this.MAPanel);



        //Creating of pane objects
        JPanel ActivePatientPane = new JPanel();
//        JPanel AppointmentPane = new JPanel();
//        JPanel NewPatientPane = new JPanel();

        //Setting names of panes
        this.MAPanel.add("Active Patient", ActivePatientPane);
//        this.MAPanel.add("Appiontments", AppointmentPane);
//        this.MAPanel.add("New Patient", NewPatientPane);

        //set layouts
        ActivePatientPane.setLayout(new GridBagLayout());
//        AppointmentPane.setLayout(new GridBagLayout());
//        NewPatientPane.setLayout(new GridBagLayout());

        String[] labels = {"First Name","Last Name", "Date of birth","e-mail", "address","ZIP","phone","Insurrance",
                           "Medical Card","Social Insurance Number","Last Test","Anamnesis","Diagnose","Prescription"};

        //MA Frame components
        this.actPatient.ActivePatient(labels, ActivePatientPane);        

        //Panel properties
        this.frame.setContentPane(this.MAPanel);
        this.frame.setTitle("HEALTH CARE SYSTEM. Medical Assistent");
        this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 200,
                Toolkit.getDefaultToolkit().getScreenSize().height - 100);
        this.frame.setMinimumSize(new Dimension(900, 600));
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }//End init

    private int checkTab() {
        //listener to trace the number of active pane
        final JTextField tmp = new JTextField();
        tmp.setText(String.valueOf(0));
        this.MAPanel.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                tmp.setText(String.valueOf(MAPanel.getSelectedIndex()));
            }
        });
        return Integer.parseInt(tmp.getText());
    }
}
