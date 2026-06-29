package models;

import java.util.ArrayList;

public class User extends Logginable {
    private String firstName;
    private String lastName;
    private ArrayList<Address> addresses = new ArrayList<>();
    private int nextAddressId = 1;
    private ArrayList<CreditCard> creditCards = new ArrayList<>();
    private int nextCreditCardId = 1;
    private ArrayList<CartItem> productsInCart = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private int nextOrderID = 101;


    public User(String firstName, String lastName, String password, String emailAddress) {
        super(password, emailAddress);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addCreditCard(CreditCard newCreditCard) {
        this.creditCards.add(newCreditCard);
        this.nextCreditCardId++;
    }

    public void addAddress(Address newAddress) {
        this.addresses.add(newAddress);
        this.nextAddressId++;
    }

    public void addOrder(Order newOrder) {
        this.orders.add(newOrder);
        this.nextOrderID++;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public int getNextOrderID() {
        return nextOrderID;
    }

    public void setNextOrderID(int nextOrderID) {
        this.nextOrderID = nextOrderID;
    }

    public ArrayList<CartItem> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(ArrayList<CartItem> productsInCart) {
        this.productsInCart = productsInCart;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(ArrayList<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public int getNextCreditCardId() {
        return nextCreditCardId;
    }

    public void setNextCreditCardId(int nextCreditCardId) {
        this.nextCreditCardId = nextCreditCardId;
    }

    public int getNextAddressId() {
        return nextAddressId;
    }

    public void setNextAddressId(int nextAddressId) {
        this.nextAddressId = nextAddressId;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public void deleteAccount() {
        for (CartItem cartItem : productsInCart) {
            Product product = cartItem.getProduct();
            Market market = product.getMarket();

            market.getListOfProductsAndCount().compute(product,
                    (k, oldQuantity) -> oldQuantity + cartItem.getQuantity());
        }

        //TODO اما اطلاعات خرید های قبلی در تاریخچه فروش فروشگاه ذخیره می‌شود.
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CartItem getCartItemInCartByID(int cartItemID) {
        for (CartItem cartItem : productsInCart) {
            if (cartItem.getProduct().getProductID() == cartItemID) {
                return cartItem;
            }
        }
        return null;
    }

    public void addNewProductsToCart(int productID, int amount, int discountPercentage, boolean isDiscounted) {
        for (CartItem cartItem : ((User) App.getLoggedInUserOrMarket()).getProductsInCart()) {
            if (cartItem.getProduct().getProductID() == productID &&
                    cartItem.getDiscountPercentage() == discountPercentage) {
                cartItem.setQuantity(cartItem.getQuantity() + amount);
                return;
            }
        }
        ((User) App.getLoggedInUserOrMarket()).getProductsInCart().add(new CartItem(
                App.getProductByID(productID),
                amount,
                discountPercentage,
                isDiscounted));

    }
}
