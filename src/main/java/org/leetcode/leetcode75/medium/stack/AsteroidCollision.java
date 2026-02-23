package org.leetcode.leetcode75.medium.stack;

public class AsteroidCollision {
//    public int[] asteroidCollision(int[] asteroids) {
//        int top = -1;
//        for(int i=0; i<asteroids.length-1; i++){
//            if(asteroids[i] < 0 && top >= 0 && top+1 < asteroids.length){
//                if(asteroids[i+1]+asteroids[i] == 0){
//                    //1, 10, -10
//                    top--;
//                }
//                else if(asteroids[i+1]+asteroids[top] > 0){
//                    //1, 10, -5
//                    continue;
//                }
//                else if(asteroids[i+1]+asteroids[i] < 0 && asteroids[i] > 0){
//                    //10, 2, -5
//                    //5, 2, -5
//                    //2, 3, 2, -5
//                    asteroids[top] = asteroids[i];
//                    while(top-1>-1){
//                        if(asteroids[top]+asteroids[top-1] == 0){
//                            //1, 10, -10
//                            top-=2;
//                        }
//                        else if(asteroids[top]+asteroids[top-1] > 0){
//                            //1, 10, -5
//                            top--;
//                        }
//                        else if(asteroids[top]+asteroids[top-1] < 0 && asteroids[top-1] > 0){
//                            //1, 2, -5
//                            asteroids[--top] = asteroids[i];
//                        }
//                        else{
//                            break;
//                        }
//                    }
//                }
//                else{
//                    //1, -5, -5
//                    asteroids[++top] = asteroids[i];
//                }
//            }
//            else{
//                asteroids[++top] = asteroids[i];
//            }
//        }
//        System.out.println(top);
//        if(top < 0) return new int[0];
//        int[] ans = new int[top+1];
//        System.arraycopy(asteroids, 0, ans, 0, top+1);
//        return ans;
//    }

    public int[] asteroidCollision(int[] asteroids) {
        int i=1;
        int top=0;
        while(i<asteroids.length){
            if(asteroids[i] < 0 && top>-1){
                if(asteroids[i] + asteroids[top] == 0){
                    top--;
                }
                else if(asteroids[i] + asteroids[top] < 0 && asteroids[top] < 0){
                    asteroids[++top] = asteroids[i];
                }
                else if(asteroids[i] + asteroids[top] < 0 && asteroids[top] > 0){
                    asteroids[top] = asteroids[i];
                    int temp = top;
                    if(top==0 && i+1 == asteroids.length) break;
                    top--;
                    while(top>-1){
                        if(asteroids[temp] + asteroids[top] == 0){
                            top--;
                            break;
                        }
                        else if(asteroids[temp] + asteroids[top] < 0 && asteroids[top] < 0){
                            top++;
                            break;
                        }
                        else if(asteroids[temp] + asteroids[top] < 0 && asteroids[top] > 0) {
                            asteroids[top] = asteroids[temp];
                            temp--;
                            top--;
                            if(top == -1){
                                top = 0;
                                break;
                            }
                        }
                        else break;
                    }
                    if(top==-1 && i < asteroids.length-1) top=0;
                }
            }
            else {
                asteroids[++top] = asteroids[i];
            }
            i++;
        }
        System.out.println(top);
        if(top < 0) return new int[0];
        int[] ans = new int[top+1];
        System.arraycopy(asteroids, 0, ans, 0, top+1);
        return ans;
    }

    public static void main(String[] args) {
        AsteroidCollision asteroidCollision = new AsteroidCollision();
//        int[] ans = asteroidCollision.asteroidCollision(new int[]{-2,-2,1,-2});
//        int[] ans = asteroidCollision.asteroidCollision(new int[]{8,-8});
//        int[] ans = asteroidCollision.asteroidCollision(new int[]{10, 2, -5});
//        int[] ans = asteroidCollision.asteroidCollision(new int[]{-2,2,-2,-2});
//        int[] ans = asteroidCollision.asteroidCollision(new int[]{5, 10, -5});
//        int[] ans = asteroidCollision.asteroidCollision(new int[]{1,-2,-2,-2});
//        int[] ans = asteroidCollision.asteroidCollision(new int[]{1,1,-2,-2});
//        int[] ans = asteroidCollision.asteroidCollision(new int[]{2,-1,1,-2});
//        int[] ans = asteroidCollision.asteroidCollision(new int[]{1,-1,1,-2});
//        int[] ans = asteroidCollision.asteroidCollision(new int[]{1,1,1,-2});
        int[] ans = asteroidCollision.asteroidCollision(new int[]{2,1,-2,-2});
        for(int i : ans){
            System.out.print(i + " ");
        }
    }
}
