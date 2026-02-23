package org.leetcode.dailyProblems;

import java.util.HashMap;
import java.util.Map;

public class AreSentencesSimilar {
    //Time Complexity: O(n) where n is the length of the sentence
//    public boolean areSentencesSimilar(String sentence1, String sentence2) {
//        String[] splitSentence1 = sentence1.split(" ");
//        String[] splitSentence2 = sentence2.split(" ");
//        if(splitSentence1.length == splitSentence2.length) return sentence1.equals(sentence2);
//        if(splitSentence1.length < splitSentence2.length){
//            Map<String, Integer> sentence1Map = new HashMap<>();
//            for(String s : splitSentence1){
//                sentence1Map.merge(s, 1, Integer::sum);
//            }
//            boolean flag = false;
//            int count = 0;
//            for(int i=0; i<splitSentence2.length; i++){
//                if(null == sentence1Map.get(splitSentence2[i]) && !flag){
//                    while(i<splitSentence2.length
//                            && null == sentence1Map.get(splitSentence2[i])){
//                        i++;
//                    }
//                    flag = true;
//                    if(count == 0 && i == splitSentence2.length) return false;
//                }
//                else if(flag && null == sentence1Map.get(splitSentence2[i])) return false;
//                else{
//                    sentence1Map.replace(splitSentence2[i], sentence1Map.get(splitSentence2[i])-1);
//                    if(sentence1Map.get(splitSentence2[i]) == 0) sentence1Map.remove(splitSentence2[i]);
//                }
//                count++;
//
//            }
//        }
//        else{
//            Map<String, Integer> sentence2Map = new HashMap<>();
//            for(String s : splitSentence2){
//                sentence2Map.merge(s, 1, Integer::sum);
//            }
//            boolean flag = false;
//            int count = 0;
//            for(int i=0; i<splitSentence1.length; i++){
//                if(null == sentence2Map.get(splitSentence1[i]) && !flag){
//                    while(i<splitSentence1.length
//                            && null == sentence2Map.get(splitSentence1[i])){
//                        i++;
//                    }
//                    flag = true;
//                    if(count == 0 && i == splitSentence1.length) return false;
//                }
//                else if(flag && null == sentence2Map.get(splitSentence1[i])) return false;
//                else{
//                    sentence2Map.replace(splitSentence1[i], sentence2Map.get(splitSentence1[i])-1);
//                    if(sentence2Map.get(splitSentence1[i]) == 0) sentence2Map.remove(splitSentence1[i]);
//                }
//                count++;
//
//            }
//        }
//        return true;
//    }

    //Time Complexity: O(n) where n is the length of the sentence
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] splitSentence1 = sentence1.split(" ");
        String[] splitSentence2 = sentence2.split(" ");
        if(splitSentence1.length == splitSentence2.length) return sentence1.equals(sentence2);
        if(splitSentence1.length < splitSentence2.length){
            Map<String, Integer> prefix1Map = new HashMap<>();
            Map<String, Integer> suffix1Map = new HashMap<>();
            for(String s : splitSentence1){
                prefix1Map.merge(s, 1, Integer::sum);
                suffix1Map.merge(s, 1, Integer::sum);
            }
            StringBuilder prefix = new StringBuilder();
            StringBuilder suffix = new StringBuilder();
            int i = 0;
            while(i<splitSentence2.length && null != prefix1Map.get(splitSentence2[i])){
                prefix.append(" ").append(splitSentence2[i]);
                prefix1Map.replace(splitSentence2[i], prefix1Map.get(splitSentence2[i])-1);
                if(prefix1Map.get(splitSentence2[i]) == 0) prefix1Map.remove(splitSentence2[i]);
                i++;
            }
            i=splitSentence2.length-1;
            while(i>=0 && null != suffix1Map.get(splitSentence2[i])){
                suffix.insert(0, splitSentence2[i] + " ");
                suffix1Map.replace(splitSentence2[i], suffix1Map.get(splitSentence2[i])-1);
                if(suffix1Map.get(splitSentence2[i]) == 0) suffix1Map.remove(splitSentence2[i]);
                i--;
            }
            if(!prefix.toString().isEmpty()) prefix.append(" ");
            System.out.println(prefix+ suffix.toString());
            if(prefix.toString().trim().equals(sentence1)) return true;
            else if(suffix.toString().trim().equals(sentence1)) return true;
            else if((prefix+suffix.toString()).trim().equals(sentence1)) return true;
            else {
                int len = (prefix+suffix.toString()).trim().length();
                if(len < sentence1.length()) return false;
                else{
                    int diff = len - sentence1.length();
                    if((prefix.substring(0, prefix.length()-diff)+suffix).trim().equals(sentence1)) return true;
                    else return (prefix + suffix.substring(diff)).trim().equals(sentence1);
                }
            }
        }
        else{
            Map<String, Integer> prefix2Map = new HashMap<>();
            Map<String, Integer> suffix2Map = new HashMap<>();
            for(String s : splitSentence2){
                prefix2Map.merge(s, 1, Integer::sum);
                suffix2Map.merge(s, 1, Integer::sum);
            }
            StringBuilder prefix = new StringBuilder();
            StringBuilder suffix = new StringBuilder();
            int i = 0;
            while(i<splitSentence1.length && null != prefix2Map.get(splitSentence1[i])){
                prefix.append(" ").append(splitSentence1[i]);
                prefix2Map.replace(splitSentence1[i], prefix2Map.get(splitSentence1[i])-1);
                if(prefix2Map.get(splitSentence1[i]) == 0) prefix2Map.remove(splitSentence1[i]);
                i++;
            }
            i=splitSentence1.length-1;
            while(i>=0 && null != suffix2Map.get(splitSentence1[i])){
                suffix.insert(0, splitSentence1[i] + " ");
                suffix2Map.replace(splitSentence1[i], suffix2Map.get(splitSentence1[i])-1);
                if(suffix2Map.get(splitSentence1[i]) == 0) suffix2Map.remove(splitSentence1[i]);
                i--;
            }
            if(!prefix.toString().isEmpty()) prefix.append(" ");
            System.out.println(prefix+ suffix.toString());
            if(prefix.toString().trim().equals(sentence2)) return true;
            else if(suffix.toString().trim().equals(sentence2)) return true;
            else if((prefix+suffix.toString()).trim().equals(sentence2)) return true;
            else {
                int len = (prefix+suffix.toString()).trim().length();
                if(len < sentence2.length()) return false;
                else{
                    int diff = len - sentence2.length();
                    if((prefix.substring(0, prefix.length()-diff)+suffix).trim().equals(sentence2)) return true;
                    else return (prefix + suffix.substring(diff)).trim().equals(sentence2);
                }
            }
        }
    }

    public static void main(String[] args) {
        String sentence1 = "TjZ ScAi m xz PNWaKigqqY p IyJ B rok";
        String sentence2 = "TjZ ScAi m LlbJhCf gL u m R pZpiH mSk a og xz PNWaKigqqY p IyJ B rok";
        AreSentencesSimilar areSentencesSimilar = new AreSentencesSimilar();
        System.out.println(areSentencesSimilar.areSentencesSimilar(sentence1, sentence2));
    }
}
