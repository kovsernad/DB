package NursePane;

/**
 *
 * @author Nadine
 */
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientHistory implements ActionListener{
    private Func function;
    private JTabbedPane PFPanel;
    private JButton nextB;

    public PatientHistory(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    private void init(){
        this.function = new Func();
        this.nextB = new JButton();
    }
    public void patientHistory(JPanel ptPane){
     //        Labels for text field
        String[] labels1 = {"Patient", "Patient's birth date", "Previous Doctor",
            "Previous Medical Institution", "Date of the Last Exam",
            "The Reason of the Last Exam", "The chronic diseases, if any",
            "The Hepatitis virus, if any", "The Allergies, if any",
            "The Drug reactions, if any", "The Tabacoo History, if any",
            "The Alcohol History, if any", "If yes, specify the reason and date.",
            "If yes, specify the type of hipatitis and date.",
            "If yes, specify the type and date.", "If yes, specify the date.",
            "If yes, specify the reason and the date."};
        //text fields
        JTextField[] medhTF = new JTextField[labels1.length];

        //creating text fields with lables
        for (int i = 0; i < labels1.length; i++) {
            medhTF[i] = new JTextField();
            TitledBorder ttl = BorderFactory.createTitledBorder
                    (BorderFactory.createLineBorder(Color.DARK_GRAY), labels1[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            medhTF[i].setBorder(ttl);
            medhTF[i].setOpaque(false);
        }
        //labels for radio buttons
        String[] rbstr = {"Yes", "No", "Yes", "No", "Yes", "No", "Yes", "No",
            "Yes", "No", "Positive", "Negative", "Yes", "No", "Positive",
            "Negative", "Yes", "No"};
        //creating radio buttons
        JRadioButton[] rb = new JRadioButton[18];
        for (int i = 0; i < rbstr.length; i++) {
            rb[i] = new JRadioButton(rbstr[i]);
//                   rb[i].addItemListener(this);
            rb[i].setSelected(false);
        }
        //creating button groups
        ButtonGroup[] group = new ButtonGroup[9];
        for (int i = 0; i < 9; i++) {
            group[i] = new ButtonGroup();
        }

        //groups the rbuttons
        int j = 0;
        for (int i = 0; i < group.length; i++) {
            group[i].add(rb[j++]);
            group[i].add(rb[j++]);
        }
        //timer
        final JTextPane timeTP = new JTextPane();
        timeTP.setContentType("text/html");
        timeTP.setPreferredSize(new Dimension(20, 15));
        timeTP.setEditable(false);
        timeTP.setOpaque(false);
        timeTP.setBorder(null);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM d, yyyy | h:mm:ss a");

        new javax.swing.Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Calendar date = Calendar.getInstance();
                timeTP.setText("<b>" + dateFormat.format(date.getTime()) + "</b>");
            }
        }).start();
        ///
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medhTF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medhTF[1], new GridBagConstraints(3, 0, 1, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

        int pozY = 2, pozX = 0;
        for (int i = 2; i < labels1.length - 2; i++) {
            ptPane.add(medhTF[i], new GridBagConstraints
                    (pozX, pozY, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
            pozX += 2;
            if ((i + 1) % 2 == 0) {
                pozY++;
                pozX = 0;
            }
        }

        ptPane.add(new JLabel("Have you ever been hospitalized?"),
                new GridBagConstraints(0, 14, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[0], new GridBagConstraints(1, 14, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[1], new GridBagConstraints(1, 14, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("Have you ever been tested for Hepatitis?"),
                new GridBagConstraints(2, 14, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[2], new GridBagConstraints(3, 14, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[3], new GridBagConstraints(3, 14, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));

        ptPane.add(medhTF[12], new GridBagConstraints(0, 15, 2, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medhTF[13], new GridBagConstraints(2, 15, 2, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("Have you had a sexually transmitted disease?"),
                new GridBagConstraints(0, 16, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[4], new GridBagConstraints(1, 16, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[5], new GridBagConstraints(1, 16, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(new JLabel("Have you ever been tested for HIV disease?"),
                new GridBagConstraints(2, 16, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[6], new GridBagConstraints(3, 16, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[7], new GridBagConstraints(3, 16, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));

        ptPane.add(medhTF[14], new GridBagConstraints(0, 17, 2, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medhTF[15], new GridBagConstraints(2, 17, 2, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("Have you had a Tuberculsis (TB) Screening?"),
                new GridBagConstraints(0, 18, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[8], new GridBagConstraints(1, 18, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[9], new GridBagConstraints(1, 18, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("If yes, what were the results?"),
                new GridBagConstraints(2, 18, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[10], new GridBagConstraints(3, 18, 1, 1, 0.5, 0.5,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[11], new GridBagConstraints(3, 18, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("Have you had a TB screen or an x-ray?"),
                new GridBagConstraints(0, 19, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[12], new GridBagConstraints(1, 19, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[13], new GridBagConstraints(1, 19, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("If yes, what were the results?"),
                new GridBagConstraints(2, 19, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[14], new GridBagConstraints(3, 19, 1, 1, 0.5, 0.5,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[15], new GridBagConstraints(3, 19, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));

        ptPane.add(medhTF[16], new GridBagConstraints(0, 20, 2, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));


        ptPane.add(new JLabel("Could you provide the copies of tests?"),
                new GridBagConstraints(2, 20, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[16], new GridBagConstraints(3, 20, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
        ptPane.add(rb[17], new GridBagConstraints(3, 20, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));

        this.nextB.setText("Next");
        this.nextB.addActionListener(this);

        ptPane.add(this.nextB, new GridBagConstraints(3, 21, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, 15, 15, 15), 0, 0));
    }
    public void actionPerformed(ActionEvent e){
        if((JButton) e.getSource()==this.nextB){
            this.PFPanel.setSelectedIndex(3);
        }
    }
}
