package arrayandhashing;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link: <a href="https://leetcode.com/problems/contains-duplicate/description/">Contains Duplicate</a>}
 */

public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] nums) {
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
        int[] nums = {1, 2, 3, 1};
        System.out.println(containsDuplicate(nums));
    }

}
