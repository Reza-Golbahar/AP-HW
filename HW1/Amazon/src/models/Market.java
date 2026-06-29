package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Market extends Logginable {
    private final String brand;
    private HashMap<Product, Integer> listOfProductsAndCount = new HashMap<>();
    private ArrayList<CartItem> productsSoldCount = new ArrayList<>();
    private ArrayList<Discount> discountsApplied = new ArrayList<>();
    private double costs = 0;

    public Market(String brand, String password, String emailAddress) {
        super(password, emailAddress);
        this.brand = brand;
    }

    public int getTotalSold(Product product) {
        int output = 0;
        for (CartItem cartItem : productsSoldCount) {
            if (cartItem.getProduct().getProductID() == product.getProductID())
                output += cartItem.getQuantity();
        }
        return output;
    }

    public void addProduct(Product newProduct, int count) {
        listOfProductsAndCount.put(newProduct, count);
        App.setNextProductToBeAddedID(App.getNextProductToBeAddedID() + 1);
        addCosts(newProduct.getProducerCost() * count);
    }

    public void addToProductSoldCount(CartItem cartItem) {
        for (CartItem item : productsSoldCount) {
            if (item.getProduct().getProductID() == cartItem.getProduct().getProductID() &&
                    item.getPrice() == cartItem.getPrice()) { //no need to check if both are discounted
                CartItem newCartItem = new CartItem(
                        cartItem.getProduct(),
                        cartItem.getQuantity() + item.getQuantity(),
                        cartItem.getDiscountPercentage(),
                        cartItem.isDiscounted()
                );
                productsSoldCount.remove(item);
                productsSoldCount.add(newCartItem);
                return;
            }
        }
        CartItem newCartItem = new CartItem(
                cartItem.getProduct(),
                cartItem.getQuantity(),
                cartItem.getDiscountPercentage(),
                cartItem.isDiscounted());
        productsSoldCount.add(newCartItem);
    }

    public double getCosts() {
        return costs;
    }

    public void addCosts(double costs) {
        this.costs += costs;
    }

    public double getRevenue() {
        double revenue = 0;
        for (CartItem cartItem : productsSoldCount) {
            revenue += cartItem.getQuantity() * cartItem.getPrice();
        }
        return revenue;
    }

    public ArrayList<Discount> getDiscountsApplied() {
        return discountsApplied;
    }

    public HashMap<Product, Integer> getListOfProductsAndCount() {
        return listOfProductsAndCount;
    }

    public void deleteAccount() {
        for (User user : App.getAllUsers()) {
            user.getProductsInCart().removeIf(cartItem ->
                    listOfProductsAndCount.containsKey(cartItem.getProduct()));
        }
    }

    public String getBrand() {
        return brand;
    }


    public Product getProductByID(int productID) {
        for (Product product : listOfProductsAndCount.keySet()) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }
}
