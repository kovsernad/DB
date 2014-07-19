/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hcsmain;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author ContEd Student
 */
public class Staff {

    private String fname;
    private String lname;
    private Date bDate;
    private String email;
    private String address;
    private String zip;
    private String phone;
    private String ssn;
    private String position;
    private String qualif;
    private Vector<String> hospitals = new Vector();

    public String getAddress() {
        return address;
    }

    public Date getbDate() {
        return bDate;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public Vector<String> getHospitals() {
        return hospitals;
    }

    public String getLname() {
        return lname;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position;
    }

    public String getQualif() {
        return qualif;
    }

    public String getSsn() {
        return ssn;
    }

    public String getZip() {
        return zip;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setHospitals(String hospitals) {
        this.hospitals.add(hospitals);
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setQualif(String qualif) {
        this.qualif = qualif;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }



}
