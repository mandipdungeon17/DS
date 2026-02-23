package org.leetcode.dailyProblems;

public class CheckPowersOfThree {

    public boolean checkPowersOfThree(int n) {

        if(n%3 !=0 && (n-1)%3 !=0) return false;
        else{
            boolean flag = false;
            while(n != 1){
                if(n%3 !=0 && !flag){
                    flag = true;
                    n--;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckPowersOfThree cp = new CheckPowersOfThree();
        System.out.println(cp.checkPowersOfThree(21));
    }
}
