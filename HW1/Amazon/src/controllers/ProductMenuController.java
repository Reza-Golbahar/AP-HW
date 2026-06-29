package controllers;

import models.*;
import models.enums.Menu;

import java.util.ArrayList;

public class ProductMenuController {
    private int currentPage = 0;
    private ArrayList<Product> sortedProducts = new ArrayList<>();
    private String sortingCriteria1;

    public Result showProducts(String sortCriteria) {
        sortCriteria = sortCriteria.trim();

        sortingCriteria1 = sortCriteria;
        ArrayList<Product> listOfProducts = new ArrayList<>();
        for (Market market : App.getAllMarkets()) {
            listOfProducts.addAll(market.getListOfProductsAndCount().keySet());
        }

        // Sort based on criteria
        switch (sortCriteria) {
            case "rate":
                listOfProducts.sort((p1, p2) -> {
                    int ratingComparison = Double.compare(p2.getRating(), p1.getRating());
                    if (ratingComparison == 0) {
                        // If ratings are equal, sort by product ID (ascending)
                        return Integer.compare(p1.getProductID(), p2.getProductID());
                    }
                    return ratingComparison;
                });
                break;
            case "higher price to lower":
                listOfProducts.sort((p1, p2) -> {
                    int priceComparison = Double.compare(getDiscountedPrice(p2), getDiscountedPrice(p1));
                    if (priceComparison == 0) {
                        // If prices are equal, sort by product ID (ascending)
                        return Integer.compare(p1.getProductID(), p2.getProductID());
                    }
                    return priceComparison;
                });
                break;
            case "lower price to higher":
                listOfProducts.sort((p1, p2) -> {
                    int priceComparison = Double.compare(getDiscountedPrice(p1), getDiscountedPrice(p2));
                    if (priceComparison == 0) {
                        // If prices are equal, sort by product ID (ascending)
                        return Integer.compare(p1.getProductID(), p2.getProductID());
                    }
                    return priceComparison;
                });
                break;
            case "number of sold":
                listOfProducts.sort((p1, p2) -> {
                    int totalSold1 = 0, totalSold2 = 0;
                    for (Market market : App.getAllMarkets()) {
                        totalSold1 += market.getTotalSold(p1);
                        totalSold2 += market.getTotalSold(p2);
                    }
                    int totalSoldComparison = Integer.compare(totalSold2, totalSold1);
                    if (totalSoldComparison == 0) {
                        return Integer.compare(p1.getProductID(), p2.getProductID());
                    }
                    return Integer.compare(totalSold2, totalSold1);  // Sort in ascending order
                });
                break;
        }

        sortedProducts = listOfProducts;
        currentPage = 0;
        return displayPage();
    }

    public Result showNextProducts() {
        if ((currentPage + 1) * 10 >= sortedProducts.size()) {
            return new Result(false, "No more products available.");
        }
        currentPage++;
        return displayPage();
    }

    public Result showPastProducts() {
        if (currentPage == 0) {
            return new Result(false, "No more products available.");
        }
        currentPage--;
        return displayPage();
    }

