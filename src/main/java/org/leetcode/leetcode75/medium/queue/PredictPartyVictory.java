package org.leetcode.leetcode75.medium.queue;

import java.util.LinkedList;
import java.util.Queue;

public class PredictPartyVictory {

    //Time complexity: O(n^2) and Space complexity: O(n). It took 9 ms.
//    public String predictPartyVictory(String senate) {
//        StringBuilder characters = new StringBuilder(senate);
//        int rCount = 0, dCount = 0;
//        int size = characters.length()-1;
//        int i = 0;
//        while(i < characters.length()-1 && rCount < size && dCount < size){
//            if(characters.charAt(i) == 'D'){
//                if(dCount > 0){
//                    dCount--;
//                }
//                else{
//                    characters.append('D');
//                    rCount++;
//                }
//            }
//            else if(characters.charAt(i) == 'R'){
//                if(rCount > 0){
//                    rCount--;
//                }
//                else{
//                    characters.append('R');
//                    dCount++;
//                }
//            }
//            i++;
//        }
//        System.out.println(characters);
//        return characters.charAt(characters.length()-1)=='R' ? "Radiant" : "Dire";
//    }

    //Time complexity: O(n) and Space complexity: O(n). It took 10 ms.
    public String predictPartyVictory(String senate) {
        Queue<Integer> radiantQueue = new LinkedList<>(), direQueue = new LinkedList<>();
        int n = senate.length();
        for(int i=0; i<n; i++){
            if(senate.charAt(i) == 'R') radiantQueue.add(i);
            else direQueue.add(i);
        }
        while(!radiantQueue.isEmpty() && !direQueue.isEmpty()){
            int r = radiantQueue.poll(); int d = direQueue.poll();
            if(r<d) radiantQueue.add(n+r);
            else direQueue.add(d+n);
        }
        return direQueue.isEmpty() ? "Radiant" : "Dire";
    }

    public static void main(String[] args) {
        PredictPartyVictory predictPartyVictory = new PredictPartyVictory();
        System.out.println(predictPartyVictory.predictPartyVictory("DDRRR"));
    }
}
