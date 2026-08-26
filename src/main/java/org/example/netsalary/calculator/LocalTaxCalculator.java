package org.example.netsalary.calculator;

import org.example.netsalary.config.TaxParameters2026;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class LocalTaxCalculator {

    public BigDecimal calculateLombardyRegionalTax(BigDecimal taxableIncome) {
        BigDecimal regionalTax = BigDecimal.ZERO;

        regionalTax = regionalTax.add(calculateBracketTax(
                taxableIncome,
                BigDecimal.ZERO,
                TaxParameters2026.LOMBARDY_REGIONAL_TAX_FIRST_BRACKET_LIMIT,
                TaxParameters2026.LOMBARDY_REGIONAL_TAX_FIRST_BRACKET_RATE
        ));

        regionalTax = regionalTax.add(calculateBracketTax(
                taxableIncome,
                TaxParameters2026.LOMBARDY_REGIONAL_TAX_FIRST_BRACKET_LIMIT,
                TaxParameters2026.LOMBARDY_REGIONAL_TAX_SECOND_BRACKET_LIMIT,
                TaxParameters2026.LOMBARDY_REGIONAL_TAX_SECOND_BRACKET_RATE
        ));

        regionalTax = regionalTax.add(calculateBracketTax(
                taxableIncome,
                TaxParameters2026.LOMBARDY_REGIONAL_TAX_SECOND_BRACKET_LIMIT,
                TaxParameters2026.LOMBARDY_REGIONAL_TAX_THIRD_BRACKET_LIMIT,
                TaxParameters2026.LOMBARDY_REGIONAL_TAX_THIRD_BRACKET_RATE
        ));

        if (taxableIncome.compareTo(TaxParameters2026.LOMBARDY_REGIONAL_TAX_THIRD_BRACKET_LIMIT) > 0) {
            BigDecimal fourthBracketIncome = taxableIncome
                    .subtract(TaxParameters2026.LOMBARDY_REGIONAL_TAX_THIRD_BRACKET_LIMIT);
            regionalTax = regionalTax.add(
                    fourthBracketIncome.multiply(TaxParameters2026.LOMBARDY_REGIONAL_TAX_FOURTH_BRACKET_RATE)
            );
        }

        return regionalTax.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateMilanMunicipalTax(BigDecimal taxableIncome) {
        if (taxableIncome.compareTo(TaxParameters2026.MILAN_MUNICIPAL_TAX_EXEMPTION_LIMIT) <= 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        return taxableIncome
                .multiply(TaxParameters2026.MILAN_MUNICIPAL_TAX_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateBracketTax(
            BigDecimal taxableIncome,
            BigDecimal bracketStart,
            BigDecimal bracketEnd,
            BigDecimal rate
    ) {
        if (taxableIncome.compareTo(bracketStart) <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal taxableAmountInBracket = min(taxableIncome, bracketEnd).subtract(bracketStart);
        return taxableAmountInBracket.multiply(rate);
    }

    private BigDecimal min(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) <= 0 ? first : second;
    }
}
