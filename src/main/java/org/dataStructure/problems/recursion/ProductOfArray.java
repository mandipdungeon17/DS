package org.dataStructure.problems.recursion;

public class ProductOfArray {
    public int productOfArray(int A[], int N)
    {
        if(N == 0) return A[N];
        return A[N-1]*productOfArray(A, N-1);
    }

    public static void main(String[] args) {
        ProductOfArray productOfArray = new ProductOfArray();
        int A[] = {1, 2, 3, 4, 5};
        System.out.println(productOfArray.productOfArray(A, A.length));
    }
}
