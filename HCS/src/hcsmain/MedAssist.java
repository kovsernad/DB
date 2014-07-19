/*
 Vanier College
 DataBase Design Project
 Health Care System
 Medical Assitant face
 */
package hcsmain;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


/*Medical Assistant Console */
public class MedAssist {

    //Creating references of frame and tabbed panels objects
    private JFrame frame;
    private JTabbedPane MAPanel;


    protected void init(){

        //Creating objects of frame and tabbed panels objects
        this.frame = new JFrame();
        this.MAPanel = new JTabbedPane();

        //Creating of pane objects
        JPanel medAssistPane = new JPanel();
        JPanel patientGFormPane = new JPanel();

        //Setting names of panes
        this.MAPanel.add("Medical Assistens's records", medAssistPane);
        this.MAPanel.add("Patient General Form", patientGFormPane);



/*********************************MA Frame components*****************************/

        //Labels
        //block one
        JLabel patientNameL=new JLabel("Enter the name of the Patient:");
        JLabel patientBirthL=new JLabel("Enter the Date of Birth of the Patient:");
        //block two


        //Text Areas
        //block one
        JTextArea patientNameTA = new JTextArea(2,20);
        patientNameTA.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea patientBirthTA = new JTextArea(2,20);
        patientBirthTA.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea appointTA = new JTextArea(5,20);
        appointTA.setLineWrap(true);
        appointTA.setOpaque(true);
        appointTA.setEditable(true);
        //Scroll Pane
        JScrollPane appointSP = new JScrollPane(appointTA);
        appointSP.setBorder(BorderFactory.createLineBorder(Color.black));
        appointSP.setOpaque(true);
        appointSP.setEnabled(true);
        appointSP.setSize(350, 450);
        appointSP.getViewport().setOpaque(false);
        appointSP.getHorizontalScrollBar().setOpaque(false);
        appointSP.getVerticalScrollBar().setOpaque(false);
        //block two
//        JTextArea patientGTA = new JTextArea(15,20);

        //Border of Pane
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.
                             createEtchedBorder(EtchedBorder.LOWERED),"Appointments: ");
        appointSP.setBorder(title);


        //Placing into panes
        //into MA Pane
        //block one
        medAssistPane.setLayout(new GridBagLayout());
        medAssistPane.add(patientNameL,new GridBagConstraints(0,0,1,1,0.5,0,GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0) );
        medAssistPane.add(patientBirthL,new GridBagConstraints(1,0,1,1,0.5,0,GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0) );
        medAssistPane.add(patientNameTA,new GridBagConstraints(0,1,1,1,0.5,0,GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0) );
        medAssistPane.add(patientBirthTA,new GridBagConstraints(1,1,1,1,0.5,0,GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15,15,15,15), 0, 0) );
        medAssistPane.add(appointSP,new GridBagConstraints(0,2,2,3,1,1,GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15,15,15,15), 0, 0) );
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


}
