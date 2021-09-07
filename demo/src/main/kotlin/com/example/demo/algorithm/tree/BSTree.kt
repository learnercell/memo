package com.example.demo.algorithm.tree

import java.util.*
import java.util.List
import java.util.function.Consumer

class BSTree {
    var root: MyNode? = null
    fun insert(node: MyNode) {
        if (!Objects.nonNull(node)) {
            return
        }
        if (root == null) {
            root = node
        }

        // 需要把 当前节点的值，跟要插入节点的值，传入到我们的方法里。
        insertHelper(root, node)
    }

    private fun insertHelper(cur: MyNode?, insMyNode: MyNode) {
        if (insMyNode.value < cur!!.value) {
            if (cur.left == null) {
                cur.left = insMyNode
            } else {
                insertHelper(cur.left, insMyNode)
            }
        } else if (insMyNode.value > cur.value) {
            if (cur.right == null) {
                cur.right = insMyNode
            } else {
                insertHelper(cur.right, insMyNode)
            }
        }
    }

    fun traverse(r: MyNode?) {
        if (r == null) {
            return
        }
        // 前序遍历
        traverse(r.left)
        println(r.value)
        // 中序遍历
        traverse(r.right)

        // 后续遍历
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val bsTree = BSTree()
            val myNodes = List.of(
                MyNode(2),
                MyNode(1),
                MyNode(3),
                MyNode(4),
                MyNode(5)
            )
            myNodes.forEach(Consumer { node: MyNode -> bsTree.insert(node) })
            bsTree.traverse(bsTree.root)
        }
    }
}