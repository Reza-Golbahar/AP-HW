package controllers;

import models.*;
import models.enums.Menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StoreMenuController {

    public Result addProduct(String name, String producerCost,
                             String price, String aboutThisItem, String numberOfProductsToSell) {
        int numberOfProductsToBeAddedCasted = Integer.parseInt(numberOfProductsToSell);
        double priceCasted, producerCostCasted;

        try {
            producerCostCasted = Double.parseDouble(producerCost);
            priceCasted = Double.parseDouble(price);
        } catch (Exception e) {
            return new Result(false, "invalid command");
        }
        if (producerCostCasted > priceCasted) {
            return new Result(false, "Selling price must be greater than or equal to the producer cost.");
        } else if (numberOfProductsToBeAddedCasted <= 0) {
            return new Result(false, "Number of products must be a positive number.");
        }

        Product newProduct = new Product(App.getNextProductToBeAddedID(),
                name, priceCasted, producerCostCasted, aboutThisItem,
                (Market) App.getLoggedInUserOrMarket());

        ((Market) App.getLoggedInUserOrMarket()).addProduct(newProduct, numberOfProductsToBeAddedCasted);

        return new Result(true, "Product \"%s\" has been added successfully with ID %s.".formatted(
                name, newProduct.getProductID()));
    }


    public Result applyDiscount(String productID, String discountPercentage, String quantity) {
        int productIDCasted = Integer.parseInt(productID);
        int quantityCasted = Integer.parseInt(quantity);
        int discountPercentageCasted = Integer.parseInt(discountPercentage);

        if (discountPercentageCasted < 1 || discountPercentageCasted > 100) {
            return new Result(false, "Discount percentage must be between 1 and 100.");
        }
        Product productUnderOperation = null;

        for (Product product : ((Market) App.getLoggedInUserOrMarket()).getListOfProductsAndCount().keySet()) {
            if (product.getProductID() == productIDCasted) {
                productUnderOperation = product;
            }
        }

        if (productUnderOperation == null) {
            return new Result(false, "No product found.");
        } else if (((Market) App.getLoggedInUserOrMarket()).getListOfProductsAndCount().get(productUnderOperation) < quantityCasted) {
            return new Result(false, "Oops! Not enough stock to apply the discount to that many items.");
        }

        ((Market) App.getLoggedInUserOrMarket()).getDiscountsApplied().add(new Discount(
                productIDCasted, quantityCasted, discountPercentageCasted));

        return new Result(true, "A %d".formatted(discountPercentageCasted) + "% " +
                "discount has been applied to %d units of product ID %d.".formatted(
                        quantityCasted, productIDCasted));
    }


    public Result showProfit() {
        double revenue = ((Market) App.getLoggedInUserOrMarket()).getRevenue();
        double costs = ((Market) App.getLoggedInUserOrMarket()).getCosts();
        return new Result(true, "Total Profit: $%.1f\n(Revenue: $%.1f - Costs: $%.1f)".formatted(
                revenue - costs, revenue, costs
        ));
    }


    public Result showListOfProducts() {
        if (((Market) App.getLoggedInUserOrMarket()).getListOfProductsAndCount().isEmpty()) {
            return new Result(false, "No products available in the store.");
        }

        // Sort products by productID (lowest to greatest)
        List<Product> sortedProducts = new ArrayList<>(
                ((Market) App.getLoggedInUserOrMarket()).getListOfProductsAndCount().keySet());
        sortedProducts.sort(Comparator.comparingInt(Product::getProductID));

        StringBuilder result = new StringBuilder();
        result.append("""
                Store Products (Sorted by date added)
                ------------------------------------------------
                """
        );

        for (Product product : sortedProducts) {
            boolean isProductDiscounted = false;
            int discountAppliedToThisProduct = 0;
            result.append("ID: %d".formatted(product.getProductID()));

            // Check if the product is sold out
            if (((Market) App.getLoggedInUserOrMarket()).getListOfProductsAndCount().get(product) == 0) {
                result.append("  (**Sold out!**)");
            }

            // Apply discounts if available
            for (Discount discount : ((Market) App.getLoggedInUserOrMarket()).getDiscountsApplied()) {
                if (discount.getProductIDAssignedTo() == product.getProductID()) {
                    if (discount.getQuantityOfProductsDiscounted() > 0) {
                        result.append("  **(On Sale! %d units discounted)**".formatted(discount.getQuantityOfProductsDiscounted()));
                        isProductDiscounted = true;
                        discountAppliedToThisProduct = discount.getDiscountPercentage();
                    }
                }
            }

            result.append("\nName: %s".formatted(product.getName()));

            // Show the price with discount if applicable
            if (isProductDiscounted) {
                result.append("\nPrice: ~$%.1f~ → $%.1f (-%d%%)\n".formatted(
                        product.getPrice(),
                        product.getPrice() * (1 - (discountAppliedToThisProduct / 100.0)),
                        discountAppliedToThisProduct
                ));
            } else {
                result.append("\nPrice: $%.1f\n".formatted(product.getPrice()));
            }

            // Get the count of products available and sold (use 0 if null)
            int stock = ((Market) App.getLoggedInUserOrMarket()).getListOfProductsAndCount().get(product);
            int sold = ((Market) App.getLoggedInUserOrMarket()).getTotalSold(product);

            result.append("Stock: %d\nSold: %d".formatted(stock, sold));
            result.append("\n------------------------------------------------\n");
        }

        return new Result(true, result.substring(0, result.length() - 1));
    }


    public Result addStock(String productID, String amount) {
        int productIDCasted = Integer.parseInt(productID);
        int amountCasted = Integer.parseInt(amount);

        Product productUnderOperation = ((Market) App.getLoggedInUserOrMarket())
                .getProductByID(productIDCasted);

        if (productUnderOperation == null) {
            return new Result(false, "No product found.");
        } else if (amountCasted <= 0) {
            return new Result(false, "Amount must be a positive number.");
        }

        ((Market) App.getLoggedInUserOrMarket())
                .getListOfProductsAndCount().compute(productUnderOperation,
                        (k, oldQuantity) -> oldQuantity + amountCasted);

        ((Market) App.getLoggedInUserOrMarket())
                .addCosts(amountCasted * productUnderOperation.getProducerCost());

        return new Result(true, "%d units of \"%s\" have been added to the stock.".formatted(
                amountCasted, productUnderOperation.getName()
        ));
    }


    public Result updatePrice(String productID, String newPrice) {
        int productIDCasted = Integer.parseInt(productID);
        newPrice = newPrice.trim();

        double newPriceCasted;
        try {
            newPriceCasted = Double.parseDouble(newPrice);
        } catch (Exception e) {
            return new Result(false, "Price must be a positive number.");
        }

        Product productUnderOperation = null;

        for (Product product : ((Market) App.getLoggedInUserOrMarket())
                .getListOfProductsAndCount().keySet()) {
            if (product.getProductID() == productIDCasted) {
                productUnderOperation = product;
            }
        }

        if (productUnderOperation == null) {
            return new Result(false, "No product found.");
        } else if (newPriceCasted <= 0) {
            return new Result(false, "Price must be a positive number.");
        }

        int oldQuantity = ((Market) App.getLoggedInUserOrMarket())
                .getListOfProductsAndCount().get(productUnderOperation);

        ((Market) App.getLoggedInUserOrMarket()).getListOfProductsAndCount().remove(productUnderOperation);

        productUnderOperation.setPrice(newPriceCasted);

        ((Market) App.getLoggedInUserOrMarket()).getListOfProductsAndCount()
                .put(productUnderOperation, oldQuantity);

        return new Result(true, "Price of \"%s\" has been updated to $%.1f.".formatted(
                productUnderOperation.getName(), productUnderOperation.getPrice()
        ));
    }


    public Result goBack() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Redirecting to the MainMenu ...");
    }

}
