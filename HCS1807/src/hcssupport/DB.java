package hcssupport;

import hcsmain.*;
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

    public void staffUpdate(String email, String address,
            String zip, String phone, Integer posId, Integer qualId, Integer staffId){
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            StringBuffer st = new StringBuffer();
            st.append("UPDATE staff SET ");
            st.append("email = ?, ");
            st.append("address = ?, ");
            st.append("zip = ?, ");
            st.append("phone = ?, ");
            st.append("posid = ?, ");
            st.append("qualid = ? ");
            st.append("WHERE id = ?");

            PreparedStatement ins =
                        c.prepareStatement(st.toString());
                ins.setString(1, email);
                ins.setString(2, address);
                ins.setString(3, zip);
                ins.setString(4, phone);
                ins.setInt(5, posId);
                ins.setInt(6, qualId);
                ins.setInt(7, staffId);
                ins.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public ResultSet staff(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff WHERE id = ?");
            ins.setInt(1, id);
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

     public ResultSet position(String descr){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT id FROM position WHERE posdesc = ?");
            ins.setString(1, descr);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet position(){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM position ORDER BY id");
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

    public ResultSet qualification(String descr){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT id FROM qualification WHERE qualdesc = ?");
            ins.setString(1, descr);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

        public ResultSet qualification(){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM qualification ORDER BY id");
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

    public ResultSet schedule(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM schedule WHERE staffid = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet patient(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM patient WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    //added by Nadine 18.07
    public ResultSet drugs(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM drugs WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    //added by Nadine 18.07
    public ResultSet drugs(){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM drugs ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

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
     //added by Nadine 22.07
     public ResultSet drugsbyName(String dname){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM drugs WHERE name = ?");
            ins.setString(1, dname);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

     //added by Nadine 18.07
    public ResultSet prescription(){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM prescription ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    //added on 16/07 by Valerii
    //retrieving res from tests entity
    public ResultSet tests(int id){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM tests WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    //added on 16/07 by Valerii
    //retrieving testid from
    public ResultSet scheduleM(int id,int patId){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM schedule WHERE id = ? AND patientid = ?");
            ins.setInt(1, id);
            ins.setInt(2, patId);

            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

        public ResultSet scheduleM(int patId){
        ResultSet res = null;
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            PreparedStatement ins =
                    c.prepareStatement("SELECT id FROM schedule WHERE patientid = ?");
            ins.setInt(1, patId);

            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

//Added by Nadine 18.07
         public void diagAnamUpdate(String anamnesis, String diagnosis,
                                        Integer patientId){
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            StringBuffer st = new StringBuffer();
            st.append("UPDATE patient SET ");
            st.append("anamnesis = anamnesis || CHR(10) || CHR(10) || ?, ");
            st.append("diagnosis = diagnosis || CHR(10) || CHR(10) || ?");
            st.append("WHERE id = ?");

            PreparedStatement ins =
                        c.prepareStatement(st.toString());
                ins.setString(1, anamnesis);
                ins.setString(2, diagnosis);
                ins.setInt(3, patientId);
                ins.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
         //Added by Nadine 18.07 
  public void prescInsert(String prescription,
                                        Integer patientId, String drugId){
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            StringBuffer st = new StringBuffer();
            st.append("INSERT INTO prescription (patientid, prescription, drugid) VALUES ( ?, ?, ?)");

            PreparedStatement ins =
                        c.prepareStatement(st.toString());
                ins.setInt(1, patientId);
                ins.setString(2, prescription);
                ins.setString(3, drugId);
                ins.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
         //Added by Nadine 18.07 
   public void insertGmedHistory(String gmedhistory, Integer patientId){
       
       //UPDATE patient SET gmedhistory = 'Smith' WHERE id = 1;
       
       try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
            StringBuffer st = new StringBuffer();
            st.append("UPDATE patient SET gmedhistory = ?");
            st.append("WHERE id = ?");
            PreparedStatement ins =
                        c.prepareStatement(st.toString());
               
                ins.setString(1, gmedhistory);
                ins.setInt(2, patientId);

                ins.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
   }      
         
    public void close() {
        try {
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
