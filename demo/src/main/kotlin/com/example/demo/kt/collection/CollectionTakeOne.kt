package com.example.demo.kt.collection

import org.junit.Test

/**
 * 取单个元素
 */
class CollectionTakeOne {

    /**
     * 按位置取
     * 为了检索特定位置的元素，有一个函数 elementAt()。 用一个整数作为参数来调用它，你会得到给定位置的集合元素。
     * 第一个元素的位置是 0，最后一个元素的位置是 (size - 1)。
     *
     * elementAt() 对于不提供索引访问或非静态已知提供索引访问的集合很有用。
     */
    @Test
    fun elementAtTest() {
        val numbers = linkedSetOf("one", "two", "three", "four", "five")
        println(numbers.elementAt(3))

        val numbersSortedSet = sortedSetOf("one", "two", "three", "four")
        println(numbersSortedSet.elementAt(0)) // 元素以升序存储

        println("---------------------------------------")

        /**
         * 为了避免在检索位置不存在的元素时出现异常，请使用 elementAt() 的安全变体：
         * 当指定位置超出集合范围时，elementAtOrNull() 返回 null。
         * elementAtOrElse() 还接受一个 lambda 表达式，该表达式能将一个 Int 参数映射为一个集合元素类型的实例。
         * 当使用一个越界位置来调用时，elementAtOrElse() 返回对给定值调用该 lambda 表达式的结果。
         */

        val numbers2 = listOf("one", "two", "three", "four", "five")
        println(numbers2.elementAtOrNull(5))
        println(numbers2.elementAtOrElse(5) { index -> "The value for index $index is undefined"})

    }
}