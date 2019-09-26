package com.example.mycustomdb;

public class Customer {

    private int id;
    private String name;
    private double salary;
    private String hire_date;

    public Customer(int id, String name, double salary, String hire_date) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hire_date = hire_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }
}
