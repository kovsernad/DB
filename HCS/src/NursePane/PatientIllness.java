package NursePane;

/**
 *
 * @author ContEd Student
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

public class PatientIllness {
    private Func function;
    private JTabbedPane PFPanel;

    public PatientIllness(JTabbedPane Panel)
    {
        this.PFPanel = Panel;
        this.init();
    }
    private void init(){
        this.function = new Func();

    }
    public void patientIllness(JPanel ptPane){
        //lables for check boxes
        String[] cbstr = {"Heart disease/Murmur/Angina",
            "High cholesterole", "High blood pressure",
            "Low blood pressure", "Heartburn(reflux)",
            "Anemia or blood problems", "Swollen ankles",
            "Diarrhea/Constipation", "Shortness of breathe", "Asthma",
            "Lung problems/cough", "Sinus problems",
            "Seasonal allergies", "Tonsillitis", "Ear problems",
            "AIDS/HIV disease", "Eye disorder/Glaucoma",
            "Seizures", "Stroke", "Headaches/Migraines",
            "Neurological problems", "Depression/Anxiety",
            "Psychiatric care", "Diabetes", "Kidney/Bladder problems",
            "Liver problems/Hepatitas", "Arthritis", "Cancer",
            "Ulcers/colitis", "Thyroid problems", "Epilepsy",
            "Mental illness", "Palpitations(rapid heart beats)",
            "Spell of unconsiousness", "Anemia (low blood count)",
            "Excessive bleeding or bruising", "Painful urination",
            "Chest pain", "Joint stiffness, pain or swelling",
            "Attempted suicide"};
        // creating check boxes with labels
        JCheckBox[] cb = new JCheckBox[44];
        for (int i = 0; i < cbstr.length; i++) {
            cb[i] = new JCheckBox(cbstr[i]);
        }
        //labels for text fields
        String[] immstr = {"Patient", "Patient's birth date",
            "In case of other deseasis specify, please.", "Tetanus:",
            "Pneumovax:", "Hepatitis B:", "Hepatitis A:", "Varicella:",
            "Zostavax:", "Gardisil:", "Other specify:"};
        //creating text fields with lables
        JTextField[] medilTF = new JTextField[immstr.length];
        for (int i = 0; i < immstr.length; i++) {
            medilTF[i] = new JTextField();
            TitledBorder tl = BorderFactory.createTitledBorder
                    (BorderFactory.createLineBorder(Color.DARK_GRAY), immstr[i]);
            tl.setTitleJustification(TitledBorder.LEFT);
            medilTF[i].setBorder(tl);
            medilTF[i].setOpaque(false);
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

        //building pane
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medilTF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(medilTF[1], new GridBagConstraints(3, 0, 1, 1, 0.5, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));

        int pozY = 1, pozX = -1;

        for (int i = 0; i < 40; i++) {
            ptPane.add(cb[i], new GridBagConstraints
                    (++pozX, pozY, 1, 1, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
            if ((i + 1) % 4 == 0) {
                pozY++;
                pozX = -1;
            }
        }

        ptPane.add(medilTF[2], new GridBagConstraints(0, 11, 4, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(new JLabel("Indicate the last time the following vaccines were performed (or 'never'):"),
                new GridBagConstraints(0, 13, 4, 1, 0.5, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        pozY = 14;
        pozX = -1;

        for (int i = 3; i < immstr.length; i++) {
            ptPane.add(medilTF[i], new GridBagConstraints
                    (++pozX, pozY, 1, 1, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 0, 0));

            if ((i + 2) % 4 == 0) {
                pozY++;
                pozX = -1;
            }
        }

        ptPane.add(new JButton("Submit"), new GridBagConstraints(3, 17, 1, 1, 0.5, 0.5,
                GridBagConstraints.LAST_LINE_END,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

    }

}
