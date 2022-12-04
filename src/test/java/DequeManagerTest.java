import com.abiz.calculator.DequeManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.Random;
import java.util.stream.IntStream;


public class DequeManagerTest {

    static int[] values;
    static int min1Time = 10_000;
    static int max1Time = 30_000;
    static int min2Time = 50_000;
    static int max2Time = 50_005;
    private DequeManager manager;

    @BeforeAll
    public static void init() {
        Random random = new Random();
        values = IntStream.generate(() -> random.nextInt(10_000))
                .limit(100_000).toArray();
        values[min1Time] = -1;
        values[max1Time] = 10_001;
        values[min2Time] = 1;
        values[max2Time] = 9_999;
    }

    @Test
    public void interval_5() {
        DequeManager manager = new DequeManager(5);
        manager.addValues(values);
        Assertions.assertEquals(manager.getRecordedMax().getValue(), 9_999);
        Assertions.assertEquals(manager.getRecordedMax().getTime(), max2Time);
        System.out.println("max: " + manager.getRecordedMax());
        Assertions.assertEquals(manager.getRecordedMin().getValue(), 1);
        Assertions.assertEquals(manager.getRecordedMin().getTime(), min2Time);
        System.out.println("min: " + manager.getRecordedMin());
        Assertions.assertEquals(manager.getLargestVariation(), (9_999 - 1));
        System.out.println("largestVariation: " + manager.getLargestVariation());
    }

    @Test
    public void interval_20_000() {
        DequeManager manager = new DequeManager(20_000);
        manager.addValues(values);
        Assertions.assertEquals(manager.getRecordedMax().getValue(), 10_001);
        Assertions.assertEquals(manager.getRecordedMax().getTime(), max1Time);
        System.out.println("max: " + manager.getRecordedMax());
        Assertions.assertEquals(manager.getRecordedMin().getValue(), -1);
        Assertions.assertEquals(manager.getRecordedMin().getTime(), min1Time);
        System.out.println("min: " + manager.getRecordedMin());
        Assertions.assertEquals(manager.getLargestVariation(), (10_001 + 1));
        System.out.println("largestVariation: " + manager.getLargestVariation());
    }

    @Test
    public void performanceTest() {
        StopWatch stopWatch = new StopWatch();
        Random random = new Random();
        int[] ints = IntStream.generate(() -> random.nextInt(1_000_000))
                .limit(1_000_000).toArray();
        stopWatch.start();
        manager = new DequeManager(40_000);
        manager.addValues(ints);
        System.out.println("largestVariation: " + manager.getLargestVariation());
        System.out.println("max: " + manager.getRecordedMax());
        System.out.println("min: " + manager.getRecordedMin());
        stopWatch.stop();
        System.out.println("add and scan 1 million records in (ms):" + stopWatch.getTotalTimeMillis());
        stopWatch = new StopWatch();
        stopWatch.start();
        manager.findValue(manager.getRecordedMax().getValue());
        stopWatch.stop();
        System.out.println("find record in " + stopWatch.getTotalTimeMillis() + " ms");
    }


}
