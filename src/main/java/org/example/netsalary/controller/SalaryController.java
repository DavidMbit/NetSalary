package org.example.netsalary.controller;

import org.example.netsalary.model.SalaryInput;
import org.example.netsalary.model.SalaryResult;
import org.example.netsalary.service.SalaryCalculator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    private final SalaryCalculator salaryCalculator;

    public SalaryController(SalaryCalculator salaryCalculator) {
        this.salaryCalculator = salaryCalculator;
    }

    @PostMapping("/calculate")
    public SalaryResult calculate(@RequestBody SalaryInput salaryInput) {
        return salaryCalculator.calculate(salaryInput);
    }
}
