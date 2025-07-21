import java.util.Arrays;
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
       int[] a = {1,2,3,4};

        System.out.println( max(a));

    }
    private  static OptionalInt max(int[] a ){
       return  Arrays.stream(a).max();

    }
    private static  void printArray(int [] a) {
        for (int e : a) {
            System.out.print(e + " ");
        }
    }
}
