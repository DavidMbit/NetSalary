package org.example.netsalary.calculator;

import org.example.netsalary.config.TaxParameters2026;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ContributionCalculator {

    public BigDecimal calculateEmployeeContributions(BigDecimal grossAnnualSalary) {
        return grossAnnualSalary
                .multiply(TaxParameters2026.EMPLOYEE_SOCIAL_SECURITY_CONTRIBUTION_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
