package hcsmain;

/**
 *
 * @author ContEd added by Nadine 18.07
 */
public class PrescriptionInfo {
    private int id;
    private int patientid;
    private String prescription;
    private int drugid;

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getDrugid() {
        return drugid;
    }

    public String getPrescription() {
        return prescription;
    }

    public int getId() {
        return id;
    }

    public void setDrugid(int drugid) {
        this.drugid = drugid;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public void setId(int id) {
        this.id = id;
    }

}
