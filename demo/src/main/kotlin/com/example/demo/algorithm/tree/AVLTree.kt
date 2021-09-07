package com.example.demo.algorithm.tree

import java.util.*
import java.util.List
import java.util.function.Consumer

class AVLTree {
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
        val lh = heightHelper(cur.left)
        val rh = heightHelper(cur.right)
        if (lh - rh > 1) {

            // 当前节点的左子树的右子树高度，大于当前节点的左子树的左子树
            val lrh = heightHelper(cur.left?.right)
            val llh = heightHelper(cur.left?.left)
            if (lrh > llh) {
                //对当前节点的左孩子进行左旋
                //再对当前节点进行右旋
                cur.left?.let { leftRotate(it) }
            }
            // 右旋转
            rightRotate(cur)
        }
        if (rh - lh > 1) {
            // 左旋转
            // 双选转
            // todo 作为联系，填补走后的内容，代码已经实现完成
        }
    }

    /**
     * 创建一个新节点，值等于当前根节点的值
     * 把新节点的右子树设置成当前节点的右子树
     * 把新节点的左子树设置成当前节点的左子树的右子树
     * 把当前节点的值换位左子节点的值
     * 把当前节点的左子树设置成左子树的左子树
     * 把当前节点的右子树设置为新节点
     */
    // 右旋转
    private fun rightRotate(node: MyNode?) {
        val newNode = MyNode(node!!.value)
        newNode.right = node.right
        newNode.left = node.left?.right
        node.value = node.left!!.value
        node.left = node.left?.left
        node.right = newNode
    }

    /**
     * 创建一个新节点，值等于当前节点的值
     * 把新节点的左子树设置成当前节点的左子树
     * 把新节点的右子树设置位当前节点的右子树的左子树
     * 把当前节点的值换位右子节点的值
     * 把当前节点的右子树，设置为右子树的右子树
     * 把当前节点的左子树设置为新节点
     */
    private fun leftRotate(node: MyNode) {
        // 创建一个新节点，值等于当前节点的值
        val newNode = MyNode(node.value)

        //把新节点的左子树设置成当前节点的左子树
        newNode.left = node.left

        //把新节点的右子树设置位当前节点的右子树的左子树
        newNode.right = node.right?.left

        //把当前节点的值换位右子节点的值
        node.value = node.right!!.value
        node.right = node.right?.right
        node.left = newNode
    }

    fun heightHelper(node: MyNode?): Int {
        if (node == null) {
            return 0
        }
        val lh = heightHelper(node.left) + 1
        val rh = heightHelper(node.right) + 1
        return lh.coerceAtLeast(rh)
    }
}

fun main() {
//        List<Integer> right = List.of(10, 8, 12, 7, 9, 6);
//
//        AVLTree avlTree = new AVLTree();
//
//        for (Integer r : right) {
//            avlTree.insert(new MyNode(r));
//        }
//
//        System.out.println(avlTree.heightHelper(avlTree.root));

//        // 右旋转
//
//        // 双选转-右旋转
    val doubleRight = List.of(10, 11, 7, 6, 8, 9)
    val avlTree = AVLTree()
    doubleRight.forEach(Consumer { x: Int? -> avlTree.insert(MyNode(x!!)) })
    println(avlTree.heightHelper(avlTree.root))
}