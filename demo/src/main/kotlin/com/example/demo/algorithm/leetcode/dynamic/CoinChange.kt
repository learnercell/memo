package com.example.demo.algorithm.leetcode.dynamic

class CoinChange {
    /**
     * 这是一个最值问题。
     *
     * 重叠子问题、最优子结构、状态转移方程
     * @param coins      [ 1, 2, 5]
     * @param totalMoney 11
     */
    fun coinChange(coins: IntArray, totalMoney: Int): Int {
        if (coins.size == 0) {
            return -1
        }
        val memo = IntArray(totalMoney + 1)

        //
        memo[0] = 0
        for (i in 1..totalMoney) {
            var min = Int.MAX_VALUE
            for (j in coins.indices) {
                // 11- 1;11-2; 11-5
                val il = i - coins[j]
                if (il >= 0 && memo[il] < min) {
                    min = memo[il] + 1
                }
            }
            memo[i] = min
        }
        return if (memo[totalMoney] == Int.MAX_VALUE) -1 else memo[totalMoney]
    }
}