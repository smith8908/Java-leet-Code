import java.util.*;

class Solution787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // Build adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] f : flights) {
            graph.computeIfAbsent(f[0], k -> new ArrayList<>()).add(new int[]{f[1], f[2]});
        }
        
        // Min-heap: {cost, city, stops}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src, 0});
        
        // dist[city][stops] = min cost to reach city with stops
        int[][] dist = new int[n][K + 2];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[src][0] = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], city = cur[1], stops = cur[2];
            
            if (city == dst) return cost;
            if (stops > K) continue;
            
            if (!graph.containsKey(city)) continue;
            for (int[] nei : graph.get(city)) {
                int next = nei[0], price = nei[1];
                int newCost = cost + price;
                if (newCost < dist[next][stops + 1]) {
                    dist[next][stops + 1] = newCost;
                    pq.offer(new int[]{newCost, next, stops + 1});
                }
            }
        }
        return -1;
    }
}
