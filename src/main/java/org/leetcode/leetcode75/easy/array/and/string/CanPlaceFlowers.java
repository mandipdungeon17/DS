package org.leetcode.leetcode75.easy.array.and.string;

import java.util.Arrays;

public class CanPlaceFlowers {
//    public boolean canPlaceFlowers(int[] flowerbed, int n) {
//        if(flowerbed.length ==1 && flowerbed[0] == 0) n--;
//        if(flowerbed.length > 1 && flowerbed[0] == 0 && flowerbed[1] == 0){
//            n--;
//            flowerbed[0] = 1;
//        }
//        if(flowerbed.length > 1 && flowerbed[flowerbed.length-1] == 0 && flowerbed[flowerbed.length-2] ==0){
//            n--;
//            flowerbed[flowerbed.length-1] = 1;
//        }
//        for(int i=1; i< flowerbed.length-1; i++){
//
//            if(flowerbed[i-1] == 0 && flowerbed[i+1] == 0 && flowerbed[i] == 0){
//                n--;
//                flowerbed[i] = 1;
//            }
//        }
//        return n <=0;
//    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0) return true;
        for(int i=0; i<flowerbed.length; i+=2){
            if(i+1 < flowerbed.length && flowerbed[i+1] == 1){
                i++;
            }
            else if(flowerbed[i] == 0){
                n--;
                flowerbed[i] = 1;
                if(n<=0) return true;
            }
        }
        System.out.println("Flowerbed: " + Arrays.toString(flowerbed));
        return n <=0;
    }

    public static void main(String[] args) {
        CanPlaceFlowers canPlaceFlowers = new CanPlaceFlowers();
        int[] flowerbed = {0,1,0,0,1,0,0,0,1};
        int n = 2;
        boolean ans = canPlaceFlowers.canPlaceFlowers(flowerbed, n);
        System.out.println("Can Place Flowers: " + ans);
    }
}
