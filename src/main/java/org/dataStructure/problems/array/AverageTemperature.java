package org.dataStructure.problems.array;

import java.util.Scanner;

public class AverageTemperature {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("How many days ? ");
        int days = console.nextInt();
        int sum=0;
        int[] temp = new int[days];
        int count=0;

        for(int i=0; i<days; i++){
            System.out.print("Enter day's "+ (i+1)+ " the temperature : ");
            temp[i] = console.nextInt();
            sum+=temp[i];
        }
        double avg = (double) sum /days;
        System.out.print("The average temperature is : "+avg);

        for(int i : temp){
            if(i > avg){
                count++;
            }
        }
        System.out.print("\nThe number of temp above avg is : "+count);
    }
}
