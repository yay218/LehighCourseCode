import java.util.Scanner;
public class Factorials {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int sup = scan.nextInt();
        int sum = 0;
        for(int i = 1; i <= sup; i++){
            sum += factorial(i);
        }
        print(sum);
  }
    public static int factorial(int key){
        int mul = 1;
        for (int i = 1; i <= key; i++){
            mul *= i;
        }
        System.out.println(mul);
        return mul;
    }
    public static void print(int num){
        System.out.println("The super factorial is equal to " + num);
    }
}
