package com.example.demo.kt.collection

import java.util.*

class CollectionTranslate {
    companion object{
        /**
         * 映射
         *
         * 映射 转换从另一个集合的元素上的函数结果创建一个集合。 基本的映射函数是 map()。
         * *****它将给定的 lambda 函数应用于每个后续元素*****
         * 并返回 lambda 结果列表。 结果的顺序与元素的原始顺序相同。 如需应用还要用到元素索引作为参数的转换，请使用 mapIndexed()。
         */
        fun collectionMap() {
            val numbers = listOf(1,2,3,4,5)
            println(numbers.map { it * 10 })   //[10, 20, 30, 40, 50]
            println(numbers.mapIndexed { i, v ->  i * v})  //[0, 2, 6, 12, 20]
        }
        /**
         * 如果转换在某些元素上产生 null 值，则可以通过调用 mapNotNull() 函数取代 map()
         * 或 mapIndexedNotNull() 取代 mapIndexed() 来从结果集中过滤掉 null 值。
         */
        fun mapNotNull() {
            val numbers = setOf(1, 2, 3)
            println(numbers.mapNotNull { if ( it == 2) null else it * 3 })
            println(numbers.mapIndexedNotNull { idx, value -> if (idx == 0) null else value * idx })
        }
        /**
         * 映射转换时，有两个选择：转换键，使值保持不变，反之亦然。 要将指定转换应用于键，请使用 mapKeys()；
         * 反过来，mapValues() 转换值。 这两个函数都使用将映射条目作为参数的转换，因此可以操作其键与值。
         */
        fun mapKeyOrValue() {
            val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
            println(numbersMap.mapKeys { it.key.uppercase(Locale.getDefault()) })
            println(numbersMap.mapValues { it.value + it.key.length })
        }

        //-----------------------------------------------------------------------------------------------------
        /**
         * 合拢
         *
         *合拢 转换是根据两个集合中具有相同位置的元素构建配对。 在 Kotlin 标准库中，这是通过 zip() 扩展函数完成的。
         *在一个集合（或数组）上以另一个集合（或数组）作为参数调用时，zip() 返回 Pair 对象的列表（List）。
         * 接收者集合的元素是这些配对中的第一个元素。
         * 如果集合的大小不同，则 zip() 的结果为较小集合的大小；结果中不包含较大集合的后续元素。
         * zip() 也可以中缀形式调用 a zip b 。
         */
        fun collectionZip() {
            val numbers = arrayListOf(1,2,3)
            val fruit = listOf("apple", "banana", "pear")
            println( numbers zip fruit)

            val animals = listOf("dog", "monkey")
            println(fruit zip animals)
        }
        /**
         * 也可以使用带有两个参数的转换函数来调用 zip()：接收者元素和参数元素。
         * 在这种情况下，结果 List 包含在具有相同位置的接收者对和参数元素对上调用的转换函数的返回值。
         */
        fun zipTwoParam() {
            val colors = listOf("red", "brown", "grey")
            val animals = listOf("fox", "bear", "wolf")

            println(colors.zip(animals) { col, ani ->
                "The ${ani.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} is $col"
            })
        }
        /**
         * 要分割键值对列表，请调用 unzip()。
         */
        fun unzipTest(){
            val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
            println("unzipLists first is ${numberPairs.unzip().first}, second is ${numberPairs.unzip().second}")
        }

        //-----------------------------------------------------------------------------------------------------
        /**
         * 关联
         *
         *关联 转换允许从集合元素和与其关联的某些值构建 Map。 在不同的关联类型中，元素可以是关联 Map 中的键或值。
         *基本的关联函数 associateWith() 创建一个 Map，|**其中原始集合的元素是键**|，并通过给定的转换函数从中产生值。
         * 如果两个元素相等，则仅最后一个保留在 Map 中。
         */
         fun associateWithTest() {
            val numbers = listOf("one", "two", "three", "four")
            println(numbers.associateWith { it.length })
         }
        /**
         *  |**为了使用集合元素作为值来构建 Map**|，有一个函数 associateBy()。 它需要一个函数，该函数根据元素的值返回键。
         * 如果两个元素相等，则仅最后一个保留在 Map 中。 还可以使用值转换函数来调用 associateBy()。
         */
        fun associateByTest() {
            val numbers = listOf("one", "two", "three", "four", "six")
            println(numbers.associateBy { it.last().uppercase() })
            println(numbers.associateBy (keySelector = {it.uppercase()}, valueTransform = { it.length }  ))
        }
        /**
         * 另一种构建 Map 的方法是使用函数 associate()，其中 Map 键和值都是通过集合元素生成的。
         * 它需要一个 lambda 函数，该函数返回 Pair：键和相应 Map 条目的值。
         * 请注意，associate() 会生成临时的 Pair 对象，这可能会影响性能。
         * 因此，当性能不是很关键或比其他选项更可取时，应使用 associate()。
         */
         fun associateTest() {
            val numbers = listOf("one", "two", "three", "four")
            println(numbers.associate { num->  num.length to num.first().uppercase()})
         }

        //-----------------------------------------------------------------------------------------------------
        /**
         * 打平
         *
         * 如需操作嵌套的集合，则可能会发现提供对嵌套集合元素进行打平访问的标准库函数很有用。
         * 第一个函数为 flatten()。可以在一个集合的集合（例如，一个 Set 组成的 List）上调用它。
         * 该函数返回嵌套集合中的所有元素的一个 List。
         */
        fun flattenTest() {
            val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
            println(numberSets.flatten())
        }
        /**
         * 另一个函数——flatMap() 提供了一种灵活的方式来处理嵌套的集合。 它需要一个函数将一个集合元素映射到另一个集合。
         * 因此，flatMap() 返回单个列表其中包含所有元素的值。 所以，flatMap() 表现为 map()（以集合作为映射结果）与 flatten() 的连续调用。
         */
        fun flattenMapTest() {
            val containers = listOf(
                StringContainer(listOf("one", "two", "three")),
                StringContainer(listOf("four", "five", "six")),
                StringContainer(listOf("seven", "eight"))
            )
            println(containers.flatMap { it.values })
        }
    }

}

data class StringContainer(val values: List<String>)

fun main() {
    CollectionTranslate.collectionMap()
    println("-------------------------------")
    CollectionTranslate.mapNotNull()
    println("-------------------------------")
    CollectionTranslate.mapKeyOrValue()
    println("-------------------------------")
    CollectionTranslate.collectionZip()
    println("-------------------------------")
    CollectionTranslate.zipTwoParam()
    println("-------------------------------")
    CollectionTranslate.unzipTest()
    println("-------------------------------")
    CollectionTranslate.associateWithTest()
    println("-------------------------------")
    CollectionTranslate.associateByTest()
    println("-------------------------------")
    CollectionTranslate.associateTest()
    println("-------------------------------")
    CollectionTranslate.flattenTest()
    println("-------------------------------")
    CollectionTranslate.flattenMapTest()
}