package com.ifsp.tavinho.human_resources_management_system.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

import com.ifsp.tavinho.human_resources_management_system.repository.IWorkedHoursCalculator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class WorkedHoursCalculator implements IWorkedHoursCalculator {
    public int calculate(int entry, int exit) {
        if (exit < entry) exit += 24; // ajuste para turnos que passam da meia-noite
        
        int dailyHours = (exit - entry) - 1; // 1hr de almoço descontada
        int totalHours = 0;

		YearMonth currentYearMonth = YearMonth.now();
		int year = currentYearMonth.getYear();
		int month = currentYearMonth.getMonthValue();

        for (int day = 1; day <= LocalDate.of(year, month, 1).lengthOfMonth(); day++) {
            LocalDate date = LocalDate.of(year, month, day);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) totalHours += dailyHours; // sábados e domingos compensados
        }

        return totalHours;
    }

    /* 3º e 4º Contexto -> Conector entre a interface requerida e fornecida */
    @Override
    public void connect(PayrollService component) {
        component.update(calculate(component.getEntry(), component.getExit()));
    }
}
