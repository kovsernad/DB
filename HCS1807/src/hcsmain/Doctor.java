package hcsmain;

import DoctorPanes.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ContEd Student
 */
public class Doctor {
    //Creating references of frame and tabbed panels objects
    private JFrame frame;
    private JTabbedPane DPanel;
    private PatientDocFile ptfile;
    private PatientActiveFile pafile;
    private PatientHistory phistory;
    private int userId;
    
        public Doctor(int id){
            this.userId = id;
            this.init();
        }
    
    private void init(){
        //Creating objects of frame and tabbed panels objects
        this.frame = new JFrame();
        this.DPanel = new JTabbedPane();
        this.ptfile = new PatientDocFile(this.DPanel);
        this.pafile = new PatientActiveFile(this.DPanel);
        this.phistory = new PatientHistory(this.DPanel);
        
        //Creating pane objects
        JPanel pPane = new JPanel();
        JPanel paPane = new JPanel();
        JPanel hPane = new JPanel();
        
        //Setting names of panes
        this.DPanel.add("Patient File", pPane);
        this.DPanel.add("Patient Active File", paPane);
        this.DPanel.add("Patient medical History", hPane);
        
        pPane.setLayout(new GridBagLayout());
        paPane.setLayout(new GridBagLayout());
        hPane.setLayout(new GridBagLayout());
        
        this.ptfile.PatientDocFile(pPane, userId);
        this.pafile.PatientActiveFile(paPane, userId);
        this.phistory.PatientHistory(hPane, userId);
                
        this.frame.setContentPane(this.DPanel);
        this.frame.setTitle("HEALTH CARE SYSTEM. Doctor");
        this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 200,
                Toolkit.getDefaultToolkit().getScreenSize().height - 100);
        this.frame.setMinimumSize(new Dimension(900, 600));
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    
}
