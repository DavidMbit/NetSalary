package org.example.netsalary.calculator;

import org.example.netsalary.config.TaxParameters2026;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DeductionCalculator {

    private static final BigDecimal EMPLOYEE_DEDUCTION_EXTRA_LOWER_INCOME_LIMIT = new BigDecimal("25000.00");
    private static final BigDecimal EMPLOYEE_DEDUCTION_EXTRA_UPPER_INCOME_LIMIT = new BigDecimal("35000.00");

    public BigDecimal calculateEmployeeTaxDeductionBase(BigDecimal taxableIncome) {
        BigDecimal deduction;

        if (taxableIncome.compareTo(TaxParameters2026.EMPLOYEE_DEDUCTION_FIRST_BRACKET_LIMIT) <= 0) {
            deduction = TaxParameters2026.EMPLOYEE_DEDUCTION_FIRST_BRACKET_AMOUNT;
        } else if (taxableIncome.compareTo(TaxParameters2026.EMPLOYEE_DEDUCTION_LOWER_INCOME_LIMIT) <= 0) {
            BigDecimal remainingIncomeRange = TaxParameters2026.EMPLOYEE_DEDUCTION_LOWER_INCOME_LIMIT
                    .subtract(taxableIncome);

            deduction = TaxParameters2026.EMPLOYEE_DEDUCTION_BASE_AMOUNT
                    .add(TaxParameters2026.EMPLOYEE_DEDUCTION_SECOND_BRACKET_ADDITIONAL_AMOUNT
                            .multiply(remainingIncomeRange)
                            .divide(
                                    TaxParameters2026.EMPLOYEE_DEDUCTION_SECOND_BRACKET_FORMULA_DENOMINATOR,
                                    2,
                                    RoundingMode.HALF_UP
                            ));
        } else if (taxableIncome.compareTo(TaxParameters2026.EMPLOYEE_DEDUCTION_UPPER_INCOME_LIMIT) <= 0) {
            BigDecimal remainingIncomeRange = TaxParameters2026.EMPLOYEE_DEDUCTION_UPPER_INCOME_LIMIT
                    .subtract(taxableIncome);

            deduction = TaxParameters2026.EMPLOYEE_DEDUCTION_BASE_AMOUNT
                    .multiply(remainingIncomeRange)
                    .divide(TaxParameters2026.EMPLOYEE_DEDUCTION_FORMULA_DENOMINATOR, 2, RoundingMode.HALF_UP);
        } else {
            deduction = BigDecimal.ZERO;
        }

        return max(deduction, BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateEmployeeTaxDeductionExtra(BigDecimal taxableIncome) {
        if (taxableIncome.compareTo(EMPLOYEE_DEDUCTION_EXTRA_LOWER_INCOME_LIMIT) > 0
                && taxableIncome.compareTo(EMPLOYEE_DEDUCTION_EXTRA_UPPER_INCOME_LIMIT) <= 0) {
            return TaxParameters2026.EMPLOYEE_DEDUCTION_EXTRA_AMOUNT.setScale(2, RoundingMode.HALF_UP);
        }

        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateAdditionalTaxDeduction(BigDecimal taxableIncome) {
        if (taxableIncome.compareTo(TaxParameters2026.ADDITIONAL_DEDUCTION_FIRST_INCOME_LIMIT) > 0
                && taxableIncome.compareTo(TaxParameters2026.ADDITIONAL_DEDUCTION_SECOND_INCOME_LIMIT) <= 0) {
            return TaxParameters2026.ADDITIONAL_DEDUCTION_FIXED_AMOUNT.setScale(2, RoundingMode.HALF_UP);
        }

        if (taxableIncome.compareTo(TaxParameters2026.ADDITIONAL_DEDUCTION_SECOND_INCOME_LIMIT) > 0
                && taxableIncome.compareTo(TaxParameters2026.ADDITIONAL_DEDUCTION_THIRD_INCOME_LIMIT) <= 0) {
            BigDecimal remainingIncomeRange = TaxParameters2026.ADDITIONAL_DEDUCTION_THIRD_INCOME_LIMIT
                    .subtract(taxableIncome);

            return TaxParameters2026.ADDITIONAL_DEDUCTION_FIXED_AMOUNT
                    .multiply(remainingIncomeRange)
                    .divide(
                            TaxParameters2026.ADDITIONAL_DEDUCTION_PROGRESSIVE_FORMULA_DENOMINATOR,
                            2,
                            RoundingMode.HALF_UP
                    );
        }

        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal max(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) >= 0 ? first : second;
    }
}
