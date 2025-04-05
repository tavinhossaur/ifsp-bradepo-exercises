package com.ifsp.tavinho.human_resources_management_system.entity;

import com.ifsp.tavinho.human_resources_management_system.repository.IPayrollService;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class PayrollService implements IPayrollService {
    
    private int entry;
    private int exit;
    private double hourlyRate;

    /* 2º Contexto -> Método que recebe o componente como dependência direta
    public void emit(WorkedHoursCalculator workedHoursCalculator) {
      int workedHours = workedHoursCalculator.calculateWorkedHours(entry, exit);

      System.out.println("\n[SYSTEM] Worked hours: " + workedHours);
      System.out.println("[SYSTEM] Payment: " + workedHours * hourlyRate);
    } */

    /* 3º Contexto -> Método recebe o valor fixo das parcelas calculado pelo outro método */
    public void emit(int workedHours) {
      System.out.println("\n[SYSTEM] Worked hours: " + workedHours);
      System.out.println("[SYSTEM] Payment: " + workedHours * hourlyRate);
    }

    /* 3º e 4º Contexto -> Conector entre a interface requerida e fornecida */
    @Override
    public void update(int workedHours) {
      this.emit(workedHours);
    }
}
