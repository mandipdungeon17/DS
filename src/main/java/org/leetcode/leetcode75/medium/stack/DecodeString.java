package org.leetcode.leetcode75.medium.stack;

public class DecodeString {
    public String decodeString(String s) {
        StringBuilder builder = new StringBuilder();
        String str = "";
        int num = 0;
        boolean flag = false;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '['){
                flag = true;
                if(i-1 == -1) num = 1;
                else num = s.charAt(i-1);
                continue;
            }
            else if(s.charAt(i) == ']'){
                flag = false;
            }
            if(flag){
                str+=s.charAt(i);
            }
            else{
                while(num >=0){
                    builder.append(str);
                    num--;
                }
                str="";
            }
        }
        return builder.toString();
    }
}
