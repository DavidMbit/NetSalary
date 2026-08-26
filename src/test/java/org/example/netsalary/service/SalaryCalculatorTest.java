package org.example.netsalary.service;

import org.example.netsalary.calculator.ContributionCalculator;
import org.example.netsalary.calculator.DeductionCalculator;
import org.example.netsalary.calculator.IrpefCalculator;
import org.example.netsalary.calculator.LocalTaxCalculator;
import org.example.netsalary.model.SalaryInput;
import org.example.netsalary.model.SalaryResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalaryCalculatorTest {

    private SalaryCalculator salaryCalculator;

    @BeforeEach
    void setUp() {
        salaryCalculator = new SalaryCalculator(
                new ContributionCalculator(),
                new IrpefCalculator(),
                new DeductionCalculator(),
                new LocalTaxCalculator()
        );
    }

    @Test
    void calculatesGoldenCaseForGrossAnnualSalary35000() {
        SalaryResult result = calculate("35000.00");

        assertMoneyEquals("35000.00", result.getGrossAnnualSalary());
        assertMoneyEquals("3216.50", result.getEmployeeSocialSecurityContributions());
        assertMoneyEquals("31783.50", result.getTaxableIncome());
        assertMoneyEquals("7688.56", result.getGrossIrpef());
        assertMoneyEquals("1581.52", result.getEmployeeTaxDeductionBase());
        assertMoneyEquals("65.00", result.getEmployeeTaxDeductionExtra());
        assertMoneyEquals("1000.00", result.getAdditionalTaxDeduction());
        assertMoneyEquals("5042.04", result.getNetIrpef());
        assertMoneyEquals("454.98", result.getLombardyRegionalTax());
        assertMoneyEquals("254.27", result.getMilanMunicipalTax());
        assertMoneyEquals("709.25", result.getTotalLocalTaxes());
        assertMoneyEquals("8967.79", result.getTotalWithholdings());
        assertMoneyEquals("26032.21", result.getAnnualNetSalary());
        assertMoneyEquals("2169.35", result.getAverageMonthlyNetSalary());
    }

    @Test
    void calculatesMainValuesForGrossAnnualSalary20000() {
        SalaryResult result = calculate("20000.00");

        assertMoneyEquals("18162.00", result.getTaxableIncome());
        assertMoneyEquals("2810.56", result.getEmployeeTaxDeductionBase());
        assertMoneyEquals("0.00", result.getMilanMunicipalTax());
        assertMoneyEquals("1366.70", result.getNetIrpef());
        assertMoneyEquals("16560.84", result.getAnnualNetSalary());
        assertMoneyEquals("1380.07", result.getAverageMonthlyNetSalary());
    }

    @Test
    void calculatesMainValuesForGrossAnnualSalary50000() {
        SalaryResult result = calculate("50000.00");

        assertMoneyEquals("45405.00", result.getTaxableIncome());
        assertMoneyEquals("398.93", result.getEmployeeTaxDeductionBase());
        assertMoneyEquals("11784.72", result.getNetIrpef());
        assertMoneyEquals("1052.51", result.getTotalLocalTaxes());
        assertMoneyEquals("32567.77", result.getAnnualNetSalary());
        assertMoneyEquals("2713.98", result.getAverageMonthlyNetSalary());
    }

    @Test
    void calculatesMainValuesForGrossAnnualSalary60000() {
        SalaryResult result = calculate("60000.00");

        assertMoneyEquals("54486.00", result.getTaxableIncome());
        assertMoneyEquals("15628.98", result.getGrossIrpef());
        assertMoneyEquals("0.00", result.getEmployeeTaxDeductionBase());
        assertMoneyEquals("1281.80", result.getTotalLocalTaxes());
        assertMoneyEquals("37575.22", result.getAnnualNetSalary());
        assertMoneyEquals("3131.27", result.getAverageMonthlyNetSalary());
    }

    @Test
    void handlesTaxableIncomeBoundariesThroughGrossAnnualSalary() {
        SalaryResult taxableIncome15000 = calculate("16518.00");
        SalaryResult taxableIncome28000 = calculate("30833.61");
        SalaryResult taxableIncome50000 = calculate("55060.01");

        assertMoneyEquals("15000.00", taxableIncome15000.getTaxableIncome());
        assertMoneyEquals("1955.00", taxableIncome15000.getEmployeeTaxDeductionBase());
        assertMoneyEquals("184.50", taxableIncome15000.getLombardyRegionalTax());

        assertMoneyEquals("28000.00", taxableIncome28000.getTaxableIncome());
        assertMoneyEquals("1910.00", taxableIncome28000.getEmployeeTaxDeductionBase());
        assertMoneyEquals("389.90", taxableIncome28000.getLombardyRegionalTax());

        assertMoneyEquals("50000.00", taxableIncome50000.getTaxableIncome());
        assertMoneyEquals("0.00", taxableIncome50000.getEmployeeTaxDeductionBase());
        assertMoneyEquals("13700.00", taxableIncome50000.getGrossIrpef());
        assertMoneyEquals("768.30", taxableIncome50000.getLombardyRegionalTax());
    }

    private SalaryResult calculate(String grossAnnualSalary) {
        return salaryCalculator.calculate(new SalaryInput(new BigDecimal(grossAnnualSalary)));
    }

    private void assertMoneyEquals(String expected, BigDecimal actual) {
        assertEquals(0, new BigDecimal(expected).compareTo(actual));
    }
}
