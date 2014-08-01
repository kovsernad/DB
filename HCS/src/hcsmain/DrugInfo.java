package hcsmain;

/**
 *
 * @author ContEd added by Nadine 18.07
 */
public class DrugInfo {
    private int id;
    private String name;
    private String dose;
    private double price;
    private String avlb;
    private String remburs;
    private String storeid;

    public String getStoreid() {
        return storeid;
    }

    public String getRemburs() {
        return remburs;
    }

    public void setRemburs(String remburs) {
        this.remburs = remburs;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }


    public String getAvlb() {
        return avlb;
    }

    public double getPrice() {
        return price;
    }

    public String getDose() {
        return dose;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvlb(String avlb) {
        this.avlb = avlb;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

}
