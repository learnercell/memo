package com.example.demo.algorithm.leetcode.dynamic

/**
 * 重叠子问题、最优子结构、状态转移方程
 */
object Fib {
    /**
     * 重叠子问题
     */
    fun getSimpleFib(n: Int): Int {
        if (n == 0) {
            return 0
        }
        return if (n == 1) {
            1
        } else getSimpleFib(n - 1) + getSimpleFib(n - 2)
    }

    fun getCacheFib(n: Int): Int {
        val cache = HashMap<Int, Int>()
        return fibHelper(n, cache)
    }

    private fun fibHelper(n: Int, cache: MutableMap<Int, Int>): Int {
        if (n == 0) {
            return 0
        }
        if (n == 1) {
            return 1
        }
        val target = cache[n]
        if (target != null) {
            return target
        }
        val i = fibHelper(n - 1, cache) + fibHelper(n - 2, cache)
        cache[n] = i
        return i
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(getSimpleFib(20))
        println(getCacheFib(20))
    }
}