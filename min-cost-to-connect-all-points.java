import java.util.*;

class Solution1584 {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        int edgesUsed = 0;
        int cost = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0}); // {cost, pointIndex}
        
        while (edgesUsed < n) {
            int[] curr = pq.poll();
            int w = curr[0], u = curr[1];
            
            if (visited[u]) continue;
            
            visited[u] = true;
            cost += w;
            edgesUsed++;
            
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0]) +
                               Math.abs(points[u][1] - points[v][1]);
                    pq.offer(new int[]{dist, v});
                }
            }
        }
        return cost;
    }
}
