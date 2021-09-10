package com.example.demo.kt.collection

fun main() {
    /**
     * 创建集合的最常用方法是使用标准库函数 listOf<T>()、setOf<T>()、mutableListOf<T>()、mutableSetOf<T>()
     */
    val numbersSet = setOf("one", "two", "three", "four")
    val emptySet = mutableSetOf<String>()

    /**
     * 创建空集合时，须明确指定类型
     */
    val empty = emptyList<String>()

    /**
     *注意，to 符号创建了一个短时存活的 Pair 对象，因此建议仅在性能不重要时才使用它。
     */
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)

    loopPrint(listConstruct(5))

    /**
     * 为避免过多的内存使用，请使用其他方法。例如，可以创建可写 Map 并使用写入操作填充它。 apply() 函数可以帮助保持初始化流畅。
     */
    val numbersMap2 = mutableMapOf<String, String>().apply { this["one"] = "1"; this["two"] = "2" }
    loopPrint(numbersMap2)

}



/**
 * list 的初始化函数
 */
fun listConstruct(length: Int): List<Int>{
    return List(length) { it * 3 }
}

fun loopPrint(param: Any) {
//    if (param is Collection<*>) {
//        param.forEach { print("$it\t") }
//    }
//    if (param is Map<*, *>) {
//        param.forEach { k, v -> println("key: $k, value: $v") }
//    }

    when (param) {
        is Collection<*> -> {param.forEach { print("$it\n") } }
        is Map<*, *> -> param.forEach { k, v -> println("key: $k, value: $v") }
        else -> println("not support")
    }

    println("----------------------------")
}