public class Main {
    public static void main(String[] args) {
/*
* /Multiple of 10 converter
//The input is an array of integers,
* go through the array, once you find a number that's multiple of 10 (10, 20, 30, etc.),
* the numbers
//following it are changed to that number, until you find the next multiple of 10.
//example:
//int input[] =  {28,7,30,84,29,74,50,37,85,74,60,63,65,90,82}
//int output[] = {28,7,30,30,30,30,50,50,50,50,60,60,60,90,90}
*
* */
        //1. start = index=0
        //  if ( 28 % 10 == 0 ) = Multiple of 10 ?continue( number(Mul-10) : number
        //

        int input[] = {28, 7, 30, 84, 29, 74, 50, 37, 85, 74, 60, 63, 65, 90, 82};
        int ans[] = new int[input.length];
        int value_store=0;

        for(int i=0;i<input.length ;i++){
            if(input[i] % 10 == 0){
                value_store =input[i];
                ans[i] = input[i];
            }else if( value_store !=0){
                ans[i] = value_store;
            }else {
                ans[i] = input[i];
            }
        }


        // Print the result
        for (int n : ans) {
            System.out.print(n + " ");
        }
    }
}
