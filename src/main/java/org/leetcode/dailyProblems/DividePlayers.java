package org.leetcode.dailyProblems;

import java.util.Arrays;

//https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/description/?envType=daily-question&envId=2024-10-04
//Time Complexity: O(nlogn) where n is the length of the skill array. Sorting the array takes O(nlogn) time. It took 13 ms to run the code on leetcode.
public class DividePlayers {
    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);
        long sum = 0;
        int i = 0;
        int j = skill.length-1;
        int sum1 = skill[i] + skill[j];
        while(i<j){
            if(sum1 != skill[i] + skill[j]) return -1;
            sum+= (long) skill[i] *skill[j];
            i++;
            j--;
        }
        return sum;
    }

    public static void main(String[] args) {
        DividePlayers dividePlayers = new DividePlayers();
        int[] skill = {1,2,3,4,5,6};
        System.out.println(dividePlayers.dividePlayers(skill));
    }

}
