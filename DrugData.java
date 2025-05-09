import java.io.Serializable;

public class DrugData implements Serializable {
    private String drugName;
    private int drugID;
    private double drugPrice;
    private int quantity;
    private char category;

    public DrugData(String name, int id, double price, int quantity, char category) {
        this.drugName = name;
        this.drugID = id;
        this.drugPrice = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return drugName;
    }

    public int getId() {
        return drugID;
    }

    public double getPrice() {
        return drugPrice;
    }

    public char getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Invalid quantity. The quantity cannot be negative.");
        }
    }
}
