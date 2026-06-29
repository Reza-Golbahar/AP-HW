package controllers;

import models.*;
import models.enums.LoginMenuCommands;
import models.enums.Menu;
import models.enums.UserMenuCommands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;


public class UserMenuController {

    public Result listMyOrders() {
        User loggedInUser = (User) App.getLoggedInUserOrMarket();
        if (loggedInUser.getOrders().isEmpty()) {
            return new Result(false, "No orders found.");
        }

        // Sort orders by order time (assuming orders are stored in chronological order)
        List<Order> orders = new ArrayList<>(loggedInUser.getOrders());

        StringBuilder result = new StringBuilder();
        result.append("order History\n━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");

        for (Order order : orders) {
            result.append("""
                    Order ID: %d
                    Shipping Address: %s, %s, %s
                    Total Items Ordered: %d
                    
                    """.formatted(order.getOrderID(),
                    order.getAddress().getStreet(),
                    order.getAddress().getCity(),
                    order.getAddress().getCountry(),
                    order.getItemsOrderedCount()));


            List<CartItem> sortedCartItems = new ArrayList<>(order.getCartItemsOrdered());

            // Convert CartItems to Products, then sort by Product Name
            sortedCartItems.sort(Comparator.comparing(cartItem -> {
                Product product = cartItem.getProduct();
                return (product != null) ? product.getName() : ""; // Handle potential null values
            }));


            result.append("Products (Sorted by Name):\n");
            int index = 1;
            for (CartItem cartItem : sortedCartItems) {
                Product product = cartItem.getProduct();
                result.append("  %d- %s\n".formatted(index++, product.getName()));
            }

            result.append("\n━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");
        }

        return new Result(true, result.toString().trim());
    }

    public Result showOrderDetails(String orderID) {
        User loggedInUser = (User) App.getLoggedInUserOrMarket();

        // Find order by ID
        Order foundOrder = null;
        for (Order order : loggedInUser.getOrders()) {
            if (Integer.toString(order.getOrderID()).equals(orderID)) {
                foundOrder = order;
                break;
            }
        }

        if (foundOrder == null) {
            return new Result(false, "Order not found.");
        }

        // Sort CartItems by their associated Product's ID (ascending)
        List<CartItem> sortedCartItems = foundOrder.getCartItemsOrdered().stream()
                .sorted(Comparator.comparingInt(cartItem -> cartItem.getProduct().getProductID()))
                .toList();


        StringBuilder result = new StringBuilder();
        result.append("Products in this order:\n\n");

        int index = 1;
        for (CartItem cartItem : sortedCartItems) {
            Product product = cartItem.getProduct();

            result.append("%d- Product Name: %s\n    ID: %d\n    Brand: %s\n"
                    .formatted(index++, product.getName(), product.getProductID(), product.getMarket().getBrand()));
            result.append("    Rating: %.1f/5\n    Quantity: %d\n    Price: $%.1f%s\n\n"
                    .formatted(product.getRating(), cartItem.getQuantity(), cartItem.getPrice(),
                            (cartItem.getQuantity() > 1) ? " each" : ""));
        }

        // Calculate total cost
        double totalCost = sortedCartItems.stream()
                .mapToDouble(p -> p.getQuantity() * (p.getProduct().getPrice() * (1 - p.getDiscountPercentage())))
                .sum();

        result.append("━━━━━━━━━━━━━━━━━━━━━━━━━━\n")
                .append("**Total Cost: $%.1f**\n".formatted(totalCost));

        return new Result(true, result.substring(0, result.length() - 1));
    }


    public Result editName(String firstName, String lastName, String password) {
        if (!App.getLoggedInUserOrMarket().getPassword().equals(password)) {
            return new Result(false, "Incorrect password. Please try again.");
        }
        User loggedInUser = (User) App.getLoggedInUserOrMarket();
        if (loggedInUser.getFirstName().equals(firstName) || loggedInUser.getLastName().equals(lastName)) {
            return new Result(false, "The new name must be different from the current name.");
        } else if (firstName.length() < 3 || lastName.length() < 3) {
            return new Result(false, "Name is too short.");
        } else if ((LoginMenuCommands.Name.getMatcher(firstName) == null) ||
                (LoginMenuCommands.Name.getMatcher((lastName)) == null)) {
            return new Result(false, "Incorrect name format.");
        }
        loggedInUser.setFirstName(firstName);
        loggedInUser.setLastName(lastName);
        App.setLoggedInUserOrMarket(loggedInUser);
        return new Result(true, "Name updated successfully.");
    }


