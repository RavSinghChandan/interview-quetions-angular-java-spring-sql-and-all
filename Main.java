import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String s = "java developer full stack";

        f(s);
    }
    private static void f(String s){

       String s1 =  Arrays.stream(s.split(" "))
                      .map(word -> new StringBuilder(word).reverse().toString())
               .collect(Collectors.joining(" "));
        System.out.println(s1);



    }

}
