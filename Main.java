import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] [] input = {
                {1,3},
                {2,6},
                {8,10},
                {15,18}};
        int m = input[0].length;
        int n  = input.length;
        for(int row =0;row< n;row++){
            for(int col =0 ;col<m;col++){
              if(input[row][0] >= input[col][0]){
                  System.out.print(input[row][col]+" ");
              }

            }
        }
    }
    static void l(String s){
        if(true)System.out.println(s);
    }
}


/*Problem: Given a collection of intervals, merge all overlapping intervals.

Example:

Input: [[1,3], [2,6], [8,10], [15,18]]

Output: [[1,6], [8,10], [15,18]]

Explanation: The intervals [1,3] and [2,6] overlap, so they are merged into [1,6]
Judging Criteria: */