    public Result editEmail(String email, String password) {
        if (!App.getLoggedInUserOrMarket().getPassword().equals(password)) {
            return new Result(false, "Incorrect password. Please try again.");
        } else if (App.getLoggedInUserOrMarket().getEmailAddress().equals(email)) {
            return new Result(false, "The new email must be different from the current email.");
        } else if (LoginMenuCommands.Email.getMatcher(email) == null) {
            return new Result(false, "Incorrect email format.");
        }
        for (User user : App.getAllUsers()) {
            if (user.getEmailAddress().equals(email)) {
                return new Result(false, "Email already exists.");
            }
        }
        for (Market market : App.getAllMarkets()) {
            if (market.getEmailAddress().equals(email)) {
                return new Result(false, "Email already exists.");
            }
        }
        App.getLoggedInUserOrMarket().setEmailAddress(email);
        return new Result(true, "Email updated successfully.");
    }


    public Result editPassword(String newPassword, String oldPassword) {
        if (!App.getLoggedInUserOrMarket().getPassword().equals(oldPassword)) {
            return new Result(false, "Incorrect password. Please try again.");
        } else if (LoginMenuCommands.Password.getMatcher(newPassword) == null) {
            return new Result(false, "The new password is weak.");
        } else if (newPassword.equals(oldPassword)) {
            return new Result(false, "The new password must be different from the old password.");
        }
        App.getLoggedInUserOrMarket().setPassword(newPassword);
        return new Result(true, "Password updated successfully.");
    }


    public Result showMyInfo() {
        User loggedInUser = (User) App.getLoggedInUserOrMarket();
        return new Result(true, "Name: " + loggedInUser.getFirstName() +
                " " + loggedInUser.getLastName() + "\nEmail: " + loggedInUser.getEmailAddress());
    }


    public Result addAddress(String country, String city, String street, String postal) {
        if (UserMenuCommands.Postal.getMatcher(postal) == null) {
            return new Result(false, "Invalid postal code. It should be a 10-digit number.");
        }
        User loggedInUser = (User) App.getLoggedInUserOrMarket();
        for (Address address : loggedInUser.getAddresses()) {
            if (address.getPostal().equals(postal)) {
                return new Result(false, "This postal code is already associated with an existing address.");
            }
        }
        loggedInUser.addAddress(new Address(country, city, street, postal, loggedInUser.getNextAddressId()));
        App.setLoggedInUserOrMarket(loggedInUser);
        return new Result(true, "Address successfully added with id "
                + (loggedInUser.getNextAddressId() - 1) + ".");
    }


    public Result deleteAddress(String id) {
        User user = (User) App.getLoggedInUserOrMarket();
        for (Address address : user.getAddresses()) {
            if (address.getID() == Integer.parseInt(id)) {
                user.getAddresses().remove(address);
                return new Result(true, "Address with id " + id + " deleted successfully.");
            }
        }
        return new Result(false, "No address found.");
    }


    public Result listMyAddresses() {
        User user = (User) App.getLoggedInUserOrMarket();
        if (user.getAddresses().isEmpty()) {
            return new Result(false, "No addresses found. Please add an address first.");
        }

        StringBuilder result = new StringBuilder();
        result.append("Saved Addresses\n");
        result.append("━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");
        for (Address address : user.getAddresses()) {
            result.append("Address ").append(address.getID())
                    .append(":\n").append("Postal Code: ")
                    .append(address.getPostal()).append("\nCountry: ")
                    .append(address.getCountry()).append("\nCity: ")
                    .append(address.getCity()).append("\nStreet: ")
                    .append(address.getStreet())
                    .append("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");
        }
        return new Result(true, result.substring(0, result.length() - 2));
    }


