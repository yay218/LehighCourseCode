import java.util.Scanner;
public class ArrayInputs{
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter an integer for the size of an array: ");
        int size = in.nextInt();
        int[] numbers = new int[size];
        int i;
        for(i = 0; i<size;i++){
	        System.out.print("Please enter a positive integer: ");
	        int x;
	        if(in.hasNextInt()){
	        	x=in.nextInt();
	        	if(check(x)==true){
	        		numbers[i]=x;
	        	}
	        	else{
	        		System.out.println("Sorry, the number you entered is negative.");
	        		i--;
	        	}
	        }
	        else{
	        	System.out.println("Sorry, you did not enter an integer.");
	        	i--;
	        	in.next();
	        	continue;
	        }
        }
        System.out.println("The array is: ");
        for(int j = 0;j<size;j++){
        	System.out.println(numbers[j]);
        } 
    }
	public static boolean check(int x){
		if(x>0){
			return true;
		}
		else{
			return false;
		}
	}
}