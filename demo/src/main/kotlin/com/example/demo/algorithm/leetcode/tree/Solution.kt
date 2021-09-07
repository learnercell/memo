package com.example.demo.algorithm.leetcode.tree

import com.example.demo.algorithm.tree.MyNode
import java.util.LinkedList

object Solution {

    fun nodePlus(node: MyNode?) {
        if (node == null) {
            return
        }
        node.value = node.value + 1
        nodePlus(node.left)
        nodePlus(node.right)
    }

    fun sameTree(node1: MyNode?, node2: MyNode?): Boolean {
        if (node1 == null && node2 == null) {
            return true
        }
        if (node1 == null || node2 == null) {
            return false
        }
        if (node1.value != node2.value) {
            return false
        }
        val b1 = sameTree(node1.left, node2.left)
        val b2 = sameTree(node1.right, node2.right)
        return b1 && b2
    }

    fun isBST(node: MyNode?): Boolean {
        return if (node == null) {
            true
        } else bstHelper(node, null, null)
    }

    private fun bstHelper(cur: MyNode?, minNode: MyNode?, maxNode: MyNode?): Boolean {
        if (cur == null) {
            return true
        }
        if (minNode != null && cur.value < minNode.value) {
            return false
        }
        if (maxNode != null && cur.value > maxNode.value) {
            return false
        }
        val b1 = bstHelper(cur.left, minNode, cur)
        val b2 = bstHelper(cur.right, cur, maxNode)
        return b1 && b2
    }

    val serList = LinkedList<Int>()

    /**
     * -1 代表空值
     */
    fun serialize(node: MyNode?) {
        if (node == null) {
            serList.add(-1)
            return
        }
        serList.add(node.value)
        serialize(node.left)
        serialize(node.right)
    }

    fun deSerialize(deList: LinkedList<Int>): MyNode? {
        val first = deList.removeFirst()
        if (first == -1) {
            return null
        }
        val myNode = MyNode(first)
        myNode.left = deSerialize(deList)
        myNode.right = deSerialize(deList)
        return myNode
    }

}

fun main(args: Array<String>) {
    // test1
//        List<Integer> list1 = List.of(2, 3, 4, 5, 6);
//        BSTree bsTree = new BSTree();

//        list1.forEach(x->bsTree.insert(new MyNode(x)));
//        bsTree.traverse(bsTree.root);

//        nodePlus(bsTree.root);

//        bsTree.traverse(bsTree.root);

    // test2
//        List<Integer> list1 = List.of(2, 3, 4, 5, 6);
//        BSTree bsTree1 = new BSTree();
//        list1.forEach(x -> bsTree1.insert(new MyNode(x)));
//
//        List<Integer> list2 = List.of(2, 3, 4, 5, 7);
//        BSTree bsTree2 = new BSTree();
//        list2.forEach(x -> bsTree2.insert(new MyNode(x)));
//
//        System.out.println(sameTree(bsTree1.root, bsTree2.root));

    // test3
//        List<Integer> list1 = List.of(2, 3, 4, 5, 6);
//        BSTree bsTree1 = new BSTree();
//        list1.forEach(x -> bsTree1.insert(new MyNode(x)));
//
//        MyNode myNode = new MyNode(1);
//        myNode.left = new MyNode(5);
//        myNode.left.left = new MyNode(6);
//
//        System.out.println(isBST(bsTree1.root));
//        System.out.println(isBST(myNode));

    //test4
    val myNode = MyNode(5)
    myNode.left = MyNode(1)
    myNode.left!!.left = MyNode(6)
    Solution.serialize(myNode)
    val serializeNode = Solution.deSerialize(Solution.serList)
    println(serializeNode!!.value)
}