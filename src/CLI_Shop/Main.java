package CLI_Shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String EXIT_CASE = "esc";

    public static void main(String[] args) {

        Map<String, Product> products = new HashMap<>();
        Map<String, Customer> customers = new HashMap<>();
        Map<String, ShoppingCart> shoppingCarts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String keyboardInput = "";

        while (!keyboardInput.equals("11")) {
            System.out.println("""
                                        
                    Select entry:
                    1. Create user
                    2. Change user details
                    3. Show products
                    4. Show shopping cart
                    5. Add product to shopping cart
                    6. Remove product form the shopping cart
                    7. Place order
                    8. Show customer details (admin only)
                    9. Add product (admin only)
                    10.Change product price (admin only)
                    11.Exit application
                    """);

            while (true) {
                keyboardInput = scanner.nextLine();
                if (keyboardInput.matches("\\d+") && Integer.parseInt(keyboardInput) >= 1 && Integer.parseInt(keyboardInput) <= 11) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 11:");
                }
            }

            switch (keyboardInput) {
                case "1" -> {
                    System.out.println("Please enter your first name:");
                    String firstName = scanner.nextLine();
                    if (firstName.equals(EXIT_CASE)) {
                        break;
                    }

                    System.out.println("Please enter your last name:");
                    String lastName = scanner.nextLine();
                    if (lastName.equals(EXIT_CASE)) {
                        break;
                    }

                    System.out.println("Please enter your mobile phone number:");
                    String phoneNumber = scanner.nextLine();
                    if (phoneNumber.equals(EXIT_CASE)) {
                        break;
                    }

                    System.out.println("Please enter your email-address:");
                    String emailAddress = scanner.nextLine();
                    if (emailAddress.equals(EXIT_CASE)) {
                        break;
                    }

                    System.out.println("Please enter your password:");
                    String password = scanner.nextLine();
                    if (password.equals(EXIT_CASE)) {
                        break;
                    }

                    Customer customer = new Customer(firstName, lastName, phoneNumber, emailAddress, password);
                    customers.put(emailAddress, customer);
                    System.out.println("First name: " + customer.getFirstName() + ", Last name: " + customer.getLastName() + ", mobile phone number: " + customer.getPhoneNumber());
                    System.out.println("Email-address: " + customer.getEmailAddress() + ", Password: " + customer.getPassword());
                }
                case "2" -> {
                    if (customers.isEmpty()) {
                        System.out.println("No user has been created yet.");
                    } else {
                        System.out.println("Which user details should be changed ? ");
                        while (true) {
                            System.out.println("Please enter the corresponding email-address: ");
                            String emailAddress = scanner.nextLine();
                            if (emailAddress.equals(EXIT_CASE)) {
                                break;
                            }

                            System.out.println("Please enter the password: ");
                            String password = scanner.nextLine();
                            if (password.equals(EXIT_CASE)) {
                                break;
                            }

                            Customer customer = customers.get(emailAddress);
                            if (customers.containsKey(emailAddress) && password.equals(customer.getPassword())) {
                                System.out.println("Current user details:");
                                System.out.println("First name: " + customer.getFirstName() + ", Last name: " + customer.getLastName() + ", mobile phone number: " + customer.getPhoneNumber());
                                System.out.println("If the entry is not to be changed, simply press the Enter key.");

                                System.out.println("Please enter a new first name:");
                                String firstName = scanner.nextLine();
                                if (firstName.equals(EXIT_CASE)) {
                                    break;
                                }
                                if (!firstName.equals("")) {
                                    customer.setFirstName(firstName);
                                }

                                System.out.println("Please enter a new last name:");
                                String lastName = scanner.nextLine();
                                if (lastName.equals(EXIT_CASE)) {
                                    break;
                                }
                                if (!lastName.equals("")) {
                                    customer.setLastName(lastName);
                                }

                                System.out.println("Please enter a new mobile phone number:");
                                String phoneNumber = scanner.nextLine();
                                if (phoneNumber.equals(EXIT_CASE)) {
                                    break;
                                }
                                if (!phoneNumber.equals("")) {
                                    customer.setPhoneNumber(phoneNumber);
                                }
                                System.out.println("First name: " + customer.getFirstName() + ", Last name: " + customer.getLastName() + ", mobile phone number: " + customer.getPhoneNumber());
                                break;
                            } else {
                                System.out.println("The e-mail does not match the password. Please enter both again.");
                            }
                        }
                    }
                }
                case "3" -> {
                    if (products.isEmpty()) {
                        System.out.println("List of all products in the shop: 0");
                    } else {
                        System.out.println("List of all products in the shop:");
                        for (String name : products.keySet()) {
                            Product product = products.get(name);
                            System.out.println("Product name: " + product.getName() + ", Price: " + product.getPrice() + ", Available quantity: " + product.getQuantity());
                        }
                    }
                }
                case "4" -> {
                    double totalPrice = 0.0;
                    if (shoppingCarts.isEmpty()) {
                        System.out.println("List of all products in the shopping cart: 0");
                    } else {
                        System.out.println("List of all products in the shopping cart:");
                        for (String name : shoppingCarts.keySet()) {
                            ShoppingCart shoppingCart = shoppingCarts.get(name);
                            Product product = products.get(name);
                            System.out.println("Product name: " + product.getName() + ", Price: " + product.getPrice() + ", Available quantity: " + shoppingCart.getQuantity());
                            totalPrice += shoppingCart.getPrice() * shoppingCart.getQuantity();
                        }
                        System.out.println("The total price is: " + totalPrice);
                    }
                }
                case "5" -> {
                    if (products.isEmpty()) {
                        System.out.println("There are no products in the shop that can be added to the shopping cart.");
                    } else {
                        boolean leaveLoop;
                        System.out.println("Please enter the name of the product you want to add to the shopping cart:");
                        while (true) {
                            String productName = scanner.nextLine();
                            if (productName.equals(EXIT_CASE)) {
                                break;
                            }

                            Product product = products.get(productName);
                            if (products.containsKey(productName)) {
                                System.out.println("Product description from the shop:");
                                System.out.println("Product name: " + product.getName() + ", Price: " + product.getPrice() + ", Available quantity: " + product.getQuantity());

                                System.out.println("Please enter the number of products to be added to the shopping cart:");
                                while (true) {
                                    String quantity = scanner.nextLine();
                                    if (quantity.equals(EXIT_CASE)) {
                                        leaveLoop = true;
                                        break;
                                    }
                                    try {
                                        ShoppingCart shoppingCart = new ShoppingCart(product.getName(), product.getPrice(), Integer.parseInt(quantity));
                                        shoppingCarts.put(productName, shoppingCart);
                                        System.out.println("Product name: " + product.getName() + ", Price: " + product.getPrice() + ", quantity: " + shoppingCart.getQuantity());
                                        leaveLoop = true;
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter the number of products to be added to the shopping cart:");
                                    }
                                }
                                if (leaveLoop) {
                                    break;
                                }
                            } else {
                                System.out.println("This product does not exist. Please enter the number of the product to be added to the shopping cart:");
                            }
                        }
                    }
                }
                case "6" -> {
                    if (shoppingCarts.isEmpty()) {
                        System.out.println("There are no products in the shopping cart that can be removed.");
                    } else {
                        boolean leaveLoop;
                        System.out.println("Please enter the name of the product you want to remove from the shopping cart:");
                        while (true) {
                            String productName = scanner.nextLine();
                            if (productName.equals(EXIT_CASE)) {
                                break;
                            }

                            Product product = products.get(productName);
                            ShoppingCart shoppingCart = shoppingCarts.get(productName);
                            if (products.containsKey(productName)) {
                                System.out.println("Product description from the shopping cart:");
                                System.out.println("Product name: " + product.getName() + ", Price: " + product.getPrice() + ", Quantity: " + shoppingCart.getQuantity());

                                System.out.println("Please enter the number of products you want to remove from the shopping cart:");
                                while (true) {
                                    String quantity = scanner.nextLine();
                                    if (quantity.equals(EXIT_CASE)) {
                                        leaveLoop = true;
                                        break;
                                    }
                                    try {
                                        shoppingCart.reduceQuantity(Integer.parseInt(quantity));
                                        System.out.println("Product name: " + shoppingCart.getProductName() + ", Price: " + shoppingCart.getPrice() + ", Quantity: " + shoppingCart.getQuantity());
                                        leaveLoop = true;
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid Input. Please enter the number of products you want to remove from the shopping cart:");
                                    }
                                }
                                if (leaveLoop) {
                                    break;
                                }
                            } else {
                                System.out.println("This product does not exist. Please enter the name of the product you want to remove from the shopping cart:");
                            }
                        }
                    }
                }
                case "7" -> {
                    if (shoppingCarts.isEmpty()) {
                        System.out.println("No products have been added to the shopping cart yet.");
                    } else {
                        double totalPrice = 0.0;
                        System.out.println("Should the following products be placed in the shopping cart for ordering?");
                        for (String productName : shoppingCarts.keySet()) {
                            ShoppingCart shoppingCart = shoppingCarts.get(productName);
                            totalPrice += shoppingCart.getPrice() * shoppingCart.getQuantity();
                            System.out.println("Product name: " + shoppingCart.getProductName() + ", Price: " + shoppingCart.getPrice() + ", Quantity: " + shoppingCart.getQuantity());
                        }
                        System.out.println("The total price is: " + totalPrice);

                        System.out.println("\nShould this order be placed? (yes/no)");
                        while (true) {
                            String selection = scanner.nextLine();
                            if (selection.equals(EXIT_CASE)) {
                                break;
                            }
                            if (selection.equals("yes") || selection.equals("no")) {
                                if (selection.equals("yes")) {
                                    HashMap<String, Boolean> productStatus = new HashMap<>();
                                    for (String productName : products.keySet()) {
                                        productStatus.put(productName, false);
                                    }

                                    for (String productNameShoppingCart : shoppingCarts.keySet()) {
                                        ShoppingCart shoppingCart = shoppingCarts.get(productNameShoppingCart);
                                        for (String productName : products.keySet()) {
                                            Product product = products.get(productName);
                                            if (!productStatus.get(productName) && shoppingCart.getQuantity() < product.getQuantity()) {
                                                product.reduceQuantity(shoppingCart.getQuantity());
                                                productStatus.put(productName, true);
                                            }
                                        }
                                    }

                                    shoppingCarts.clear();
                                    System.out.println("Order is now being prepared and sent off.");
                                } else {
                                    System.out.println("Order process is cancelled.");
                                }
                                break;
                            } else {
                                System.out.println("Invalid input. Should this order be placed? (yes/no)");
                            }
                        }
                    }
                }
                case "8" -> {
                    if (customers.isEmpty()) {
                        System.out.println("List of all customers: 0");
                    }
                    for (String email : customers.keySet()) {
                        Customer customer = customers.get(email);
                        System.out.println("List of all customers:");
                        System.out.println("First name: " + customer.getFirstName() + ", Last name: " + customer.getLastName() + ", mobile phone number: " + customer.getPhoneNumber());
                        System.out.println("email-address: " + customer.getEmailAddress() + ", Password: " + customer.getPassword() + "\n");
                    }
                }
                case "9" -> {
                    double price = 0.0;
                    boolean leaveLoop = false;
                    System.out.println("Please enter the name of the product:");
                    String productName = scanner.nextLine();
                    if (productName.equals(EXIT_CASE)) {
                        break;
                    }

                    System.out.println("Please enter the price of the product:");
                    while (true) {
                        String priceInput = scanner.nextLine();
                        if (priceInput.equals(EXIT_CASE)) {
                            leaveLoop = true;
                            break;
                        }
                        try {
                            price = Double.parseDouble(priceInput);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter the price of the product:");
                        }
                    }
                    if (leaveLoop) {
                        break;
                    }

                    System.out.println("Please enter the number of products:");
                    while (true) {
                        String quantity = scanner.nextLine();
                        if (quantity.equals(EXIT_CASE)) {
                            break;
                        }
                        try {
                            Product product = new Product(productName, price, Integer.parseInt(quantity));
                            products.put(productName, product);
                            System.out.println("Product name: " + product.getName() + ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter the name of the products:");
                        }
                    }
                }
                case "10" -> {
                    if (products.isEmpty()) {
                        System.out.println("No product has been added to the shop yet.");
                    } else {
                        boolean leaveLoop = false;
                        while (true) {
                            System.out.println("Please enter the product name: ");
                            String productName = scanner.nextLine();
                            if (productName.equals(EXIT_CASE)) {
                                break;
                            }

                            if (products.containsKey(productName)) {
                                Product product = products.get(productName);
                                System.out.println("Current price: " + product.getPrice());
                                System.out.println("Please enter a new price:");
                                while (true) {
                                    String price = scanner.nextLine();
                                    if (price.equals(EXIT_CASE)) {
                                        leaveLoop = true;
                                        break;
                                    }
                                    try {
                                        product.setPrice(Double.parseDouble(price));
                                        System.out.println("The new price is: " + product.getPrice());
                                        leaveLoop = true;
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter the a new price:");
                                    }
                                }
                            } else {
                                System.out.println("Invalid input. Please enter the new product name:");
                            }
                            if (leaveLoop) {
                                break;
                            }
                        }
                    }
                }
                case "11" -> System.out.println("Console will now be closed.");
            }
        }
    }
}
