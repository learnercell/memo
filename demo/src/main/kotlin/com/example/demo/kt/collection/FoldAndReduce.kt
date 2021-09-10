package com.example.demo.kt.collection

import org.junit.Test

class FoldAndReduce {

    /**
     * Fold 与 reduce
     * 对于更特定的情况，有函数 reduce() 和 fold()，它们依次将所提供的操作应用于集合元素并返回累积的结果。
     * ----操作有两个参数：先前的累积值和集合元素。---
     * 这两个函数的区别在于：fold() 接受一个初始值并将其用作第一步的累积值，
     * 而 reduce() 的第一步则将第一个和第二个元素作为第一步的操作参数。
     */

    @Test
    fun foldReduceTest() {
        val numbers = listOf(5, 2, 10, 4)

        val sum = numbers.reduce { sum, element -> sum + element }
        println(sum)
        val sumDoubled = numbers.fold(0) { sum, element -> sum + element * 2 } //第一步 sum=0
        println(sumDoubled)
        val sumDoubled2 = numbers.reduce { sum, element -> sum + element * 2 } //第一个元素在结果中没有加倍 第一步 sum=5
        println(sumDoubled2)
    }
}