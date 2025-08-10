class Solution1932 {
    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        
        for (TreeNode t : trees) {
            map.put(t.val, t);
            count.put(t.val, count.getOrDefault(t.val, 0) + 1);
            if (t.left != null) count.put(t.left.val, count.getOrDefault(t.left.val, 0) + 1);
            if (t.right != null) count.put(t.right.val, count.getOrDefault(t.right.val, 0) + 1);
        }
        
        TreeNode root = null;
        for (TreeNode t : trees) {
            if (count.get(t.val) == 1) {
                root = t;
                break;
            }
        }
        if (root == null) return null;
        
        if (!merge(root, map, Integer.MIN_VALUE, Integer.MAX_VALUE) || map.size() != 1)
            return null;
        
        return root;
    }
    
    private boolean merge(TreeNode root, Map<Integer, TreeNode> map, int low, int high) {
        if (root == null) return true;
        if (root.val <= low || root.val >= high) return false;
        
        if (root.left == null && root.right == null && map.containsKey(root.val) && map.get(root.val) != root) {
            TreeNode t = map.get(root.val);
            root.left = t.left;
            root.right = t.right;
            map.remove(t.val);
        }
        
        return merge(root.left, map, low, root.val) && merge(root.right, map, root.val, high);
    }
}
