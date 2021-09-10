package com.example.demo.kt.collection

import org.junit.Test

/**
 * 取集合的一部分
 * Kotlin 标准库包含用于取集合的一部分的扩展函数。
 * 这些函数提供了多种方法来选择结果集合的元素：显式列出其位置、指定结果大小等。
 */
class CollectionTake {

    /**
     * Slice
     * slice() 返回具有给定索引的集合元素列表。 索引既可以是作为区间传入的也可以是作为整数值的集合传入的
     */
    @Test
    fun sliceTest() {
        val numbers = listOf("one", "two", "three", "four", "five", "six")
        println(numbers.slice(1..3))
        println(numbers.slice(0..4 step 2))
        println(numbers.slice(setOf(3, 5, 0)))
        println(numbers.slice(listOf(1, 2, 0)))
    }

    /**
     * Take 与 drop
     * 要从头开始获取指定数量的元素，请使用 take() 函数。 要从尾开始获取指定数量的元素，请使用 takeLast()。
     * 当调用的数字大于集合的大小时，两个函数都将返回整个集合。
     * 要从头或从尾去除给定数量的元素，请调用 drop() 或 dropLast() 函数。
     */
    @Test
    fun takeDropTest() {
        val numbers = listOf("one", "two", "three", "four", "five", "six")
        println(numbers.take(3))
        println(numbers.takeLast(3))
        println(numbers.drop(1))
        println(numbers.dropLast(5))
        println(numbers.take(7))
        println(numbers.drop(7))
    }

    /**
     * 还可以使用谓词来定义要获取或去除的元素的数量。 有四个与上述功能相似的函数：
     * takeWhile() 是带有谓词的 take()：它将不停获取元素直到排除与谓词匹配的首个元素。如果首个集合元素与谓词匹配，则结果为空。
     * takeLastWhile() 与 takeLast() 类似：它从集合末尾获取与谓词匹配的元素区间。
     * 区间的首个元素是与谓词不匹配的最后一个元素右边的元素。如果最后一个集合元素与谓词匹配，则结果为空。
     * dropWhile() 与具有相同谓词的 takeWhile() 相反：它将首个与谓词不匹配的元素返回到末尾。
     * dropLastWhile() 与具有相同谓词的 takeLastWhile() 相反：它返回从开头到最后一个与谓词不匹配的元素。
     */
    @Test
    fun takeDropWhileTest() {
        val numbers = listOf("one", "two", "three", "four", "five", "six")
        println(numbers.takeWhile { !it.startsWith('f') })
        println(numbers.takeLastWhile { it != "three" })
        println(numbers.dropWhile { it.length == 3 })
        println(numbers.dropLastWhile { it.contains('i') })
        println(numbers.takeWhile { !it.startsWith('o') })
    }

    /**
     * Chunked
     *
     * 要将集合分解为给定大小的“块”，请使用 chunked() 函数。
     * chunked() 采用一个参数（块的大小），并返回一个 List 其中包含给定大小的 List。
     * 第一个块从第一个元素开始并包含 size 元素，第二个块包含下一个 size 元素，依此类推。 最后一个块的大小可能较小。
     */
     @Test
     fun chunkTest() {
        val numbers = (0..13).toList()
        println(numbers.chunked(3))
        println("-------------------------------------------------")
        /**
         * 还可以立即对返回的块应用转换。 为此，请在调用 chunked() 时将转换作为 lambda 函数提供。 lambda 参数是集合的一块。
         * 当通过转换调用 chunked() 时， 这些块是临时的 List，应立即在该 lambda 中使用。
         */

        println(numbers.chunked(3) { it.sum() })  // `it` 为原始集合的一个块
    }

    /**
     * Windowed
     * 可以检索给定大小的集合元素中所有可能区间。 获取它们的函数称为 windowed()：它返回一个元素区间列表，比如通过给定大小的滑动窗口查看集合，则会看到该区间。
     * 与 chunked() 不同，windowed() 返回从每个集合元素开始的元素区间（窗口）。 所有窗口都作为单个 List 的元素返回。
     */

    @Test
    fun windowedTest() {
        val numbers = listOf("one", "two", "three", "four", "five")
        println(numbers.windowed(2))
        println("-------------------------------------------------")
        /**
         * windowed() 通过可选参数提供更大的灵活性：
         * step 定义两个相邻窗口的第一个元素之间的距离。默认情况下，该值为 1，因此结果包含从所有元素开始的窗口。如果将 step 增加到 2，将只收到以奇数元素开头的窗口：第一个、第三个等。
         * partialWindows 包含从集合末尾的元素开始的较小的窗口。例如，如果请求三个元素的窗口，就不能为最后两个元素构建它们。在本例中，启用 partialWindows 将包括两个大小为2与1的列表。
         * 最后，可以立即对返回的区间应用转换。 为此，在调用 windowed() 时将转换作为 lambda 函数提供。
         */
        val numbersInt = (1..10).toList()
        println(numbersInt.windowed(3, step = 3, partialWindows = true))
        println(numbersInt.windowed(3, step = 3, partialWindows = false))
        println(numbersInt.windowed(3) { it.sum() })

        println("-------------------------------------------------")

        /**
         * 要构建两个元素的窗口，有一个单独的函数——zipWithNext()。 它创建接收器集合的相邻元素对。
         * 请注意，zipWithNext() 不会将集合分成几对；它为 每个 元素创建除最后一个元素外的对，
         * 因此它在 [1, 2, 3, 4] 上的结果为 [[1, 2], [2, 3], [3, 4]]，而不是 [[1, 2]，[3, 4]]。
         * zipWithNext() 也可以通过转换函数来调用；它应该以接收者集合的两个元素作为参数。
         */
        val numbersString = listOf("one", "two", "three", "four", "five")
        println(numbersString.zipWithNext())
        println(numbersString.zipWithNext() { s1, s2 -> s1.length > s2.length})
    }
}