package backend.academy.seminar9.test.coffee.tobedone;

import backend.academy.seminar9.coffee.withbugs.DecalcDetector;
import backend.academy.seminar9.coffee.withbugs.AutomaticCoffeeMachine;
import backend.academy.seminar9.coffee.withbugs.BeverageType;
import backend.academy.seminar9.coffee.withbugs.CoffeeMachineInventory;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Disabled("Remove this to actually test")
public class AutomaticCoffeeMachineTest {

    @ParameterizedTest
    @CsvSource({})
    void shouldNotBrewCustomWithNegativeParams(int coffee, int water, int milk) {

    }

    @Test
    void shouldBrewCustomBeverage() {

    }

    @ParameterizedTest
    @EnumSource(BeverageType.class)
    void shouldBrewCanonicalBeverages(BeverageType beverageType) {

    }

    @Test
    void shouldNotBrewIfDecalcificationRequired() {

    }

    @Test
    void shouldBrewAfterMaintenance() {
        var coffeeMachine = new AutomaticCoffeeMachine(getUnlimitedInventory(), getUnlimitedDetector());

        coffeeMachine.startMaintenance();

        assertDoesNotThrow(() -> coffeeMachine.brewCoffee(BeverageType.ESPRESSO));
    }

    @ParameterizedTest
    @MethodSource("getNotEnoughSuppliesInventory")
    void shouldNotBrewIfNotEnoughSupplies(
        BeverageType beverageType,
        CoffeeMachineInventory inventory,
        String expectedMessage
    ) {

    }

    private static Stream<Arguments> getNotEnoughSuppliesInventory() {
        return Stream.of();
    }


    private static CoffeeMachineInventory getUnlimitedInventory() {
        return new CoffeeMachineInventory(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private static DecalcDetector getUnlimitedDetector() {
        return new DecalcDetector(Integer.MAX_VALUE);
    }

}
