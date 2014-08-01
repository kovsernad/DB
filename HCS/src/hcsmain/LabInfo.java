package hcsmain;

/**
 *
 * @author ContEd Student
 */
public class LabInfo {
    private int id;
    private String labname;
    private String labaddress;
    private String labphone;

    public String getLabphone() {
        return labphone;
    }

    public String getLabname() {
        return labname;
    }

    public String getLabaddress() {
        return labaddress;
    }

    public int getId() {
        return id;
    }

    public void setLabphone(String labphone) {
        this.labphone = labphone;
    }

    public void setLabaddress(String labaddress) {
        this.labaddress = labaddress;
    }

    public void setLabname(String labname) {
        this.labname = labname;
    }

    public void setId(int id) {
        this.id = id;
    }



}
