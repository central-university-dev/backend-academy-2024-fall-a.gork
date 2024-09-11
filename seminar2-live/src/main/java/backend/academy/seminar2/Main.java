package backend.academy.seminar2.live;

import backend.academy.seminar2.live.model.Product;
import backend.academy.seminar2.live.model.User;
import backend.academy.seminar2.live.service.ProductService;
import backend.academy.seminar2.live.service.UserService;
import backend.academy.seminar2.live.utils.ItemNotFoundHandler;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        UserService userService = new UserService();

        initializeProducts(productService);
        initializeUsers(userService);

        User user = userService.findUserById(1)
            .orElseThrow(() -> new NoSuchElementException("User not found"));

        ItemNotFoundHandler handler = items -> {
            double amount = 0;
            for (Product product : items) {
                amount += product.getPrice();
            }
            return amount/items.size();
        };
        double price = productService.getProductPriceOrDefault(1, handler);
        System.out.println("Price: " + price);


        int stock = productService.getProductStockOrCalculate(2, () -> 10);
        System.out.println("Stock: " + stock);

        try {
            Product product = productService.getProductOrThrow(99);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void initializeProducts(ProductService productService) {
        productService.addProduct(new Product(1, "Laptop", 1500.00, 10));
        productService.addProduct(new Product(2, "Smartphone", 800.00, 25));
        productService.addProduct(new Product(3, "Tablet", 600.00, 15));
        productService.addProduct(new Product(4, "Smartwatch", 200.00, 30));
        productService.addProduct(new Product(5, "Headphones", 100.00, 50));
    }

    private static void initializeUsers(UserService userService) {
        userService.addUser(new User(1, "Alice", "alice@example.com"));
        userService.addUser(new User(2, "Bob", "bob@example.com"));
        userService.addUser(new User(3, "Charlie", "charlie@example.com"));
        userService.addUser(new User(4, "Diana", "diana@example.com"));
        userService.addUser(new User(5, "Eve", "eve@example.com"));
    }

    private static class Handler implements ItemNotFoundHandler {
        @Override
        public double handle(Collection<Product> items) {

            double amount = 0;
            for (Product product : items) {
                amount += product.getPrice();
            }
            return amount/items.size();
        }
    }
}
