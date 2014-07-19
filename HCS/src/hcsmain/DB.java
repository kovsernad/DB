package hcsmain;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Leo Dubovyi
 * Vanier College
 *
 * Lab #
 * @Project  @Class DB
 */
public class DB {

    public static DB db;
    private String user;
    private String pass;
    private String url;
    private Connection c;

    public DB() {
        try {
            Scanner in = new Scanner(new FileReader("db.conf"));
            user = in.next();
            pass = in.next();
            url = in.next();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            in.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet staff(){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet staff(String login){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff WHERE login = ?");
            ins.setString(1, login);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

     public ResultSet staff(String lName, java.util.Date bDate){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff WHERE bdate = ? AND lname = ?");
            ins.setDate(1,(java.sql.Date) bDate);
            ins.setString(2, lName);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

        public ResultSet staff(String name, int pos){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff WHERE posid = ? AND lname LIKE ?");
            ins.setInt(1, pos);
            ins.setString(2, name+"%");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet position(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM position WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet qualification(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM qualification WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }


    public ResultSet staffSchedule(int staffId, int hospId){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staffSchedule WHERE staffid = ? AND hospid = ?");
            ins.setInt(1, staffId);
            ins.setInt(2, hospId);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

        public ResultSet staffSchedule(int staffId){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staffSchedule WHERE staffid = ?");
            ins.setInt(1, staffId);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet hospital(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM hospital WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

     public ResultSet hospital(String name){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM hospital WHERE name = ?");
            ins.setString(1, name);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }
//added on 9th July by Nadine
     public ResultSet patient(){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM patient ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
//added on 9th July by Nadine
    public ResultSet patient(String lName, java.util.Date bDate){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM patient WHERE bdate = ? AND lname = ?");
            ins.setDate(1,(java.sql.Date) bDate);
            ins.setString(2, lName);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }
    //added on 9th July by Nadine
    public ResultSet prescription(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM prescription WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }
    //added on 10/07 by Nadine
    public ResultSet patientPresc(int patientID){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT prescription FROM prescription WHERE id = (SELECT prescid FROM patient WHERE id = ?)");
            ins.setInt(1, patientID);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }
    //added on 10/07 by Nadine
    public void addPatient(String diag, String anam){


    }

    public void close() {
        try {
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
