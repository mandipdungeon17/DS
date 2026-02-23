package org.dataStructure.problems.array;

import java.util.Arrays;

public class RotateMatrix {

    public static void rotateMatrix(int[][] matrix) {
//        int[][] matrix_new = new int[matrix.length][matrix.length];
//        int row = 0;
//        for(int i=0; i<matrix.length ; i++){
//            int col=0;
//            for(int j=matrix.length-1; j>=0 ; j--){
//                matrix_new[row][col] = matrix[j][i];
//                col++;
//            }
//            row++;
//        }
//        matrix = matrix_new;
//        System.out.print("Matrix : " + Arrays.deepToString(matrix));

//        if (matrix.length == 0 || matrix.length != matrix[0].length) return false;
        int n = matrix.length;
        for (int layer = 0; layer < n/2; layer++) {
            int last = n - 1 - layer;
            for (int i = layer; i<last; i++) {
                int offset = i - layer;
                int top = matrix[layer][i];
                matrix[layer][i] = matrix[last-offset][layer];
                matrix[last-offset][layer] = matrix[last][last-offset];
                matrix[last][last-offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
    }
    public static void main(String[] args){
        int[][] matrix = new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
//        int[][] matrix = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        rotateMatrix(matrix);
        System.out.print("Rotated Matrix : " + Arrays.deepToString(matrix));
    }
}
