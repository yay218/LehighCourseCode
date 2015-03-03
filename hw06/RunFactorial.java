////////////////////////////////////////////
//Name: Yang Yi
//Date: 03/01/15
//Professor: Brian Chen 
//Class: CSE 002
//
//hw06
//RunFactorial Program
//Use while-loop to calculate the factorial of the input number

import java.util.Scanner; //Scanner is in the java.util package

public class RunFactorial{

	public static void main(String[ ] args) { // main method required for every Java program
		Scanner myScanner = new Scanner(System.in); //Create a Scanner object
		System.out.println("Please enter an integer that is between 9 and 16: "); //ask the use to enter an integer that is between 9 and 16
		int x=0; //the initial value of the input called x is 0
		int factorial=1; //the initial value of the factorial is 1
		int count=1; //the initial value of the count is 1
		while(true){ //begin while loop
			if(myScanner.hasNextInt()){ //check if the input is an integer
				x = myScanner.nextInt(); //read the input
				if(x>=9&&x<=16){ //check if the input is between 9 and 16 inclusively
					while (count<=x){ //begin the second while loop
						factorial*=count; //factorial equal to factorial multiply count
						count++; //count equal to count plus 1
					}
					System.out.println("Input accepted:"); //print that input is accepted
					System.out.println(x+"!="+factorial); //print the value of factorial
					break; //stop the second while loop
				}
				else{ //if the input is not between 9 and 16 inclusively
					System.out.println("Invalid input, enter again!"); //tell the user that the input is invalid
				}
			}
			else{ //if the input is not an integer
				System.out.println("Invalid input, enter again!"); //tell the user that the input is invalid
				myScanner.next(); //clean the scanner input which is not useful to avoid infinite loop
			}
		}
	}
}