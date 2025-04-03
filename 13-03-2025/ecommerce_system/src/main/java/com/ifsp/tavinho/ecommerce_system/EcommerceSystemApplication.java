package com.ifsp.tavinho.ecommerce_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ifsp.tavinho.ecommerce_system.entity.OrderPurchaseService;
import com.ifsp.tavinho.ecommerce_system.entity.StockValidator;

@SpringBootApplication
public class EcommerceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceSystemApplication.class, args);

		int stockBalance = 4723; // quantidade disponível em estoque
        int orderQuantity = 1285; // quantidade do pedido

		/* 1º Contexto -> Duas funções com dependência entre si */
		// processOrder(orderQuantity, stockBalance);

		/* 2º Contexto -> Duas classes com dependência entre si, uma chama a outra diretamente por meio dos métodos */
        // OrderPurchaseService orderPurchaseService = new OrderPurchaseService(orderQuantity, stockBalance);
		// orderPurchaseService.performPurchase(new StockValidator());

		/* 3º Contexto -> Conectores entre a interface requerida e fornecida (usando métodos) */
		StockValidator stockValidator = new StockValidator();
		stockValidator.connect(new OrderPurchaseService(orderQuantity, stockBalance));
	}

	/* 1º Contexto
	public static boolean validateStock(int orderQuantity, int stockBalance) {
		if (orderQuantity <= stockBalance) return true;
		return false;
	} */

	/* 1º Contexto
	public static void processOrder(int orderQuantity, int stockBalance) {
		System.out.println("\n[SYSTEM] User is ordering a total amount of " + orderQuantity);
		System.out.println("[SYSTEM] Current stock balance is: " + this.stockBalance);

		if (validateStock(orderQuantity, stockBalance)) {
			System.out.println("[SYSTEM] Order completed successfully.");
			stockBalance -= orderQuantity;
			System.out.println("[SYSTEM] Stock balance updated -> Remaining stock: " + stockBalance);
		} else System.err.println("[ERROR] Insufficient stock: The order can't be processed.");
	} */
}
