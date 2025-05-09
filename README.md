# Pharmacy Management System

A Java-based application for managing pharmacy inventory, orders, and sales with both console and GUI interfaces.

## Features

- **Drug Management**:
  - Add new drugs to inventory
  - Remove drugs from inventory
  - View current inventory
- **Order Processing**:
  - Place orders for available drugs
  - Handle prescription requirements for controlled substances
  - Automatic sales calculation with 20% markup for 'c' category drugs
- **Reporting**:
  - Track total sales
  - Log all operations to both text (`report.txt`) and binary (`report.dat`) files
- **User Interface**:
  - Swing-based GUI for easy interaction
  - Input validation and error handling

## Class Structure

### Main Classes

1. **Main** - Entry point of the application
2. **DrugData** - Represents drug information (name, ID, price, quantity, category)
3. **DrugMethods** - Core business logic for pharmacy operations
4. **PharmacyManagementGUI** - Graphical user interface for the system

## Installation & Usage

### Prerequisites
- Java JDK 8 or later
- Maven (for building)

### Running the Application

1. Clone the repository:
   ```bash
   git clone [your-repository-url]
   cd Pharmacy-Management-System

###File Structure

Pharmacy-Management-System/
├── src/
│   ├── Main.java                 - Application entry point
│   ├── DrugData.java             - Drug data model
│   ├── DrugMethods.java          - Pharmacy operations logic
│   ├── PharmacyManagementGUI.java - GUI implementation
├── report.txt                   - Text log of operations
├── report.dat                   - Binary log of operations
├── README.md                    - This file
└── project.iml                  - IntelliJ project file
