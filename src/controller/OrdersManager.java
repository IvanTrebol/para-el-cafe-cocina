/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Chef;
import model.Cookbook;
import model.Order;
import model.recipe.Recipe;
import model.OrdersList;
import view.OrdersBoard;

/**
 *
 * @author jorge
 */
public final class OrdersManager extends Manager {
    private final OrdersBoard ordersBoard;
    private final Chef chef;
    
    
    public OrdersManager() {
        this.ordersBoard = new OrdersBoard();
        this.chef = new Chef();
        this.ordersBoard.setVisible(true);
        this.addActionListeners();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        Object eventSource = event.getSource();
        if(eventSource == this.ordersBoard.getButtonOk()){
            this.selectOrdersToBake();
        }
    }

    protected void addActionListeners() {
        this.ordersBoard.getButtonOk().addActionListener(this);
    }
    
    private void selectOrdersToBake() {
        OrdersList orders = this.ordersBoard.getProductionTable();
        for(int ordersCount = 0; ordersCount<orders.getRowCount();ordersCount++){
            boolean isProductSelected = (boolean)orders.getValueAt(ordersCount, OrdersList.SELECTION);
            if(isProductSelected){
                String productName = (String)orders.getValueAt(ordersCount, OrdersList.PRODUCT_NAME);
                int productQuantity = (int)orders.getValueAt(ordersCount, OrdersList.PRODUCT_QUANTITY);
                String dueDate = (String)orders.getValueAt(ordersCount, OrdersList.DUE_DATE);
                Order selectedOrder = new Order(productName, productQuantity, dueDate);
                this.chef.createIngredientsList(selectedOrder);
            }
        }
    }
    
}
