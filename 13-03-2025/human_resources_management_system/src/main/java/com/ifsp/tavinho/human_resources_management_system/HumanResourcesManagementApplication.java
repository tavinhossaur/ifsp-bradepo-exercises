package com.ifsp.tavinho.human_resources_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ifsp.tavinho.human_resources_management_system.entity.PayrollService;
import com.ifsp.tavinho.human_resources_management_system.entity.WorkedHoursCalculator;

@SpringBootApplication
public class HumanResourcesManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumanResourcesManagementApplication.class, args);

		int entry = 7; // início do expediente
        int exit = 17; // término do expediente
        double hourlyRate = 20; // valor da hora

		/* 1º Contexto -> Duas funções com dependência entre si */
		// emitPayroll(entry, exit, hourlyRate);

		/* 2º Contexto -> Duas classes com dependência entre si, uma chama a outra diretamente por meio dos métodos */
        // PayrollService payrollService = new PayrollService(entry, exit, hourlyRate);
		// payrollService.emitPayroll(new WorkedHoursCalculator());

		/* 3º Contexto -> Conectores entre a interface requerida e fornecida (usando métodos) */
		WorkedHoursCalculator workedHoursCalculator = new WorkedHoursCalculator();
		workedHoursCalculator.connect(new PayrollService(entry, exit, hourlyRate));
	}

	/* 1º Contexto 
	public static void emitPayroll(int entry, int exit, double hourlyRate) {
		int workedHours = calculateWorkedHours(entry, exit);

		System.out.println("\n[SYSTEM] Worked hours: " + workedHours);
		System.out.println("[SYSTEM] Payment: " + workedHours * hourlyRate);
	} */

	/* 1º Contexto 
	public static int calculateWorkedHours(int entry, int exit) {
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
	} */
}