    public Result addCreditCard(String cardNumber, String expirationDate, String cvv, String initialValue) {
        if (UserMenuCommands.CreditCardNumber.getMatcher(cardNumber) == null) {
            return new Result(false, "The card number must be exactly 16 digits.");
        }

        Matcher expirationDateMatcher;
        if ((expirationDateMatcher = UserMenuCommands.CreditCardExpirationDate.getMatcher(expirationDate)) == null) {
            return new Result(false, "Invalid expiration date. Please enter a valid date in MM/YY format.");
        } else if (Integer.parseInt(expirationDateMatcher.group("month")) > 12 ||
                Integer.parseInt(expirationDateMatcher.group("month")) <= 0) {
            return new Result(false, "Invalid expiration date. Please enter a valid date in MM/YY format.");
        }

        try {
            Integer.parseInt(cvv);
        } catch (Exception e) {
            return new Result(false, "The CVV code must be 3 or 4 digits.");
        }

        if (UserMenuCommands.CVV.getMatcher(cvv) == null) {
            return new Result(false, "The CVV code must be 3 or 4 digits.");
        }

        try {
            Double.parseDouble(initialValue);
        } catch (Exception e) {
            return new Result(false, "The initial value cannot be negative.");
        }
        if (Double.parseDouble(initialValue) < 0) {
            return new Result(false, "The initial value cannot be negative.");
        }
        User loggedInUser = (User) App.getLoggedInUserOrMarket();
        for (CreditCard creditCard : loggedInUser.getCreditCards()) {
            if (creditCard.getCardNumber().equals(cardNumber)) {
                return new Result(false, "This card is already saved in your account.");
            }
        }
        CreditCardExpirationDate expirationDate1 = new CreditCardExpirationDate(
                Integer.parseInt(expirationDateMatcher.group("year")),
                Integer.parseInt(expirationDateMatcher.group("month"))
        );
        loggedInUser.addCreditCard(new CreditCard(cardNumber, expirationDate1, cvv,
                Double.parseDouble(initialValue), loggedInUser.getNextCreditCardId()));
        App.setLoggedInUserOrMarket(loggedInUser);
        return new Result(true, "Credit card with Id %d has been added successfully.".
                formatted(loggedInUser.getNextCreditCardId() - 1));
    }


    public Result chargeCreditCard(String amount, String id) {
        double amount1;
        try {
            amount1 = Double.parseDouble(amount);
        } catch (Exception e) {
            return new Result(false, "The amount must be greater than zero.");
        }

        if (amount1 <= 0) {
            return new Result(false, "The amount must be greater than zero.");
        }

        User loggedInUser = (User) App.getLoggedInUserOrMarket();
        for (CreditCard creditCard : loggedInUser.getCreditCards()) {
            if (creditCard.getID() == Integer.parseInt(id)) {
                creditCard.setAccountBalance(creditCard.getAccountBalance() + amount1);
                return new Result(true, "$%.1f has been added to the credit card %s. New balance: $%.1f."
                        .formatted(amount1, id, creditCard.getAccountBalance()));

            }
        }
        return new Result(false, "No credit card found.");
    }


    public Result checkCreditCardBalance(String id) {
        User loggedInUser = (User) App.getLoggedInUserOrMarket();
        for (CreditCard creditCard : loggedInUser.getCreditCards()) {
            if (creditCard.getID() == Integer.parseInt(id)) {
                return new Result(true, "Current balance : $%.1f".formatted(creditCard.getAccountBalance()));
            }
        }
        return new Result(false, "No credit card found.");
    }


