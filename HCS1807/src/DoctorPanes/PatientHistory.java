package DoctorPanes;

/**
 *
 * @author Nadin
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientHistory {
    private Func function;
    private JTabbedPane PFPanel;
    private JTextPane[] patientTP;
    private JTextField[] patientTF;
    private JButton backB;
    
    public PatientHistory(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    
    private void init(){
        
    }
    
}
