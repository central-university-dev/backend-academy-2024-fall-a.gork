package backend.academy.seminar9.test.coffee.completed;

import backend.academy.seminar9.coffee.completed.AutomaticCoffeeMachine;
import backend.academy.seminar9.coffee.completed.BeverageType;
import backend.academy.seminar9.coffee.completed.CoffeeBeverage;
import backend.academy.seminar9.coffee.completed.CoffeeMachineInventory;
import backend.academy.seminar9.coffee.completed.DecalcDetector;
import backend.academy.seminar9.coffee.exceptions.DecalcificationRequiredException;
import backend.academy.seminar9.coffee.exceptions.NotEnoughSuppliesException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AutomaticCoffeeMachineTest {

    @ParameterizedTest
    @CsvSource({
        "-1, 0, 0",
        "0, -1, 0",
        "0, 0, -1",
        "-1, -1, 0",
        "-1, 0, -1",
        "0, -1, -1",
        "-1, -1, -1"
    })
    void shouldNotBrewCustomWithNegativeParams(int coffee, int water, int milk) {
        var coffeeMachine = new AutomaticCoffeeMachine(getUnlimitedInventory(), getUnlimitedDetector());

        assertThrows(IllegalArgumentException.class, () -> coffeeMachine.brewCustom(coffee, water, milk));
    }

    @Test
    void shouldBrewCustomBeverage() {
        var inventory = new CoffeeMachineInventory(100, 200, 200);
        var detector = new DecalcDetector(10);
        var coffeeMachine = new AutomaticCoffeeMachine(inventory, detector);

        CoffeeBeverage beverage = coffeeMachine.brewCustom(45, 50, 100);
        assertNotNull(beverage);
        assertAll(
            () -> assertEquals(1, detector.getCounter()),
            () -> assertEquals(78, inventory.getBeans()),
            () -> assertEquals(78, inventory.getWater()),
            () -> assertEquals(100, inventory.getMilk())
        );
    }

    @ParameterizedTest
    @EnumSource(BeverageType.class)
    void shouldBrewCanonicalBeverages(BeverageType beverageType) {
        var coffeeMachine = new AutomaticCoffeeMachine(getUnlimitedInventory(), getUnlimitedDetector());

        CoffeeBeverage result = coffeeMachine.brewCoffee(beverageType);
        assertNotNull(result);
        assertTrue(beverageType.isCanonical(result));
    }

    @Test
    void shouldNotBrewIfDecalcificationRequired(@Mock DecalcDetector mockDetector) {
        var coffeeMachine = new AutomaticCoffeeMachine(getUnlimitedInventory(), mockDetector);

        when(mockDetector.isDecalcificationRequired()).thenReturn(true);
        assertThrows(DecalcificationRequiredException.class, () -> coffeeMachine.brewCoffee(BeverageType.ESPRESSO));
    }

    @Test
    void shouldBrewAfterMaintenance() {
        var decalcDetector = Mockito.spy(new DecalcDetector(1));
        decalcDetector.incrementCounter(10);

        var coffeeMachine = new AutomaticCoffeeMachine(getUnlimitedInventory(), decalcDetector);

        coffeeMachine.startMaintenance();
        assertDoesNotThrow(() -> coffeeMachine.brewCoffee(BeverageType.ESPRESSO));
        assertEquals(1, decalcDetector.getCounter());
        verify(decalcDetector, times(1)).reset();
    }

    @ParameterizedTest
    @MethodSource("getNotEnoughSuppliesInventory")
    void shouldNotBrewIfNotEnoughSupplies(
        BeverageType beverageType,
        CoffeeMachineInventory inventory,
        String expectedMessage
    ) {
        var coffeeMachine = new AutomaticCoffeeMachine(inventory, getUnlimitedDetector());

        NotEnoughSuppliesException exception = assertThrows(
            NotEnoughSuppliesException.class,
            () -> coffeeMachine.brewCoffee(beverageType)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static CoffeeMachineInventory getUnlimitedInventory() {
        return new CoffeeMachineInventory(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private static DecalcDetector getUnlimitedDetector() {
        return new DecalcDetector(Integer.MAX_VALUE);
    }

    private static Stream<Arguments> getNotEnoughSuppliesInventory() {
        return Stream.of(
            arguments(
                BeverageType.ESPRESSO,
                new CoffeeMachineInventory(10, 200, 0),
                "Not enough supplies: Coffee: 5"
            ),
            arguments(
                BeverageType.ESPRESSO,
                new CoffeeMachineInventory(10, 0, 0),
                "Not enough supplies: Coffee: 5, Water: 45"
            ),
            arguments(
                BeverageType.CAPPUCCINO,
                new CoffeeMachineInventory(15, 200, 100),
                "Not enough supplies: Milk: 20"
            ),
            arguments(
                BeverageType.CAPPUCCINO,
                new CoffeeMachineInventory(15, 15, 200),
                "Not enough supplies: Water: 36"
            ),
            arguments(
                BeverageType.CAPPUCCINO,
                new CoffeeMachineInventory(0, 0, 0),
                "Not enough supplies: Coffee: 15, Water: 51, Milk: 120"
            )
        );
    }
}
