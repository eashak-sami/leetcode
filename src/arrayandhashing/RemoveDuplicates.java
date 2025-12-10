package arrayandhashing;

public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        int uniqueElements = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i + 1] != nums[i]) {
                nums[uniqueElements] = nums[i + 1];
                uniqueElements++;
            }
        }
        return uniqueElements;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,0,0,1,1,1,1,1,1,1,1,2,2,3,3,4,5,6,7,8,8,8,8};
        System.out.println(removeDuplicates(nums));
    }
}
