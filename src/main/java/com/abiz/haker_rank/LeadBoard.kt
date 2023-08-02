package com.abiz.haker_rank

/**
 * EXAMPLE 1:
Input:
ranked = [100, 90, 80, 80, 80, 70, 70, 35, 10]
player = [0, 5, 75, 85, 100]
Output:
[7, 7, 4, 3, 1]
EXAMPLE 2:
Input:
ranked = [365, 365, 365, 270, 180, 90]
player = [75, 100, 270, 360]
Output:
[5, 4, 2, 2]
EXAMPLE 3:
Input:
ranked = [100, 90, 80]
player = [80, 90, 100]
Output:
[3, 2, 1]
 */

fun main() {
    lead(arrayOf(100, 90, 80, 80, 80, 70, 70, 35, 10), arrayOf(0, 5, 75, 85, 100))
        .forEach { print("$it ") }
    println()
    lead(arrayOf(365, 365, 365, 270, 180, 90), arrayOf(75, 100, 270, 360))
        .forEach { print("$it ") }
    println()
    lead(arrayOf(100, 90, 80), arrayOf(80, 90, 100))
        .forEach { print("$it ") }
}


fun lead(ranks: Array<Int>, players: Array<Int>): Array<Int> {

    val outPut = arrayOfNulls<Int>(players.size)

    data class Node(
        var right: Node? = null,
        var left: Node? = null,
        var offset: Int = 0,
        var rank: Int,
        val value: Int
    ) {
        override fun toString(): String {
            return "$rank:$value"
        }
    }

    val addToRight: (Node, Node) -> Unit = { newNode, mainNode ->
        newNode.rank = mainNode.rank
        mainNode.offset++
        mainNode.right = newNode
    }

    val addToLeft: (Node, Node) -> Unit = { newNode, mainNode ->
        newNode.rank = mainNode.rank + mainNode.offset + 1
        mainNode.left = newNode
    }

    fun attach(newNode: Node, mainNode: Node, index: Int? = null) {
        if (newNode.value == mainNode.value) {
            index?.let { outPut[it] = mainNode.rank }
        } else if (newNode.value > mainNode.value) {
            if (mainNode.right == null) {
                addToRight(newNode, mainNode)
                index?.let { outPut[it] = newNode.rank }
            } else {
                attach(newNode, mainNode.right!!, index)
            }
        } else {
            if (mainNode.left == null) {
                addToLeft(newNode, mainNode)
                index?.let { outPut[it] = newNode.rank }
            } else {
                attach(newNode, mainNode.left!!, index)
            }
        }
    }

    val node = ranks.map { Node(rank = 1, value = it) }.reduce { acc, i ->
        attach(i, acc)
        acc
    }

    players.forEachIndexed { ix, it ->
        attach(Node(rank = 0, value = it), node, ix)
    }
    return outPut.requireNoNulls()
}