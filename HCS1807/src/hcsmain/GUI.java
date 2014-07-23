/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hcsmain;

import hcssupport.JTextFieldLimit;
import hcssupport.Auth;
import hcssupport.DB;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author ContEd Student
 */
public class GUI {

    private JFrame startFrame;
    private JPanel main;
    private int id;

    protected void init() {

        try {
            DB.db = new DB();

        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.startFrame = new JFrame();

        main = new JPanel();
        main.setLayout(new GridBagLayout());

        final JButton okB = new JButton("Ok");
        final JButton cancelB = new JButton("Cancel");
        final JPasswordField passwTF = new JPasswordField(16);
        final JTextField loginTF = new JTextField(16);
        JLabel loginL = new JLabel("Username");
        JLabel passwL = new JLabel("Password");
        JLabel lblImage = new JLabel(new ImageIcon(getClass().getResource("image.png")));

        passwTF.setDocument(new JTextFieldLimit(16));
        loginTF.setDocument(new JTextFieldLimit(16));

        passwTF.setEchoChar('*');

        main.add(lblImage, new GridBagConstraints(0, 0, 1, 2, 0, 0, GridBagConstraints.NORTH,
                GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
        main.add(loginL, new GridBagConstraints(1, 0, 1, 1, 0, 1, GridBagConstraints.NORTH,
                GridBagConstraints.NONE, new Insets(30, 0, 0, 20), 5, 5));
        main.add(loginTF, new GridBagConstraints(2, 0, 2, 1, 0, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(30, 0, 0, 20), 5, 5));

        main.add(passwL, new GridBagConstraints(1, 1, 1, 1, 0, 1, GridBagConstraints.NORTH,
                GridBagConstraints.NONE, new Insets(0, 0, 0, 20), 5, 5));

        /* next the same
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 0;
        c.gridheight = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,0,20);
        c.ipadx = 5;
        c.ipady = 5;

        main.add(passwTF, c);
         * */
        main.add(passwTF, new GridBagConstraints(2, 1, 0, 0, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 20), 5, 5));

        okB.setPreferredSize(cancelB.getPreferredSize());
        okB.setMinimumSize(okB.getPreferredSize());

        main.add(okB, new GridBagConstraints(0, 2, 0, 0, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 60, 35, 0), 5, 5));
        main.add(cancelB, new GridBagConstraints(2, 2, 0, 0, 0, 0, GridBagConstraints.LINE_END,
                GridBagConstraints.NONE, new Insets(10, 60, 35, 40), 5, 5));

        ActionListener btnHandler = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if ((JButton) e.getSource() == cancelB)
                    System.exit(0);
                else if ((JButton) e.getSource() == okB) {
                    String login = loginTF.getText();
                    String passw = new String(passwTF.getPassword());
                    checkLogin(login, passw);
                }
            }
        };

        KeyListener enterKey = new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String login = loginTF.getText();
                    String passw = new String(passwTF.getPassword());
                    checkLogin(login, passw);
                }
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }
        };

        cancelB.addActionListener(btnHandler);
        okB.addActionListener(btnHandler);
        passwTF.addKeyListener(enterKey);
        this.startFrame.addKeyListener(enterKey);

        this.startFrame.setContentPane(main);
        this.startFrame.setTitle("HEALTH CARE SYSTEM. Authorisation required");
        this.startFrame.setSize(400, 250);
        this.startFrame.setResizable(false);
        this.startFrame.setLocationRelativeTo(null);
        this.startFrame.setVisible(true);
        this.startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void checkLogin(String login, String passw) {
//        MedOfficer fr = new MedOfficer();

        try {
            Auth t = new Auth(login, passw);
            ResultSet res = DB.db.staff();
            boolean exist = false;
            String psw = null;
            int position = 0;
            id = 0;
            while (res.next()) {
                if (login.equals(res.getString("login"))) {
                    psw = res.getString("password");
                    position = res.getInt("posid");
                    id = res.getInt("id");
                    exist = true;
                }
            }
            DB.db.close();
            if (exist)
                if (t.getPasswHash().equals(psw) && t.getLogin().equals(login)) {
                    startFrame.setVisible(false);
                    res = DB.db.position(position);
                    while (res.next()) {
                        if (res.getString("posdesc").equalsIgnoreCase("ma"))
                            javax.swing.SwingUtilities.invokeLater(new Runnable() {

                                public void run() {
                                    new MedAssist();
                                }
                            });
                        else if (res.getString("posdesc").equalsIgnoreCase("ns"))
                            javax.swing.SwingUtilities.invokeLater(new Runnable() {

                                public void run() {
                                    new Nurse();
//                                    new Nurse(id);
                                }
                            });
                        else if (res.getString("posdesc").equalsIgnoreCase("mo"))
                            javax.swing.SwingUtilities.invokeLater(new Runnable() {

                                public void run() {
                                    new MedOfficer(id);
                                }
                            });
                        else if (res.getString("posdesc").equalsIgnoreCase("gp"))
                            javax.swing.SwingUtilities.invokeLater(new Runnable() {

                                public void run() {
                                    new Doctor();
                                }
                            });
                    }

                } else
                    JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "ERROR 2", "ERROR 2", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
