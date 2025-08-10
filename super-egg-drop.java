class Solution:
    def superEggDrop(self, k: int, n: int) -> int:
        memo = {}

        def dp(eggs, floors):
            # Base cases
            if floors == 0 or floors == 1:
                return floors
            if eggs == 1:
                return floors

            if (eggs, floors) in memo:
                return memo[(eggs, floors)]

            low, high = 1, floors
            res = float('inf')

            while low <= high:
                mid = (low + high) // 2
                break_case = dp(eggs - 1, mid - 1)   # Egg breaks
                no_break_case = dp(eggs, floors - mid)  # Egg doesn't break

                worst = 1 + max(break_case, no_break_case)
                res = min(res, worst)

                if break_case > no_break_case:
                    high = mid - 1
                else:
                    low = mid + 1

            memo[(eggs, floors)] = res
            return res

        return dp(k, n)
