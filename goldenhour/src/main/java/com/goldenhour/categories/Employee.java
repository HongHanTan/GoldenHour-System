package com.goldenhour.categories;

public class Employee {
    private String id;
    private String name;
    private String password;
    private String role; // Manager / Part-time / Full-time

    public Employee(String id, String name, String role, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public String toCSV() {
        return String.join(",", id, name, role, password);
    }

    public static Employee fromCSV(String line) {
        String[] data = line.split(",");
        for (int i = 0 ; i < data.length; i++) {
            data[i] = data[i].trim();
        }

        return new Employee(data[0], data[1], data[2], data[3]);
    }
}
