package benchmarkutil;

/**
 * BenchmarkResult - holds statistics of a benchmarked task.
 * Fields:
 * <ul>
 *   <li>name: descriptive benchmark name</li>
 *   <li>avgWallTimeNs: average wall-clock time per operation (ns)</li>
 *   <li>avgCpuTimeNs: average CPU time per operation (ns)</li>
 *   <li>avgBytesAllocated: average memory allocated per operation (bytes)</li>
 *   <li>heapDeltaBytes: change in heap memory during the benchmark (bytes)</li>
 *   <li>gcCount: total GC events during the measured iterations</li>
 *   <li>gcTimeMs: total GC time during measured iterations (ms)</li>
 * </ul>
 *
 * Notes:
 * - Wall time = real elapsed time.
 * - CPU time = time spent by CPU executing this thread.
 * - Allocated bytes = gross allocations during task execution.
 * - Heap delta may be negative if GC frees memory.
 */
public class BenchmarkResult {
    public final String name;
    public final long avgWallTimeNs;
    public final long avgCpuTimeNs;
    public final long avgBytesAllocated;
    public final long heapDeltaBytes;
    public final long gcCount;
    public final long gcTimeMs;

    public BenchmarkResult(String name, long avgWallTimeNs, long avgCpuTimeNs,
                           long avgBytesAllocated, long heapDeltaBytes,
                           long gcCount, long gcTimeMs) {
        this.name = name;
        this.avgWallTimeNs = avgWallTimeNs;
        this.avgCpuTimeNs = avgCpuTimeNs;
        this.avgBytesAllocated = avgBytesAllocated;
        this.heapDeltaBytes = heapDeltaBytes;
        this.gcCount = gcCount;
        this.gcTimeMs = gcTimeMs;
    }

    /**
     * Return CPU time as a user-friendly string:
     * - If <1ms, show in nanoseconds
     * - Else show in milliseconds
     */
    public String cpuTimeString() {
        if (avgCpuTimeNs < 1_000_000) return avgCpuTimeNs + " ns";
        else return String.format("%.3f ms", avgCpuTimeNs / 1_000_000.0);
    }

    @Override
    public String toString() {
        return String.format("%s → Wall: %.3f ms, CPU: %s, Alloc: %.3f KB, HeapDelta: %.3f MB, GC count: %d, GC time: %.3f ms",
                name,
                avgWallTimeNs / 1_000_000.0,
                cpuTimeString(),
                avgBytesAllocated / 1024.0,
                heapDeltaBytes / (1024.0*1024.0),
                gcCount,
                (double) gcTimeMs);  // cast to double
    }
}