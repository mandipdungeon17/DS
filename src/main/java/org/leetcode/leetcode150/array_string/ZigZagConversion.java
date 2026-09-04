package org.leetcode.leetcode150.array_string;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/zigzag-conversion/?envType=study-plan-v2&envId=top-interview-150
public class ZigZagConversion {
    //Time Complexity O(n) and Space Complexity O(n). It took 5ms.
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;
        StringBuilder res = new StringBuilder();
        List<StringBuilder> list = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        int j = 0;
        int i = 0;
        while (i<s.length()) {
            while(j<numRows && i<s.length()) {
                list.get(j).append(s.charAt(i));
                j++;
                i++;
            }
            j=j-2;
            while(j>0 && i<s.length()) {
                list.get(j).append(s.charAt(i));
                j--;
                i++;
            }
        }
        for(StringBuilder sb : list){
            res.append(sb);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion zc = new ZigZagConversion();
        System.out.println(zc.convert("PAYPALISHIRING", 3)); //"PAHNAPLSIIGYIR"
        System.out.println(zc.convert("PAYPALISHIRING", 4)); //"PINALSIGYAHRPI"
        System.out.println(zc.convert("A", 1));
//        P   A   H   N
//        A P L S I I G
//        Y   I   R
    }
}
