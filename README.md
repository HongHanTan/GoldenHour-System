# Golden Hour Store Management System

A simple, console-based (as of now) inventory and sales system for store operations. It manages employees, stock, and outlets, storing all primary data in CSV files. 

---

## 🚀 Features

* **Authentication:** Secure employee login and registration.
* **Attendance:** Clock-in and clock-out tracking for employees.
* **Stock Management:** Track inventory with stock counts and stock movement.
* **Sales:** Process new sales and record them to CSV and text-based receipts.
* **Search Information:** 
* **Edit Information:**
* **Storage System:** All data is read from and saved to local `.csv` files.
* **Data Load State:**

---

## 🏗️ How It Works: Architecture

The application is split into several key packages, each with a distinct responsibility.

### Key Folder Overview

* **`main`**: The application's entry point (`Main.java`) that launches the UI.
* **`categories`**: Contain classes that represent for each object. (e.g., `Employee`, `Stock`).
* **`service`**: The feature logic. All operations (logging in, adding stock, making a sale) are handled by these classes.
* **`ui`**: The user-facing console interface. These classes handle user input and display.
* **`util`**: Helper classes, such as `TimeUtil`.
* **`data`**: (Directory) Contains all persistent data as `.csv` files.

### File Structure

```bash
GoldenHour-System/
└── goldenhour/                  ← Maven project root
    ├── pom.xml                  ← Maven configuration
    ├── src/main/java/com/goldenhour/
    │   ├── main/                ← Application entry point
    │   │   └── Main.java
    │   ├── categories/          ← Data models (POJOs)
    │   │   └── Employee.java
    │   │   └── Stock.java
    │   │   └── Outlet.java
    │   │   └── Sales.java
    │   ├── service/             ← Business logic
    │   │   └── AuthService.java
    │   │   └── RegistrationService.java
    │   │   └── AttendanceService.java
    │   │   └── SalesService.java
    │   │   └── StockCountService.java
    │   │   └── StockMovementService.java
    │   ├── ui/                  ← Console UI pages
    │   │   └── ConsoleUI.java
    │   │   └── StockUI.java
    │   │   └── SalesUI.java
    │   └── util/                ← Utility helpers
    │       └── TimeUtil.java
    └── data/                    ← CSV data storage
        └── employee.csv
        └── model.csv
        └── outlet.csv
        └── sales.csv

```
## 🔄 Key Workflows

Here’s how the components interact during common operations.

### 1. Application Start

1.  `Main.java` is executed.
2.  It creates an instance of `ConsoleUI`.
3.  `ConsoleUI` is run, which displays the main menu (e.g., "Login", "Register").

### 2. User Login/Logout + Registration

1.  **`ConsoleUI`**: Prompts the user for an ID and password.
2.  **`AuthService`**: The UI calls `authService.login(id, password)`.
3.  **`storage` (CSV)**: `AuthService` reads `data/employee.csv` to find a matching user.
4.  **`Employee.java`**: The `Employee.fromCSV()` static method is used to turn each CSV line into an `Employee` object.
5.  **`AuthService`**: Compares the user's input to the list of `Employee` objects to find a match.
6.  **`ConsoleUI`**: Receives a success/failure response and either proceeds to the main menu or shows an error.

### 3. Attendance Log


### 4. Stock Management System

1.  **`StockUI`**: The user selects the service from a stock system
2.  **`Morning/Night Stock Count`**: System proceed to `StockCountService.performStockCount(...)` 
3. **`Stock In/Stock Out`** : The 
3.  **`storage` (CSV)**: The service updates `data/model.csv` (to decrease stock) and appends a new line to `data/sales.csv`using `CSVHandler`
4.  **`storage` (Receipt)**: The service also appends a human-readable receipt to a `.txt` file using `ReceiptHandler`

### 4. Sales System

### 5. Search Information

### 6. Edit Information

### 7. Storage System

### 8. Data Load State

---

## ⛓️ Component Interaction Flow 
### Concept : Classes & Object (OOP)

This is a more detailed, class-by-class flow of how components call each other:

* **`Main` → `ConsoleUI`**: The program starts in `Main.java`, which delegates all interactive flows to the `ConsoleUI`.
* **`ConsoleUI` → `AuthService`**: Login prompts are handled by `ConsoleUI`, which calls `AuthService` to verify credentials.
* **`ConsoleUI` → `Service Classes`**: All main actions (e.g., "Stock Management", "Sales Service") call the corresponding service class (e.g., `StockCountService`, `StockMovementService`).
* **`AuthService` / `Services` → `CSVHandler`**: The service classes load data by calling the `CSVHandler` (e.g., `readEmployees`, `readStock`, `writeStock`).
* **`CSVHandler` → Models (`Employee`, `Stock`... )**: The `CSVHandler` uses static factory methods (like `Employee.fromCSV()`) to parse individual CSV rows into data objects.

## 📊 Data Flow
### Concept : IO handling

All data operations in this application follow a consistent, two-way pattern. A `Service` class always acts as the controller, which then uses a model-specific method (like `.fromCSV()` or `.toCSV()`) to handle the data conversion.

---

### Reading from CSV (Text to Object)

This flow is used for actions like logging in or loading all stock at startup.

1.  **Action Triggered:** A user attempts to log in.
2.  **`Service` Class:** The request is sent to the assigned service (e.g., `AuthService`).
3.  **Call Method:** The service calls a "read" method (e.g., `readEmployees()`).
4.  **Conversion Method:** This method reads the `.csv` file and uses a static `.fromCSV()` method (e.g., `Employee.fromCSV(line)`) to parse each line of text.
5.  **Result:** The raw text is converted into a Java `Object` (or a list of objects) for the application to use.

**Example Flow:**
`Employee.csv` → `AuthService` → `readEmployees()` → `Employee.fromCSV()` → `Employee` (Object)

---

### Writing to CSV (Object to Text)

This flow is used for actions like saving a sale, registering a new user, or updating stock quantities.

1.  **Action Triggered:** A user moves stock from one outlet to another.
2.  **`Service` Class:** The request is handled by the assigned service (e.g., `StockMovementService`), which now has the updated data as a Java `Object` (e.g., a `Stock` object).
3.  **Call Method:** The service calls a "write" method (e.g., `writeStock()`).
4.  **Conversion Method:** This method calls a `.toCSV()` instance method (e.g., `stock.toCSV()`) to format the object's data into a comma-separated string.
5.  **Result:** The string is written as a new line (or overwrites the file) in the corresponding `.csv` file, saving the changes. <br>
**Example Flow:**
`Stock` (Object) → `StockMovementService` → `writeStock()` → `stock.toCSV()` → `model.csv`
---



## 🤝 How to Contribute

**please follow these steps:**
1.  **Fork** the repository.
2.  **Clone your forked repo** to local machine

`Note: Step 1 & 2 are one-time setup, only need to be done once`

3.  Create a new branch (`git checkout -b feature/your name`).
4.  Make your changes.
5.  Stage your changes (`git add .`)
6.  Commit your changes (`git commit -m 'Add some feature'`).
7.  Push to the branch (`git push origin feature/your name`).
8.  Create a new pull Request.