package org.leetcode.leetcode75.easy.array.and.string;

public class ReverseVowels {
//    public String reverseVowels(String s) {
//        char[] c = s.toCharArray();
//        List<Character> characters = new ArrayList<>();
//        for(int i=c.length-1; i>= 0; i--){
//            if(c[i] == 'A' || c[i] == 'E' || c[i] == 'I' || c[i] == 'O'
//                    || c[i] == 'U' || c[i] == 'a' || c[i] == 'e' || c[i] == 'i'
//                    || c[i] == 'o' || c[i] == 'u'){
//                characters.add(c[i]);
//            }
//        }
//        int rev = 0;
//        for(int i=0; i< c.length; i++){
//            if(c[i] == 'A' || c[i] == 'E' || c[i] == 'I' || c[i] == 'O'
//                    || c[i] == 'U' || c[i] == 'a' || c[i] == 'e' || c[i] == 'i'
//                    || c[i] == 'o' || c[i] == 'u'){
//                c[i] = characters.get(rev++);
//            }
//        }
//        return String.valueOf(c);
//    }

    public String reverseVowels(String s){
        boolean[] vowels = new boolean[128];
        for(char c : "aeiouAEIOU".toCharArray()){
            vowels[c] = true;
        }
        char[] ch = s.toCharArray();
        int i=0;
        int j=ch.length-1;
        while(i<j){
            if(vowels[ch[i]] && vowels[ch[j]]){
                char c = ch[j];
                ch[j] = ch[i];
                ch[i] = c;
                i++;
                j--;
            }
            else if(vowels[ch[i]]) j--;
            else if(vowels[ch[j]]) i++;
            else{
                i++;
                j--;
            }
        }
        return String.valueOf(ch);
    }

    public static void main(String[] args) {
        ReverseVowels reverseVowels = new ReverseVowels();
        String s = "hello";
        String ans = reverseVowels.reverseVowels(s);
        System.out.println("Reverse Vowels: " + ans);
    }
}
