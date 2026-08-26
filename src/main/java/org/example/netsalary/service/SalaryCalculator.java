package org.example.netsalary.service;

import org.example.netsalary.calculator.ContributionCalculator;
import org.example.netsalary.calculator.DeductionCalculator;
import org.example.netsalary.calculator.IrpefCalculator;
import org.example.netsalary.calculator.LocalTaxCalculator;
import org.example.netsalary.model.SalaryInput;
import org.example.netsalary.model.SalaryResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SalaryCalculator {

    private static final BigDecimal MONTHS_IN_YEAR = new BigDecimal("12");

    private final ContributionCalculator contributionCalculator;
    private final IrpefCalculator irpefCalculator;
    private final DeductionCalculator deductionCalculator;
    private final LocalTaxCalculator localTaxCalculator;

    public SalaryCalculator(
            ContributionCalculator contributionCalculator,
            IrpefCalculator irpefCalculator,
            DeductionCalculator deductionCalculator,
            LocalTaxCalculator localTaxCalculator
    ) {
        this.contributionCalculator = contributionCalculator;
        this.irpefCalculator = irpefCalculator;
        this.deductionCalculator = deductionCalculator;
        this.localTaxCalculator = localTaxCalculator;
    }

    public SalaryResult calculate(SalaryInput salaryInput) {
        BigDecimal grossAnnualSalary = money(salaryInput.getGrossAnnualSalary());

        BigDecimal employeeSocialSecurityContributions = contributionCalculator
                .calculateEmployeeContributions(grossAnnualSalary);

        BigDecimal taxableIncome = grossAnnualSalary
                .subtract(employeeSocialSecurityContributions)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal grossIrpef = irpefCalculator.calculateGrossIrpef(taxableIncome);

        BigDecimal employeeTaxDeductionBase = deductionCalculator.calculateEmployeeTaxDeductionBase(taxableIncome);
        BigDecimal employeeTaxDeductionExtra = deductionCalculator.calculateEmployeeTaxDeductionExtra(taxableIncome);
        BigDecimal additionalTaxDeduction = deductionCalculator.calculateAdditionalTaxDeduction(taxableIncome);

        BigDecimal totalTaxDeductions = employeeTaxDeductionBase
                .add(employeeTaxDeductionExtra)
                .add(additionalTaxDeduction)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal netIrpef = max(grossIrpef.subtract(totalTaxDeductions), BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal lombardyRegionalTax = localTaxCalculator.calculateLombardyRegionalTax(taxableIncome);
        BigDecimal milanMunicipalTax = localTaxCalculator.calculateMilanMunicipalTax(taxableIncome);

        BigDecimal totalLocalTaxes = lombardyRegionalTax
                .add(milanMunicipalTax)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal totalWithholdings = employeeSocialSecurityContributions
                .add(netIrpef)
                .add(totalLocalTaxes)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal annualNetSalary = grossAnnualSalary
                .subtract(employeeSocialSecurityContributions)
                .subtract(netIrpef)
                .subtract(totalLocalTaxes)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal averageMonthlyGrossSalary = grossAnnualSalary
                .divide(MONTHS_IN_YEAR, 2, RoundingMode.HALF_UP);

        BigDecimal averageMonthlyNetSalary = annualNetSalary
                .divide(MONTHS_IN_YEAR, 2, RoundingMode.HALF_UP);

        return new SalaryResult(
                grossAnnualSalary,
                averageMonthlyGrossSalary,
                employeeSocialSecurityContributions,
                taxableIncome,
                grossIrpef,
                employeeTaxDeductionBase,
                employeeTaxDeductionExtra,
                additionalTaxDeduction,
                netIrpef,
                lombardyRegionalTax,
                milanMunicipalTax,
                totalLocalTaxes,
                totalWithholdings,
                annualNetSalary,
                averageMonthlyNetSalary
        );
    }

    private BigDecimal money(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal max(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) >= 0 ? first : second;
    }
}
