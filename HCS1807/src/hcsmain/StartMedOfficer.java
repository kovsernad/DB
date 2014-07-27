package hcsmain;

import MedOfficerPanes.Staffs;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ContEd Student
 */
public class StartMedOfficer {

    private JFrame frame;
    private JTabbedPane MOPanel;
    private int userId;
    private Staffs staffs;

    public StartMedOfficer(int id){
        this.userId = id;
        this.init();
    }

    private void init() {

        this.frame = new JFrame();
        this.MOPanel = new JTabbedPane();
        this.staffs = new Staffs(this.MOPanel);
    
        JPanel staffPane = new JPanel();

        this.MOPanel.add("Staff's records", staffPane);

        // set layouts
        staffPane.setLayout(new GridBagLayout());

        String[] labels = {"First Name", "Last Name", "Date of birth", 
            "Social Security Number", "e-mail", "Address", "ZIP",
             "Qualification", "Position", "Phone",};

        /*********************** Staff's record ****************************/
        this.staffs.staff(labels, staffPane, userId);

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


