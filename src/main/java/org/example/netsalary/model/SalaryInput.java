package org.example.netsalary.model;

import java.math.BigDecimal;

public class SalaryInput {

    private BigDecimal grossAnnualSalary;

    public SalaryInput() {
    }

    public SalaryInput(BigDecimal grossAnnualSalary) {
        this.grossAnnualSalary = grossAnnualSalary;
    }

    public BigDecimal getGrossAnnualSalary() {
        return grossAnnualSalary;
    }

    public void setGrossAnnualSalary(BigDecimal grossAnnualSalary) {
        this.grossAnnualSalary = grossAnnualSalary;
    }
}
