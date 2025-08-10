import java.util.*;

class Solution {
    static class State {
        int cost, time, node;
        State(int cost, int time, int node) {
            this.cost = cost;
            this.time = time;
            this.node = node;
        }
    }

    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;

        // Build graph
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]}); // node, time
            graph[e[1]].add(new int[]{e[0], e[2]});
        }

        // minTime[node] = min time taken to reach node
        int[] minTime = new int[n];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        minTime[0] = 0;

        // Priority Queue: sort by cost
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new State(passingFees[0], 0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int cost = cur.cost, time = cur.time, node = cur.node;

            // If destination reached
            if (node == n - 1) return cost;

            // Explore neighbors
            for (int[] nei : graph[node]) {
                int nextNode = nei[0], travelTime = nei[1];
                int newTime = time + travelTime;
                int newCost = cost + passingFees[nextNode];

                if (newTime <= maxTime && newTime < minTime[nextNode]) {
                    minTime[nextNode] = newTime;
                    pq.offer(new State(newCost, newTime, nextNode));
                }
            }
        }
        return -1; // Not reachable
    }
}
