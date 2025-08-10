import java.util.*;

class Solution947_UF {
    public int removeStones(int[][] stones) {
        UnionFind uf = new UnionFind(20000); // Enough for rows + columns
        Set<Integer> unique = new HashSet<>();
        
        for (int[] stone : stones) {
            uf.union(stone[0], stone[1] + 10000); // Offset columns
            unique.add(stone[0]);
            unique.add(stone[1] + 10000);
        }
        
        int components = 0;
        for (int x : unique) {
            if (uf.find(x) == x) components++;
        }
        return stones.length - components;
    }
    
    static class UnionFind {
        int[] parent;
        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }
}
