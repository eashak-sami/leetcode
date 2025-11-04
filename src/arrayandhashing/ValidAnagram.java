package arrayandhashing;

/**
 * {@link: <a href="https://leetcode.com/problems/valid-anagram/description/">Valid Anagram</a>}
 */

public class ValidAnagram {

    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        int[] sum = new int[26];

        for (int i = 0; i < s.length(); i++) {
            sum[s.charAt(i) - 'a']++;
            sum[t.charAt(i) - 'a']--;
        }

        for (int i : sum) {
            if(i != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("", ""));
    }

}
