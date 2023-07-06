# Algorithms 101

In this repository, you will find two Algorithms for the problems defined below. The metirial, is a part of the course [Algorithms [NCO-04-03]](https://elearning.auth.gr/course/view.php?id=9809) (2021/22) of the Department of Computer Science at Aristotle University of Thessaloniki.

## Description

### Skyline

Definition: When a dominance relation is given on a dataset, the skyline query returns all the objects that are not dominated by any other object in the set. In the case of multidimensional objects, an object dominates another if it is the same or better in all dimensions and strictly better in at least one dimension.

Formally, for two-dimensional objects $A(x_1, y_1)$ and $B(x_2, y_2)$, considering that a lower value is better in each dimension, we have that A dominates B if and only if: $x_1 \leq x_2$ and $y_1 \leq y_2$ and ($x_1 \neq x_2$ or $y_1 \neq y_2$).

The algorithm implemented is a efficient one to find the skyline in sets of two-dimensional points in the Euclidean plane.

### DPnet

We have a complex process consisting of N steps (or processes) in a chain format: 
$\Delta_1 \rightarrow ... \rightarrow \Delta_N$. The arrows indicate that the outputs of $Δ_1$ are passed as inputs to $Δ_2$, the outputs of $Δ_2$ to $Δ_3$, and so on. We also have $M$ virtual machine types. As input, we have two matrices. The first one is $N \times M$ and represents the total cost to run a process on a virtual machine type. The second matrix is $M \times M$ and represents the cost of sending data from one machine to another. 

A dynamic programming algorithm is implemented, to find the minimum cost of the complex process at each step and for each machine. More specifically, this algorithm will populate a Costs matrix of size $N \times M$, where each cell Cost(i, j) will represent the minimum total cost of the complex process up to step i when step i is executed on machine j. To execute step i on machine j, either the results of step i-1 should already be on machine j, or they need to be transferred to machine j from other machines, taking into account the communication cost.
