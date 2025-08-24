package benchmarkutil;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;

/**
 * SystemMetrics - helper to read JVM-level metrics.
 * Tracks:
 * - Heap used
 * - GC count and time
 * - CPU time per thread
 * - Allocated memory (if JVM supports)
 */
final class SystemMetrics {

    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
    private final List<GarbageCollectorMXBean> gcMxBeans = ManagementFactory.getGarbageCollectorMXBeans();
    private final ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
    private final com.sun.management.ThreadMXBean sunThreadMxBean;

    SystemMetrics() {
        com.sun.management.ThreadMXBean candidate = null;
        try {
            candidate = ManagementFactory.getPlatformMXBean(com.sun.management.ThreadMXBean.class);
        } catch (Throwable ignored) {
        }
        this.sunThreadMxBean = candidate;

        try {
            if (threadMxBean.isCurrentThreadCpuTimeSupported() && !threadMxBean.isThreadCpuTimeEnabled())
                threadMxBean.setThreadCpuTimeEnabled(true);
        } catch (Throwable ignored) {
        }

        if (sunThreadMxBean != null) {
            try {
                if (!sunThreadMxBean.isThreadAllocatedMemoryEnabled())
                    sunThreadMxBean.setThreadAllocatedMemoryEnabled(true);
            } catch (Throwable ignored) {
            }
        }
    }

    long heapUsed() {
        return memoryMXBean.getHeapMemoryUsage().getUsed();
    }

    long gcCountSum() {
        long sum = 0L;
        for (GarbageCollectorMXBean g : gcMxBeans) {
            long c = g.getCollectionCount();
            if (c > 0) sum += c;
        }
        return sum;
    }

    long gcTimeSumMs() {
        long sum = 0L;
        for (GarbageCollectorMXBean g : gcMxBeans) {
            long t = g.getCollectionTime();
            if (t > 0) sum += t;
        }
        return sum;
    }

    boolean cpuTimeSupported() {
        try {
            return threadMxBean.isCurrentThreadCpuTimeSupported();
        } catch (Throwable t) {
            return false;
        }
    }

    long threadCpuTimeNs() {
        try {
            if (!cpuTimeSupported()) return -1L;
            return threadMxBean.getCurrentThreadCpuTime();
        } catch (Throwable t) {
            return -1L;
        }
    }

    boolean threadAllocSupported() {
        try {
            return sunThreadMxBean != null && sunThreadMxBean.isThreadAllocatedMemoryEnabled();
        } catch (Throwable t) {
            return false;
        }
    }

    long threadAllocatedBytes(long tid) {
        if (sunThreadMxBean == null) return -1L;
        try {
            long v = sunThreadMxBean.getThreadAllocatedBytes(tid);
            return v < 0 ? -1 : v;
        } catch (Throwable t) {
            return -1L;
        }
    }

    static long getUsedMemory() {
        Runtime r = Runtime.getRuntime();
        return r.totalMemory() - r.freeMemory();
    }
}