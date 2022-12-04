import com.abiz.calculator.RepeatedNumbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Equals;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RepeatedNumbersTest {

    @Test
    public void test() {
        Random random = new Random();
        int[] values = IntStream.generate(() -> 1 + random.nextInt(999)).limit(10_000).toArray();
        int[] copy = Arrays.copyOf(values, values.length);
        List<Integer> copyList = Arrays.stream(copy).boxed().collect(Collectors.toList());
        RepeatedNumbers.extractRepeatedNumbers(values);
        int[] repeatedCounts = IntStream.range(1, 10_001).map(i ->
                (int) copyList.stream().filter(val -> val == i).count()
        ).toArray();
        Assertions.assertArrayEquals(values, repeatedCounts);
    }

}
