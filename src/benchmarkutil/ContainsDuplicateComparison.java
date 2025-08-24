package benchmarkutil;

import java.util.*;

public class ContainsDuplicateComparison {

    // --- HashSet approach ---
    public static boolean containsDuplicateHashSet(List<Integer> nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }

    // --- Sorting approach ---
    public static boolean containsDuplicateSorting(List<Integer> nums) {
        nums.sort(Integer::compareTo); // modifies the array
        for (int i = 1; i < nums.size(); i++) {
            if (Objects.equals(nums.get(i), nums.get(i - 1))) {
                return true;
            }
        }
        return false;
    }

    // --- HashMap approach ---
    public static boolean containsDuplicateHashMap(List<Integer> nums) {
        Map<Integer, Integer> numsMap = new HashMap<>();

        for (int num : nums) {
            if (numsMap.getOrDefault(num, 0) > 0) {
                return true;
            }
            numsMap.put(num, 1);
        }
        return false;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int size = 1_000_000_00; // test with 1M elements
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            nums.add(random.nextInt(1_000_000_0));
        }

        // Copy array so each method runs on same input
        List<Integer> numsForHashSet = new ArrayList<>(nums);
        List<Integer> numsForSorting = new ArrayList<>(nums);
        List<Integer> numsForHashMap = new ArrayList<>(nums);

        System.out.println("=== Duplicate Detection Benchmark ===");

        BenchmarkResult resultHashMap = BenchmarkUtil.run("HashMap", () -> {
            return containsDuplicateHashMap(numsForHashMap);
        });

        System.out.println(resultHashMap);

        BenchmarkResult resultHashSet = BenchmarkUtil.run("HashSet", () -> {
            return containsDuplicateHashSet(numsForHashSet);
        });

        System.out.println(resultHashSet);

        BenchmarkResult resultSorting = BenchmarkUtil.run("Sorting", () -> {
            return containsDuplicateSorting(numsForSorting);
        });

        System.out.println(resultSorting);
    }
}
