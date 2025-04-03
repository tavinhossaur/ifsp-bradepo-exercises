package com.ifsp.tavinho.ecommerce_system.entity;

import com.ifsp.tavinho.ecommerce_system.repository.IOrderPurchaseService;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class OrderPurchaseService implements IOrderPurchaseService {

    private int orderQuantity;
    private int stockBalance;

    /* 2º Contexto -> Método recebe o componente como dependência direta 
    public void performPurchase(StockValidator stockValidator) {
        System.out.println("\n[SYSTEM] User is ordering a total amount of " + orderQuantity);
        
        if (stockValidator.validateStockQuantity(this.orderQuantity, this.stockBalance)) {
            this.stockBalance -= this.orderQuantity;

            System.out.println("[SYSTEM] Order completed successfully.");
            System.out.println("[SYSTEM] Stock balance updated -> Remaining stock: " + this.stockBalance);
        }
    } */

    /* 3º Contexto -> Método recebe a confirmação se o estoque possui quantidade suficiente */
    public void performPurchase(boolean hasAvailableStock) {        
        if (hasAvailableStock) {
            this.stockBalance -= this.orderQuantity;

            System.out.println("[SYSTEM] Order completed successfully.");
            System.out.println("[SYSTEM] Stock balance updated -> Remaining stock: " + this.stockBalance);
        }
    } 

    // /* 3º e 4º Contexto -> Conector entre a interface requerida e fornecida */
    @Override
    public void update(boolean hasAvailableStock) {
        this.performPurchase(hasAvailableStock);
    }
}