import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
       String s1 = "Persistent";
       String s2 = "ersPintets";

       char[] s1c = s1.toCharArray();
       char[] s2c = s2.toCharArray();

        Arrays.sort(s1c);
        Arrays.sort(s2c);

        System.out.println(s1c);
        System.out.println("----------");
        System.out.println(s2c);




    }

}
