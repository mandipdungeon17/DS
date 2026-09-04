package org.leetcode.leetcode150.two_pointers;

//https://leetcode.com/problems/gas-station/description/?envType=study-plan-v2&envId=top-interview-150
public class CanCompleteCircuit {
    //Time Complexity O(n^2) and Space Complexity O(1). It took 627ms.
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        if(gas.length == 1){
            return gas[0] - cost[0] < 0 ? -1: 0;
        }
        int[] arr = new int[gas.length];
        for(int i=0; i<gas.length; i++){
            arr[i] = gas[i] - cost[i];
        }
        for(int i=0; i<n; i++){
            if(arr[i] <= 0) continue;
            int tank = gas[i];
            int j=i+1;
            int k = i;
            if(i == gas.length-1) j=0;
            while(j != i){
                tank -= cost[k];
                if(tank < 0) break;
                else tank += gas[j];
                j++;
                k++;
                if(j == n) j=0;
                if(k == n) k=0;
            }
            tank -= cost[k];
            if(tank >= 0){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CanCompleteCircuit canCompleteCircuit = new CanCompleteCircuit();
        System.out.println(canCompleteCircuit.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
        System.out.println(canCompleteCircuit.canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
        System.out.println(canCompleteCircuit.canCompleteCircuit(new int[]{5,1,2,3,4}, new int[]{4,4,1,5,1}));
    }
}