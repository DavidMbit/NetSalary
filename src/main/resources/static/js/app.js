const API_URL = "/api/salary/calculate";

const form = document.getElementById("salary-form");
const salaryInput = document.getElementById("gross-annual-salary");
const resultSection = document.getElementById("result-section");
const submitButton = form?.querySelector('button[type="submit"]');

const currencyFormatter = new Intl.NumberFormat("it-IT", {
    style: "currency",
    currency: "EUR",
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
});

const resultFields = {
    grossAnnualSalary: {
        id: "gross-annual-salary-result",
        sign: "normal"
    },
    averageMonthlyGrossSalary: {
        id: "average-monthly-gross-salary",
        sign: "normal"
    },
    employeeSocialSecurityContributions: {
        id: "employee-social-security-contributions",
        sign: "withholding"
    },
    taxableIncome: {
        id: "taxable-income",
        sign: "normal"
    },
    grossIrpef: {
        id: "gross-irpef",
        sign: "withholding"
    },
    employeeTaxDeductionBase: {
        id: "employee-tax-deduction-base",
        sign: "normal"
    },
    employeeTaxDeductionExtra: {
        id: "employee-tax-deduction-extra",
        sign: "normal"
    },
    additionalTaxDeduction: {
        id: "additional-tax-deduction",
        sign: "normal"
    },
    netIrpef: {
        id: "net-irpef",
        sign: "withholding"
    },
    lombardyRegionalTax: {
        id: "lombardy-regional-tax",
        sign: "withholding"
    },
    milanMunicipalTax: {
        id: "milan-municipal-tax",
        sign: "withholding"
    },
    totalLocalTaxes: {
        id: "total-local-taxes",
        sign: "withholding"
    },
    totalWithholdings: {
        id: "total-withholdings",
        sign: "withholding"
    },
    annualNetSalary: {
        id: "annual-net-salary",
        sign: "normal"
    },
    averageMonthlyNetSalary: {
        id: "average-monthly-net-salary",
        sign: "normal"
    }
};

if (form && salaryInput && submitButton) {
    form.addEventListener("submit", handleSalarySubmit);
}

async function handleSalarySubmit(event) {
    event.preventDefault();
    clearError();

    const grossAnnualSalary = readGrossAnnualSalary();
    if (!isValidSalary(grossAnnualSalary)) {
        showError("Inserisci una RAL valida e maggiore di zero.");
        hideResults();
        return;
    }

    setLoading(true);

    try {
        const result = await calculateSalary(grossAnnualSalary);
        updateResults(result);
        showResults();
    } catch (error) {
        hideResults();
        showError(error.message);
    } finally {
        setLoading(false);
    }
}

function readGrossAnnualSalary() {
    return Number(salaryInput.value);
}

function isValidSalary(value) {
    return Number.isFinite(value) && value > 0;
}

async function calculateSalary(grossAnnualSalary) {
    let response;

    try {
        response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify({ grossAnnualSalary })
        });
    } catch {
        throw new Error("Il server non e raggiungibile. Riprova tra qualche istante.");
    }

    if (!response.ok) {
        throw new Error("Non e stato possibile completare il calcolo. Controlla la RAL e riprova.");
    }

    try {
        return await response.json();
    } catch {
        throw new Error("La risposta del server non e valida. Riprova piu tardi.");
    }
}

function updateResults(result) {
    Object.entries(resultFields).forEach(([fieldName, config]) => {
        const element = document.getElementById(config.id);
        if (!element) {
            return;
        }

        element.textContent = formatMoney(result[fieldName], config.sign);
    });
}

function formatMoney(value, signType) {
    const numericValue = Number(value);

    if (!Number.isFinite(numericValue)) {
        return "N/D";
    }

    const formattedValue = currencyFormatter.format(Math.abs(numericValue));

    if (signType === "withholding" && numericValue !== 0) {
        return `- ${formattedValue}`;
    }

    return formattedValue;
}

function showResults() {
    if (resultSection) {
        resultSection.hidden = false;
    }
}

function hideResults() {
    if (resultSection) {
        resultSection.hidden = true;
    }
}

function setLoading(isLoading) {
    submitButton.disabled = isLoading;
    submitButton.textContent = isLoading ? "Calcolo..." : "Calcola netto";
}

function showError(message) {
    salaryInput.classList.add("is-invalid");
    salaryInput.setAttribute("aria-invalid", "true");

    const errorElement = getOrCreateErrorElement();
    errorElement.textContent = message;
}

function clearError() {
    salaryInput.classList.remove("is-invalid");
    salaryInput.removeAttribute("aria-invalid");

    const errorElement = document.getElementById("salary-input-error");
    if (errorElement) {
        errorElement.textContent = "";
    }
}

function getOrCreateErrorElement() {
    const existingErrorElement = document.getElementById("salary-input-error");
    if (existingErrorElement) {
        return existingErrorElement;
    }

    const errorElement = document.createElement("p");
    errorElement.id = "salary-input-error";
    errorElement.className = "error-message";
    errorElement.setAttribute("role", "alert");

    salaryInput.setAttribute("aria-describedby", errorElement.id);
    form.appendChild(errorElement);

    return errorElement;
}
