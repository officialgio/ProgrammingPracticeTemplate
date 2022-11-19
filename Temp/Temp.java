import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import static java.lang.System.out;
import java.util.*;
import java.io.*;
import java.math.*;

public class Temp {
	public static void main(String[] args) {
        int[] arr = {1,1,3,2};
        int[] sort = {3, 1, 8, 6, 2};
        int[] arrTwo = {1,1,2};
        int[] threeSumInput = {-1 , 0, 1, 2, -1, -4};
        int[] remvoveElementArr = {3, 2, 2, 3};
        int[] findPairArr = {8, 7, 2, 5, 3, 1};
        int[] firstMissingPostiveArr = {1, 2, 0};
        int[] findKthLargestArr = {3,2,1,5,6,4};
        int[] maxSubArrayArr = {-2,1,-3,4,-1,2,1,-5,4};
        char[] charArray = {'a', 'b','c', 'd'};
        int[] binarySubArr = {1,0,1,0,1};
        String[] strArr = {"Hello", "Hello", "Gio"};
        String[] str = {"flower","flow","flight"};

        String s = "A man, a plan, a canal: Panama";
        String revStr = "abcd";
        String palindrome = "racecar";
        String findHighestFrequencyStr = "aababcaab";
        String palindromicSubstring = "aaa";      
        
    }

    public static int[] readArr(int N, BufferedReader infile, StringTokenizer st) throws Exception {
        int[] arr = new int[N];
        st = new StringTokenizer(infile.readLine());
        for(int i=0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        return arr;
    }

    //for debugging only
    public static void print(int[] arr) {
        for (int x : arr)
            out.print(x + " ");
        out.println();
    }

    //for debugging only
    public static <T> void printObject(T[] arr) {
        for(T x : arr)
            out.print(x+" ");
        out.println();
    }

    public static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n % 2 == 0 || n % 3 == 0) return false;
        long sqrtN = (long)Math.sqrt(n) + 1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n % (i - 1) == 0 || n % (i + 1) == 0) return false;
        }
        return true;
    }

    public static long gcd(long a, long b) {
        if(a > b)
            a = (a + b) - (b = a);
        if(a == 0L)
            return b;
        return gcd(b % a, a);
    }

    public static void sort(int[] arr) {
        //because Arrays.sort() uses quicksort which is not ideal
        //Collections.sort() uses merge sort
        ArrayList<Integer> ls = new ArrayList<Integer>();
        for(int x: arr)
            ls.add(x);
        Collections.sort(ls);
        for(int i = 0; i < arr.length; i++)
            arr[i] = ls.get(i);
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


    public static boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        String palindrome = "";

        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) 
                palindrome += s.charAt(i);
        }
        palindrome = palindrome.toLowerCase();

        int left = 0;
        int right = palindrome.length() - 1; 

        while (left < right) {
            if (palindrome.charAt(left) != palindrome.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0 ) {
                prefix = prefix.substring(0, prefix.length() - 1);

            }
        }
        return prefix;
    }


    public static List<List<Integer>> demo(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result  = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
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

                } else if (sum < 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    // Kadane's Algorithm
    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0 ; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(sum, max);

            if (sum < 0) sum = 0;
        }
        return max;
    }


    // to print maxSubArray index
    public static int maxSubArrayPrint(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        // two pointer, one point to start index, the other point to the end index; include start_index and end_index
        int start_index = -1, end_index = -1;
        int temp_start_index = -1; // the temporary start position


        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum > max) {
                end_index = i;
                start_index = temp_start_index;
            }
            max = max(sum, max);
            if (sum < 0){
                sum = 0;
                temp_start_index = i + 1;
            }
        }
        out.println("the start index is " + start_index + " while end index is " + end_index);

        return max;
    }

    // 1st mehthod
    public static int numSubarraysWithSum(int[] nums, int goal) {
        if (nums == null || nums.length == 0) return 0;
        int numOfSubArrays = 0;
        int currentSum = 0;
        int left = 0, right = 0;

        while (left < nums.length && right < nums.length) {
            currentSum += nums[right];

            if (currentSum < goal) {
                right++; // keep finding
            }
            else if (currentSum > goal) {
                left++;
                right = left; // increase right from new left po.
                currentSum = 0;
            } else {
                numOfSubArrays++;
                right++;

                if (right == nums.length) {
                    left++;
                    right = left; // increase right from new left po.
                    currentSum = 0;
                }
            }
        } 
        return numOfSubArrays;
    }


    // 2nd method
   public static int numSubarraysWithSumTwo(int[] input, int goal) {
        int n = input.length;
        int[] arr = new int[n + 1];
        for (int i = 0; i < n; ++i)
            arr[i + 1] = arr[i] + input[i];

        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        for (int i: arr) {
            ans += count.getOrDefault(i, 0);
            count.put(i+goal, count.getOrDefault(i+goal, 0) + 1);
        }
        return ans;
    }

    public static int lengthOfLongestSubstring(String s) {
        int i = 0;
        int j = 0;
        int max = 0;

        Set<Character> set = new HashSet<>();

        while (i < s.length()) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                set.add(c);
                max = max(max, i - j + 1);
                i++;
            } else {
                set.remove(s.charAt(j));
                j++; 
            }
        }
        return max;
    }


    // 349. Intersection of Two Arrays
    // 1304. Find N Unique Integers Sum up to Zero
    // 27. Remove Element

}