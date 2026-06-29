package models;

import java.util.ArrayList;

public class Order {
    int orderID;
    double totalPaid;
    Address address;
    ArrayList<CartItem> cartItemsOrdered = new ArrayList<>();

    public int getItemsOrderedCount() {
        int output = 0;
        for (CartItem cartItem : cartItemsOrdered) {
            output += cartItem.getQuantity();
        }

        return output;
    }

    public Order(int orderID, double totalPaid, Address address) {
        this.orderID = orderID;
        this.totalPaid = totalPaid;
        this.address = address;
    }

    public ArrayList<CartItem> getCartItemsOrdered() {
        return cartItemsOrdered;
    }

    public void setCartItemsOrdered(ArrayList<CartItem> cartItemsOrdered) {
        this.cartItemsOrdered = cartItemsOrdered;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
