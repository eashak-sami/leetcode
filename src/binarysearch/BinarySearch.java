package binarysearch;

/**
 * {@link: <a href="https://leetcode.com/problems/binary-search/description/">...</a>}
 * */

public class BinarySearch {
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (right >= left) {
            int mid = left + (right - left)/2;

            if(nums[mid] == target) {
                return mid;
            }

            if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {-1,0,3,5,9,12};
        System.out.println(search(arr, 13));
    }
}
