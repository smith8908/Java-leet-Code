import java.util.*;

public class PartitionMinDifference {
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        int totalSum = Arrays.stream(nums).sum();
        
        int[] left = Arrays.copyOfRange(nums, 0, n / 2);
        int[] right = Arrays.copyOfRange(nums, n / 2, n);
        
        List<List<Integer>> leftSums = generateSums(left);
        List<List<Integer>> rightSums = generateSums(right);
        
        for (List<Integer> list : rightSums) Collections.sort(list);
        
        int ans = Integer.MAX_VALUE;
        
        for (int k = 0; k <= n / 2; k++) {
            for (int sumLeft : leftSums.get(k)) {
                int target = totalSum / 2 - sumLeft;
                
                List<Integer> rightList = rightSums.get(n / 2 - k);
                int idx = Collections.binarySearch(rightList, target);
                
                if (idx < 0) idx = -idx - 1;
                
                if (idx < rightList.size()) {
                    int sumRight = rightList.get(idx);
                    ans = Math.min(ans, Math.abs(totalSum - 2 * (sumLeft + sumRight)));
                }
                if (idx > 0) {
                    int sumRight = rightList.get(idx - 1);
                    ans = Math.min(ans, Math.abs(totalSum - 2 * (sumLeft + sumRight)));
                }
            }
        }
        return ans;
    }

    private List<List<Integer>> generateSums(int[] arr) {
        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i <= n; i++) res.add(new ArrayList<>());
        
        for (int mask = 0; mask < (1 << n); mask++) {
            int bits = Integer.bitCount(mask);
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) sum += arr[j];
            }
            res.get(bits).add(sum);
        }
        return res;
    }

    public static void main(String[] args) {
        PartitionMinDifference obj = new PartitionMinDifference();
        System.out.println(obj.minimumDifference(new int[]{3,9,7,3})); // Output: 2
    }
}
