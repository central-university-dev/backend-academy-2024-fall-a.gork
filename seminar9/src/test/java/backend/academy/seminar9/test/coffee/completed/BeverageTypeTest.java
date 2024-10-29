package backend.academy.seminar9.test.coffee.completed;

import backend.academy.seminar9.coffee.completed.BeverageType;
import backend.academy.seminar9.coffee.completed.CoffeeBeverage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BeverageTypeTest {

    @Test
    void shouldGetNotCanonicalIfBeverageIsNull() {
        var beverageType = BeverageType.AMERICANO;
        assertFalse(beverageType.isCanonical(null));
    }

    @Test
    void shouldGetNotCanonicalIfBeverageParamsAreEmpty() {
        var beverageType = BeverageType.AMERICANO;
        assertFalse(beverageType.isCanonical(new CoffeeBeverage(0, 0, 0)));
    }

    @Test
    void shouldGetNotCanonicalWithNegativeCoffee() {
        var beverageType = BeverageType.AMERICANO;
        assertFalse(beverageType.isCanonical(new CoffeeBeverage(-1, 2, 0)));
    }

    @Test
    void shouldGetNotCanonicalWithNegativeWater() {
        var beverageType = BeverageType.AMERICANO;
        assertFalse(beverageType.isCanonical(new CoffeeBeverage(1, -2, 0)));
    }

    @Test
    void shouldGetNotCanonicalWithNegativeCoffeeAndWater() {
        var beverageType = BeverageType.AMERICANO;
        assertFalse(beverageType.isCanonical(new CoffeeBeverage(-1, -2, 0)));
    }

    @Test
    void shouldGetNotCanonicalWithNegativeMilk() {
        var beverageType = BeverageType.CAPPUCCINO;
        assertFalse(beverageType.isCanonical(new CoffeeBeverage(1, 0, -4)));
    }

    @Test
    void shouldGetNotCanonicalWithNegativeCoffeeAndMilk() {
        var beverageType = BeverageType.CAPPUCCINO;
        assertFalse(beverageType.isCanonical(new CoffeeBeverage(-1, 0, -4)));
    }

    @Test
    void shouldGetNotCanonicalWithIntOverflow() {
        var beverageType = BeverageType.AMERICANO;
        int coffee = Integer.MAX_VALUE / 2 + 1;
        int water = Integer.MIN_VALUE;
        assertFalse(beverageType.isCanonical(new CoffeeBeverage(coffee, water, 0)));
    }

    @Test
    void shouldGetCanonicalEspresso() {
        var beverageType = BeverageType.ESPRESSO;
        assertTrue(beverageType.isCanonical(new CoffeeBeverage(1, 0, 0)));
    }

    @Test
    void shouldGetCanonicalAmericano() {
        var beverageType = BeverageType.AMERICANO;
        assertTrue(beverageType.isCanonical(new CoffeeBeverage(1, 2, 0)));
    }

    @Test
    void shouldGetCanonicalCappuccino() {
        var beverageType = BeverageType.CAPPUCCINO;
        assertTrue(beverageType.isCanonical(new CoffeeBeverage(1, 0, 4)));
    }

    @Test
    void shouldGetCanonicalLatte() {
        var beverageType = BeverageType.LATTE;
        assertTrue(beverageType.isCanonical(new CoffeeBeverage(1, 0, 6)));
    }
}
