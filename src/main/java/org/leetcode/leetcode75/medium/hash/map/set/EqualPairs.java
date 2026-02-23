package org.leetcode.leetcode75.medium.hash.map.set;

import java.util.Arrays;

/*
Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.

A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).

Example 1:

Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
Output: 1
Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]

Example 2:
Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
Output: 3
Explanation: There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]

Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
1 <= grid[i][j] <= 105
*/
public class EqualPairs {
    //Time complexity: O(n^2) and Space complexity: O(n^2). It took 133 ms.
//    public int equalPairs(int[][] grid) {
//        List<List<Integer>> column = new ArrayList<>();
//        List<List<Integer>> row = new ArrayList<>();
//        for(int i=0; i<grid.length; i++){
//            List<Integer> columnList = new ArrayList<>();
//            List<Integer> rowList = new ArrayList<>();
//            for (int j=0; j<grid.length; j++) {
//                columnList.add(grid[j][i]);
//                rowList.add(grid[i][j]);
//            }
//            column.add(columnList);
//            row.add(rowList);
//        }
//        System.out.println("Column: " + column + " Row : " + row);
//        int count = 0;
//        for (List<Integer> integerList : row) {
//            for (List<Integer> integers : column) {
//                if (integerList.equals(integers)) count++;
//            }
//        }
//        System.out.println("Column: " + column + " Row : " + row);
//        return count;
//    }

    //Time complexity: O(n^2) and Space complexity: O(n^2). It took 133 ms.
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int[][] column = new int[n][n];
        for(int i=0; i<n; i++){
            for (int j=0; j<n; j++) {
                column[i][j] = grid[j][i];
            }
        }
        System.out.println("Column: " + Arrays.deepToString(column));
        int count = 0;
        for (int[] integerList : grid) {
            for (int[] integers : column) {
                if (Arrays.equals(integerList, integers)) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        EqualPairs equalPairs = new EqualPairs();
        int[][] grid = {{3,1,2,2},{1,4,4,5},{2,4,2,2},{2,4,2,2}};
        int ans = equalPairs.equalPairs(grid);
        System.out.println("Equal Pairs: " + ans);
    }
}
