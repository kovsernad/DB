package NursePane;

/**
 *
 * @author ContEd Student
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientHistoryC {
    private Func function;
    private JTabbedPane PFPanel;
    private JButton nextB;

    public PatientHistoryC(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    private void init(){
        this.function = new Func();
        this.nextB = new JButton();
    }
    public void patientHistoryC(JPanel ptPane){
        //labels for text fields
        String[] labels = {"Patient", "Patient's birth date",
            "If yes, specify the reason and date.",
            "If yes, specify the type of hipatitis and date.",
            "If yes, specify the type and date.", "If yes, specify the date.",
            "If yes, specify the reason and the date.",
             "Tetanus:",
            "Pneumovax:", "Hepatitis B:", "Hepatitis A:", "Varicella:",
            "Zostavax:", "Gardisil:", "Other specify:"};

        //creating text fields with lables
        JTextField[] medTF = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            medTF[i] = new JTextField();
            TitledBorder tl = BorderFactory.createTitledBorder
                    (BorderFactory.createLineBorder(Color.DARK_GRAY), labels[i]);
            tl.setTitleJustification(TitledBorder.LEFT);
            medTF[i].setBorder(tl);
            medTF[i].setOpaque(false);
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
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medTF[0], new GridBagConstraints(1, 0, 2, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medTF[1], new GridBagConstraints(3, 0, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("Have you ever been hospitalized?"),
                new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[0], new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[1], new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("Have you ever been tested for Hepatitis?"),
                new GridBagConstraints(2, 1, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[2], new GridBagConstraints(3, 1, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[3], new GridBagConstraints(3, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(medTF[3], new GridBagConstraints(0, 2, 2, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medTF[4], new GridBagConstraints(2, 2, 2, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("Have you had a sexually transmitted disease?"),
                new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[4], new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[5], new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(new JLabel("Have you ever been tested for HIV disease?"),
                new GridBagConstraints(2, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[6], new GridBagConstraints(3, 3, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[7], new GridBagConstraints(3, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(medTF[5], new GridBagConstraints(0, 4, 2, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medTF[6], new GridBagConstraints(2, 4, 2, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("Have you had a Tuberculsis (TB) Screening?"),
                new GridBagConstraints(0, 5, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[8], new GridBagConstraints(1, 5, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[9], new GridBagConstraints(1, 5, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("If yes, what were the results?"),
                new GridBagConstraints(2, 5, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[10], new GridBagConstraints(3, 5, 1, 1, 0, 0,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[11], new GridBagConstraints(3, 5, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("Have you had a TB screen or an x-ray?"),
                new GridBagConstraints(0, 6, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[12], new GridBagConstraints(1, 6, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[13], new GridBagConstraints(1, 6, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("If yes, what were the results?"),
                new GridBagConstraints(2, 6, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[14], new GridBagConstraints(3, 6, 1, 1, 0, 0,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[15], new GridBagConstraints(3, 6, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(new JLabel("Could you provide the copies of tests?"),
                new GridBagConstraints(0, 7, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[16], new GridBagConstraints(1, 7, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[17], new GridBagConstraints(1, 7, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

       ptPane.add(new JLabel("Indicate the last time the following vaccines were performed (or 'never'):"),
                new GridBagConstraints(0, 8, 4, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        int pozY = 9, pozX = -1;

        for (int i = 7; i < labels.length; i++) {
            ptPane.add(medTF[i], new GridBagConstraints
                    (++pozX, pozY, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));

            if ((i + 2) % 4 == 0) {
                pozY++;
                pozX = -1;
            }
        }
       ptPane.add(new JButton("Submit"), new GridBagConstraints(3, 10, 1, 1, 0, 0,
                GridBagConstraints.LAST_LINE_END,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

    }
}
