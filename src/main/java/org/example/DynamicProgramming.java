package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;

public class DynamicProgramming {

    //1. Climbing Stairs
    public int climbStairs(int n) {

        //Approach 1: Recursion
        if (n == 0 || n == 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
        //TC: O(2^n)
        //SC: O(N)

        //Approach 2: Memoization
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        if (n <= 1) {
            return 1;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = climbStairs(n - 1) + climbStairs(n - 2);
        return dp[n];
        //TC: O(N)
        //SC: O(N);

        //Approach 3: Tabulation
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
        //TC: O(N)
        //SC: O(N);
    }

    //2. Coin Change
    public int coinChange(int[] coins, int amount) {

        //Approach: Tabulation
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] >= dp[amount] ? -1 : dp[amount];
        //TC: O(N*N)
        //SC: O(N)
    }

    //3. Longest Increasing Subsequence
    public int lengthOfLIS(int[] nums) {

        //Approach: Tabulation
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int ele : dp) {
            max = Math.max(ele, max);
        }
        return max;
        //TC: O(N*N)
        //SC: O(N);
    }

    //4. Longest Common Subsequence
    public int longestCommonSubsequence(String text1, String text2) {

        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
        //TC: O(N*N)
        //SC: O(N*N)
    }

    //5. Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target){

        List<List<Integer>> result = new ArrayList<>();
        find(0, result, new ArrayList<>(), candidates, target);
        return result;
        //TC: O(2^N)
        //SC: O(N)
    }
    public static void find(int index, List<List<Integer>> result, List<Integer> list, int[] nums, int target){

        if(nums.length == index){
            if(target == 0){
                result.add(new ArrayList<>(list));
            }
            return;
        }

        if(nums[index] <= target){
            list.add(nums[index]);
            find(index, result, list, nums, target - nums[index]);
            list.remove(list.size() - 1);
        }
        find(index+1, result, list, nums, target);
    }

    //6. House Robber
    public int rob(int[] nums){

        //Approach: Tabulation
        int n = nums.length;
        int[] dp = new int[n];

        if(n < 2){
            return nums[0];
        }

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], dp[0]);

        for(int i = 2; i<n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n-1];
        //TC: O(N)
        //SC: O(N)
    }

    //7. House Robber II
    public int robcirc(int[] nums){

        int n = nums.length;
        int[] start = new int[n-1];
        int[] end = new int[n-1];

        if(n < 2){
            return nums[0];
        }
        for(int i = 0; i<n-1; i++){
            start[i] = nums[i];
            end[i] = nums[i+1];
        }
        int s = find(start);
        int e = find(end);

        return Math.max(s, e);
        //TC: O(N)
        //SC: O(N)
    }
    public int find(int[] arr){

        int n = arr.length;
        int[] dp = new int[n];
        if(n < 2){
            return arr[0];
        }
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for(int i = 2; i<n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + arr[i]);
        }
        return dp[n-1];
    }

    //8. Unique Paths
    public int uniquePaths(int m, int n){

        int[][] dp = new int[m][n];

        for(int[] arr: dp){
            Arrays.fill(arr, 1);
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
        //TC: O(N*N)
        //SC: O(N*N)
    }

    //9. Word Break
    public boolean wordBreak(String s, List<String> wordDict){

        HashSet<String> set = new HashSet<>(wordDict);
        int n = s.length();

        int max = 0;
        for(String ele: set){
            max = Math.max(max, ele.length());
        }

        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for(int i = 0; i <= n; i++){
            for(int j = i-1; j >= Math.max(0, i-max); j--){
                if(dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                }
            }
        }
        return dp[n];
        //TC: O(N*N)
        //SC: O(N)
    }

    //10. Jump Game
    public boolean canJump(int[] nums){

        //Approach: Starting from index 0
        int idx = 0;

        for(int i = 0; i < nums.length; i++){
            if(i > idx){
                return false;
            }
            idx = Math.max(idx, i+nums[i]);
        }
        return true;
        //TC: O(N)
        //SC: O(1)

        //Approach: Starting from index nums.length-1
        int idx = nums.length - 1;

        for(int i = nums.length - 2; i >= 0; i--){
            if(i + nums[i] >= idx){
                idx = i;
            }
        }
        return idx == 0;
        //TC: O(N)
        //SC: O(1)
    }
}
