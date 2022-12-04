# Curve-Analysis
### To find maximum variation of curve in pre-defined frame size using Binary-tree and Queue

The aim of this project is to find the largest variation among pre-defined frame size of a curve
with arbitrary size
by defining a Node that is combination of node in Binary-tree and Queue<br>
Sequence of values is kept as well as their order.
Frame moves through curve and updates Tree and Queue structure.<br>
DequeManager is defined to reserve maximum variation and corresponding extreme points.

###How to Use:
- create new cure by posting frames size to end-point: [http :8080/create]
it returns {index} of curve that should be used later for adding values to curve
and getting details of curve.

>ex : echo 10 | http :8080/create

- add values to curve {index} by posting arbitrary number of values in Array format to [http :8080/add/{index}]
it returns the largest variation

>ex : cat [5, 6, 22, 1, 65, 0, 1, 8, 9, 3, 31, ...] | http :8080/add/0

- get detail of curve by calling [http :8080/detail/0]

--------------------------part two-----------------------------<br> 

The aim of this part is to find count of array numbers starting from 1
 without using extra memory
###how to use:
- post array of numbers(greater than zero) to [http :8080/repeated-numbers]
it returns count of each number in place of index

> ex : echo [4,6,2,4,3,3,5,8,9,5,5] | http :8080/repeated-numbers
returns : [0, 1, 2, 2, 3, 1, 0, 1, 1, 0, 0]
        (0 times 1)(1 times 2)(2 times 3) ...

