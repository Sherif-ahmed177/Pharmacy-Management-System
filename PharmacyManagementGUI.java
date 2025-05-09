import java.awt.*;
import javax.swing.*;

public class PharmacyManagementGUI {
    private final DrugMethods drugMethods;

    public PharmacyManagementGUI(DrugMethods drugMethods) {
        this.drugMethods = drugMethods;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Pharmacy Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton addDrugButton = new JButton("Add Drug");
        JButton removeDrugButton = new JButton("Remove Drug");
        JButton placeOrderButton = new JButton("Place Order");
        JButton getTotalSalesButton = new JButton("Get Total Sales");
        JButton exitButton = new JButton("Exit");

        addDrugButton.addActionListener(e -> showAddDrugDialog());
        removeDrugButton.addActionListener(e -> showRemoveDrugDialog());
        placeOrderButton.addActionListener(e -> showPlaceOrderDialog());
        getTotalSalesButton.addActionListener(e -> showTotalSales());
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(addDrugButton);
        panel.add(removeDrugButton);
        panel.add(placeOrderButton);
        panel.add(getTotalSalesButton);
        panel.add(exitButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showAddDrugDialog() {
        try {
            String name = JOptionPane.showInputDialog("Enter drug name:");
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter drug ID:"));
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter drug price:"));
            char category = JOptionPane.showInputDialog("Enter drug category (c/p/o):").charAt(0);
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter available quantity:"));

            drugMethods.addDrug(name, id, price, category, quantity);
            JOptionPane.showMessageDialog(null, "Drug added successfully.");
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showRemoveDrugDialog() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter drug ID:"));
            drugMethods.removeDrug(id);
            JOptionPane.showMessageDialog(null, "Drug removed successfully.");
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showPlaceOrderDialog() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter drug ID:"));
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
            boolean hasPrescription = false;
            if (drugMethods.findDrugById(id).getCategory() == 'p') {
                int option = JOptionPane.showConfirmDialog(null, "Does the patient have a prescription?", "Prescription", JOptionPane.YES_NO_OPTION);
                hasPrescription = option == JOptionPane.YES_OPTION;
            }

            drugMethods.placeOrder(id, quantity, hasPrescription);
            JOptionPane.showMessageDialog(null, "Order placed successfully.");
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showTotalSales() {
        JOptionPane.showMessageDialog(null, "Total sales for today is: " + drugMethods.getTotalSales());
    }

    public static void main(String[] args) {
        // Create DrugMethods instance with initial capacity
        DrugMethods drugMethods = new DrugMethods(100); // Set initial capacity to 100, for example

        // Launch GUI
        SwingUtilities.invokeLater(() -> new PharmacyManagementGUI(drugMethods));
    }
}
