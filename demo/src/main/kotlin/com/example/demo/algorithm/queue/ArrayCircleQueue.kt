package com.example.demo.algorithm.queue

import java.util.*
import java.util.concurrent.ArrayBlockingQueue

class ArrayCircleQueue(private val size: Int) {
    private val items: Array<Any?> = arrayOfNulls(size)
    private var putIndex = 0
    private var takeIndex = 0

    // t full f empty
    private var flag = false
    fun enqueue(item: Any?) {
        if (putIndex == takeIndex && flag) {
            throw RuntimeException("queue is full")
        }
        putIndex %= size
        items[putIndex] = item
        putIndex++
        if (putIndex == takeIndex) {
            flag = true
        }
    }

    fun dequeue(): Any? {
        if (putIndex == takeIndex && !flag) {
            throw RuntimeException("queue is empty")
        }
        takeIndex %= size
        val item = items[takeIndex]
        takeIndex++
        if (putIndex == takeIndex) {
            flag = false
        }
        return item
    }
}

fun main(args: Array<String>) {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    val length = arr.size
    val queue = ArrayCircleQueue(length)
    Arrays.stream(arr).forEach { item: Int -> queue.enqueue(item) }
    for (i in 0 until length) {
        println(queue.dequeue())
    }
    Arrays.stream(arr).forEach { item: Int -> queue.enqueue(item) }
    for (i in 0 until length) {
        println(queue.dequeue())
    }
    val arr2 = intArrayOf(1, 2, 3)
    Arrays.stream(arr2).forEach { item: Int -> queue.enqueue(item) }
    queue.dequeue()
    val arr3 = intArrayOf(1, 3)
    Arrays.stream(arr3).forEach { item: Int -> queue.enqueue(item) }
    for (i in 0..3) {
        println(queue.dequeue())
    }
}