package hcssupport;

import hcsmain.*;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
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


    public void openConnection(){
        try {
            c = DriverManager.getConnection(url, user, pass);
            c.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public ResultSet staff() {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void staffUpdate(String email, String address,
            String zip, String phone, Integer posId, Integer qualId, Integer staffId) {
        try {
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

    public ResultSet staff(String login) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff WHERE login = ?");
            ins.setString(1, login);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet staff(int id) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet staff(String lName, java.util.Date bDate) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff WHERE bdate = ? AND lname = ?");
            ins.setDate(1, (java.sql.Date) bDate);
            ins.setString(2, lName);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet staff(String name, int pos) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staff WHERE posid = ? AND lname LIKE ?");
            ins.setInt(1, pos);
            ins.setString(2, name + "%");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet position(int id) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM position WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet position(String descr) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT id FROM position WHERE posdesc = ?");
            ins.setString(1, descr);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet position() {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM position ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet qualification(int id) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM qualification WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet qualification(String descr) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT id FROM qualification WHERE qualdesc = ?");
            ins.setString(1, descr);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet qualification() {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM qualification ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet staffSchedule(int staffId, int hospId) {
        ResultSet res = null;
        try {
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

    public ResultSet staffSchedule(int staffId) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM staffSchedule WHERE staffid = ?");
            ins.setInt(1, staffId);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet hospital(int id) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM hospital WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet hospital() {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM hospital");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet hospital(String name) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM hospital WHERE name = ?");
            ins.setString(1, name);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet schedule(Integer staffId, Integer patientId) {
        ResultSet res = null;
        try {
            if (patientId == null) {
                PreparedStatement ins =
                        c.prepareStatement("SELECT * FROM schedule WHERE staffid = ?");
                ins.setInt(1, staffId);
                res = ins.executeQuery();
            } else {
                PreparedStatement ins =
                        c.prepareStatement("SELECT * FROM schedule WHERE patientid = ?");
                ins.setInt(1, patientId);
                res = ins.executeQuery();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet patient(int id) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM patient WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public ResultSet patient() {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM patient ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    // added 21.07 
    // maybe need check if user exists
    public void addStaff(String[] staff, int qualid, int posid, String login, String password) {

        java.util.Date day;
        java.sql.Date rv = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        try {
            day = format.parse(staff[2]);
            rv = new java.sql.Date(day.getTime());
            StringBuffer st = new StringBuffer();
            st.append("INSERT INTO staff (fname,  lname, bdate, SSN, ");
            st.append("email, address, zip, phone, qualid, posid,  ");
            st.append("login, password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            PreparedStatement ins =
                    c.prepareStatement(st.toString());
            ins.setString(1, staff[0]);
            ins.setString(2, staff[1]);
            ins.setDate(3, rv);
            ins.setString(4, staff[3]);
            ins.setString(5, staff[4]);
            ins.setString(6, staff[5]);
            ins.setString(7, staff[6]);
            ins.setString(8, staff[9]);
            ins.setInt(9, qualid);
            ins.setInt(10, posid);
            ins.setString(11, login);
            ins.setString(12, password);
            ins.executeUpdate();

        } catch (ParseException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // added 18.07
    public void editStaffHospitalSchedual(int staffId, int hospId, Object day, Object workAM, Object workPM) {
        ResultSet res = null;
        try {
            StringBuffer st = new StringBuffer();
            st.append("SELECT workhouram, workhourpm FROM staffSchedule ");
            st.append("WHERE hospid = ? AND staffid = ? AND workday = ?");
            PreparedStatement ins =
                    c.prepareStatement(st.toString());
            ins = c.prepareStatement(st.toString());
            ins.setInt(1, hospId);
            ins.setInt(2, staffId);
            ins.setString(3, day.toString().substring(0, 3));
            res = ins.executeQuery();
            if (res.next()) {
                st = new StringBuffer();
                st.append("UPDATE staffSchedule SET ");
                st.append("workhouram = ?, ");
                st.append("workhourpm = ? ");
                st.append("WHERE hospid = ? AND staffid = ? AND workday = ?");

                ins = c.prepareStatement(st.toString());
                ins.setObject(1, workAM);
                ins.setObject(2, workPM);
                ins.setInt(3, hospId);
                ins.setInt(4, staffId);
                ins.setString(5, day.toString().substring(0, 3));
                ins.executeUpdate();
            } else {
                st = new StringBuffer();
                st.append("INSERT INTO staffSchedule ");
                st.append("(workhouram, workhourpm, hospid, staffid, workday) ");
                st.append("VALUES (?, ?, ?, ?, ?)");

                ins = c.prepareStatement(st.toString());
                ins.setObject(1, workAM);
                ins.setObject(2, workPM);
                ins.setInt(3, hospId);
                ins.setInt(4, staffId);
                ins.setString(5, day.toString().substring(0, 3));
                ins.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

//added on 9th July by Nadine
    public ResultSet patient(String lName, java.util.Date bDate) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM patient WHERE bdate = ? AND lname = ?");
            ins.setDate(1, (java.sql.Date) bDate);
            ins.setString(2, lName);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    //added on 9th July by Nadine

    public ResultSet prescription(int id) {
        ResultSet res = null;
        try {
            
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM prescription WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }
    
    //added by Nadine 28.07
    public ResultSet prescriptionByPatientId(String id) {
        ResultSet res = null;
        try {
            
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM prescription WHERE patientid = ?");
            ins.setString(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    //added by Nadine 22.07
    public ResultSet drugsbyName(String dname) {
        ResultSet res = null;
        try {
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
    public ResultSet prescription() {
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

    public ResultSet tests(int id) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM tests WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public ResultSet drugs(int drugId) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM drugs WHERE id = ?");
            ins.setInt(1, drugId);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    //added by Nadine 18.07
    public ResultSet drugs() {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM drugs ORDER BY id");
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    //added on 16/07 by Valerii
    //retrieving testid from
    public ResultSet scheduleM(int id, int patId) {
        ResultSet res = null;
        try {
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


//Added by Nadine 18.07
    public void diagAnamUpdate(String anamnesis, String diagnosis,
            Integer patientId) {
        try {
            DB.db.openConnection();
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
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        DB.db.close();
        }

    }
    //Added by Nadine 18.07

    public void prescInsert(String prescription,
            Integer patientId, String drugId) {
        try {
            DB.db.openConnection();
            StringBuffer st = new StringBuffer();
            st.append("INSERT INTO prescription (patientid, prescription, drugid) VALUES ( ?, ?, ?)");

            PreparedStatement ins =
                    c.prepareStatement(st.toString());
            ins.setInt(1, patientId);
            ins.setString(2, prescription);
            ins.setString(3, drugId);
            ins.executeUpdate();
                DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            DB.db.close();
        }

    }
    //Added by Nadine 18.07

    public void insertGmedHistory(String gmedhistory, Integer patientId) {

        try {
            DB.db.openConnection();
            StringBuffer st = new StringBuffer();
            st.append("UPDATE patient SET gmedhistory = ?");
            st.append("WHERE id = ?");
            PreparedStatement ins =
                    c.prepareStatement(st.toString());

            ins.setString(1, gmedhistory);
            ins.setInt(2, patientId);

            ins.executeUpdate();
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            DB.db.close();
        }
    }


      //added by Nadine 24 July
     public ResultSet testIdFromSchedule(int ptid, int staffid) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT testid FROM schedule WHERE patientid = ? AND staffid = ?");
            ins.setInt(1, ptid);
            ins.setInt(2, staffid);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }
    //added by Nadine 24 July
     public ResultSet laboratory(int id) {
        ResultSet res = null;
        try {
            PreparedStatement ins =
                    c.prepareStatement("SELECT * FROM laboratory WHERE id = ?");
            ins.setInt(1, id);
            res = ins.executeQuery();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }


    public void close() {
        try {
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //added by Nadine 25 July
    
     public void insertIllHistory(String illhistory, Integer patientId) {

        try {
            DB.db.openConnection();
            StringBuffer st = new StringBuffer();
            st.append("UPDATE patient SET illnesshistory = ?");
            st.append("WHERE id = ?");
            PreparedStatement ins =
                    c.prepareStatement(st.toString());

            ins.setString(1, illhistory);
            ins.setInt(2, patientId);

            ins.executeUpdate();
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            DB.db.close();
        }
    }
     
     //added by Nadine 25 July
    
     public void insertSpecHistory(String sphistory, Integer patientId) {

        try {
            DB.db.openConnection();
            StringBuffer st = new StringBuffer();
            st.append("UPDATE patient SET medspechistory = ?");
            st.append("WHERE id = ?");
            PreparedStatement ins =
                    c.prepareStatement(st.toString());

            ins.setString(1, sphistory);
            ins.setInt(2, patientId);

            ins.executeUpdate();
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            DB.db.close();
        }
    }
}
