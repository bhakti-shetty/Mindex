/*
 * Author: Bhakti Shetty
 * */
package com.mindex.challenge.data;

import java.time.Instant;

public class Compensation {
    private Employee employee;
    private String salary;
    private Instant effectiveDate;

    public Compensation(){

    }

    public Compensation(Employee employee, String salary, Instant effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString() {
        return "Compensation{" +
                "employee=" + employee +
                ", salary='" + salary + '\'' +
                ", effectiveDate=" + effectiveDate +
                '}';
    }
}
