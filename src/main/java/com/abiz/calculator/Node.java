package com.abiz.calculator;

import lombok.Data;

@Data
public class Node {
    private long time;
    private int value;
    //---------Queue----------
    private Node nextNode;
    private Node preNode;
    //------BinaryTree------
    private Node parent;
    private Node leftHand;
    private Node rightHand;

    public Node(long time, int value) {
        this.time = time;
        this.value = value;
    }

    public void setLeftHand(Node leftHand) {
        this.leftHand = leftHand;
        if (leftHand != null)
            leftHand.setParent(this);
    }

    public void setRightHand(Node rightHand) {
        this.rightHand = rightHand;
        if (rightHand != null)
            rightHand.setParent(this);
    }

    @Override
    public String toString() {
        return "{" +
                "time:" + time +
                ", value:" + value +
                '}';
    }

}
