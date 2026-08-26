package org.example.netsalary.config;

import java.math.BigDecimal;

public final class TaxParameters2026 {

    private TaxParameters2026() {
    }

    public static final BigDecimal EMPLOYEE_SOCIAL_SECURITY_CONTRIBUTION_RATE = new BigDecimal("0.0919");

    public static final BigDecimal IRPEF_FIRST_BRACKET_LIMIT = new BigDecimal("28000.00");
    public static final BigDecimal IRPEF_SECOND_BRACKET_LIMIT = new BigDecimal("50000.00");
    public static final BigDecimal IRPEF_FIRST_BRACKET_RATE = new BigDecimal("0.23");
    public static final BigDecimal IRPEF_SECOND_BRACKET_RATE = new BigDecimal("0.33");
    public static final BigDecimal IRPEF_THIRD_BRACKET_RATE = new BigDecimal("0.43");

    public static final BigDecimal EMPLOYEE_DEDUCTION_LOWER_INCOME_LIMIT = new BigDecimal("28000.00");
    public static final BigDecimal EMPLOYEE_DEDUCTION_UPPER_INCOME_LIMIT = new BigDecimal("50000.00");
    public static final BigDecimal EMPLOYEE_DEDUCTION_FIRST_BRACKET_LIMIT = new BigDecimal("15000.00");
    public static final BigDecimal EMPLOYEE_DEDUCTION_FIRST_BRACKET_AMOUNT = new BigDecimal("1955.00");
    public static final BigDecimal EMPLOYEE_DEDUCTION_BASE_AMOUNT = new BigDecimal("1910.00");
    public static final BigDecimal EMPLOYEE_DEDUCTION_SECOND_BRACKET_ADDITIONAL_AMOUNT = new BigDecimal("1190.00");
    public static final BigDecimal EMPLOYEE_DEDUCTION_SECOND_BRACKET_FORMULA_DENOMINATOR = new BigDecimal("13000.00");
    public static final BigDecimal EMPLOYEE_DEDUCTION_FORMULA_DENOMINATOR = new BigDecimal("22000.00");
    public static final BigDecimal EMPLOYEE_DEDUCTION_EXTRA_AMOUNT = new BigDecimal("65.00");

    public static final BigDecimal ADDITIONAL_DEDUCTION_FIRST_INCOME_LIMIT = new BigDecimal("20000.00");
    public static final BigDecimal ADDITIONAL_DEDUCTION_SECOND_INCOME_LIMIT = new BigDecimal("32000.00");
    public static final BigDecimal ADDITIONAL_DEDUCTION_THIRD_INCOME_LIMIT = new BigDecimal("40000.00");
    public static final BigDecimal ADDITIONAL_DEDUCTION_FIXED_AMOUNT = new BigDecimal("1000.00");
    public static final BigDecimal ADDITIONAL_DEDUCTION_PROGRESSIVE_FORMULA_DENOMINATOR = new BigDecimal("8000.00");

    public static final BigDecimal LOMBARDY_REGIONAL_TAX_FIRST_BRACKET_LIMIT = new BigDecimal("15000.00");
    public static final BigDecimal LOMBARDY_REGIONAL_TAX_SECOND_BRACKET_LIMIT = new BigDecimal("28000.00");
    public static final BigDecimal LOMBARDY_REGIONAL_TAX_THIRD_BRACKET_LIMIT = new BigDecimal("50000.00");
    public static final BigDecimal LOMBARDY_REGIONAL_TAX_FIRST_BRACKET_RATE = new BigDecimal("0.0123");
    public static final BigDecimal LOMBARDY_REGIONAL_TAX_SECOND_BRACKET_RATE = new BigDecimal("0.0158");
    public static final BigDecimal LOMBARDY_REGIONAL_TAX_THIRD_BRACKET_RATE = new BigDecimal("0.0172");
    public static final BigDecimal LOMBARDY_REGIONAL_TAX_FOURTH_BRACKET_RATE = new BigDecimal("0.0173");

    public static final BigDecimal MILAN_MUNICIPAL_TAX_RATE = new BigDecimal("0.0080");
    public static final BigDecimal MILAN_MUNICIPAL_TAX_EXEMPTION_LIMIT = new BigDecimal("23000.00");

    public static final String LOMBARDY_REGIONAL_TAX_SOURCE_URL =
            "https://www.regione.lombardia.it/bollo-auto-e-tributi-regionali/red-addizionale-regionale-irpef";
    public static final String MILAN_MUNICIPAL_TAX_SOURCE_URL =
            "https://www.comune.milano.it/argomenti/tributi/addizionale-comunale-irpef";
}