    private Result displayPage() {
        if (sortedProducts.isEmpty()) {
            return new Result(false, "No products available.");
        }

        int start = currentPage * 10;
        int end = Math.min(start + 10, sortedProducts.size());

        StringBuilder result = new StringBuilder();
        String sortingCriteriaPrintable = sortingCriteria1.substring(0, 1).toUpperCase() +
                sortingCriteria1.substring(1);
        result.append("Product List (Sorted by: %s)\n------------------------------------------------\n"
                .formatted(sortingCriteriaPrintable));

        for (int i = start; i < end; i++) {
            Product product = sortedProducts.get(i);
            Market market = product.getMarket();
            boolean isSoldOut = market.getListOfProductsAndCount().get(product) == 0;
            boolean hasDiscount = false;
            Discount discountApplied = null;

            for (Discount discount : market.getDiscountsApplied()) {
                if (discount.getProductIDAssignedTo() == product.getProductID() && discount.getQuantityOfProductsDiscounted() > 0) {
                    hasDiscount = true;
                    discountApplied = discount;
                }
            }


            double discountedPrice = product.getPrice();
            if (discountApplied != null) {
                discountedPrice = product.getPrice() * (1 - discountApplied.getDiscountPercentage() / 100.0);
            }

            result.append("ID: %d%s\n".formatted(
                    product.getProductID(),
                    isSoldOut ? "  **(Sold out!)**" : (hasDiscount ?
                            "  **(On Sale! %d units discounted)**".formatted(discountApplied.getQuantityOfProductsDiscounted()) : "")
            ));

            result.append("Name: %s\nRate: %.1f/5\n".formatted(
                    product.getName(), product.getRating()
            ));

            if (hasDiscount) {
                double discountPercent = ((product.getPrice() - discountedPrice) / product.getPrice()) * 100;
                result.append("Price: ~$%.1f~ → $%.1f (-%.0f%%)\n".formatted(
                        product.getPrice(), discountedPrice, discountPercent
                ));
            } else {
                result.append("Price: $%.1f\n".formatted(product.getPrice()));
            }

            result.append("Brand: %s\nStock: %d\n------------------------------------------------\n".formatted(
                    market.getBrand(),
                    market.getListOfProductsAndCount().get(product)
            ));
        }

        result.append("(Showing %d-%d out of %d)\n".formatted(start + 1, start + 10, sortedProducts.size()));

        if (end < sortedProducts.size()) {
            result.append("Use `show next 10 products' to see more.\n");
        }

        return new Result(true, result.substring(0, result.length() - 1)); //escaping the last \n
    }

    private double getDiscountedPrice(Product product) {
        for (Market market : App.getAllMarkets()) {
            for (Discount discount : market.getDiscountsApplied()) {
                if (discount.getProductIDAssignedTo() == product.getProductID() && discount.getQuantityOfProductsDiscounted() > 0) {
                    return product.getPrice() * (1 - discount.getDiscountPercentage() / 100.0);
                }
            }
        }
        return product.getPrice();
    }

    public Result showInformation(String productID) {

        Product productUnderOperation = null;
        Market marketUnderOperation = null;
        for (Market market : App.getAllMarkets()) {
            for (Product product : market.getListOfProductsAndCount().keySet()) {
                if (product.getProductID() == Integer.parseInt(productID)) {
                    productUnderOperation = product;
                    marketUnderOperation = market;
                }
            }
        }

        if (productUnderOperation == null) {
            return new Result(false, "No product found.");
        }

        StringBuilder result = new StringBuilder();
        result.append("Product Details\n")
                .append("------------------------------------------------\n")
                .append("Name: %s".formatted(productUnderOperation.getName()));


        if (marketUnderOperation.getListOfProductsAndCount().get(productUnderOperation) == 0) {
            result.append("  **(Sold out!)**");
        }
        boolean isDiscounted = false;
        int discountApplied = 0;

        for (Discount discount : marketUnderOperation.getDiscountsApplied()) {
            if (discount.getProductIDAssignedTo() == Integer.parseInt(productID)) {
                if (discount.getQuantityOfProductsDiscounted() > 0) {
                    isDiscounted = true;
                    discountApplied = discount.getDiscountPercentage();
                    result.append("  **(On Sale! %d units discounted)**"
                            .formatted(discount.getQuantityOfProductsDiscounted()));
                    break;
                }
            }
        }

        result.append("\nID: %d\nRating: %.1f/5\n".formatted(
                Integer.parseInt(productID), productUnderOperation.getRating()
        ));

        if (isDiscounted) {
            result.append("Price: ~$%.1f~ → $%.1f (-%d%%)\n".formatted(
                    productUnderOperation.getPrice(),
                    (productUnderOperation.getPrice() * (1 - (discountApplied / 100.0))),
                    discountApplied
            ));
        } else {
            result.append("Price: $%.1f\n".formatted(productUnderOperation.getPrice()));
        }

        result.append("Brand: %s\n".formatted(marketUnderOperation.getBrand()))
                .append("Number of Products Remaining: %d\n".formatted(marketUnderOperation.getListOfProductsAndCount().get(productUnderOperation)))
                .append("About this item:\n")
                .append("%s\n\n".formatted(productUnderOperation.getAboutThisItem()));

        result.append("""
                Customer Reviews:
                ------------------------------------------------
                """);
        for (Review customerReview : productUnderOperation.getCustomerReviews()) {
            if (customerReview.getMessage() == null) {
                result.append("%s %s (%d/5)\n".formatted(
                        customerReview.getWriterFirstName(),
                        customerReview.getWriterLastName(),
                        customerReview.getRating()
                ));
            } else {
                result.append("%s %s (%d/5)\n\"%s\"\n".formatted(
                        customerReview.getWriterFirstName(),
                        customerReview.getWriterLastName(),
                        customerReview.getRating(),
                        customerReview.getMessage()
                ));
            }
            result.append("------------------------------------------------\n");
        }

        return new Result(true, result.substring(0, result.length() - 1));
    }

