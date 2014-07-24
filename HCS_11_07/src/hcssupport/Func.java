/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hcssupport;

import hcsmain.PatientInfo;
import hcsmain.StartMedOfficer;
import MedOfficerPanes.Staffs;
import hcsmain.DrugInfo;
import hcsmain.StaffInfo;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author ContEd Student
 */
public class Func<T> {

    public void makeElementWithBorder(Object element, String title, Color color, boolean opaqueOfList) {
        if (element instanceof JScrollPane) {
            JScrollPane elem = (JScrollPane) element;
            if (color != null) {
                TitledBorder ttl = BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(color), title);
                ttl.setTitleJustification(TitledBorder.LEFT);
                elem.setBorder(ttl);
            } else {
                TitledBorder ttl = BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), title);
                ttl.setTitleJustification(TitledBorder.LEFT);
                elem.setBorder(ttl);
            }
        } else if (element instanceof JTextPane) {
            JTextPane elem = (JTextPane) element;
            if (color != null) {
                TitledBorder ttl = BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(color), title);
                ttl.setTitleJustification(TitledBorder.LEFT);
                elem.setBorder(ttl);
            } else {
                TitledBorder ttl = BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), title);
                ttl.setTitleJustification(TitledBorder.LEFT);
                elem.setBorder(ttl);
            }
        } else if (element instanceof JTextField) {
            JTextField elem = (JTextField) element;
            if (color != null) {
                TitledBorder ttl = BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(color), title);
                ttl.setTitleJustification(TitledBorder.LEFT);
                elem.setBorder(ttl);
            } else {
                TitledBorder ttl = BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), title);
                ttl.setTitleJustification(TitledBorder.LEFT);
                elem.setBorder(ttl);
            }
        } else if (element instanceof JList) {
            JList elem = (JList) element;
            if (color != null) {
                TitledBorder ttl = BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(color), title);
                ttl.setTitleJustification(TitledBorder.LEFT);
                elem.setBorder(ttl);
            } else {
                TitledBorder ttl = BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), title);
                ttl.setTitleJustification(TitledBorder.LEFT);
                elem.setBorder(ttl);
            }
            elem.setOpaque(opaqueOfList);
            ((javax.swing.DefaultListCellRenderer) elem.getCellRenderer()).setOpaque(opaqueOfList);
        }
    }

    public void fillStaff(Vector<T> staff, int id) {
        ResultSet rs = DB.db.staff();
        try {
            while (rs.next()) {
                StaffInfo s = new StaffInfo();
                s.setId(rs.getInt("id"));
                s.setLname(rs.getString("lname"));
                s.setFname(rs.getString("fname"));
                s.setbDate(rs.getDate("bDate"));
                if (rs.getInt("posid") == id) {
                    staff.add((T) s);
                }
            }
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(StartMedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillStaff(Vector<T> staff) {
        ResultSet rs = DB.db.staff();
        try {
            while (rs.next()) {
                StaffInfo s = new StaffInfo();
                s.setId(rs.getInt("id"));
                s.setLname(rs.getString("lname"));
                s.setFname(rs.getString("fname"));
                s.setbDate(rs.getDate("bDate"));
                ResultSet ps = DB.db.position(rs.getInt("posid"));
                while (ps.next()) {
                    s.setPosition(ps.getString("posdesc"));
                }
                ps = DB.db.qualification(rs.getInt("qualid"));
                while (ps.next()) {
                    s.setQualif(ps.getString("qualdesc"));
                }
                staff.add((T) s);
            }
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(StartMedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//added patient fillings by Nadine 23.07
    public void fillPatient(PatientInfo patient, int patientId) {
        try {
            ResultSet rs = DB.db.patient(patientId);
            while (rs.next()) {
                patient.setId(patientId);
                patient.setAddress(rs.getString("address"));
                patient.setAnam(rs.getString("anamnesis"));
                patient.setBdate(rs.getDate("bdate"));
                patient.setDiagn(rs.getString("diagnosis"));
                patient.setEmail(rs.getString("email"));
                patient.setFname(rs.getString("fname"));
                patient.setInsurrance(rs.getString("insurrance"));
                patient.setLname(rs.getString("lname"));
                patient.setMedcard(rs.getString("medcard"));
                patient.setPhone(rs.getString("phone"));
                patient.setSsn(rs.getString("ssn"));
                patient.setZip(rs.getString("zip"));
                //reading general history from db and replacing delimiter
                String tempgh = rs.getString("gmedhistory");
                tempgh.trim();
                tempgh = tempgh.replaceAll("[|]", "<br />");
              
                patient.setGmedhistory(tempgh);
                //reading illness history from db and replacing delimiter
                String tempil = rs.getString("illnesshistory");
                tempil.trim();
                tempil = tempil.replaceAll("[|]", "<br />");
                        
                patient.setIllnesshistory(tempil);
                 //reading medical specific history from db and replacing delimiter
                String tempsp = rs.getString("medspechistory");
                tempsp = tempsp.trim();
                tempsp = tempsp.replaceAll("[|]", "<br />");
                
                patient.setMedspechistory(tempsp);
                
                ResultSet res = DB.db.prescription();
                int currPrescr = -1;
                while(res.next()){
                    if(res.getInt("patientid") == patientId && res.getInt("id") > currPrescr){
                        currPrescr = res.getInt("id");
                    }
                }
                res = DB.db.prescription(currPrescr);
                String[] drugId = null;
                Vector<String> drug = new Vector<String>();
                while(res.next()){
                    String t = res.getString("prescription");
                    t.trim();
//                    t = t.substring(1, t.length()-1);
                    t = t.replaceAll("[\n]", "<br />");
                    patient.setPrescriptions(t);
                    String tmp = res.getString("drugid");
                    tmp.trim();
                    tmp = tmp.substring(1, tmp.length()-1);
                    drugId = tmp.split("[|]");
                }

                for(int i = 0; i < drugId.length; i++){
                    res = DB.db.drugs(Integer.parseInt(drugId[i]));
                    while(res.next()){
                        drug.add("<b>" + res.getString("name")+" </b>/ "+res.getString("dose"));
                    }
                }
                patient.setDrugs(drug);
                DB.db.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setHospitalsList(DefaultListModel listModel) {
        try {
            ResultSet rs;
            rs = DB.db.hospital();
            Vector<StringBuffer> t = new Vector();
            listModel.removeAllElements();
            while (rs.next()) {
                StringBuffer tmp = new StringBuffer();
                tmp.append(rs.getString("name"));
                t.add(tmp);
            }
            for (int i = 0; i < t.size(); i++) {
                listModel.add(i, t.get(i).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void setElementList(DefaultListModel listModel, boolean positionTable, boolean qualificationTable) {
        ResultSet rs;
        String field;
        if (positionTable) {
            rs = DB.db.position();
            field = "posdesc";
        } else {
            rs = DB.db.qualification();
            field = "qualdesc";
        }
        try {
            Vector<StringBuffer> t = new Vector();
            listModel.removeAllElements();
            while (rs.next()) {
                StringBuffer tmp = new StringBuffer();
                tmp.append(rs.getString(field));
                t.add(tmp);
            }
            for (int i = 0; i < t.size(); i++) {
                listModel.add(i, t.get(i).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Staffs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOpacity(Object comp) {
        if (comp instanceof JScrollPane) {
            JScrollPane cp = (JScrollPane) comp;
            cp.setOpaque(false);
            cp.getViewport().setOpaque(false);
            cp.getHorizontalScrollBar().setOpaque(false);
            cp.getVerticalScrollBar().setOpaque(false);
        } else if (comp instanceof JTextPane) {
            JTextPane cp = (JTextPane) comp;
            cp.setContentType("text/html");
            cp.setOpaque(false);
            cp.setEditable(false);
        }
    }

    public void patientPersonalInfo(PatientInfo patient, JTextField[] tfPatient) {

        tfPatient[2].setText(patient.getBdate().toString());
        tfPatient[3].setText(patient.getSsn().substring(0, 3) + "-" + patient.getSsn().substring(3, 6) + "-" + patient.getSsn().substring(6, 9));
        tfPatient[4].setText(patient.getEmail());
        tfPatient[5].setText(patient.getAddress());
        tfPatient[6].setText(patient.getZip());
        tfPatient[7].setText(patient.getPhone());
        tfPatient[8].setText(patient.getMedcard());
        tfPatient[9].setText(patient.getInsurrance());
    }

    public void staffPersonalInfo(Vector<StaffInfo> staff, String toMatch, JTextField... staffTF) {
        try {
            int ind = -1;
            String lName = toMatch.substring(0, toMatch.indexOf(","));
            String dBirth = toMatch.substring(toMatch.indexOf("(") + 1, toMatch.indexOf(")"));
            for (int i = 0; i < staff.size(); i++) {
                if (staff.get(i).getLname().equalsIgnoreCase(lName) && staff.get(i).getbDate().toString().equalsIgnoreCase(dBirth)) {
                    ind = i;
                }
            }
            ResultSet res = DB.db.staff(staff.get(ind).getLname(), staff.get(ind).getbDate());
            while (res.next()) {
                staffTF[0].setText(res.getString("fname"));
                staffTF[1].setText(res.getString("lname"));
                staffTF[2].setText(res.getDate("bdate").toString());
                String tmp = res.getString("SSN");
                staffTF[3].setText(tmp.substring(0, 3) + "-" + tmp.substring(3, 6) + "-" + tmp.substring(6, 9));
                staffTF[4].setText(res.getString("email"));
                staffTF[5].setText(res.getString("address"));
                staffTF[6].setText(res.getString("zip"));
                ResultSet rs = DB.db.qualification(res.getInt("qualid"));
                while (rs.next()) {
                    staffTF[7].setText(rs.getString("qualdesc"));
                }
                rs = DB.db.position(res.getInt("posid"));
                while (rs.next()) {
                    staffTF[8].setText(rs.getString("posdesc"));
                }
                staffTF[9].setText(res.getString("phone"));
            }
            DB.db.close();

        } catch (SQLException ex) {
            Logger.getLogger(StartMedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String patientForDoctor(Vector<StaffInfo> staff, String toMatch) {
        StringBuffer info = new StringBuffer();
        try {
            info.append("<table>");
            int ind = -1;
            String lName = toMatch.substring(0, toMatch.indexOf(","));
            String dBirth = toMatch.substring(toMatch.indexOf("(") + 1, toMatch.indexOf(")"));
            for (int i = 0; i < staff.size(); i++) {
                if (staff.get(i).getLname().equalsIgnoreCase(lName) && staff.get(i).getbDate().toString().equalsIgnoreCase(dBirth)) {
                    ind = i;
                }
            }
            ResultSet rs = DB.db.staff(staff.get(ind).getLname(), staff.get(ind).getbDate());
            while (rs.next()) {
                ResultSet rsh = DB.db.schedule(rs.getInt("id"), null);
                while (rsh.next()) {
                    ResultSet rp = DB.db.patient(rsh.getInt("patientid"));
                    while (rp.next()) {
                        info.append("<tr><td>");
                        info.append("<b>" + rp.getString("lname"));
                        info.append(", " + rp.getString("fname") + "</b>");
                        info.append("</tr></td>");
                        info.append("<tr><td>");
                        info.append("Date of birth: <b>" + rp.getDate("bdate").toString() + "</b>");
                        info.append("</tr></td>");
                        info.append("<tr><td>");
                        info.append("E-mail: <b>" + rp.getString("email") + "</b>");
                        info.append("</tr></td>");
                        info.append("<tr><td>");
                        info.append("Address: <b>" + rp.getString("address") + ", " + rp.getString("zip") + "</b>");
                        info.append("</tr></td>");
                        info.append("<tr><td>");
                        info.append("Phone: <b>" + rp.getString("phone") + "</b>");
                        info.append("</tr></td>");
                        info.append("<tr><td>");
                        info.append("MedCard number: <br /><b>" + rp.getString("medcard") + "</b>");
                        info.append("</tr></td>");
                        info.append("<tr><td>");
                        info.append("Insurance number: <br /><b>" + rp.getString("insurrance") + "</b>");
                        info.append("</tr></td>");
                        info.append("<tr><td>");
                        info.append("</tr></td>");

                    }
                }

            }
            info.append("</table>");
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info.toString();
    }

    /*Added 04.07*/
    public void staffListHosp(Vector<StaffInfo> staff, String toMatch, DefaultListModel list) {
        try {
            list.removeAllElements();
            Vector<StringBuffer> t = new Vector();
            int ind = -1;
            String lName = toMatch.substring(0, toMatch.indexOf(","));
            String dBirth = toMatch.substring(toMatch.indexOf("(") + 1, toMatch.indexOf(")"));
            for (int i = 0; i < staff.size(); i++) {
                if (staff.get(i).getLname().equalsIgnoreCase(lName) && staff.get(i).getbDate().toString().equalsIgnoreCase(dBirth)) {
                    ind = i;
                }
            }
            ResultSet rs = DB.db.staff(staff.get(ind).getLname(), staff.get(ind).getbDate());
            while (rs.next()) {
                ResultSet rh = DB.db.staffSchedule(rs.getInt("id"));
                while (rh.next()) {
                    ResultSet rsh = DB.db.hospital(rh.getInt("hospid"));
                    while (rsh.next()) {
                        StringBuffer tmp = new StringBuffer();
                        tmp.append(rsh.getString("name"));
                        boolean exist = false;
                        for (int i = 0; i < t.size(); i++) {
                            if (t.get(i).toString().equalsIgnoreCase(tmp.toString())) {
                                exist = true;
                            }
                        }
                        if (!exist) {
                            t.add(tmp);
                        }
                    }
                }
            }
            // building list
            for (int i = 0; i < t.size(); i++) {
                list.add(i, t.get(i).toString());
            }
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void staffListInfo(Vector<StaffInfo> staff, Object comp, JTextField tf, DefaultListModel list) {
        JTextPane cp = (JTextPane) comp;
        list.removeAllElements();
        StringBuffer st = new StringBuffer();
        Vector<StringBuffer> t = new Vector();
        st.append("<table width='250'>");
        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).getLname().toLowerCase().startsWith(
                    tf.getText().toLowerCase())) {
                // preparing information for staff info
                st.append("<tr><td><b>");
                st.append(staff.get(i).getLname());
                st.append(", " + staff.get(i).getFname());
                st.append("</b></td>");
                st.append("<td><b>");
                st.append(staff.get(i).getbDate().toString());
                st.append("</b></td>");
                st.append("</tr>");
                // preparing information for list
                StringBuffer tmp = new StringBuffer();
                tmp.append(staff.get(i).getLname() + ", ");
                tmp.append(staff.get(i).getFname().charAt(0) + ". (");
                tmp.append(staff.get(i).getbDate().toString() + ")");
                t.add(tmp);
            }
        }
        //finishing and sending information to staff info
        st.append("</table>");
        cp.setText(st.toString());
        // building list
        for (int i = 0; i < t.size(); i++) {
            list.add(i, t.get(i).toString());
        }

    }

    // check for using !!!
    public String fillStaffInfo(String staffPos) {

        ResultSet rs = DB.db.staff();
        StringBuffer info = new StringBuffer();
        try {
            info.append("<table>");
            while (rs.next()) {
                // check through the positions
                ResultSet res = DB.db.position(rs.getInt("posid"));
                // fill panes according to the position
                while (res.next()) {
                    if (res.getString("posdesc").equalsIgnoreCase(staffPos)) {
                        if (staffPos.equalsIgnoreCase("gp")) {
                            info.append("<tr><td>Dr. <b>");
                        } else if (staffPos.equalsIgnoreCase("ns")) {
                            info.append("<tr><td>R.N. <b>");
                        } else if (staffPos.equalsIgnoreCase("ma")) {
                            info.append("<tr><td>Med.As. <b>");
                        }
                        info.append(rs.getString("lname"));
                        info.append(", " + rs.getString("fname"));
                        info.append("</b></td></tr>");
                        info.append("<tr><td>Phone: <b>");
                        info.append(rs.getString("phone") + "</b></td></tr>");
                        // find the qualification
                        ResultSet ress = DB.db.qualification(rs.getInt("qualid"));
                        while (ress.next()) {
                            info.append("<tr><td>Qualification: <b>");
                            info.append(ress.getString("qualdesc") + "</b></td></tr>");
                        }
                        //find the hospital
                        ResultSet rss = DB.db.staffSchedule(rs.getInt("id"));
                        while (rss.next()) {
                            ress = DB.db.hospital(rss.getInt("hospid"));
                            while (ress.next()) {
                                info.append("<tr><td>Hospital: <b>");
                                info.append(ress.getString("name") + "</b></td></tr>");
                            }
                        }
                        info.append("<tr></tr>");
                    }
                }
            }
            info.append("</table>");
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(StartMedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info.toString();
    }

    // added 21.07
    public void addStaffInfo(Vector<StaffInfo> staff, JTextField[] staffTF, JList positionList, JList qualificationList,
            String hospitalList, JTable table) {
        try {
            int posid = -1;
            int qualid = -1;
            int hospId = -1;
            String[] tmp = new String[staffTF.length];
            for (int i = 0; i < staffTF.length; i++) {
                tmp[i] = staffTF[i].getText();
            }
            if (positionList == null || qualificationList == null) {
                throw new Exception();
            }

            // make login and password
            String login = JOptionPane.showInputDialog("Enter desired login");
            String password = null;
            JPasswordField passwordField = new JPasswordField();
            passwordField.setEchoChar('*');
            Object[] obj = {"Please enter the password:\n\n", passwordField};
            Object stringArray[] = {"OK", "Cancel"};
            if (JOptionPane.showOptionDialog(null, obj, "Desired password",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj) == JOptionPane.YES_OPTION) {
                password = new String(passwordField.getPassword());
            }
            // login and password done

            ResultSet rs = DB.db.position(positionList.getSelectedValue().toString());
            while (rs.next()) {
                posid = rs.getInt("id");
            }
            rs = DB.db.qualification(qualificationList.getSelectedValue().toString());
            while (rs.next()) {
                qualid = rs.getInt("id");
            }

            Auth t = new Auth(login, password);
            DB.db.addStaff(tmp, qualid, posid, t.getLogin(), t.getPasswHash());
            DB.db.close();
            staff.removeAllElements();
            this.fillStaff((Vector<T>) staff);
            rs = (ResultSet) DB.db.hospital(hospitalList);
            while (rs.next()) {
                hospId = rs.getInt("id");
            }
            for (int i = 0; i < 7; i++) {
                DB.db.editStaffHospitalSchedual(staff.lastElement().getId(), hospId, table.getValueAt(i, 0),
                        table.getValueAt(i, 1), table.getValueAt(i, 2));
            }
            DB.db.close();


        } catch (Exception ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // added 18.07
    public void editMainStaffInfo(Vector<StaffInfo> staff, JTextField[] staffTF, JList positionList, JList qualificationList) {
        try {
            int id = -1;
            int posId = -1;
            int qualId = -1;
            for (int i = 0; i < staff.size(); i++) {
                if (staff.get(i).getLname().equals(staffTF[1].getText()) && staff.get(i).getbDate().toString().equals(staffTF[2].getText())) {
                    id = staff.get(i).getId();
                }
            }
            if (positionList == null || qualificationList == null) {
                throw new Exception();
            }
            ResultSet rs = DB.db.position(positionList.getSelectedValue().toString());
            while (rs.next()) {
                posId = rs.getInt("id");
            }
            rs = DB.db.qualification(qualificationList.getSelectedValue().toString());
            while (rs.next()) {
                qualId = rs.getInt("id");
            }
            DB.db.staffUpdate(staffTF[4].getText(), staffTF[5].getText(), staffTF[6].getText(), staffTF[9].getText(), posId, qualId, id);


            DB.db.close();
        } catch (Exception ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // added 18.07
    public void editSchedualStaffInfo(Vector<StaffInfo> staff, JTextField[] staffTF, JTable table, String hospitalList) {
        try {
            int id = -1;
            int hospId = -1;
            for (int i = 0; i < staff.size(); i++) {
                if (staff.get(i).getLname().equals(staffTF[1].getText()) && staff.get(i).getbDate().toString().equals(staffTF[2].getText())) {
                    id = staff.get(i).getId();
                }
            }
            ResultSet rs = (ResultSet) DB.db.hospital(hospitalList);
            while (rs.next()) {
                hospId = rs.getInt("id");
            }

            for (int i = 0; i < 7; i++) {
                //System.out.println("hospID" + hospId);
                DB.db.editStaffHospitalSchedual(id, hospId, table.getValueAt(i, 0),
                        table.getValueAt(i, 1), table.getValueAt(i, 2));
            }
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    /* Added 08.07.2014*/
    public void checkStaffSchedule(Vector<StaffInfo> staff, String toMatch, JTable tableElement, JList item) {
        int ind = -1;
        int hospId = -1;
        int staffId = -1;
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j < 3; j++) {
                tableElement.setValueAt("", i, j);
            }
        }
        try {
            //parse string
            String lName = toMatch.substring(0, toMatch.indexOf(","));
            String dBirth = toMatch.substring(toMatch.indexOf("(") + 1, toMatch.indexOf(")"));

            //check for staff
            for (int i = 0; i < staff.size(); i++) {
                if (staff.get(i).getLname().equalsIgnoreCase(lName) && staff.get(i).getbDate().toString().equalsIgnoreCase(dBirth)) {
                    ind = i;
                }
            }

            // got hospitalID
            ResultSet res = DB.db.hospital(item.getSelectedValue().toString());
            while (res.next()) {
                hospId = res.getInt("id");
            }

            // got staffID
            res = DB.db.staff(staff.get(ind).getLname(), staff.get(ind).getbDate());
            while (res.next()) {
                staffId = res.getInt("id");
            }

            //got schedule for staff and hospital
            res = DB.db.staffSchedule(staffId, hospId);
            while (res.next()) {
                String tmp = res.getString("workday");
                if (tmp.equalsIgnoreCase("Sun")) {
                    tableElement.setValueAt(res.getString("workhouram"), 0, 1);
                    tableElement.setValueAt(res.getString("workhourpm"), 0, 2);
                }
                if (tmp.equalsIgnoreCase("Mon")) {
                    tableElement.setValueAt(res.getString("workhouram"), 1, 1);
                    tableElement.setValueAt(res.getString("workhourpm"), 1, 2);
                }
                if (tmp.equalsIgnoreCase("Tue")) {
                    tableElement.setValueAt(res.getString("workhouram"), 2, 1);
                    tableElement.setValueAt(res.getString("workhourpm"), 2, 2);
                }
                if (tmp.equalsIgnoreCase("Wed")) {
                    tableElement.setValueAt(res.getString("workhouram"), 3, 1);
                    tableElement.setValueAt(res.getString("workhourpm"), 3, 2);
                }
                if (tmp.equalsIgnoreCase("Thu")) {
                    tableElement.setValueAt(res.getString("workhouram"), 4, 1);
                    tableElement.setValueAt(res.getString("workhourpm"), 4, 2);
                }
                if (tmp.equalsIgnoreCase("Fri")) {
                    tableElement.setValueAt(res.getString("workhouram"), 5, 1);
                    tableElement.setValueAt(res.getString("workhourpm"), 5, 2);
                }
                if (tmp.equalsIgnoreCase("Sat")) {
                    tableElement.setValueAt(res.getString("workhouram"), 6, 1);
                    tableElement.setValueAt(res.getString("workhourpm"), 6, 2);
                }
            }
            DB.db.close();
//            tableElement.fire
        } catch (SQLException ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // make it later
    public Vector<T> findChoosenStaff(String staffPos, String name) {

        Vector<T> st = new Vector();
        // ,\make it through calling the db
        int pos;
        if (staffPos.equalsIgnoreCase("gp")) {
            pos = 1;
        } else if (staffPos.equalsIgnoreCase("ns")) {
            pos = 2;
        } else if (staffPos.equalsIgnoreCase("ma")) {
            pos = 3;
        } else {
            pos = 4;
        }
        ////
        ResultSet rs = DB.db.staff(name, pos);
        try {
            while (rs.next()) {
                StaffInfo t = new StaffInfo();
                t.setFname(rs.getString("fname"));
                t.setLname(rs.getString("lname"));
                t.setAddress(rs.getString("address"));
                t.setEmail(rs.getString("email"));
                t.setPhone(rs.getString("phone"));
                t.setSsn(rs.getString("SSN"));
                t.setZip(rs.getString("zip"));
//                t.setbDate(rs.getDate("bdate").toString());
                ResultSet rss = DB.db.staffSchedule(rs.getInt("id"));
                while (rss.next()) {
                    ResultSet ress = DB.db.hospital(rss.getInt("hospid"));
                    while (ress.next()) {
                        t.setHospitals(ress.getString("name"));
                    }
                }
                rss = DB.db.qualification(rs.getInt("qualid"));
                while (rss.next()) {
                    t.setQualif(rss.getString("qualdesc"));
                }
                st.add((T) t);
            }
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(StartMedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return st;
    }

    //added 09.07 by Nadine
    public void fillSPatient(Vector<T> patient) {
        ResultSet rs = DB.db.patient();
        try {
            while (rs.next()) {
                PatientInfo p = new PatientInfo();
                p.setId(rs.getInt("id"));
                p.setFname(rs.getString("fname"));
                p.setLname(rs.getString("lname"));
                p.setBdate(rs.getDate("bDate"));
                p.setEmail(rs.getString("email"));
                p.setAddress(rs.getString("address"));
                p.setZip(rs.getString("zip"));
                p.setPhone(rs.getString("phone"));
                p.setMedcard(rs.getString("medcard"));
                p.setInsurrance(rs.getString("insurrance"));
                p.setSsn(rs.getString("ssn"));
                p.setAnam(rs.getString("anamnesis"));
                p.setDiagn(rs.getString("diagnosis"));
//                if (rs.getInt("posid") == id)
                patient.add((T) p);
            }
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(StartMedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //added 09.07 by Nadine

    public void patientList(Vector<PatientInfo> patient, JTextField tf, DefaultListModel list) {
        list.removeAllElements();
        Vector<StringBuffer> t = new Vector();
        for (int i = 0; i < patient.size(); i++) {
            if (patient.get(i).getLname().toLowerCase().startsWith(
                    tf.getText().toLowerCase())) {
                // preparing information for list
                StringBuffer tmp = new StringBuffer();
                tmp.append(patient.get(i).getLname() + ", ");
                tmp.append(patient.get(i).getFname().charAt(0) + ". (");
                tmp.append(patient.get(i).getBdate().toString() + ")");
                t.add(tmp);
            }
        }
        for (int i = 0; i < t.size(); i++) {
            list.add(i, t.get(i).toString());
        }
        DB.db.close();
    }

    //added by Nadine 18.07
       public void fillDrugs(Vector<T> drug) {
        ResultSet rs = DB.db.drugs();
        try {
            while (rs.next()) {
                DrugInfo p = new DrugInfo();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDose(rs.getString("dose"));
                p.setPrice(rs.getDouble("price"));
                p.setAvlb(rs.getString("avlb"));
                p.setRemburs(rs.getString("remburs"));
                p.setStoreid(rs.getInt("drugstoreid"));
                drug.add((T) p);
            }
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       //added 10/07 by Nadine
    public int patientInfo(Vector<PatientInfo> patient, String toMatch, JTextField[] tf, JTextPane tp, JTextPane[] patientTP) {
        int id = 0;
        StringBuffer info = new StringBuffer();

        try {
            int ind = -1;
            String lName = toMatch.substring(0, toMatch.indexOf(","));
            String dBirth = toMatch.substring(toMatch.indexOf("(") + 1, toMatch.indexOf(")"));
            for (int i = 0; i < patient.size(); i++) {
                if (patient.get(i).getLname().equalsIgnoreCase(lName) && patient.get(i).getBdate().toString().equalsIgnoreCase(dBirth)) {
                    ind = i;
                }
            }
            ResultSet res = DB.db.patient(patient.get(ind).getLname(), patient.get(ind).getBdate());

            info.append("<table>");

            while (res.next()) {
                //look at leo's part and call his function instead of this
                info.append("<tr><td>Patient: <b>");
                info.append(res.getString("lname"));
                info.append(", " + res.getString("fname"));
                info.append("</b></td></tr>");
                info.append("<tr><td>Phone: <b>");
                info.append(res.getString("phone") + "</b></td></tr>");
                info.append("<tr><td>Address: <b>");
                info.append(res.getString("address") + ", " + res.getString("zip") + "</b></td></tr>");
                info.append("<tr><td>Med card: <b>");
                info.append(res.getString("medcard") + "</b></td></tr>");
                info.append("<tr><td>Insurance: <b>");
                info.append(res.getString("insurrance") + "</b></td></tr>");
                info.append("<tr><td>SSN: <b>");
                info.append(res.getString("ssn") + "</b></td></tr>");
                info.append("<tr><td>E-mail: <b>");
                info.append(res.getString("email") + "</b></td></tr>");

                id = res.getInt("id");

                tf[0].setText(res.getString("lname") + " " + res.getString("fname"));
                tf[1].setText(res.getDate("bdate").toString());
                if (tp == null) {
                    if(patientTP  != null){
                    patientTP[2].setText(res.getString("anamnesis"));
                    patientTP[3].setText(res.getString("diagnosis"));

                    ResultSet rres = DB.db.prescription(id);
                    while (rres.next()) {
                        patientTP[1].setText(rres.getString("prescription"));
                    }
                }
                }
            }

            info.append("</table>");
            if (tp == null) {
                if(patientTP != null){
                patientTP[0].setText(info.toString());

                }
            } else {
                tp.setText(info.toString());

            }
            DB.db.close();

        } catch (SQLException ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    //added by Nadine 18.07
   public void drugsList(Vector<DrugInfo> drugs, JTextField tf, DefaultListModel list) {
        list.removeAllElements();
        Vector<StringBuffer> t = new Vector();
        for (int i = 0; i < drugs.size(); i++) {
            if (drugs.get(i).getName().toLowerCase().startsWith(
                    tf.getText().toLowerCase())) {
                // preparing information for list
                StringBuffer tmp = new StringBuffer();
                tmp.append(drugs.get(i).getName() + ", (");
                tmp.append(drugs.get(i).getDose().charAt(0) + ").");
                t.add(tmp);
            }
        }
        for (int i = 0; i < t.size(); i++) {
            list.add(i, t.get(i).toString());
        }

    }

   //added 22.07 by Nadine
    public int drugInfo(Vector<DrugInfo> drugs, String toMatch, JTextField tf, JTextArea ta){
        int drugId = 0;
        String drugname = "";
        try{
            int ind = -1;
            String drug = toMatch.substring(0, toMatch.indexOf(","));
            for(int i = 0; i< drugs.size(); i++){
                if(drugs.get(i).getName().equalsIgnoreCase(drug)){
                    ind = i;
                }
            }
            ResultSet res = DB.db.drugsbyName(drugs.get(ind).getName());

            if(ta != null){
            while(res.next()){
            drugId = res.getInt("id");

            tf.setText(res.getString("name"));
            drugname = res.getString("name")+" "+res.getString("dose");
            ta.append(drugname+"\n");
            }
            }
            DB.db.close();

        } catch (SQLException ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);

        }

        return drugId;
    }

    //added 17/07 by Valerii
    public int patientInfoMA(Vector<PatientInfo> patient, String toMatch, JTextField[] tf, JTextPane... patientTP) {
        int id = 0;

        try {
            int ind = -1;
            String lName = toMatch.substring(0, toMatch.indexOf(","));
            String dBirth = toMatch.substring(toMatch.indexOf("(") + 1, toMatch.indexOf(")"));
            for (int i = 0; i < patient.size(); i++) {
                if (patient.get(i).getLname().equalsIgnoreCase(lName) && patient.get(i).getBdate().toString().equalsIgnoreCase(dBirth)) {
                    ind = i;
                }
            }
            ResultSet res = DB.db.patient(patient.get(ind).getLname(), patient.get(ind).getBdate());
            //ResultSet res2 = DB.db.patient(scheduleID.get(ind).getLname(), patient.get(ind).getBdate());

            while (res.next()) {

                id = res.getInt("id");

                //Separate Text Fields
                tf[0].setText(res.getString("fname"));
                tf[1].setText(res.getString("lname"));
                tf[2].setText(res.getDate("bdate").toString());
                tf[3].setText(res.getString("email"));
                tf[4].setText(res.getString("address"));
                tf[5].setText(res.getString("zip"));
                tf[6].setText(res.getString("phone"));
                tf[7].setText(res.getString("insurrance"));
                tf[8].setText(res.getString("medcard"));
                tf[9].setText(res.getString("ssn"));

                //retrieving schedule id
                ResultSet rs = DB.db.schedule(null, res.getInt("id"));

                //retrieving tests
                while (rs.next()) {


                    ResultSet tres = DB.db.scheduleM(rs.getInt("id"), res.getInt("id"));

                    while (tres.next()) {
                        System.out.println(tres.getInt("testid"));
                        //tf[10].setText(tres.getString("res"));
                        }
                }
                //tf[10].setText(shres.getString("testid"));
//                  }
                //retrieving tests
//                  ResultSet tres = DB.db.tests(res.getInt("testid"));
////                  while(tres.next()){
////                        patientTP[2].setText(tres.getString("res"));
////                  }
//                  tf[10].setText(res.getString("res"));

                //Separate panes
                patientTP[0].setText(res.getString("anamnesis"));
                patientTP[1].setText(res.getString("diagnosis"));

                //Retrieving prescriptions
                ResultSet rres = DB.db.prescription(res.getInt("prescid"));
                while (rres.next()) {
                    patientTP[2].setText(rres.getString("prescription"));
                }



            }
            DB.db.close();

        } catch (SQLException ex) {
            Logger.getLogger(StartMedOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }//End patientInfoMA

    public void checkPatientSchedual(JTable table, PatientInfo patient){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM d, yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
        try {
            int id = patient.getId();
            ResultSet res = DB.db.schedule(null, id);
            while (res.next()) {
                String staff = null;
                Calendar date = Calendar.getInstance();
                Calendar appDate = Calendar.getInstance();
                appDate.setTime(res.getTimestamp("sdate"));
                ResultSet rs = DB.db.staff(res.getInt("staffid"));
                while(rs.next()){
                    staff = rs.getString("lname");
                    staff += ", "+rs.getString("fname");
                }
                if(appDate.after(date)){
                    String tmp = appDate.getTime().toString();
                    for(int i = 0; i < 7; i++){
                        if(table.getValueAt(i, 0).toString().contains(tmp.substring(0, 2))){
                            String t = table.getValueAt(i, 1).toString();
                            String tt = table.getValueAt(i, 2).toString();
                            String td = table.getValueAt(i, 3).toString();
                            if(t.isEmpty()){
                                t = dateFormat.format(appDate.getTime());
                                tt = timeFormat.format(appDate.getTime());
                                td = staff;
                            }
                            else {
                                t = t + " || "+dateFormat.format(appDate.getTime());
                                tt = tt + " || " + timeFormat.format(appDate.getTime());
                                td = td + " || " + staff;
                            }
                            table.setValueAt(t, i, 1);
                            table.setValueAt(tt, i, 2);
                            table.setValueAt(td, i, 3);
                        }
                    }
                }
            }
            DB.db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Func.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
