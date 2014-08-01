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
    private int userId;


    public MedAssist(int id){
        this.userId = id;
        this.init();
    }

    private void init(){

        //Creating objects of frame and tabbed panels objects
        this.frame = new JFrame();
        this.MAPanel = new JTabbedPane();
        this.actPatient=new ActivePatient (this.MAPanel);

        //Creating of pane objects
        JPanel ActivePatientPane = new JPanel();

        //Setting names of panes
        this.MAPanel.add("Active Patient", ActivePatientPane);

        //set layouts
        ActivePatientPane.setLayout(new GridBagLayout());

        String[] labels = {"First Name","Last Name", "Date of birth",
            "SSN","e-mail", "Address","ZIP",
            "Phone","Insurrance", "Medical Card"};

/*********************************MA Frame components*****************************/

        this.actPatient.ActivePatient(labels, ActivePatientPane,userId);

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
