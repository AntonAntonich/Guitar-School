package com.anton.gs.model.entity.order;

public final  class OrderCreator {
    private static OrderCreator orderCreator;

    private OrderCreator(){}

    public static OrderCreator getInstance(){
        if(orderCreator == null) {
            orderCreator = new OrderCreator();
        }
        return orderCreator;
    }

    public static Order creteOrder(int orderId, String email, String genre, boolean isPaid, boolean isConfirmed){
        Order order = new Order(orderId, email, genre, isPaid, isConfirmed);
        return order;
    }
}
