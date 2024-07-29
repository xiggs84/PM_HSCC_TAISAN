package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TaisanSaiQsddDgcTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TaisanSaiQsddDgc getTaisanSaiQsddDgcSample1() {
        return new TaisanSaiQsddDgc().id(1L).idMaster(1L).noiCapQsdd("noiCapQsdd1");
    }

    public static TaisanSaiQsddDgc getTaisanSaiQsddDgcSample2() {
        return new TaisanSaiQsddDgc().id(2L).idMaster(2L).noiCapQsdd("noiCapQsdd2");
    }

    public static TaisanSaiQsddDgc getTaisanSaiQsddDgcRandomSampleGenerator() {
        return new TaisanSaiQsddDgc()
            .id(longCount.incrementAndGet())
            .idMaster(longCount.incrementAndGet())
            .noiCapQsdd(UUID.randomUUID().toString());
    }
}
