package com.anton.gs.model.entity.order;

import com.anton.gs.model.entity.Entity;

import java.util.Objects;

public class Order extends Entity {
    private int orderId;
    private String email;
    private String genre;
    boolean isPaid;
    boolean isConfirmed;

    public Order() {

    }

    public Order(int orderId, String email, String genre, boolean isPaid, boolean isConfirmed) {
        this.orderId = orderId;
        this.email = email;
        this.genre = genre;
        this.isPaid = isPaid;
        this.isConfirmed = isConfirmed;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (isPaid != order.isPaid) return false;
        if (isConfirmed != order.isConfirmed) return false;
        if (email != null ? !email.equals(order.email) : order.email != null) return false;
        return genre != null ? genre.equals(order.genre) : order.genre == null;
    }

    @Override
    public int hashCode() {
        return orderId * 31;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", email='").append(email).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", isPaid=").append(isPaid);
        sb.append(", isConfirmed=").append(isConfirmed);
        sb.append('}');
        return sb.toString();
    }
}
