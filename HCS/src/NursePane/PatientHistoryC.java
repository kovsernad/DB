package NursePane;

/**
 *
 * @author Nadine
 */
import hcsmain.*;
import hcssupport.DB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.event.*;

public class PatientHistoryC implements  ActionListener, ItemListener, ChangeListener {
    private Func function;
    private Vector<StaffInfo> staff;
    private JTabbedPane PFPanel;
    private JButton submitB;
     //labels for text fields
    private String[] labels = {"Patient", "Patient's birth date",
            "If yes, specify the reason and date.",
            "If yes, specify the type of hipatitis and date.",
            "If yes, specify the type and date.", "If yes, specify the date.",
             "Tetanus:",
            "Pneumovax:", "Hepatitis B:", "Hepatitis A:", "Varicella:",
            "Zostavax:", "Gardisil:", "Other specify:"};
    private JTextField[] medTF = new JTextField[labels.length];
    private ButtonGroup[] group = new ButtonGroup[9];

    private String[] labelsL = {"Have you ever been hospitalized?",
                                "Have you ever been tested for Hepatitis?",
                                "Have you had a sexually transmitted disease?",
                                "Have you ever been tested for HIV disease?",
                                "Have you had a Tuberculosis (TB) Screening?",
                                "If yes, what were the results?",
                                "Have you had a TB screen or an x-ray?",
                                "If yes, what were the results?",
                                "Could you provide the copies of tests?",
                                "Indicate the last time the following vaccines were performed (or 'never'):"};

    private JLabel[] medL = new JLabel[labelsL.length];

   //labels for radio buttons
   private String[] rbstr = {"Yes", "No", "Yes", "No", "Yes", "No", "Yes", "No",
            "Yes", "No", "Positive", "Negative", "Yes", "No", "Positive",
            "Negative", "Yes", "No"};
   private JRadioButton[] rb = new JRadioButton[18];
    public static int ptcid;
    private PatientInfo temppatient;
    private String[] str = new String[9];
//    private StringBuffer info = new StringBuffer();

