package com.ifsp.tavinho.financial_system.entity;

import com.ifsp.tavinho.financial_system.repository.IAmortizationCalculator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class AmortizationCalculator implements IAmortizationCalculator {

    private double principal;
    private double rate;
    private int periods;

    /* 2º Contexto -> Método que recebe o componente como dependência direta
    public void generate(CompoundInterestCalculator interestCalculator) { 
        double remainingBalance = principal;

        double fixedInstallment = interestCalculator.calculate(principal, rate, periods);
        
        System.out.println("Amortization Schedule");
        System.out.printf("Fixed installment: $%.2f\n", fixedInstallment);
        System.out.println("------------------------------------------------");
        
        for (int i = 1; i <= periods; i++) {
            double interest = remainingBalance * rate;
            double amortization = fixedInstallment - interest;
            remainingBalance -= amortization;
            
            System.out.printf("Installment %d: Interest: $%.2f, Amortization: $%.2f, Remaining Balance: $%.2f\n", i, interest, amortization, remainingBalance);
        }
    } */

    /* 3º Contexto -> Método recebe o valor fixo das parcelas calculado pelo outro método */
    public void generate(double fixedInstallment) {
        double remainingBalance = principal;
        
        System.out.println("\n[SYSTEM] Amortization Schedule");
        System.out.printf("[SYSTEM] Fixed installment: $%.2f\n", fixedInstallment);
        System.out.println("------------------------------------------------");
        
        for (int i = 1; i <= periods; i++) {
            double interest = remainingBalance * rate;
            double amortization = fixedInstallment - interest;
            remainingBalance -= amortization;

            System.out.printf("[SYSTEM] Installment %d: Interest: $%.2f, Amortization: $%.2f, Remaining Balance: $%.2f\n", i, interest, amortization, remainingBalance);
        }
    }

    /* 3º e 4º Contexto -> Conector entre a interface requerida e fornecida */
    @Override
    public void update(double totalAmount) {
        this.generate(totalAmount);
    }
}
