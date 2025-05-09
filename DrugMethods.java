import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DrugMethods {
    private int capacity;
    private double totalSales = 0;
    private ArrayList<DrugData> drugs = new ArrayList<>();

    public DrugMethods(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addDrug(String name, int id, double price, char category, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (findDrugById(id) != null) {
            throw new IllegalArgumentException("Drug with the specified ID already exists.");
        }
        if (capacity <= 0) {
            throw new IllegalStateException("No space in the pharmacy.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }

        DrugData drug = new DrugData(name, id, price, quantity, category);
        drugs.add(drug);
        capacity--;
        String operation = "Added drug: " + drug.getName();
        writeToTextFile(operation);
        writeToBinaryFile(operation);
    }

    public void placeOrder(int id, int quantity, boolean hasPrescription) {
        DrugData drug = findDrugById(id);
        if (drug == null) {
            throw new IllegalArgumentException("Drug with the specified ID was not found.");
        }

        if (drug.getCategory() == 'p' && !hasPrescription) {
            throw new IllegalStateException("This drug requires a prescription.");
        }

        double price = drug.getCategory() == 'c' ? drug.getPrice() * 1.2 : drug.getPrice();
        if (quantity > drug.getQuantity()) {
            throw new IllegalArgumentException("Insufficient quantity available.");
        }

        double totalPrice = price * quantity;
        totalSales += totalPrice;
        drug.setQuantity(drug.getQuantity() - quantity);

        String operation = "Placed order for drug with ID: " + drug.getId();
        writeToTextFile(operation);
        writeToBinaryFile(operation);
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void removeDrug(int id) {
        DrugData drug = findDrugById(id);
        if (drug == null) {
            throw new IllegalArgumentException("Drug with the specified ID was not found.");
        }

        drugs.remove(drug);
        capacity++;
        String operation = "Removed drug with ID: " + drug.getId();
        writeToTextFile(operation);
        writeToBinaryFile(operation);
    }

    public DrugData findDrugById(int id) {
        for (DrugData drug : drugs) {
            if (drug.getId() == id) {
                return drug;
            }
        }
        return null;
    }

    private void writeToTextFile(String operation) {
        try (FileWriter writer = new FileWriter("report.txt", true)) {
            writer.write(operation + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to text file: " + e.getMessage());
        }
    }

    private void writeToBinaryFile(String operation) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("report.dat", true))) {
            outputStream.writeObject(operation);
        } catch (IOException e) {
            System.err.println("Error writing to binary file: " + e.getMessage());
        }
    }
}
