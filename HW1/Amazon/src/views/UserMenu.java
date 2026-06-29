package views;

import controllers.UserMenuController;
import models.enums.UserMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class UserMenu implements AppMenu {
    private final UserMenuController controller = new UserMenuController();

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = UserMenuCommands.ListMyOrders.getMatcher(input)) != null) {
            System.out.println(controller.listMyOrders());
        } else if ((matcher = UserMenuCommands.ShowOrderDetails.getMatcher(input)) != null) {
            System.out.println(controller.showOrderDetails(matcher.group("orderID")));
        } else if ((matcher = UserMenuCommands.EditName.getMatcher(input)) != null) {
            System.out.println(controller.editName(
                    matcher.group("firstName").trim(),
                    matcher.group("lastName").trim(),
                    matcher.group("password").trim()
            ));
        } else if ((matcher = UserMenuCommands.EditEmail.getMatcher(input)) != null) {
            System.out.println(controller.editEmail(
                    matcher.group("email").trim(),
                    matcher.group("password").trim()
            ));
        } else if ((matcher = UserMenuCommands.EditPassword.getMatcher(input)) != null) {
            System.out.println(controller.editPassword(
                    matcher.group("newPassword").trim(),
                    matcher.group("oldPassword").trim()
            ));
        } else if ((matcher = UserMenuCommands.ShowMyInfo.getMatcher(input)) != null) {
            System.out.println(controller.showMyInfo());
        } else if ((matcher = UserMenuCommands.AddAddress.getMatcher(input)) != null) {
            System.out.println(controller.addAddress(
                    matcher.group("country").trim(),
                    matcher.group("city").trim(),
                    matcher.group("street").trim(),
                    matcher.group("postal").trim()));
        } else if ((matcher = UserMenuCommands.DeleteAddress.getMatcher(input)) != null) {
            System.out.println(controller.deleteAddress(matcher.group("ID")));
        } else if ((matcher = UserMenuCommands.ListMyAddresses.getMatcher(input)) != null) {
            System.out.println(controller.listMyAddresses());
        } else if ((matcher = UserMenuCommands.AddACreditCard.getMatcher(input)) != null) {
            System.out.println(controller.addCreditCard(
                    matcher.group("cardNumber").trim(),
                    matcher.group("expirationDate").trim(),
                    matcher.group("cvv").trim(),
                    matcher.group("initialValue").trim()
            ));
        } else if ((matcher = UserMenuCommands.ChargeCreditCard.getMatcher(input)) != null) {
            System.out.println(controller.chargeCreditCard(
                    matcher.group("amount"),
                    matcher.group("id")
            ));
        } else if ((matcher = UserMenuCommands.CheckCreditCardBalance.getMatcher(input)) != null) {
            System.out.println(controller.checkCreditCardBalance(
                    matcher.group("id")
            ));
        } else if ((matcher = UserMenuCommands.ShowProductsInCart.getMatcher(input)) != null) {
            System.out.println(controller.showProductsInCart());
        } else if ((matcher = UserMenuCommands.Checkout.getMatcher(input)) != null) {
            System.out.println(controller.checkout(
                    matcher.group("cardID"),
                    matcher.group("addressID")
            ));
        } else if ((matcher = UserMenuCommands.RemoveFromCart.getMatcher(input)) != null) {
            System.out.println(controller.removeFromCart(
                    matcher.group("productID"),
                    matcher.group("amount")
            ));
        } else if ((matcher = UserMenuCommands.GoBack.getMatcher(input)) != null) {
            System.out.println(controller.goBack());
        } else {
            System.out.println("invalid command");
        }
    }
}
