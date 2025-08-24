package benchmarkutil;

import java.util.function.Supplier;

/**
 * BenchmarkUtil - entry point for running benchmarks.
 * <p>
 * Provides overloaded methods for:
 * - default benchmarking
 * - custom iterations
 * - full customization via BenchmarkOptions
 */
public class BenchmarkUtil {

    /**
     * Run benchmark with default options.
     */
    public static <T> BenchmarkResult run(String name, Supplier<T> task) {
        return BenchmarkRunner.run(name, task, BenchmarkOptions.defaults());
    }

    /**
     * Run benchmark with custom warmup and measurement iterations.
     */
    public static <T> BenchmarkResult run(String name, Supplier<T> task,
                                          int warmupIters, int measureIters) {
        return BenchmarkRunner.run(name, task, new BenchmarkOptions(warmupIters, measureIters));
    }

    /**
     * Run benchmark with fully customized options.
     */
    public static <T> BenchmarkResult run(String name, Supplier<T> task,
                                          BenchmarkOptions options) {
        return BenchmarkRunner.run(name, task, options);
    }
}