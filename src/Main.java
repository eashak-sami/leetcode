import java.util.*;

public class Main {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        List<Integer> results = new ArrayList<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.compute(num, (key, current) -> current + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() >= k) {
                results.add(entry.getKey());
            }
        }
        return results.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3};

        int[] res = topKFrequent(arr, 2);

        for (int re : res) {
            System.out.println(re);
        }
    }
}