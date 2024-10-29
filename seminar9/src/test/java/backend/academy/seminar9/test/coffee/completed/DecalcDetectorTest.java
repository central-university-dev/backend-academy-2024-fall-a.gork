package backend.academy.seminar9.test.coffee.completed;

import backend.academy.seminar9.coffee.completed.DecalcDetector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecalcDetectorTest {

    @Test
    void shouldIncrementCounter() {
        var decalcDetector = new DecalcDetector(Integer.MAX_VALUE);

        decalcDetector.incrementCounter();
        assertEquals(1, decalcDetector.getCounter());
    }

    @Test
    void shouldIncrementCounterBySpecifiedValue() {
        var decalcDetector = new DecalcDetector(Integer.MAX_VALUE);

        decalcDetector.incrementCounter(5);
        assertEquals(5, decalcDetector.getCounter());
    }

    @Test
    void shouldNotIncrementCounterByNegativeValue() {
        var decalcDetector = new DecalcDetector(Integer.MAX_VALUE);

        assertThrows(IllegalArgumentException.class, () -> decalcDetector.incrementCounter(-5));
    }

    @Test
    void shouldResetCounter() {
        var decalcDetector = new DecalcDetector(Integer.MAX_VALUE);

        decalcDetector.incrementCounter(1835789);
        assertNotEquals(0, decalcDetector.getCounter());

        decalcDetector.reset();
        assertEquals(0, decalcDetector.getCounter());
    }

    @ParameterizedTest
    @ValueSource(ints = {-6173576, 0})
    void shouldNotCreateDetectorWithThresholdLessThanZero(int threshold) {
        assertThrows(IllegalArgumentException.class, () -> new DecalcDetector(threshold));
    }

    @Test
    void shouldNotRequireDecalcificationBeforeThreshold() {
        int threshold = 3;
        var decalcDetector = new DecalcDetector(threshold);

        decalcDetector.incrementCounter();
        assertFalse(decalcDetector.isDecalcificationRequired());
    }

    @Test
    void shouldRequireDecalcificationAfterThreshold() {
        int threshold = 10;
        var decalcDetector = new DecalcDetector(threshold);

        decalcDetector.incrementCounter(threshold);
        assertTrue(decalcDetector.isDecalcificationRequired());

        decalcDetector.incrementCounter();
        assertTrue(decalcDetector.isDecalcificationRequired());
    }

    @Test
    void shouldHandleCounterWithIntegerOverflow() {
        var decalcDetector = new DecalcDetector(7485);

        decalcDetector.incrementCounter(Integer.MAX_VALUE);
        decalcDetector.incrementCounter();
        assertTrue(decalcDetector.isDecalcificationRequired());
    }
}
