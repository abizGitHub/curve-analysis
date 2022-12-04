package com.abiz.calculator;

public class RepeatedNumbers {


    public static void main(String[] args) {
        int[] val = {3, 1, 3, 4};
        extractRepeatedNumbers(val);
    }

    public static void extractRepeatedNumbers(int[] x) {
        sort(x);
        compress(x);
    }

    private static void sort(int[] x) {
        int tmp;
        for (int j = 0; j < x.length - 1; j++) {
            for (int i = 0; i < x.length - 1; i++) {
                if (x[i] > x[i + 1]) {
                    tmp = x[i + 1];
                    x[i + 1] = x[i];
                    x[i] = tmp;
                }
            }
        }
    }

    private static void compress(int[] x) {
        int pivot = 0;
        for (int i = 0; i < x.length; i++) {
            if ((i + 1) == x[i]) {
                pivot = i;
                break;
            }
        }
        for (int i = pivot; i < x.length; i++) {
            int counter = 0;
            for (int j = i; j < x.length; j++) {
                if (x[j] == (i + 1)) {
                    counter++;
                    x[j] = 0;
                } else if (x[j] > (i + 1)) {
                    break;
                }
            }
            x[i] = counter;
        }
        int c = 0;
        for (int i = pivot - 1; i >= 0; i--) {
            if (x[i] == (pivot + 1)) {
                c++;
                x[i] = 0;
            } else break;
        }
        x[pivot] += c;
        --pivot;
        for (int i = pivot; i >= 0; i--) {
            int counter = 0;
            for (int j = i; j >= 0; j--) {
                if (x[j] == (i + 1)) {
                    counter++;
                    x[j] = 0;
                } else if (x[j] > (i + 1)) {
                    break;
                }
            }
            x[i] = counter;
        }

    }

}
