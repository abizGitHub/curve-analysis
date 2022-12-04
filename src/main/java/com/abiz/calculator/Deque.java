package com.abiz.calculator;

import lombok.Data;

@Data
public class Deque {

    private Node tail;
    private Node head;
    private Node root;

    public void init(int initVal) {
        Node node = new Node(0, initVal);
        head = node;
        tail = node;
        root = node;
    }

    private void addToQueue(Node currentNode) {
        tail.setNextNode(currentNode);
        currentNode.setPreNode(tail);
        tail = currentNode;
    }

    private void addToTree(Node currentNode) {
        insert(root, currentNode);
    }

    private void insert(Node pointer, Node currentNode) {
        if (pointer.getValue() <= currentNode.getValue()) {
            Node rightHand = pointer.getRightHand();
            if (rightHand == null) {
                pointer.setRightHand(currentNode);
            } else {
                insert(rightHand, currentNode);
            }
        } else {
            Node leftHand = pointer.getLeftHand();
            if (leftHand == null) {
                pointer.setLeftHand(currentNode);
            } else {
                insert(leftHand, currentNode);
            }
        }

    }

    public Node getMax() {
        return traverseRight(root);
    }

    public Node getMin() {
        return traverseLeft(root);
    }

    private Node traverseLeft(Node node) {
        Node leftHand = node.getLeftHand();
        if (leftHand == null) return node;
        else return traverseLeft(leftHand);
    }

    private Node traverseRight(Node node) {
        Node rightHand = node.getRightHand();
        if (rightHand == null) return node;
        else return traverseRight(rightHand);
    }

    public void deleteFromTree(Node node) {
        Node leftHand = node.getLeftHand();
        Node rightHand = node.getRightHand();
        if (rightHand == null && leftHand == null) {
            root = deleteAsLeaf(node);
        } else if ((rightHand == null && leftHand != null) || (rightHand != null && leftHand == null)) {
            root = deleteAsOneChild(node);
            root.setParent(null);
        } else {
            root = deleteAsTwoChild(node);
            root.setParent(null);
        }
        node.setLeftHand(null);
        node.setRightHand(null);
    }

    private Node deleteAsTwoChild(Node node) {
        Node parent = node.getParent();
        Node rightHand = node.getRightHand();
        Node leftHand = node.getLeftHand();
        Node successor = traverseLeft(rightHand);
        successor.setLeftHand(leftHand);
        if (!successor.equals(rightHand)) {
            Node successorRightHand = successor.getRightHand();
            Node successorParent = successor.getParent();
            successor.setRightHand(rightHand);
            successorParent.setLeftHand(successorRightHand);
        }
        if (parent != null) {
            if (parent.getLeftHand() != null && parent.getLeftHand().equals(node)) {
                parent.setLeftHand(successor);
            } else {
                parent.setRightHand(successor);
            }
            return root;
        } else {
            return successor;
        }
    }

    private Node deleteAsOneChild(Node node) {
        Node child = node.getRightHand();
        if (child == null) child = node.getLeftHand();
        Node parent = node.getParent();
        if (parent != null) {
            if (parent.getLeftHand() != null && parent.getLeftHand().equals(node)) {
                parent.setLeftHand(child);
            } else {
                parent.setRightHand(child);
            }
            node.setLeftHand(null);
            node.setRightHand(null);
            return root;
        } else {
            return child;
        }
    }

    private Node deleteAsLeaf(Node node) {
        Node parent = node.getParent();
        if (parent == null) return null;
        if (node.equals(parent.getLeftHand())) {
            parent.setLeftHand(null);
        } else {
            parent.setRightHand(null);
        }
        return root;
    }

    public Node find(int value) {
        return find(root, value);
    }

    private Node find(Node pointer, int value) {
        if (pointer == null) return null;
        if (pointer.getValue() == value) return pointer;
        if (pointer.getValue() < value) return find(pointer.getRightHand(), value);
        if (pointer.getValue() > value) return find(pointer.getLeftHand(), value);
        return null;
    }

    public void deleteHead() {
        Node nextNode = head.getNextNode();
        deleteFromTree(head);
        nextNode.setPreNode(nextNode);
        head = nextNode;
    }

    public void addToDeque(Node node) {
        addToQueue(node);
        addToTree(node);
    }

}
