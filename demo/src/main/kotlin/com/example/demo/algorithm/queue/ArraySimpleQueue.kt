package com.example.demo.algorithm.queue

class ArraySimpleQueue(size: Int) {
    private val items: Array<Any?> = arrayOfNulls(size)
    private var putIndex = 0
    private var takeIndex = 0
    private val size: Int = size
    fun enqueue(item: Any?) {
        if (putIndex == size) {
            throw RuntimeException("queue is full")
        }
        items[putIndex] = item
        putIndex++
    }

    fun dequeue(): Any? {
        if (putIndex == takeIndex) {
            throw RuntimeException("queue is empty")
        }
        val item = items[takeIndex]
        takeIndex++
        return item
    }
}

fun main() {
    val arr =  listOf(1, 2, 3, 4, 5, 6, 7)
    val length = arr.size
    val queue = ArraySimpleQueue(length)
    arr.forEach { item: Int -> queue.enqueue(item) }
    for (i in 0 until length) {
        println(queue.dequeue())
    }
    //arr.forEach { item: Int -> queue.enqueue(item) }
}