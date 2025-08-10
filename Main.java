public class Main {
    public static void main(String[] args) {
        System.out.println(largestDigit(10));
    }
    public static int largestDigit(int n) {
        int largestDigit = Integer.MIN_VALUE;
        while(n > 0){
            int lastDigit = n%10;
            if(lastDigit > largestDigit){
                largestDigit = lastDigit;
            }
            n = n /10;
        }
        return largestDigit;
    }
}
