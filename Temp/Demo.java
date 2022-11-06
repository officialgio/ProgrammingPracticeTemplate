import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import static java.lang.System.out;
import java.util.*;
import java.io.*;
import java.math.*;

 


public class Demo {
	public static void main(String[] args) {
        int[] arr = {1,1,3,2};
        int[] sort = {3, 1, 8, 6, 2};
        int[] arrTwo = {1,1,2};
        int[] threeSumInput = {-1 , 0, 1, 2, -1, -4};
        int[] remvoveElementArr = {3, 2, 2, 3};
        int[] findPairArr = {8, 7, 2, 5, 3, 1};
        int[] firstMissingPostiveArr = {1, 2, 0};
        int[] findKthLargestArr = {3,2,1,5,6,4};
        char[] charArray = {'a', 'b','c', 'd'};
        String[] strArr = {"Hello", "Hello", "Gio"};

        String s = "A man, a plan, a canal: Panama";
        String revStr = "abcd";
        String palindrome = "racecar";
        String findHighestFrequencyStr = "aababcaab";
        String palindromicSubstring = "aaa";
        

        int kthLargest = findKthLargest(findKthLargestArr, 2);
        out.println("Result: " + kthLargest);



        
    

    }

   public static void reverseString(char[] s) {
        int i = 0; 
        int j = s.length - 1;
        
        while (i <= j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
   }




   private static boolean stringPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return  false;
            i++;
            j--;
        }
        return true;
   }

   public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0; 
        int right = nums.length - 1;

        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > nums[right])
                left = middle + 1;
            else
                right = middle;
        }

        int start = left; 
        left = 0; // reset binary saerch
        right = nums.length - 1;

        // sorted version
        if (target >= nums[start] && target <= nums[right])
            left = start; // search right
        else
            right = start; // search left

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] == target) 
                return middle;
            else if (nums[middle] < target)
                left = middle + 1;
            else {
                right = middle - 1;
            }
        }
        return -1;
   }

   private static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;


            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
   }


   public static int removeElement(int[] nums, int val) {
        int validSize = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[validSize] = nums[i];
                validSize++;
            }
        }
        return validSize;
   }



   // https://www.techiedelight.com/find-pair-with-given-sum-array/
   public static int[] findPair(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                System.out.printf("Pair found (%d, %d)\n", nums[map.get(target - nums[i])], nums[i]);
                //return new int[] {map.get(target - nums[i]), i};  // return index (Two Sum)
                return new int[] {target - nums[i], nums[i]}; // 
            }

            map.put(nums[i], i);
        }
        return nums; 
   }

   public static boolean findPalindrome(String s) {
        int i = 0; 
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) return false;
            i++;
            j++;
        }

        return true;
   }


   // Find the highest frequency of a character in a given string
   // Given a string s, return the character with the highest frequency.
   // if 2 or more letters have the same frequency, choose one.
   // Input: s "aababcaab"
   // Output: a
   // Hint: keep track of the numbers of times we've seen each character

   public static char findHighestFrequency(String input) {
        HashMap<Character, Integer> map = new HashMap<>();
        // loop and store the character
        // ifit was seen before increase the n amount of times it was seen before
        // otherwise just store the char and it's initial count 1
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }

        int maxFrequency = -1;
        char letterWithHighestFrequency = 'z';

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                letterWithHighestFrequency = entry.getKey();
            }
        }
        return letterWithHighestFrequency;
    }


    // Find all the Palidromic Substrings of a given String s
    // Given a string s, return the numeber of palindromic substrings in it. 
    // Input s = "abc"
    // Output: 3

    public static int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += extract(s, i, i) ;
            count += extract(s, i, i + 1);
        }
        return count;
    }

    public static int extract(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }



    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        int containsOne = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1)
                containsOne = 1;
            else if (nums[i] <= 0 || nums[i] > n)
                nums[i] = 1;
        }

        if (containsOne == 0) return 1;

        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0)
                nums[index] = -1 *nums[index];
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i + 1;
        }
        return n + 1;
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i : nums) {
            minHeap.add(i);
            if (minHeap.size() > k)
                minHeap.remove();
        }
        return minHeap.remove();
    }

    // Input: s "aababcaab"
    // Output: a
    public static char findHighestFrequencyTwo(String input) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < 0; i++) {
            char c = input.charAt(i);
            if(map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }

        char letterWithMaxFrequency = 'a';
        int count = 0;

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > count) {
                letterWithMaxFrequency = entry.getKey();
                count = entry.getValue();
            }
        }
        return letterWithMaxFrequency;
    }

    public void moveZeroes(int[] nums) {
        if (nums.length < 2) return;
        int left = 0, right = 1;
        while (right < nums.length) {
            if (nums[left] != 0) {
                left++;
                right++;
            } else if (nums[right] == 0) {
                right++;
            } else {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;

            }
        }
    }









    // 349. Intersection of Two Arrays
    // 1304. Find N Unique Integers Sum up to Zero
    // 27. Remove Element






}