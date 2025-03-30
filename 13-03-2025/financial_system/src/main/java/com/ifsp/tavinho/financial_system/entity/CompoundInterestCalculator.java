package com.ifsp.tavinho.financial_system.entity;

import com.ifsp.tavinho.financial_system.repository.ICompoundInterestCalculator;

public class CompoundInterestCalculator implements ICompoundInterestCalculator {
    public double calculate(double principal, double rate, int periods) {
        return principal * (rate * Math.pow(1 + rate, periods)) / (Math.pow(1 + rate, periods) - 1);
    }

    /* 3º e 4º Contexto -> Conector entre a interface requerida e fornecida */
    public void connect(AmortizationCalculator component) {
        component.update(calculate(component.getPrincipal(), component.getRate(), component.getPeriods()));
    }
}
