public class RateLimiter {
    // RateLimiter provides two factory methods. At last, the following two functions will be called to generate two subclasses of RateLimiter.

//    static RateLimiter create(SleepingStopwatch stopwatch, double permitsPerSecond) {
//        RateLimiter rateLimiter = new SmoothBursty(stopwatch, 1.0 /* maxBurstSeconds */);
//        rateLimiter.setRate(permitsPerSecond);
//        return rateLimiter;
//    }
//    static RateLimiter create(
//            SleepingStopwatch stopwatch, double permitsPerSecond, long warmupPeriod, TimeUnit unit,
//            double coldFactor) {
//        RateLimiter rateLimiter = new SmoothWarmingUp(stopwatch, warmupPeriod, unit, coldFactor);
//        rateLimiter.setRate(permitsPerSecond);
//        return rateLimiter;
//    }
}
