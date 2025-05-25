package org.example;
import java.util.*;

public class Arrays {

    //1. Two Sum
    public static int[] twoSum(int[] nums, int target){

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for(int i = 0; i < nums.length; i++){
            int difference = target - nums[i];

            if(map.containsKey(difference)){
                result[0] = map.get(difference);
                result[1] = i;
            }
            map.put(nums[i], i);
        }
        return result;
        //TC: O(N)
        //SC: O(N)
    }

    //2. Best Time to Buy and Sell Stock

    public static int maxProfit(int[] prices){

        int max = 0;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < prices.length; i++){
            min = Math.min(min, prices[i]);
            int profit = prices[i] - min;
            max = Math.max(max, profit);
        }
        return max;
        //TC: O(N)
        //SC: O(N)
    }

    //3. Contains Duplicate

    public boolean containsDuplicate(int[] nums){
        //Approach 1 - Sorting
        java.util.Arrays.sort(nums);
        for(int i = 0; i < nums.length-1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
        //TC: O(NlogN)
        //SC: O(1)

        //Approach 2 - HashMap
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int ele : nums){
            map.put(ele, map.getOrDefault(ele, 0)+1);
        }
        for(Map.Entry<Integer, Integer> val: map.entrySet()){
            if(val.getValue() > 1){
                return true;
            }
        }
        return false;
        //TC: O(N)
        //SC: O(N)
    }

    //4. Product of Array Except Self

    public int[] productExceptSelf(int[] nums){

        int n = nums.length;
        int[] result = new int[n];
        int[] right = new int[n];
        int[] left = new int[n];

        left[0] = 1;
        for(int i = 1; i < n; i++){
            left[i] = left[i-1] * nums[i-1];
        }

        right[n-1] = 1;
        for(int i = n-2; i >= 0; i--){
            right[i] = right[i+1] * nums[i+1];
        }

        for(int i = 0; i < n; i++){
            result[i] = left[i] * right[i];
        }
        return result;
    }

    //5. Maximum Subarray Sum - Kadane's Algorithm

    public int maxSubArray(int[] nums){

        int sum = 0;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++){
            sum = sum + nums[i];

            if(sum > max){
                max = sum;
            }

            if(sum < 0) {
                sum = 0;
            }
        }
        return max;
        //TC: O(N)
        //SC: O(1)
    }

    //6. Maximum Product Subarray

    public int maxProduct(int[] nums){

        int n = nums.length;
        int left = 1;
        int right = 1;
        int product = nums[0];

        for(int i = 0; i < n; i++){

            left = (left == 0) ? 1 : left;
            right = (right == 0) ? 1 : right;

            left = nums[i] * left;
            right = nums[n - i - 1] * right;

            int maxProduct = Math.max(left, right);
            product = Math.max(product, maxProduct);
        }
        return product;
        //TC: O(N)
        //SC: O(1)
    }

    //7. Find Minimum in Rotated Sorted Array

    public int findMin(int[] nums){

        //Approach 1 - Linear Search
        int min = Integer.MAX_VALUE;
        for(int ele : nums){
            min = Math.min(min, ele);
        }
        return min;
        //TC: O(N)
        //SC: O(1)

        //Approach 2 - Binary Search
        int start = 0;
        int end = nums.length - 1;
        int min = Integer.MAX_VALUE;

        while(start < end){
            int mid = (start + end) /2;

            if(nums[mid] >= nums[start]){
                min = Math.min(min, nums[start]);
                start = mid + 1 ;
            }
            else{
                min = Math.min(min, nums[mid]);
                end = mid - 1;
            }
        }
        return min;
        //TC: O(logN)
        //SC: O(1)
    }

    //8. Search in Rotated Sorted Array

    public int search(int[] nums, int target){

        //Approach 1 - Linear Search
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                return i;
            }
        }
        return -1;
        //TC: O(N)
        //SC: O(1)

        //Approach 2 - Binary Search
        int low = 0;
        int high = nums.length - 1;
        int min = Integer.MAX_VALUE;

        while(low <= high){

            int mid = (low + high)/2;

            if(nums[mid] == target){
                return mid;
            }

            if(nums[mid] >= nums[low]){ //first half of array is sorted
                if(nums[low] <= target && target <= nums[mid]){
                    high = mid - 1;
                }
                else{
                    low = mid + 1;
                }
            }
            else{ //second half of array is sorted
                if(nums[mid] <= target && target <= nums[high]){
                    low = mid + 1;
                }
                else{
                    high = mid - 1;
                }
            }
        }
        return -1;
        //TC: O(logN)
        //SC: O(1)
    }

    //9. 3 Sum

    public List<List<Integer>> threeSum(int[] nums){

        //Approach 1 - 3 pointer(Brute Force)
        HashSet<List<Integer>> set = new HashSet<>();
        int n = nums.length;

        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                for(int k = j+1; k < n; k++){
                    if(nums[i] + nums[j] + nums[k] == 0){
                        List<Integer> res = new ArrayList<>();
                        res.add(nums[i]);
                        res.add(nums[j]);
                        res.add(nums[k]);
                        Collections.sort(res);
                        set.add(res);
                    }
                }
            }
        }
        List<List<Integer>> list = new ArrayList<>(set);
        return list;
        //TC: O(N*N*N)
        //SC: O(N)

        //Approach 2 : Better Time Complexity using Extra Space
        HashSet<List<Integer>> set = new HashSet<>();
        int n = nums.length;

        for(int i = 0; i < n; i++){
            HashSet<Integer> hash = new HashSet<>();
            for(int j = i+1; j < n; j++){
                int k = -(nums[i] + nums[j]);

                if(hash.contains(k)){
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[i]);
                    res.add(nums[j]);
                    res.add(k);
                    Collections.sort(res);
                    set.add(res);
                }
                hash.add(nums[j]);
            }
        }
        List<List<Integer>> list = new ArrayList<>(set);
        return list;
        //TC: O(N*N)
        //SC: O(N)

        //Approach 3 : Two Pointers + Sorting + No Extra Space
        java.util.Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();

        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while(j < k){
                int sum = nums[i] + nums[j] + nums[k];

                if(sum < 0){
                    j++;
                }
                else if(sum > 0){
                    k--;
                }
                else{
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[i]);
                    res.add(nums[j]);
                    res.add(nums[k]);
                    list.add(res);
                    j++;
                    k--;

                    while(j < k && nums[j] == nums[j-1]){
                        j++;
                    }
                    while(j < k && nums[k] == nums[k+1]){
                        k--;
                    }
                }
            }
        }
        return list;
        //TC: O(N*N + NlogN)
        //SC: O(1)
    }

    //10. Container With Most Water

    public int maxArea(int[] height) {

        //Approach 1 : Brute Force
        int maxArea = Integer.MIN_VALUE;
        int n = height.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int length = Math.min(height[i], height[j]);
                int width = j - i;
                int area = length * width;
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
        //TC: O(N*N)
        //SC: O(1)

        //Approach 2: Two Pointer
        int n = height.length;
        int i = 0;
        int j = n - 1;
        int maxArea = Integer.MIN_VALUE;

        while (i < j) {
            int length = Math.min(height[i], height[j]);
            int width = j - i;
            int area = length * width;
            maxArea = Math.max(maxArea, area);

            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return maxArea;
        //TC: O(N)
        //SC: O(1)
    }
}
