package org.dataStructure.problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListReverse {

    public static void reverse(List<Integer> array){

        for(int i=0; i<array.size()/2; i++){
            int temp = array.get(i);
            int index = array.size()-1-i;
            array.set(i, array.get(index));
            array.set(index, temp);
        }
    }

    public static void main(String[] args){
        List<Integer> array = new ArrayList<>(Arrays.asList(1, 3, 4, 5));
        reverse(array);
        System.out.print("Reversed list : " + array);
    }
}
