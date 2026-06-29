package views;

import controllers.ProductMenuController;
import models.enums.ProductMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProductMenu implements AppMenu {
    private final ProductMenuController controller = new ProductMenuController();

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = ProductMenuCommands.ShowProducts.getMatcher(input)) != null) {
            System.out.println(controller.showProducts(
                    matcher.group("sortCriteria")
            ));
        } else if ((matcher = ProductMenuCommands.ShowNext10Products.getMatcher(input)) != null) {
            System.out.println(controller.showNextProducts());
        } else if ((matcher = ProductMenuCommands.ShowPrevious10Products.getMatcher(input)) != null) {
            System.out.println(controller.showPastProducts());
        } else if ((matcher = ProductMenuCommands.ShowInformation.getMatcher(input)) != null) {
            System.out.println(controller.showInformation(
                    matcher.group("productID")
            ));
        } else if ((matcher = ProductMenuCommands.RateProduct.getMatcher(input)) != null) {
            System.out.println(controller.rateProduct(
                    matcher.group("number"),
                    matcher.group("message"),
                    matcher.group("id")
            ));
        } else if ((matcher = ProductMenuCommands.AddToCart.getMatcher(input)) != null) {
            System.out.println(controller.addToCart(
                    matcher.group("productID"),
                    matcher.group("amount")
            ));
        } else if ((matcher = ProductMenuCommands.GoBack.getMatcher(input)) != null) {
            System.out.println(controller.goBack());
        } else {
            System.out.println("invalid command");
        }
    }
}
