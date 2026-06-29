package views;

import controllers.StoreMenuController;
import models.enums.StoreMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class StoreMenu implements AppMenu {
    private final StoreMenuController controller = new StoreMenuController();

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = StoreMenuCommands.AddProduct.getMatcher(input)) != null) {
            System.out.println(controller.addProduct(
                    matcher.group("name").trim(),
                    matcher.group("producerCost").trim(),
                    matcher.group("price").trim(),
                    matcher.group("aboutThisItem").trim(),
                    matcher.group("numberOfProductsToSell")
            ));
        } else if ((matcher = StoreMenuCommands.ApplyDiscount.getMatcher(input)) != null) {
            System.out.println(controller.applyDiscount(
                    matcher.group("productID"),
                    matcher.group("discountPercentage"),
                    matcher.group("quantity")
            ));
        } else if ((matcher = StoreMenuCommands.ShowProfit.getMatcher(input)) != null) {
            System.out.println(controller.showProfit());
        } else if ((matcher = StoreMenuCommands.ShowListOfProducts.getMatcher(input)) != null) {
            System.out.println(controller.showListOfProducts());
        } else if ((matcher = StoreMenuCommands.AddStock.getMatcher(input)) != null) {
            System.out.println(controller.addStock(
                    matcher.group("productID"),
                    matcher.group("amount")
            ));
        } else if ((matcher = StoreMenuCommands.UpdatePrice.getMatcher(input)) != null) {
            System.out.println(controller.updatePrice(
                    matcher.group("productID"),
                    matcher.group("newPrice")
            ));
        } else if ((matcher = StoreMenuCommands.GoBack.getMatcher(input)) != null) {
            System.out.println(controller.goBack());
        } else {
            System.out.println("invalid command");
        }
    }
}
