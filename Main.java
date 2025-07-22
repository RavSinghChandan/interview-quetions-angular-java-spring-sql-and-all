import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String s1 = "listen";
        String s2 = "silent";

        f(s1,s2);
    }

    private static void f(String s1,String s2){

        Map<Character,Integer> chotu1 = new HashMap<>();
        Map<Character,Integer> chotu2 = new HashMap<>();

        for(char c1 : s1.toCharArray()){
            chotu1.put(c1,chotu1.getOrDefault(c1,0)+1);
        }
        for(char c2 : s2.toCharArray()){
            chotu2.put(c2,chotu2.getOrDefault(c2,0)+1);
        }
        System.out.println(chotu1);
        System.out.println(chotu2);
        System.out.println(chotu1.equals(chotu2));

    }

}
