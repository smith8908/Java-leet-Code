import java.util.*;

class Solution632 {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int maxVal = Integer.MIN_VALUE;
        
        // Step 1: Initialize heap with first element of each list
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            pq.offer(new int[]{val, i, 0});
            maxVal = Math.max(maxVal, val);
        }
        
        int start = 0, end = Integer.MAX_VALUE;
        
        // Step 2: Process until one list runs out
        while (pq.size() == nums.size()) {
            int[] cur = pq.poll();
            int val = cur[0], row = cur[1], idx = cur[2];
            
            // Update best range
            if (maxVal - val < end - start) {
                start = val;
                end = maxVal;
            }
            
            // Move to next element in same list
            if (idx + 1 < nums.get(row).size()) {
                int nextVal = nums.get(row).get(idx + 1);
                pq.offer(new int[]{nextVal, row, idx + 1});
                maxVal = Math.max(maxVal, nextVal);
            } else {
                break; // One list is exhausted
            }
        }
        
        return new int[]{start, end};
    }
}
