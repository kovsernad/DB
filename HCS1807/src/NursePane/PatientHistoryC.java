package NursePane;

/**
 *
 * @author Nadine
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import hcssupport.Func;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientHistoryC implements  ActionListener, ItemListener {
    private Func function;
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
    
    public PatientHistoryC(JTabbedPane Panel){
        this.PFPanel = Panel;
        this.init();
    }
    private void init(){
        this.function = new Func();
        this.submitB = new JButton();

    }
    public void patientHistoryC(JPanel ptPane){
       
        //creating text fields with lables
        for (int i = 0; i < labels.length; i++) {
            medTF[i] = new JTextField();
            TitledBorder tl = BorderFactory.createTitledBorder
                    (BorderFactory.createLineBorder(Color.DARK_GRAY), labels[i]);
            tl.setTitleJustification(TitledBorder.LEFT);
            medTF[i].setBorder(tl);
            medTF[i].setOpaque(false);
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
        ptPane.add(timeTP, new GridBagConstraints(0, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(medTF[0], new GridBagConstraints(1, 0, 2, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(medTF[1], new GridBagConstraints(3, 0, 1, 1, 0.5, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(5, 15, 5, 15), 0, 0));

        ptPane.add(medL[0],
                new GridBagConstraints(0, 1, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[0], new GridBagConstraints(1, 1, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[1], new GridBagConstraints(1, 1, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));

        ptPane.add(medL[1],
                new GridBagConstraints(2, 1, 1, 1, 0.5, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[2], new GridBagConstraints(3, 1, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[3], new GridBagConstraints(3, 1, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));

        ptPane.add(medTF[2], new GridBagConstraints(0, 2, 2, 1, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(medTF[3], new GridBagConstraints(2, 2, 2, 1, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 15), 0, 0));

        ptPane.add(medL[2],
                new GridBagConstraints(0, 3, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[4], new GridBagConstraints(1, 3, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[5], new GridBagConstraints(1, 3, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(medL[3],
                new GridBagConstraints(2, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[6], new GridBagConstraints(3, 3, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[7], new GridBagConstraints(3, 3, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));

        ptPane.add(medTF[4], new GridBagConstraints(0, 4, 2, 1, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(medTF[5], new GridBagConstraints(2, 4, 2, 1, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(5, 15, 5, 15), 0, 0));

        ptPane.add(medL[4],
                new GridBagConstraints(0, 5, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));
        ptPane.add(rb[8], new GridBagConstraints(1, 5, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[9], new GridBagConstraints(1, 5, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));

        ptPane.add(medL[5],
                new GridBagConstraints(2, 5, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[10], new GridBagConstraints(3, 5, 1, 1, 0, 0.5,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[11], new GridBagConstraints(3, 5, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(15, 15, 15, 15), 0, 0));

        ptPane.add(medL[6],
                new GridBagConstraints(0, 6, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[12], new GridBagConstraints(1, 6, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[13], new GridBagConstraints(1, 6, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));

        ptPane.add(medL[7],
                new GridBagConstraints(2, 6, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[14], new GridBagConstraints(3, 6, 1, 1, 0, 0.5,
                GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[15], new GridBagConstraints(3, 6, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));

        ptPane.add(medL[8],
                new GridBagConstraints(0, 7, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[16], new GridBagConstraints(1, 7, 1, 1, 0, 0.5,
                GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));
        ptPane.add(rb[17], new GridBagConstraints(1, 7, 1, 1, 0, 0.5,
                GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));

       ptPane.add(medL[9],
                new GridBagConstraints(0, 8, 4, 1, 0, 0.5,
                GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(5, 15, 5, 15), 0, 0));

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

    }
    
    public void actionPerformed(ActionEvent e){
        Object button = e.getSource();
    if(button==this.submitB){
        //vaccins
        for(int i = 7; i<medTF.length; i++)
        {
            System.out.println("*****111111*********" + group[1].getSelection().getActionCommand());
            System.out.println("*******00000*******" + group[0].getSelection().getActionCommand());
//        System.out.println(" "+labels[i]+" "+medTF[i].getText());
        }
        

        }
        
    }
    
    public void itemStateChanged(ItemEvent e){
         Object state = e.getItemSelectable();
         int j = 0;

         for(int i =0; i<rb.length ; i++){

            if (state.equals(rb[i]) && e.getStateChange()==ItemEvent.SELECTED){
            
                if(i <=1){
                    System.out.println("lb "+labelsL[0]+"?????"+rb[i].getText());
                   
                }
                else if(i<=3 && i>1){
                    System.out.println("lb "+labelsL[1]+"?????"+rb[i].getText());
                }
                else if(i<=5 && i>3){
                    System.out.println("lb "+labelsL[2]+"?????"+rb[i].getText());
                }
                else if(i<=7 && i>5){
                    System.out.println("lb "+labelsL[3]+"?????"+rb[i].getText());
                } 
                else if(i<=9 && i>7){
                    System.out.println("lb "+labelsL[4]+"?????"+rb[i].getText());
                } 
                else if(i<=11 && i>9){
                    System.out.println("lb "+labelsL[5]+"?????"+rb[i].getText());
                } 
                else if(i<=13 && i>11){
                    System.out.println("lb "+labelsL[6]+"?????"+rb[i].getText());
                } 
                else if(i<=15 && i>13){
                    System.out.println("lb "+labelsL[7]+"?????"+rb[i].getText());
                } 
                else if(i<=17 && i>15){
                    System.out.println("lb "+labelsL[8]+"?????"+rb[i].getText());
                } 
            }
            
        }

    }

}


