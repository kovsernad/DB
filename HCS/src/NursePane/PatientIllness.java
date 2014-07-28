package NursePane;

/**
 *
 * @author Nadine
 */
import hcsmain.*;
import hcssupport.DB;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.event.*;

public class PatientIllness implements ActionListener, ItemListener, ChangeListener{
    private Func function;
    private Vector<StaffInfo> staff;
    private JTabbedPane PFPanel;
    private JButton nextB;
    //lables for check boxes
    private String[] cbstr = {"Heart disease/Murmur/Angina",
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
    private     JCheckBox[] cb ;
    private JTextField[] medilTF;
    public static int ptiid;
    private PatientInfo temppatient;
    private String str = "|";

    public PatientIllness(JTabbedPane Panel)
    {
        this.PFPanel = Panel;
        this.init();
    }
    private void init(){
        this.function = new Func();
        this.staff = new Vector();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                     function.fillStaff(staff);
                }
        });

        this.nextB = new JButton();
//        this.temppatient = new PatientInfo();

    }
    public void patientIllness(JPanel ptPane, int loggedId){
        // for welcome label
        StringBuffer loggedUser = new StringBuffer();
        JTextPane tpLoggedStaff = new JTextPane();

        for(int i = 0; i < staff.size(); i++){
            if (staff.get(i).getId() == loggedId){
                loggedUser.append("<b>"+staff.get(i).getLname());
                loggedUser.append(", ");
                loggedUser.append(staff.get(i).getFname()+"</b>");
                if(staff.get(i).getPosition().equalsIgnoreCase("gp"))
                    loggedUser.append("  [logged as Dr.]");
                else if (staff.get(i).getPosition().equalsIgnoreCase("ns"))
                    loggedUser.append("  [logged as R.N.]");
                else if (staff.get(i).getPosition().equalsIgnoreCase("ma"))
                    loggedUser.append("  [logged as Med.As.]");
                else if (staff.get(i).getPosition().equalsIgnoreCase("mo"))
                    loggedUser.append("  [logged as Med.Of.]");

            }
        }
        tpLoggedStaff.setContentType("text/html");
        tpLoggedStaff.setText(loggedUser.toString());
        this.function.setOpacity(tpLoggedStaff);
        tpLoggedStaff.setEditable(false);

        // creating check boxes with labels
        cb = new JCheckBox[cbstr.length];
        for (int i = 0; i < cbstr.length; i++) {
            cb[i] = new JCheckBox(cbstr[i]);
            cb[i].addItemListener(this);
        }
        //labels for text fields
        String[] immstr = {"Patient", "Patient's birth date",
            "In case of other deseasis specify, please."};
        //creating text fields with lables
        medilTF = new JTextField[immstr.length];
        for (int i = 0; i < immstr.length; i++) {
            medilTF[i] = new JTextField();
            function.makeElementWithBorder(medilTF[i], immstr[i], Color.DARK_GRAY, false);
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
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(tpLoggedStaff, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(50, 15, 0, 15), 0, 0));
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
                    GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
            if ((i + 1) % 4 == 0) {
                pozY++;
                pozX = -1;
            }
        }

        ptPane.add(medilTF[2], new GridBagConstraints(0, 11, 4, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(5, 15, 15, 15), 0, 0));

        this.nextB.setText("Next");
        this.nextB.addActionListener(this);
        this.PFPanel.addChangeListener(this);

        ptPane.add(this.nextB, new GridBagConstraints(3, 13, 1, 1, 0, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
    }

        public void actionPerformed(ActionEvent e){
        if((JButton) e.getSource()==this.nextB){
            if(ptiid == 0){
                 JOptionPane.showMessageDialog(null, "You have to chose a patient first!");
            }
            else{
            this.PFPanel.setSelectedIndex(4);
            StringBuffer info = new StringBuffer();
            info.append(str);
            info.append(medilTF[2].getText()+"|");

            DB.db.insertIllHistory(info.toString(), ptiid);

//            System.out.println(info.toString());
            }
        }
    }
        public void itemStateChanged(ItemEvent e){
            StringBuffer tempinfo = new StringBuffer();
            Vector<StringBuffer> temp = new Vector();

            Object sourse = e.getItemSelectable();

            for(int i = 0; i<cbstr.length; i++ ){
                if(sourse.equals(cb[i])){

                tempinfo.append(cb[i].getText()+"|");
                temp.add(tempinfo);
                }
            }
            for(int i = 0; i < temp.size(); i++){
                str += temp.get(i).toString();
            }
        }
    public void stateChanged(ChangeEvent e) {
        ptiid = PatientHistory.pthid;
        temppatient = new PatientInfo();
        function.fillPatient(temppatient, ptiid);
        medilTF[0].setText(this.temppatient.getFname()+" "+this.temppatient.getLname());
        medilTF[1].setText(""+temppatient.getBdate());

    }

}
