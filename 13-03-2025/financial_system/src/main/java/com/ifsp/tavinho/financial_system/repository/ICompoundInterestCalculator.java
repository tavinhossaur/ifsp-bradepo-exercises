package com.ifsp.tavinho.financial_system.repository;

import com.ifsp.tavinho.financial_system.entity.AmortizationCalculator;

public interface ICompoundInterestCalculator {
    public void connect(AmortizationCalculator component);
}
