package com.ifsp.tavinho.ecommerce_system.entity;

import com.ifsp.tavinho.ecommerce_system.repository.IStockValidator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class StockValidator implements IStockValidator {    
    public boolean validateStockQuantity(int orderQuantity, int stockBalance) {
        System.out.println("\n[SYSTEM] User is ordering a total amount of " + orderQuantity);
        System.out.println("[SYSTEM] Current stock balance is: " + stockBalance);

        if (orderQuantity <= stockBalance) return true;

        System.err.println("[ERROR] Insufficient stock: The order can't be processed.");

        return false;
    }

    /* 3º e 4º Contexto -> Conector entre a interface requerida e fornecida */
    @Override
    public void connect(OrderPurchaseService orderPurchaseService) {
        orderPurchaseService.update(validateStockQuantity(orderPurchaseService.getOrderQuantity(), orderPurchaseService.getStockBalance()));
    }
}
