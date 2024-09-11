package backend.academy.seminar2.live.utils;

import backend.academy.seminar2.live.model.Product;
import java.util.Collection;

@FunctionalInterface
public interface ItemNotFoundHandler {
    double handle(Collection<Product> items);
}
