package backend.academy.seminar9.test.coffee.tobedone;

import backend.academy.seminar9.coffee.withbugs.DecalcDetector;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled("Remove this to actually test")
class DecalcDetectorTest {

    private Random rnd = new Random();

    @Test
    void shouldIncrementCounter() {
        DecalcDetector decalcDetector = new DecalcDetector(100);
        decalcDetector.incrementCounter();
        assertEquals(1, decalcDetector.getCounter());
    }

    @Test
    void shouldIncrementCounterBySpecifiedValue() {
        DecalcDetector decalcDetector = new DecalcDetector(100);
        int step = rnd.nextInt(0, 1000);
        decalcDetector.incrementCounter(step);
        assertEquals(step, decalcDetector.getCounter());
    }

    @Test
    void shouldNotIncrementCounterByNegativeValue() {
        DecalcDetector decalcDetector = new DecalcDetector(100);
        int step = rnd.nextInt(-100, -1);
        assertThrows(RuntimeException.class, () -> decalcDetector.incrementCounter(step));
        assertEquals(0, decalcDetector.getCounter());
    }

    @Test
    void shouldResetCounter() {

    }

    @ParameterizedTest
    @ValueSource(ints = {})
    void shouldNotCreateDetectorWithThresholdLessThanZero(int threshold) {

    }

    @Test
    void shouldNotRequireDecalcificationBeforeThreshold() {

    }

    @Test
    void shouldRequireDecalcificationAfterThreshold() {

    }

    @Test
    void shouldHandleCounterWithIntegerOverflow() {

    }
}
