package hcsmain;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author ContEd Student
 * Vanier College
 * DBProject
 * Course Project
 * @Project HCS @Class PatientInfo
 */
public class PatientInfo {

    private int id;
    private String fname;
    private String lname;
    private Date bdate;
    private String email;
    private String address;
    private String zip;
    private String phone;
    private String medcard;
    private String insurrance;
    private String ssn;
    private String anam;
    private String diagn;
    private String gmedhistory;
    private String illnesshistory;
    private String medspechistory;
    private String prescriptions;
    private Vector<String> drugs;

    public Vector<String> getDrugs() {
        return drugs;
    }

    public void setDrugs(Vector<String> drugs) {
        this.drugs = drugs;
    }


    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getMedspechistory() {
        return medspechistory;
    }

    public String getIllnesshistory() {
        return illnesshistory;
    }

    public String getGmedhistory() {
        return gmedhistory;
    }

    public void setMedspechistory(String medspechistory) {
        this.medspechistory = medspechistory;
    }

    public void setIllnesshistory(String illnesshistory) {
        this.illnesshistory = illnesshistory;
    }

    public void setGmedhistory(String gmedhistory) {
        this.gmedhistory = gmedhistory;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMedcard(String medcard) {
        this.medcard = medcard;
    }

    public void setInsurrance(String insurrance) {
        this.insurrance = insurrance;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setAnam(String anam) {
        this.anam = anam;
    }

    public void setDiagn(String diagn) {
        this.diagn = diagn;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getBdate() {
        return bdate;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getMedcard() {
        return medcard;
    }

    public String getInsurrance() {
        return insurrance;
    }

    public String getSsn() {
        return ssn;
    }

    public String getAnam() {
        return anam;
    }

    public String getDiagn() {
        return diagn;
    }

}
