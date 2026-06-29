package models;

public class CartItem {
    private final Product product;
    private int quantity;
    private int discountPercentage;
    private final boolean isDiscounted;

    public CartItem(Product product, int quantity, int discountPercentage, boolean isDiscounted) {
        this.product = product;
        this.quantity = quantity;
        this.discountPercentage = discountPercentage;
        this.isDiscounted = isDiscounted;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public double getPrice() {
        return product.getPrice() * (1 - (discountPercentage / 100.0));
    }
}
