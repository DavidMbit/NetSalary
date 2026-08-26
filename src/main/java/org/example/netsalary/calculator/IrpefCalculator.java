package org.example.netsalary.calculator;

import org.example.netsalary.config.TaxParameters2026;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class IrpefCalculator {

    public BigDecimal calculateGrossIrpef(BigDecimal taxableIncome) {
        BigDecimal grossIrpef = BigDecimal.ZERO;

        grossIrpef = grossIrpef.add(calculateBracketTax(
                taxableIncome,
                BigDecimal.ZERO,
                TaxParameters2026.IRPEF_FIRST_BRACKET_LIMIT,
                TaxParameters2026.IRPEF_FIRST_BRACKET_RATE
        ));

        grossIrpef = grossIrpef.add(calculateBracketTax(
                taxableIncome,
                TaxParameters2026.IRPEF_FIRST_BRACKET_LIMIT,
                TaxParameters2026.IRPEF_SECOND_BRACKET_LIMIT,
                TaxParameters2026.IRPEF_SECOND_BRACKET_RATE
        ));

        if (taxableIncome.compareTo(TaxParameters2026.IRPEF_SECOND_BRACKET_LIMIT) > 0) {
            BigDecimal thirdBracketIncome = taxableIncome.subtract(TaxParameters2026.IRPEF_SECOND_BRACKET_LIMIT);
            grossIrpef = grossIrpef.add(thirdBracketIncome.multiply(TaxParameters2026.IRPEF_THIRD_BRACKET_RATE));
        }

        return grossIrpef.setScale(2, RoundingMode.HALF_UP);
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
