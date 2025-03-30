package com.ifsp.tavinho.financial_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ifsp.tavinho.financial_system.entity.AmortizationCalculator;
import com.ifsp.tavinho.financial_system.entity.CompoundInterestCalculator;

@SpringBootApplication
public class FinancialSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialSystemApplication.class, args);

		double principal = 10000; // empréstimo
        double rate = 0.02; // taxa de juros mensal
        int periods = 12; // quantidade de meses

		/* 1º Contexto -> Duas funções com dependência entre si */
        // generateAmortizationSchedule(principal, rate, periods);

		/* 2º Contexto -> Duas classes com dependência entre si, uma chama a outra diretamente por meio dos métodos */
        // AmortizationCalculator amortization = new AmortizationCalculator(principal, rate, periods);
		// amortization.generate(new CompoundInterestCalculator());

		/* 3º Contexto -> Conectores entre a interface requerida e fornecida (usando métodos) */
		CompoundInterestCalculator interestCalculator = new CompoundInterestCalculator();
		interestCalculator.connect(new AmortizationCalculator(principal, rate, periods));
	}

	/* 1º Contexto 
    public static double calculate(double principal, double rate, int periods) {
        return principal * (rate * Math.pow(1 + rate, periods)) / (Math.pow(1 + rate, periods) - 1);
    } */

	/* 1º Contexto
    public static void generateAmortizationSchedule(double principal, double rate, int periods) {
        double remainingBalance = principal;
        double fixedInstallment = calculate(principal, rate, periods);
        
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
}
