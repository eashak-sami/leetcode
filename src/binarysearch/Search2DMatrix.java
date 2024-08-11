package binarysearch;

/**
 * {@link: <a href="https://leetcode.com/problems/search-a-2d-matrix/description/">...</a>}
 * */

public class Search2DMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int left = 0;
            int right = matrix[0].length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (matrix[i][mid] == target) {
                    return true;
                }
                if (matrix[i][mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr = {{1,3,5,7}, {10,11,16,20},{23,30,34,60}};

        System.out.println(searchMatrix(arr, 3));
    }
}
