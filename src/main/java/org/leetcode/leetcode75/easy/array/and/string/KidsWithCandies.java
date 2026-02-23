package org.leetcode.leetcode75.easy.array.and.string;

import java.util.ArrayList;
import java.util.List;

public class KidsWithCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        List<Boolean> ans = new ArrayList<>(candies.length);
        for (int candy : candies) {
            if (max < candy) {
                max = candy;
            }
        }

        for (int candy : candies) {
            ans.add(max <= candy + extraCandies);
        }
        return ans;
    }

    public static void main(String[] args) {
        KidsWithCandies kidsWithCandies = new KidsWithCandies();
        int[] candies = {2, 3, 5, 1, 3};
        int extraCandies = 3;
        List<Boolean> ans = kidsWithCandies.kidsWithCandies(candies, extraCandies);
        System.out.println("Kids with Candies: " + ans);
    }
}