    public Result showProductsInCart() {
        User loggedInUser = (User) App.getLoggedInUserOrMarket();
        if (loggedInUser.getProductsInCart().isEmpty()) {
            return new Result(false, "Your shopping cart is empty.");
        }

        // Convert CartItems to Products and sort by name
        List<CartItem> cartItems = new ArrayList<>(loggedInUser.getProductsInCart());
        cartItems.sort(Comparator.comparing(cartItem -> {
            Product product = cartItem.getProduct();
            return product != null ? product.getName() : "";
        }));

        StringBuilder result = new StringBuilder();
        result.append("Your Shopping Cart:\n")
                .append("------------------------------------\n");
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            result.append("Product ID  : %d\nName        : %s\nQuantity    : %d\nPrice       : $%.1f"
                    .formatted(product.getProductID(),
                            product.getName(),
                            cartItem.getQuantity(),
                            cartItem.getPrice()));

            result.append(" (each)\nTotal Price : $%.1f\nBrand       : %s\nRating      : %.1f/5\n"
                    .formatted(
                            cartItem.getQuantity() * cartItem.getPrice(),
                            product.getMarket().getBrand(),
                            product.getRating()
                    ));

            result.append("------------------------------------\n");
        }
        return new Result(true, result.substring(0, result.length() - 1));
    }


    public Result checkout(String cardID, String addressID) {
        int addressIDCasted = Integer.parseInt(addressID);
        int cardIDCasted = Integer.parseInt(cardID);

        User loggedInUser = (User) App.getLoggedInUserOrMarket();
        if (loggedInUser.getProductsInCart().isEmpty()) {
            return new Result(false, "Your shopping cart is empty.");
        }

        Address wantedAddress = null;
        for (Address address : loggedInUser.getAddresses()) {
            if (address.getID() == addressIDCasted) {
                wantedAddress = address;
            }
        }
        if (wantedAddress == null) {
            return new Result(false, "The provided address ID is invalid.");
        }

        double shoppingCartTotalPrice = 0;

        CreditCard creditCardUnderOperation = null;

        for (CreditCard creditCard : loggedInUser.getCreditCards()) {
            if (creditCard.getID() == cardIDCasted) {
                creditCardUnderOperation = creditCard;

                for (CartItem cartItem : loggedInUser.getProductsInCart()) {
                    shoppingCartTotalPrice += cartItem.getPrice() * cartItem.getQuantity();
                }

                if (creditCard.getAccountBalance() < shoppingCartTotalPrice) {
                    return new Result(false, "Insufficient balance in the selected card.");
                }
            }
        }

        if (creditCardUnderOperation == null) {
            return new Result(false, "The provided card ID is invalid.");
        }

        creditCardUnderOperation.setAccountBalance(
                creditCardUnderOperation.getAccountBalance() - shoppingCartTotalPrice
        );

        for (CartItem cartItem : loggedInUser.getProductsInCart()) {
            Product product = App.getProductByID(cartItem.getProduct().getProductID());
            Market market = product.getMarket();
            market.addToProductSoldCount(cartItem);
        }

        Order newOrder = new Order(loggedInUser.getNextOrderID(), shoppingCartTotalPrice,
                wantedAddress);

        newOrder.setCartItemsOrdered(((User) App.getLoggedInUserOrMarket()).getProductsInCart());
        loggedInUser.addOrder(newOrder);
        loggedInUser.setProductsInCart(new ArrayList<>()); //making cart empty

        App.setLoggedInUserOrMarket(loggedInUser);

        return new Result(true, "Order has been placed successfully!\nOrder ID: %d"
                .formatted(loggedInUser.getNextOrderID() - 1)
                + "\nTotal Paid: $%.1f\nShipping to: %s, %s, %s".formatted(
                shoppingCartTotalPrice,
                wantedAddress.getStreet(), wantedAddress.getCity(), wantedAddress.getCountry()
        ));
    }


    public Result removeFromCart(String productID, String quantity) {
        User loggedInUser = (User) App.getLoggedInUserOrMarket();

        if (loggedInUser.getProductsInCart().isEmpty()) {
            return new Result(false, "Your shopping cart is empty.");
        }

        int productIDCasted = Integer.parseInt(productID);
        int quantityCasted = Integer.parseInt(quantity);

        Product productUnderOperation = App.getProductByID(productIDCasted);
        CartItem cartItemUnderOperation = loggedInUser.getCartItemInCartByID(productIDCasted);

        if (cartItemUnderOperation == null) {
            return new Result(false, "The product with ID " + productID + " is not in your cart.");
        } else if (quantityCasted <= 0) {
            return new Result(false, "Quantity must be a positive number.");
        }

        int productQuantityInCart = cartItemUnderOperation.getQuantity();

        if (productQuantityInCart < quantityCasted) {
            return new Result(false,
                    "You only have %d of \"%s\" in your cart."
                            .formatted(productQuantityInCart, productUnderOperation.getName()));
        }

        Market marketUnderOperation = productUnderOperation.getMarket();

        marketUnderOperation.getListOfProductsAndCount().put(
                productUnderOperation,
                marketUnderOperation.getListOfProductsAndCount().get(productUnderOperation) + quantityCasted
        );

        for (Discount discount : marketUnderOperation.getDiscountsApplied()) {
            if (discount.getProductIDAssignedTo() == productIDCasted) {
                discount.setQuantityOfProductsDiscounted(
                        discount.getQuantityOfProductsDiscounted() + quantityCasted
                );
            }
        }

        if (quantityCasted == productQuantityInCart) {
            loggedInUser.getProductsInCart().remove(cartItemUnderOperation);
            App.setLoggedInUserOrMarket(loggedInUser);
            return new Result(true,
                    "\"%s\" has been completely removed from your cart."
                            .formatted(productUnderOperation.getName()));
        }

        cartItemUnderOperation.setQuantity(
                cartItemUnderOperation.getQuantity() - quantityCasted
        );

        return new Result(true,
                "Successfully removed %d of \"%s\" from your cart."
                        .formatted(quantityCasted, productUnderOperation.getName()));
    }


    public Result goBack() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Redirecting to the MainMenu ...");
    }

}
