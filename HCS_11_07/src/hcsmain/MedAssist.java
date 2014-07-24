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



/*********************************MA Frame components*****************************/

        this.actPatient.ActivePatient(labels, ActivePatientPane);

        //Labels
        //block one
//        JLabel patientNameL=new JLabel("Enter the name of the Patient:");
//        JLabel patientBirthL=new JLabel("Enter the Date of Birth of the Patient:");
//        //block two
//
//
//        //Text Areas
//        //block one
//        JTextArea patientNameTA = new JTextArea(2,20);
//        patientNameTA.setBorder(BorderFactory.createLineBorder(Color.black));
//        JTextArea patientBirthTA = new JTextArea(2,20);
//        patientBirthTA.setBorder(BorderFactory.createLineBorder(Color.black));
//        JTextArea appointTA = new JTextArea(5,20);
//        appointTA.setLineWrap(true);
//        appointTA.setOpaque(true);
//        appointTA.setEditable(true);
//        //Scroll Pane
//        JScrollPane appointSP = new JScrollPane(appointTA);
//        appointSP.setBorder(BorderFactory.createLineBorder(Color.black));
//        appointSP.setOpaque(true);
//        appointSP.setEnabled(true);
//        appointSP.setSize(350, 450);
//        appointSP.getViewport().setOpaque(false);
//        appointSP.getHorizontalScrollBar().setOpaque(false);
//        appointSP.getVerticalScrollBar().setOpaque(false);
//        //block two
////        JTextArea patientGTA = new JTextArea(15,20);
//
//        //Border of Pane
//        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.
//                             createEtchedBorder(EtchedBorder.LOWERED),"Appointments: ");
//        appointSP.setBorder(title);
//
//
//        //Placing into panes
//        //into MA Pane
//        //block one
//        ActivePatientPane.setLayout(new GridBagLayout());
//        ActivePatientPane.add(patientNameL,new GridBagConstraints(0,0,1,1,0.5,0,GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0) );
//        ActivePatientPane.add(patientBirthL,new GridBagConstraints(1,0,1,1,0.5,0,GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0) );
//        ActivePatientPane.add(patientNameTA,new GridBagConstraints(0,1,1,1,0.5,0,GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0) );
//        ActivePatientPane.add(patientBirthTA,new GridBagConstraints(1,1,1,1,0.5,0,GridBagConstraints.CENTER,
//                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0) );
//        ActivePatientPane.add(appointSP,new GridBagConstraints(0,2,2,3,1,1,GridBagConstraints.NORTHWEST,
//                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0) );
        //block two

//        into PatientGForm Pane
//        patientGFormPane.setLayout(new GridBagLayout());
//        patientGFormPane.add(patientGTA, new GridBagConstraints(0,0,5,5,1,1,GridBagConstraints.NORTHWEST,
//                GridBagConstraints.VERTICAL, new Insets(15,15,15,15), 0, 0));


/*********************************END of MA Frame components*****************************/

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
