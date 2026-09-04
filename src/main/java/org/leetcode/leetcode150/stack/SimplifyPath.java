package org.leetcode.leetcode150.stack;

import java.util.Stack;

//https://leetcode.com/problems/simplify-path/submissions/2080869801/?envType=study-plan-v2&envId=top-interview-150
public class SimplifyPath {
    //Time Complexity O(n) and Space Complexity O(n). It took 4ms.
    public String simplifyPath(String path) {
        String[] str = path.split("/");
        Stack<String> res = new Stack<>();
        for(String s: str){
            if(s.equals("..")){
                if(!res.isEmpty()) res.pop();
            } else if(s.equals(".") || s.isEmpty()){
                continue;
            }
            else{
                res.push(s);
            }
        }

        if(res.isEmpty()) return "/";
        StringBuilder sb = new StringBuilder();
        for(String s: res){
            sb.append("/").append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath("/a/./b/../../c/")); // Output: "/c"
        System.out.println(simplifyPath.simplifyPath("/a/../../b/../c//.//")); // Output: "/c"
        System.out.println(simplifyPath.simplifyPath("/../"));
        System.out.println(simplifyPath.simplifyPath("/."));
        System.out.println(simplifyPath.simplifyPath("/home/"));
    }
}