    public Result rateProduct(String number, String message, String id) {
        number = number.trim();

        Market marketUnderOperation = null;
        Product productUnderOperation = null;
        for (Market market : App.getAllMarkets()) {
            for (Product product : market.getListOfProductsAndCount().keySet()) {
                if (product.getProductID() == Integer.parseInt(id)) {
                    marketUnderOperation = market;
                    productUnderOperation = product;
                }
            }
        }

        if (productUnderOperation == null) {
            return new Result(false, "No product found.");
        }

        int numberCasted;
        try {
            numberCasted = Integer.parseInt(number);
        } catch (Exception e) {
            return new Result(false, "invalid command");
        }
        if (numberCasted < 1 || numberCasted > 5) {
            return new Result(false, "Rating must be between 1 and 5.");
        }

        if (!MainMenuController.checkUserLogin()) {
            return new Result(false, "You must be logged in to rate a product.");
        }
        User loggedInUser = (User) App.getLoggedInUserOrMarket();

        int oldQuantity = marketUnderOperation.getListOfProductsAndCount().get(productUnderOperation);
        marketUnderOperation.getListOfProductsAndCount().remove(productUnderOperation);

        productUnderOperation.getCustomerReviews().add(new Review(
                numberCasted, message, loggedInUser.getFirstName(), loggedInUser.getLastName()));

        marketUnderOperation.getListOfProductsAndCount().put(productUnderOperation, oldQuantity);
        return new Result(true, "Thank you! Your rating and review have been submitted successfully.");
    }


    public Result addToCart(String productID, String amount) {
        if (App.getLoggedInUserOrMarket() == null)
            return new Result(false, "You must be logged in to add items to your cart.");
        try {
            User user = (User) App.getLoggedInUserOrMarket();
        } catch (Exception e) {
            return new Result(false, "You must be logged in to add items to your cart.");
        }

        int amountCasted = Integer.parseInt(amount);
        int productIDCasted = Integer.parseInt(productID);

        Product productUnderOperation = App.getProductByID(productIDCasted);
        if (productUnderOperation == null) {
            return new Result(false, "No product found.");
        }
        Market marketUnderOperation = productUnderOperation.getMarket();

        if (amountCasted <= 0) {
            return new Result(false, "Quantity must be a positive number.");
        }

        if (marketUnderOperation.getListOfProductsAndCount().get(productUnderOperation) < amountCasted) {
            return new Result(false,
                    "Only %d units of \"%s\" are available.".formatted(
                            marketUnderOperation.getListOfProductsAndCount().get(productUnderOperation),
                            productUnderOperation.getName()
                    ));
        }

        int discountPercentage = 0;
        boolean isDiscounted = false;

        for (Discount discount : marketUnderOperation.getDiscountsApplied()) {
            if (discount.getProductIDAssignedTo() == productIDCasted) {
                if (discount.getQuantityOfProductsDiscounted() > 0) {
                    isDiscounted = true;
                    discountPercentage = discount.getDiscountPercentage();
                    discount.setQuantityOfProductsDiscounted(
                            discount.getQuantityOfProductsDiscounted() - amountCasted
                    );
                    break;
                }
            }
        }

        ((User) App.getLoggedInUserOrMarket()).addNewProductsToCart(
                productIDCasted, amountCasted, discountPercentage, isDiscounted
        );

        marketUnderOperation.getListOfProductsAndCount().put(productUnderOperation,
                marketUnderOperation.getListOfProductsAndCount().get(productUnderOperation) - amountCasted);

        return new Result(true, "\"%s\" (x%d) has been added to your cart.".formatted(
                productUnderOperation.getName(), amountCasted
        ));
    }


    public Result goBack() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Redirecting to the MainMenu ...");
    }

}
