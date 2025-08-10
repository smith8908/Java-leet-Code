class TreeAncestor {
    int[][] up;
    int LOG;
    int n;

    public TreeAncestor(int n, int[] parent) {
        this.n = n;
        LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        up = new int[n][LOG];
        
        for (int i = 0; i < n; i++) up[i][0] = parent[i];
        
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                if (up[i][j - 1] != -1)
                    up[i][j] = up[up[i][j - 1]][j - 1];
                else
                    up[i][j] = -1;
            }
        }
    }
    
    public int getKthAncestor(int node, int k) {
        for (int j = 0; j < LOG; j++) {
            if (((k >> j) & 1) == 1) {
                node = up[node][j];
                if (node == -1) break;
            }
        }
        return node;
    }
}
