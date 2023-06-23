# Array Strictly Increasing

An array is an object containing a fixed number of values of the same type. The elements of an array are indexed, which means we can access them with numbers (called indices).

We can consider an array as a numbered list of cells, each cell being a variable holding a value. In Java, the numbering starts at 0.

There are primitive type arrays and object type arrays. This means we can use arrays of int, float, boolean, … But also arrays of String, Object and custom types as well.

- Java offers a lot of methods to deal with arrays through the Arrays utility class. There also are utility classes to manipulate arrays in libraries such as Apache Commons or Guava.

-  This repository contains a solution to the "Array Strictly Increasing" problem. The problem requires finding the minimum number of operations needed to make one array strictly increasing by performing element replacements using another array.

## Problem Description

Given two integer arrays `arr1` and `arr2`, the task is to make `arr1` strictly increasing by performing a minimum number of operations. In each operation, you can choose two indices `i` from `arr1` and `j` from `arr2` and assign `arr1[i] = arr2[j]`. The goal is to determine the minimum number of such operations required to make `arr1` strictly increasing. If it is not possible to make `arr1` strictly increasing, the solution returns `-1`.

## Solution Approach

The solution implements a recursive Depth-First Search (DFS) algorithm with memoization to optimize the computation. The algorithm performs the following steps:

1. Sort the `arr2` array in ascending order to enable efficient searching for the smallest value greater than the current element in `arr1`.

2. Implement the `dfs` function, which takes the current index `i`, the previous element `prev`, `arr1`, and `arr2` as input parameters.

3. Handle the base case where `i` is equal to the length of `arr1`. In this case, return `0` to indicate that no operations are needed.

4. Check if the pair `(i, prev)` is present in the memoization map. If so, return the stored value to avoid redundant calculations.

5. Initialize the `cost` variable to a large value, representing an invalid cost.

6. Check if the current element `arr1[i]` is already greater than the previous element `prev`. If it is, recursively call `dfs` with the next index `i + 1` and `arr1[i]` as the new previous element. Update the `cost` with the result obtained.

7. Find the smallest value in `arr2` that is greater than the previous element `prev`. Use binary search (`bisectRight` function) to find the insertion point for `prev` in `arr2`. The returned index `idx` represents the smallest value greater than `prev` or the length of `arr2` if no such value exists.

8. If `idx` is within the bounds of `arr2`, calculate the cost of replacing `arr1[i]` with `arr2[idx]` as `1` (representing the assignment operation) plus the cost obtained by recursively calling `dfs` with the next index `i + 1` and the chosen replacement value `arr2[idx]`. Compare this cost with the current minimum `cost` and update if it is smaller.

9. Store the calculated `cost` in the memoization map with the pair `(i, prev)` as the key.

10. Return the `cost` as the result.

## Code Usage

The solution code is implemented in Java and can be used as follows:

1. Provide the input arrays `arr1` and `arr2` to the `makeArrayIncreasing` function.

2. The function returns the minimum number of operations needed to make `arr1` strictly increasing. If it is not possible, the function returns `-1`.

3. Modify the `main` function in the provided `Solution.java` file to input the arrays and display the result.

4. Compile and run the program to see the minimum number of operations required.

## Example

```java
int[] arr1 = {1, 5, 3, 6, 7};
int[] arr2 = {1, 3, 2, 4};

int answer = makeArrayIncreasing(arr1, arr2);
System.out.println("Minimum number of operations: " + answer);
```


Output:
```
Minimum number of operations: 1
```

This indicates that only one operation is needed to make the arr1 array strictly increasing.

## Complexity Analysis
The time complexity of the solution is O(n * m * log m), where n is the length of arr1 and m is the length of arr2. This is because for each element in arr1, we perform a binary search on arr2 to find the replacement value. The space complexity is O(n * m) to store the results in the memoization map.

Feel free to explore the code and experiment with different inputs to understand the problem and solution better.
