package benchmarkutil;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.function.Supplier;

/**
 * Core benchmark runner that executes warmup, measures iterations,
 * collects wall time, CPU time, allocated memory, heap delta, and GC stats.
 */
class BenchmarkRunner {

    static <T> BenchmarkResult run(String name, Supplier<T> task, BenchmarkOptions options) {

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        boolean cpuSupported = threadMXBean.isCurrentThreadCpuTimeSupported();
        SystemMetrics metrics = new SystemMetrics();

        long heapBefore = metrics.heapUsed();
        long gcBeforeCount = metrics.gcCountSum();
        long gcBeforeTime = metrics.gcTimeSumMs();

        // Warmup iterations
        for (int i = 0; i < options.warmupIters; i++) task.get();

        long totalWallNs = 0;
        long totalCpuNs = 0;
        long totalAllocBytes = 0;

        for (int i = 0; i < options.measureIters; i++) {
            if (options.gcBetweenIters) System.gc();
            if (options.sleepBetweenItersMs > 0) {
                try {
                    Thread.sleep(options.sleepBetweenItersMs);
                } catch (InterruptedException ignored) {
                }
            }

            long beforeWall = System.nanoTime();
            long beforeCpu = cpuSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;
            long beforeMem = SystemMetrics.getUsedMemory();

            for (int j = 0; j < options.batchSize; j++) task.get();

            long afterWall = System.nanoTime();
            long afterCpu = cpuSupported ? threadMXBean.getCurrentThreadCpuTime() : 0;
            long afterMem = SystemMetrics.getUsedMemory();

            totalWallNs += (afterWall - beforeWall);
            totalCpuNs += (afterCpu - beforeCpu);
            totalAllocBytes += Math.max(0, afterMem - beforeMem);
        }

        long heapAfter = metrics.heapUsed();
        long gcAfterCount = metrics.gcCountSum();
        long gcAfterTime = metrics.gcTimeSumMs();

        return new BenchmarkResult(
                name,
                totalWallNs / ((long) options.measureIters * options.batchSize),
                totalCpuNs / ((long) options.measureIters * options.batchSize),
                totalAllocBytes / ((long) options.measureIters * options.batchSize),
                heapAfter - heapBefore,
                gcAfterCount - gcBeforeCount,
                gcAfterTime - gcBeforeTime
        );
    }
}