    public PatientHistoryC(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    private void init(){
        this.function = new Func();
        this.submitB = new JButton();
        this.staff = new Vector();
        this.function.fillStaff(staff);

    }
    public void patientHistoryC(JPanel ptPane, int loggedId){
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

        //creating text fields with lables
        for (int i = 0; i < labels.length; i++) {
            medTF[i] = new JTextField(15);
            function.makeElementWithBorder(medTF[i], labels[i], Color.DARK_GRAY, false);
            medTF[i].setOpaque(false);
//            medTF[i].setPreferredSize(new Dimension(80, 40));
        }

        for(int i =0; i<labelsL.length; i++){
            medL[i] = new JLabel(labelsL[i]);
        }

        //creating radio buttons
        for (int i = 0; i < rbstr.length; i++) {
            rb[i] = new JRadioButton(rbstr[i]);
            rb[i].setActionCommand(Integer.toString(i));
            rb[i].addItemListener(this);
            rb[i].addActionListener(this);
            rb[i].setSelected(false);
        }

        //creating button groups
//        ButtonGroup[] group = new ButtonGroup[9];
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
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(tpLoggedStaff, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(50, 15, 0, 15), 0, 0));
        ptPane.add(medTF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medTF[1], new GridBagConstraints(3, 0, 1, 1, 0.5, 0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medL[0],new GridBagConstraints(0, 1, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[0], new GridBagConstraints(1, 1, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[1], new GridBagConstraints(1, 1, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medL[1], new GridBagConstraints(2, 1, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[2], new GridBagConstraints(3, 1, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[3], new GridBagConstraints(3, 1, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medTF[2], new GridBagConstraints(0, 2, 2, 1, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 5, 15), 0, 0));
        ptPane.add(medTF[3], new GridBagConstraints(2, 2, 2, 1, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 5, 15), 0, 0));
        ptPane.add(medL[2], new GridBagConstraints(0, 3, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[4], new GridBagConstraints(1, 3, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[5], new GridBagConstraints(1, 3, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medL[3], new GridBagConstraints(2, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[6], new GridBagConstraints(3, 3, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[7], new GridBagConstraints(3, 3, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medTF[4], new GridBagConstraints(0, 4, 2, 1, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 5, 15), 0, 0));
        ptPane.add(medTF[5], new GridBagConstraints(2, 4, 2, 1, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 5, 15), 0, 0));
        ptPane.add(medL[4], new GridBagConstraints(0, 5, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[8], new GridBagConstraints(1, 5, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[9], new GridBagConstraints(1, 5, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medL[5], new GridBagConstraints(2, 5, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[10], new GridBagConstraints(3, 5, 1, 1, 0, 0.5,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[11], new GridBagConstraints(3, 5, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medL[6], new GridBagConstraints(0, 6, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[12], new GridBagConstraints(1, 6, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[13], new GridBagConstraints(1, 6, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medL[7], new GridBagConstraints(2, 6, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[14], new GridBagConstraints(3, 6, 1, 1, 0, 0.5,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[15], new GridBagConstraints(3, 6, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medL[8], new GridBagConstraints(0, 7, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[16], new GridBagConstraints(1, 7, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[17], new GridBagConstraints(1, 7, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
       ptPane.add(medL[9], new GridBagConstraints(0, 8, 4, 1, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        int pozY = 9, pozX = -1;

        for (int i = 7; i < labels.length; i++) {
            ptPane.add(medTF[i], new GridBagConstraints
                    (++pozX, pozY, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 15), 0, 0));

            if ((i + 2) % 4 == 0) {
                pozY++;
                pozX = -1;
            }
        }

       submitB.setText("Submit");
       submitB.addActionListener(this);
       ptPane.add(submitB, new GridBagConstraints(3, 11, 1, 1, 0, 0.5,
                GridBagConstraints.LAST_LINE_END,
                GridBagConstraints.NONE, new Insets(5, 15, 15, 15), 0, 0));
       this.PFPanel.addChangeListener(this);

    }


    public void itemStateChanged(ItemEvent e){

         Object state = e.getItemSelectable();

         for(int i =0; i<rb.length ; i++){

            if (state.equals(rb[i]) && e.getStateChange()==ItemEvent.SELECTED){

                if(i <=1 && rb[i].getText().equals( "Yes")){
                    str[0] = labelsL[0]+"|"+rb[i].getText()+"|";
                }
                else {str[0] = "";}

                if(i<=3 && i>1 && rb[i].getText().equals( "Yes")){
                    str[1] = labelsL[1]+"|"+rb[i].getText()+"|";
                }
                else {str[1] = "";}

                if(i<=5 && i>3 && rb[i].getText().equals( "Yes")){
                    str[2] = labelsL[2]+"|"+rb[i].getText()+"|";
                }
                else {str[2] = "";}

                if(i<=7 && i>5 && rb[i].getText().equals( "Yes")){
                    str[3] = labelsL[3]+"|"+rb[i].getText()+"|";
                }
                else {str[3] = "";}

                if(i<=9 && i>7 && rb[i].getText().equals( "Yes")){
                    str[4] = labelsL[4]+"|"+rb[i].getText()+"|";
                }
                else {str[4] = "";}

                if(i<=11 && i>9 && rb[i].getText().equals( "Yes")){
                    str[5] = labelsL[5]+"|"+rb[i].getText()+"|";
                }
                else {str[5] = "";}

                if(i<=13 && i>11 && rb[i].getText().equals( "Yes")){
                    str[6] = labelsL[6]+"|"+rb[i].getText()+"|";
                }
                else {str[6] = "";}

                if(i<=15 && i>13 && rb[i].getText().equals( "Yes")){
                    str[7] = labelsL[7]+"|"+rb[i].getText()+"|";
                }
                else {str[7] = "";}

                if(i<=17 && i>15 && rb[i].getText().equals( "Yes")){
                    str[8] = labelsL[8]+"|"+rb[i].getText()+"|";
                }
                else {str[8] = "";}
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        StringBuffer info = new StringBuffer();
        Object button = e.getSource();
    if(button==this.submitB){
        if(ptcid == 0){
                 JOptionPane.showMessageDialog(null, "You have to chose a patient first!");
            }
            else{

            if(str[0].isEmpty() == false){
                info.append(str[0]);
                info.append(labels[2]+"|"+medTF[2].getText()+"|");
            }
            if(str[1].isEmpty() == false){
                info.append(str[1]);
                info.append(labels[3]+"|"+medTF[3].getText()+"|");
            }
            if(str[2].isEmpty() == false){
               info.append(str[2]);
               info.append(labels[4]+"|"+medTF[4].getText()+"|");
            }
            if (str[3].isEmpty() == false){
               info.append(str[3]);
               info.append(labels[5]+"|"+medTF[5].getText()+"|");
            }
               info.append(str[4]);
               info.append(str[5]);
               info.append(str[6]);
               info.append(str[7]);
               info.append(str[8]);

            //vaccins
            for(int i = 7; i<medTF.length; i++)
                {
                    info.append(labels[i]+"|"+medTF[i].getText()+"|");
                }
            }
//         System.out.println("!!!!????!!!!!!"+info);
         DB.db.insertSpecHistory(info.toString(), ptcid);
         JOptionPane.showMessageDialog(null, "The History is submitted!");
        }
    }


    public void stateChanged(ChangeEvent e) {
        ptcid = PatientHistory.pthid;
        temppatient = new PatientInfo();
        function.fillPatient(temppatient, ptcid);
        medTF[0].setText(this.temppatient.getFname()+" "+this.temppatient.getLname());
        medTF[1].setText(""+temppatient.getBdate());
    }
}


