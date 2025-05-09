import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Use Swing's event-dispatching thread to ensure thread safety
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize the capacity of the pharmacy
                int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the capacity of the pharmacy:"));

                // Initialize the DrugMethods with the given capacity
                DrugMethods drugMethods = new DrugMethods(capacity);

                // Initialize the GUI with the drugMethods instance
                new PharmacyManagementGUI(drugMethods);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input for capacity. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
