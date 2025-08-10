import java.util.*;

class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        int edgesUsed = 0;
        int totalCost = 0;

        // Min-heap storing {cost, pointIndex}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Start from point 0
        pq.offer(new int[]{0, 0});

        while (edgesUsed < n) {
            int[] curr = pq.poll();
            int cost = curr[0];
            int pointIndex = curr[1];

            if (visited[pointIndex]) {
                continue;
            }

            visited[pointIndex] = true;
            totalCost += cost;
            edgesUsed++;

            for (int next = 0; next < n; next++) {
                if (!visited[next]) {
                    int nextCost = Math.abs(points[pointIndex][0] - points[next][0]) +
                                   Math.abs(points[pointIndex][1] - points[next][1]);
                    pq.offer(new int[]{nextCost, next});
                }
            }
        }

        return totalCost;
    }
}
