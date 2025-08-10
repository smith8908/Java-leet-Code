class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        
        // dp[i][j] = min score to triangulate polygon between vertices i and j
        for (int len = 3; len <= n; len++) { // length of the polygon section
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) { // choose a third vertex to form a triangle
                    int cost = dp[i][k] + dp[k][j] + values[i] * values[j] * values[k];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}
