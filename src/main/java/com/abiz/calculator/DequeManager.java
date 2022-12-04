package com.abiz.calculator;

public class DequeManager {

    private int frameSize;
    private Node recordedMax, recordedMin;
    private long largestVariation;
    private Deque deque;
    private long counter;

    public DequeManager(int frameSize) {
        this.frameSize = frameSize;
        deque = new Deque();
    }

    public long addValues(int... values) {
        if (counter < frameSize) {
            if (deque.getRoot() == null) {
                deque.init(values[0]);
            }
        }
        for (int value : values) {
            deque.addToDeque(new Node(counter++, value));
            if (counter > frameSize) {
                deque.deleteHead();
            }
            Node maxNode = deque.getMax();
            Node minNode = deque.getMin();
            if ((!maxNode.equals(recordedMax)) || (!minNode.equals(recordedMin))) {
                if (maxNode.getValue() - minNode.getValue() > largestVariation) {
                    largestVariation = maxNode.getValue() - minNode.getValue();
                    recordedMin = minNode;
                    recordedMax = maxNode;
                }
            }
        }
        return largestVariation;
    }

    public Node getRecordedMax() {
        return recordedMax;
    }

    public Node getRecordedMin() {
        return recordedMin;
    }

    public long getLargestVariation() {
        return largestVariation;
    }

    public Node findValue(int val) {
        return deque.find(val);
    }

    public int getFrameSize() {
        return frameSize;
    }

    public long getCounter() {
        return counter;
    }
}
