package org.leetcode.dailyProblems;

//https://leetcode.com/problems/count-square-submatrices-with-all-ones/description/
//Time complexity: O(n^3) where n is the size of the matrix and space complexity: O(1)
public class CountSquares {
    public int countSquares(int[][] matrix) {
        int sum = 0;
        for (int[] into : matrix) {
            for (int anInt : into) {
                if (anInt == 1) sum++;
            }
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int startRow = 0;
        int endRow = 2;
        int startCol = 0;
        int endCol = 2;
        int num =2;
        int size = Math.max(row, col);

        while(startRow<row && startCol<col && num < size){
        if (endRow <= row && endCol <= col) {
            sum += subSquareMatrix(startRow, endRow, startCol, endCol, matrix);
        }
          startCol++;
          endCol = startCol + num;
          if(endCol > col){
              startRow++;
              endRow = startRow + num;
              startCol = 0;
              endCol = num;
          }
          if(endRow > row) {
              num++;
              startRow = 0;
              endRow = num;
              startCol = 0;
              endCol = num;
          }
        }

        return sum;
    }

    private int subSquareMatrix(int startRow, int endRow, int startCol, int endCol, int[][] matrix) {
        int col = startCol;
        while(startRow < endRow){
            while(startCol < endCol){
                if(matrix[startRow][startCol] != 1) return 0;
                startCol++;
            }
            startRow++;
            startCol=col;
        }
        return 1;
    }

    public static void main(String[] args) {
        CountSquares countSquares = new CountSquares();
//        int[][] matrix = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        int[][] matrix = {{1,0,1}, {1, 1, 0}, {1,1,0}};
        System.out.println(countSquares.countSquares(matrix));
    }
}
