package benchmarkutil;

/**
 * BenchmarkOptions - configuration for benchmarking.
 *
 * Fields:
 * <ul>
 *   <li>warmupIters: iterations for JIT warmup (not measured)</li>
 *   <li>measureIters: iterations measured for statistics</li>
 *   <li>batchSize: number of task executions per iteration (reduces noise)</li>
 *   <li>gcBetweenIters: run GC before each measurement iteration to reduce noise</li>
 *   <li>sleepBetweenItersMs: pause between iterations (ms) to stabilize measurements</li>
 *   <li>measureCpuTime: enable CPU time measurement per thread</li>
 *   <li>measureThreadAlloc: track memory allocated per iteration</li>
 * </ul>
 *
 * Notes:
 * - Warmup is critical to allow JVM JIT optimization.
 * - Batch size should increase for extremely fast tasks.
 */
public class BenchmarkOptions {
    public final int warmupIters;
    public final int measureIters;
    public final int batchSize;
    public final boolean gcBetweenIters;
    public final long sleepBetweenItersMs;
    public final boolean measureCpuTime;
    public final boolean measureThreadAlloc;

    public BenchmarkOptions(int warmupIters, int measureIters) {
        this(warmupIters, measureIters, 1, true, 20, true, true);
    }

    public BenchmarkOptions(int warmupIters, int measureIters, int batchSize,
                            boolean gcBetweenIters, long sleepBetweenItersMs,
                            boolean measureCpuTime, boolean measureThreadAlloc) {
        this.warmupIters = warmupIters;
        this.measureIters = measureIters;
        this.batchSize = batchSize;
        this.gcBetweenIters = gcBetweenIters;
        this.sleepBetweenItersMs = sleepBetweenItersMs;
        this.measureCpuTime = measureCpuTime;
        this.measureThreadAlloc = measureThreadAlloc;
    }

    public static BenchmarkOptions defaults() {
        return new BenchmarkOptions(5, 10);
    }
}