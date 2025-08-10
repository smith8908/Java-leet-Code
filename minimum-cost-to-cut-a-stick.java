import java.util.*;

public class MinimumCostToCutStick {

    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] arr = new int[m + 2];
        
        // Add stick start(0) and end(n) to cuts
        arr[0] = 0;
        arr[m + 1] = n;
        for (int i = 0; i < m; i++) {
            arr[i + 1] = cuts[i];
        }
        
        // Sort cuts
        Arrays.sort(arr);

        // DP array: dp[i][j] stores min cost to cut between arr[i] and arr[j]
        int[][] dp = new int[m + 2][m + 2];
        
        // Bottom-up DP
        for (int length = 2; length < m + 2; length++) { // length of the segment
            for (int i = 0; i + length < m + 2; i++) {
                int j = i + length;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) { // try each cut inside (i, j)
                    dp[i][j] = Math.min(dp[i][j], arr[j] - arr[i] + dp[i][k] + dp[k][j]);
                }
                if (dp[i][j] == Integer.MAX_VALUE) dp[i][j] = 0; // No cuts
            }
        }

        return dp[0][m + 1];
    }

    public static void main(String[] args) {
        MinimumCostToCutStick obj = new MinimumCostToCutStick();
        int n = 7;
        int[] cuts = {1, 3, 4, 5};
        System.out.println(obj.minCost(n, cuts)); // Output: 16
    }
}
