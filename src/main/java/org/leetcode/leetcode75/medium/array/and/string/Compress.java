package org.leetcode.leetcode75.medium.array.and.string;

import java.util.Arrays;

public class Compress {
//    public int compress(char[] chars) {
//        int[] freq = new int[128];
//        StringBuilder s = new StringBuilder();
//
//        for(char ch : chars){
//            ++freq[ch];
//        }
//        for(char ch : chars){
//            if(freq[ch] != 0){
//                s.append(ch);
//                if(freq[ch] != 1)
//                    s.append(freq[ch]);
//                freq[ch] = 0;
//            }
//
//        }
////        chars = new char[s.length()];
//        System.arraycopy(s.toString().toCharArray(), 0, chars, 0, s.length());
////        chars = s.toString().toCharArray();
//        System.out.println("String : " + Arrays.toString(chars));
//        return s.length();
//    }

    //Time Complexity: O(n) and Space Complexity: O(N). It took 3 ms.
//    public int compress(char[] chars) {
//        int count = 1;
//        StringBuilder builder = new StringBuilder();
//        List<Character> characterList = new ArrayList<>();
//        for (char aChar : chars) {
//            if (characterList.contains(aChar)) {
//                count++;
//            } else {
//                if (count > 1) {
//                    builder.append(count);
//                    count = 1;
//                }
//                characterList.clear();
//                characterList.add(aChar);
//                builder.append(aChar);
//            }
//        }
//        if(count > 1){
//            builder.append(count);
//        }
//        for(int i=0; i<builder.length(); i++){
//            chars[i] = builder.charAt(i);
//        }
//        System.out.println(builder);
//        System.out.println(Arrays.toString(chars));
//        return builder.length();
//    }

    //Time Complexity: O(n) and Space Complexity: O(1). It took 1 ms.
    public int compress(char[] chars) {
        int idxCompressed = 0;
        int currStreak = 1;
        for (int i = 0; i < chars.length; i++) {
            if (i + 1 < chars.length && chars[i] == chars[i + 1]) {
                currStreak++;
            } else {
                chars[idxCompressed] = chars[i];
                idxCompressed++;
                if (currStreak > 1) {
                    if (currStreak > 9) {
                        char[] streak = String.valueOf(currStreak).toCharArray();
                        for (char c : streak) {
                            chars[idxCompressed] = c;
                            idxCompressed++;
                        }
                    } else {
                        chars[idxCompressed] = (char)(currStreak + '0');
                        idxCompressed++;
                    }
                }
                currStreak = 1;
            }
        }
        return idxCompressed;
    }

    public static void main(String[] args) {
        Compress compress = new Compress();
        char[] chars = {'a','a','a','b','b','a','a'};
//        char[] chars = {'a','a','b','b','c','c','c'};
//        char[] chars = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        int ans = compress.compress(chars);
        System.out.println("Compress: " + ans);
        System.out.print(Arrays.toString(chars));
    }
}
