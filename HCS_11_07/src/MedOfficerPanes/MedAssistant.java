package MedOfficerPanes;

import hcsmain.Staff;
import hcssupport.Func;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Leo Dubovyi
 * Vanier College
 *
 * Lab #
 * @Project  @Class MedAssistant
 */
public class MedAssistant implements KeyListener, ListSelectionListener {

    private Vector<Staff> medas;
    private DefaultListModel listModelMA;
    private DefaultListModel listModelMAHosp;
    private Func function;
    private JTextField findMedAsTF;
    private JTextPane medAsTP;
    private JTextPane medAsinfoTP;
    private JList medAsList;
    private JList hospAssistList;
    private JTextField[] medAsTF;
    private JTabbedPane MOPanel;

    public MedAssistant(JTabbedPane Panel) {
        this.MOPanel = Panel;
        this.init();
    }

    private void init() {
        listModelMA = new DefaultListModel();
        listModelMAHosp = new DefaultListModel();
        this.function = new Func();
        this.medas = new Vector();
        this.findMedAsTF = new JTextField(25);
        this.medAsTP = new JTextPane();
        this.medAsinfoTP = new JTextPane();
        this.medAsList = new JList(this.listModelMA);
        this.hospAssistList = new JList(this.listModelMAHosp);
    }

    public void assistant(String[] labels, JPanel medAssistPane) {

        JScrollPane medAsSP = new JScrollPane(medAsTP);
        JScrollPane medAsinfoSP = new JScrollPane(medAsinfoTP);
        JScrollPane medAssHospSP = new JScrollPane(this.hospAssistList);

        medAsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        medAsList.setSelectedIndex(0);
        medAsList.setVisibleRowCount(8);

        hospAssistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hospAssistList.setSelectedIndex(0);
        hospAssistList.setVisibleRowCount(8);

        this.medAsTF = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            medAsTF[i] = new JTextField(15);
            TitledBorder ttl = BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLUE), labels[i]);
            ttl.setTitleJustification(TitledBorder.LEFT);
            medAsTF[i].setBorder(ttl);
            medAsTF[i].setOpaque(false);
        }


        // set opacity for left panel
        this.function.setOpacity(this.medAsTP);
        this.function.setOpacity(medAsSP);

        //set opacity for info panel
        this.function.setOpacity(medAsinfoTP);
        this.function.setOpacity(medAsinfoSP);

        this.function.setOpacity(this.hospAssistList);
        this.function.setOpacity(medAssHospSP);

        // set dimensions
        medAsinfoSP.setPreferredSize(new Dimension(400, 100));
        medAsSP.setPreferredSize(new Dimension(200, 200));
        medAsList.setPreferredSize(new Dimension(150, 175));

        // set title for list
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Nurses");
        title.setTitleJustification(TitledBorder.LEFT);
        medAsList.setBorder(title);
        medAsList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) medAsList.getCellRenderer()).setOpaque(false);

        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Hospitals");
        title.setTitleJustification(TitledBorder.LEFT);
        hospAssistList.setBorder(title);
        hospAssistList.setOpaque(false);
        ((javax.swing.DefaultListCellRenderer) hospAssistList.getCellRenderer()).setOpaque(false);

        title = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Schedule");
        title.setTitleJustification(TitledBorder.LEFT);
        medAssHospSP.setBorder(title);
        medAssHospSP.setOpaque(false);


        final JTextPane timeTP = new JTextPane();
        timeTP.setContentType("text/html");
        timeTP.setPreferredSize(new Dimension(200, 24));
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


        // building pane
         medAssistPane.add(timeTP, new GridBagConstraints(0, 0, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 0, 15), 0, 0));
        medAssistPane.add(medAsSP, new GridBagConstraints(0, 1, 2, 14, 0.5, 1, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(15, 15, 15, 15), 0, 0));
        medAssistPane.add(new JLabel("Medical Assistant"), new GridBagConstraints(2, 0, 2, 1, 1, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.NONE, new Insets(15, 0, 15, 0), 0, 0));
        medAssistPane.add(medAsList, new GridBagConstraints(4, 0, 2, 4, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 15, 15, 15), 5, 5));
        medAssistPane.add(findMedAsTF, new GridBagConstraints(2, 1, 2, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 5, 5));
        medAssistPane.add(medAsinfoSP, new GridBagConstraints(2, 2, 2, 2, 0.5, 0, GridBagConstraints.NORTHWEST,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 5, 5));

        medAssistPane.add(hospAssistList, new GridBagConstraints(2, 8, 1, 5, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));
        medAssistPane.add(medAssHospSP, new GridBagConstraints(3, 8, 2, 5, 0.5, 0.5, GridBagConstraints.NORTHWEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 15, 15), 5, 5));

        medAssistPane.add(new JButton("Add Assistant"), new GridBagConstraints(4, 13, 1, 1, 0.5, 0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));
        medAssistPane.add(new JButton("Edit Assistant"), new GridBagConstraints(3, 13, 2, 1, 0.5, 0, GridBagConstraints.EAST,
                GridBagConstraints.NONE, new Insets(0, 0, 15, 15), 5, 5));

        int pozY = 4;
        int pozX = 1;
        for (int i = 0; i < labels.length; i++) {
            medAssistPane.add(medAsTF[i], new GridBagConstraints(++pozX, pozY, 1, 1, 0.5, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 15, 15), 0, 0));
            if ((i + 1) % 3 == 0) {
                pozY++;
                pozX = 1;
            }
        }

                // fill the informations through the timer (
        new javax.swing.Timer(500000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                function.fillStaff(medas, 2);
                medAsTP.setText(function.fillStaffInfo("ma"));
            }
        }).start();

        // filling left textbox
        medAsTP.setText(this.function.fillStaffInfo("ma"));
        findMedAsTF.addKeyListener(this);
        medAsList.addListSelectionListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.MOPanel.getSelectedIndex() == 2 && listModelMA.size() == 1) {
                String t = this.listModelMA.get(0).toString();
                function.staffPersonalInfo(medas, t, medAsTF);
            }
        } else if (this.MOPanel.getSelectedIndex() == 2 &&
                ((e.getKeyChar() >= 65 && e.getKeyChar() <= 90) ||
                (e.getKeyChar() >= 97 && e.getKeyChar() <= 122) ||
                (!findMedAsTF.getText().isEmpty() &&
                (e.getKeyCode() == KeyEvent.VK_BACK_SPACE))))
            function.staffListInfo(medas, medAsinfoTP, findMedAsTF, listModelMA);
        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            if (this.MOPanel.getSelectedIndex() == 2) {
                medAsinfoTP.setText(null);
                listModelMA.removeAllElements();
            }
    }

    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting())
            if (this.MOPanel.getSelectedIndex() == 2 &&
                    medAsList.getSelectedIndex() >= 0) {
                String st = medAsList.getSelectedValue().toString();
                function.staffPersonalInfo(medas, st, medAsTF);
            }
    }
}
