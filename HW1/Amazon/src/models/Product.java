package models;

import java.util.ArrayList;

public class Product {
    private final int productID;
    private final String name;
    private double price;
    private double producerCost;
    private String aboutThisItem;
    private ArrayList<Review> customerReviews = new ArrayList<>();
    private final Market market;


    public Product(int productID, String name, double price,
                   double producerCost, String aboutThisItem, Market market) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.producerCost = producerCost;
        this.aboutThisItem = aboutThisItem;
        this.market = market;

    }

    public ArrayList<Review> getCustomerReviews() {
        return customerReviews;
    }

    public void setCustomerReviews(ArrayList<Review> customerReviews) {
        this.customerReviews = customerReviews;
    }

    public Market getMarket() {
        return market;
    }

    public double getProducerCost() {
        return producerCost;
    }

    public void setProducerCost(double producerCost) {
        this.producerCost = producerCost;
    }

    public String getAboutThisItem() {
        return aboutThisItem;
    }

    public void setAboutThisItem(String aboutThisItem) {
        this.aboutThisItem = aboutThisItem;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        if (customerReviews.isEmpty())
            return 2.5;
        double newRating = 0;
        for (Review customerReview : customerReviews) {
            newRating += customerReview.getRating();
        }
        newRating /= customerReviews.size();
        return newRating;
    }

}
