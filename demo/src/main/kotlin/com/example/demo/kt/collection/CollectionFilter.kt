package com.example.demo.kt.collection

import org.junit.Test

/**
 * 过滤
 *
 * 过滤是最常用的集合处理任务之一。在Kotlin中，过滤条件由 谓词 定义——接受一个集合元素并且返回布尔值的 lambda 表达式：
 * true 说明给定元素与谓词匹配，false 则表示不匹配。
 */
class CollectionFilter {
    /**
     * 基本的过滤函数是 filter()。当使用一个谓词来调用时，filter() 返回与其匹配的集合元素。
     * 对于 List 和 Set，过滤结果都是一个 List，对 Map 来说结果还是一个 Map。
     */
    @Test
    fun filterTest() {
        val numbers = listOf("one", "two", "three", "four")
        val longerThan3 = numbers.filter { it.length > 3 }
        println(longerThan3)

        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
        val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10}
        println(filteredMap)
    }

    /**
     * filter() 中的谓词只能检查元素的值。如果想在过滤中使用元素在集合中的位置，应该使用 filterIndexed()。
     * 它接受一个带有两个参数的谓词：元素的索引和元素的值。
     * 如果想使用否定条件来过滤集合，请使用 filterNot()。它返回一个让谓词产生 false 的元素列表。
     */
    @Test
    fun filterIndexedTest() {
        val numbers = listOf("one", "two", "three", "four")

        val filteredIdx = numbers.filterIndexed { index, s -> (index != 0) && (s.length < 5)  }
        val filteredNot = numbers.filterNot { it.length <= 3 }

        println(filteredIdx)
        println(filteredNot)
    }

    /**
     * filterIsInstance() 返回给定类型的集合元素。在一个 List<Any> 上被调用时，
     * filterIsInstance<T>() 返回一个 List<T>，从而让你能够在集合元素上调用 T 类型的函数。
     */
    @Test
    fun filterIsInstanceTest() {
        val numbers = listOf(null, 1, "two", 3.0, "four")
        numbers.filterIsInstance<Number>().forEach {
            println(it)
           // println(it.uppercase(Locale.getDefault()))
        }
    }

    /**
     * filterNotNull() 返回所有的非空元素。在一个 List<T?> 上被调用时，
     * filterNotNull() 返回一个 List<T: Any>，从而让你能够将所有元素视为非空对象。
     */
    @Test
    fun filterNotNull() {
        val numbers = listOf(null, "one", "two", null)
        numbers.filterNotNull().forEach {
            println(it.length)   // 对可空的 String 来说长度不可用
        }
    }

    /**
     * 划分
     * 另一个过滤函数 – partition() – 通过一个谓词过滤集合并且将不匹配的元素存放在一个单独的列表中。
     * 因此，你得到一个 List 的 Pair 作为返回值：第一个列表包含与谓词匹配的元素并且第二个列表包含原始集合中的所有其他元素
     */
    @Test
    fun partitionTest() {
        val numbers = listOf("one", "two", "three", "four")
        val (match, rest) = numbers.partition { it.length > 3 }

        println(match)
        println(rest)
    }
    /**
     * 检验谓词
     * 最后，有些函数只是针对集合元素简单地检测一个谓词： *
     * 如果至少有一个元素匹配给定谓词，那么 any() 返回 true。
     * 如果没有元素与给定谓词匹配，那么 none() 返回 true。
     * 如果所有元素都匹配给定谓词，那么 all() 返回 true。
     *
     * 注意，在一个空集合上使用任何有效的谓词去调用 all() 都会返回 true 。这种行为在逻辑上被称为 vacuous truth。
     */
    @Test
    fun anyNoneTest() {
        val numbers = listOf("one", "two", "three", "four")

        println(numbers.any { it.endsWith("e") })
        println(numbers.none { it.endsWith("a") })
        println(numbers.all { it.endsWith("e") })

        println(emptyList<Int>().all { it > 5 }) //vacuous truth
    }

}

