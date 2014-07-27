package hcsmain;

import java.util.Date;

/**
 *
 * @author ContEd Student
 */
public class TestInfo {
    private int id;
    private String res;
    private Date ardate;
    private Date depdate;
    private int labid;

    public int getLabid() {
        return labid;
    }

    public String getRes() {
        return res;
    }

    public Date getDepdate() {
        return depdate;
    }

    public Date getArdate() {
        return ardate;
    }

    public int getId() {
        return id;
    }

    public void setLabid(int labid) {
        this.labid = labid;
    }

    public void setDepdate(Date depdate) {
        this.depdate = depdate;
    }

    public void setArdate(Date ardate) {
        this.ardate = ardate;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public void setId(int id) {
        this.id = id;
    }

}
