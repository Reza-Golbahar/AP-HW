package models;

public class Discount {
    private int productIDAssignedTo;
    private int quantityOfProductsDiscounted;
    private int discountPercentage;

    public Discount(int productIDAssignedTo, int quantityOfProductsDiscounted, int discountPercentage) {
        this.productIDAssignedTo = productIDAssignedTo;
        this.quantityOfProductsDiscounted = quantityOfProductsDiscounted;
        this.discountPercentage = discountPercentage;
    }

    public int getProductIDAssignedTo() {
        return productIDAssignedTo;
    }

    public void setProductIDAssignedTo(int productIDAssignedTo) {
        this.productIDAssignedTo = productIDAssignedTo;
    }

    public int getQuantityOfProductsDiscounted() {
        return quantityOfProductsDiscounted;
    }

    public void setQuantityOfProductsDiscounted(int quantityOfProductsDiscounted) {
        this.quantityOfProductsDiscounted = quantityOfProductsDiscounted;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
