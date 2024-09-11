package backend.academy.seminar2.live.service;

import backend.academy.seminar2.live.model.Product;
import backend.academy.seminar2.live.utils.ItemNotFoundHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

public class ProductService {
    private final Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        Optional.ofNullable(product)
                .ifPresent(p -> products.put(p.getId(), p));
    }

    public Optional<Product> findProductById(int productId) {
        return Optional.ofNullable(products.get(productId));
    }

    public Optional<Product> findProductByName(String name) {
        return products.values().stream()
            .filter(p -> p.getName() != null && p.getName().equals(name))
            .findFirst();
    }

    public Optional<Integer> getProductStock(int productId) {
        return Optional.ofNullable(products.get(productId))
            .map(Product::getStockQuantity);
    }

    public Optional<Double> getProductPrice(int productId) {
        return Optional.ofNullable(products.get(productId))
            .map(Product::getPrice);
    }

    /**
     * Получение продукта с проверкой наличия, если нет - исключение
     */
    public Product getProductOrThrow(int productId) {
        return Optional.ofNullable(products.get(productId))
            .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    /**
     * Получение цены товара или значения по умолчанию
     */
    public double getProductPriceOrDefault(int productId, ItemNotFoundHandler handler) {
        for (Product product : products.values()) {
            if (product.getId() == productId) {
                return product.getPrice();
            }
        }
        return handler.handle(products.values());
    }

    /**
     * Получение количества товара или значение по умолчанию, рассчитанное на основе других данных
     */
    public int getProductStockOrCalculate(int productId, Supplier<Integer> stockSupplier) {
        return Optional.ofNullable(products.get(productId))
            .map(Product::getStockQuantity)
            .orElseGet(stockSupplier);
    }
}
