package org.example.netsalary.model;

import java.math.BigDecimal;

public class SalaryResult {

    private BigDecimal grossAnnualSalary;
    private BigDecimal averageMonthlyGrossSalary;
    private BigDecimal employeeSocialSecurityContributions;
    private BigDecimal taxableIncome;
    private BigDecimal grossIrpef;
    private BigDecimal employeeTaxDeductionBase;
    private BigDecimal employeeTaxDeductionExtra;
    private BigDecimal additionalTaxDeduction;
    private BigDecimal netIrpef;
    private BigDecimal lombardyRegionalTax;
    private BigDecimal milanMunicipalTax;
    private BigDecimal totalLocalTaxes;
    private BigDecimal totalWithholdings;
    private BigDecimal annualNetSalary;
    private BigDecimal averageMonthlyNetSalary;

    public SalaryResult() {
    }

    public SalaryResult(
            BigDecimal grossAnnualSalary,
            BigDecimal averageMonthlyGrossSalary,
            BigDecimal employeeSocialSecurityContributions,
            BigDecimal taxableIncome,
            BigDecimal grossIrpef,
            BigDecimal employeeTaxDeductionBase,
            BigDecimal employeeTaxDeductionExtra,
            BigDecimal additionalTaxDeduction,
            BigDecimal netIrpef,
            BigDecimal lombardyRegionalTax,
            BigDecimal milanMunicipalTax,
            BigDecimal totalLocalTaxes,
            BigDecimal totalWithholdings,
            BigDecimal annualNetSalary,
            BigDecimal averageMonthlyNetSalary
    ) {
        this.grossAnnualSalary = grossAnnualSalary;
        this.averageMonthlyGrossSalary = averageMonthlyGrossSalary;
        this.employeeSocialSecurityContributions = employeeSocialSecurityContributions;
        this.taxableIncome = taxableIncome;
        this.grossIrpef = grossIrpef;
        this.employeeTaxDeductionBase = employeeTaxDeductionBase;
        this.employeeTaxDeductionExtra = employeeTaxDeductionExtra;
        this.additionalTaxDeduction = additionalTaxDeduction;
        this.netIrpef = netIrpef;
        this.lombardyRegionalTax = lombardyRegionalTax;
        this.milanMunicipalTax = milanMunicipalTax;
        this.totalLocalTaxes = totalLocalTaxes;
        this.totalWithholdings = totalWithholdings;
        this.annualNetSalary = annualNetSalary;
        this.averageMonthlyNetSalary = averageMonthlyNetSalary;
    }

    public BigDecimal getGrossAnnualSalary() {
        return grossAnnualSalary;
    }

    public void setGrossAnnualSalary(BigDecimal grossAnnualSalary) {
        this.grossAnnualSalary = grossAnnualSalary;
    }

    public BigDecimal getAverageMonthlyGrossSalary() {
        return averageMonthlyGrossSalary;
    }

    public void setAverageMonthlyGrossSalary(BigDecimal averageMonthlyGrossSalary) {
        this.averageMonthlyGrossSalary = averageMonthlyGrossSalary;
    }

    public BigDecimal getEmployeeSocialSecurityContributions() {
        return employeeSocialSecurityContributions;
    }

    public void setEmployeeSocialSecurityContributions(BigDecimal employeeSocialSecurityContributions) {
        this.employeeSocialSecurityContributions = employeeSocialSecurityContributions;
    }

    public BigDecimal getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(BigDecimal taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public BigDecimal getGrossIrpef() {
        return grossIrpef;
    }

    public void setGrossIrpef(BigDecimal grossIrpef) {
        this.grossIrpef = grossIrpef;
    }

    public BigDecimal getEmployeeTaxDeductionBase() {
        return employeeTaxDeductionBase;
    }

    public void setEmployeeTaxDeductionBase(BigDecimal employeeTaxDeductionBase) {
        this.employeeTaxDeductionBase = employeeTaxDeductionBase;
    }

    public BigDecimal getEmployeeTaxDeductionExtra() {
        return employeeTaxDeductionExtra;
    }

    public void setEmployeeTaxDeductionExtra(BigDecimal employeeTaxDeductionExtra) {
        this.employeeTaxDeductionExtra = employeeTaxDeductionExtra;
    }

    public BigDecimal getAdditionalTaxDeduction() {
        return additionalTaxDeduction;
    }

    public void setAdditionalTaxDeduction(BigDecimal additionalTaxDeduction) {
        this.additionalTaxDeduction = additionalTaxDeduction;
    }

    public BigDecimal getNetIrpef() {
        return netIrpef;
    }

    public void setNetIrpef(BigDecimal netIrpef) {
        this.netIrpef = netIrpef;
    }

    public BigDecimal getLombardyRegionalTax() {
        return lombardyRegionalTax;
    }

    public void setLombardyRegionalTax(BigDecimal lombardyRegionalTax) {
        this.lombardyRegionalTax = lombardyRegionalTax;
    }

    public BigDecimal getMilanMunicipalTax() {
        return milanMunicipalTax;
    }

    public void setMilanMunicipalTax(BigDecimal milanMunicipalTax) {
        this.milanMunicipalTax = milanMunicipalTax;
    }

    public BigDecimal getTotalLocalTaxes() {
        return totalLocalTaxes;
    }

    public void setTotalLocalTaxes(BigDecimal totalLocalTaxes) {
        this.totalLocalTaxes = totalLocalTaxes;
    }

    public BigDecimal getTotalWithholdings() {
        return totalWithholdings;
    }

    public void setTotalWithholdings(BigDecimal totalWithholdings) {
        this.totalWithholdings = totalWithholdings;
    }

    public BigDecimal getAnnualNetSalary() {
        return annualNetSalary;
    }

    public void setAnnualNetSalary(BigDecimal annualNetSalary) {
        this.annualNetSalary = annualNetSalary;
    }

    public BigDecimal getAverageMonthlyNetSalary() {
        return averageMonthlyNetSalary;
    }

    public void setAverageMonthlyNetSalary(BigDecimal averageMonthlyNetSalary) {
        this.averageMonthlyNetSalary = averageMonthlyNetSalary;
    }
}
