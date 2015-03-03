////////////////////////////////////////////
//Name: Yang Yi
//Date: 03/01/15
//Professor: Brian Chen 
//Class: CSE 002
//
//hw06
//GetIntegers Program
//Ask the user for 5 non-negative integers and print out the sum of the 5 integers

import java.util.Scanner; //Scanner is in the java.util package

public class GetIntegers{

	public static void main(String[ ] args) { // main method required for every Java program
		Scanner myScanner = new Scanner(System.in); //Create a Scanner object
		System.out.println("Please enter 5 non-negative integers: "); //ask the user to enter 5 non-negative integers
		int x=0; //the initial value of the input is 0
		int i=1; //the initial value of the count called i is 1
		int sum=0; //the initial value of the sum is 0
		for (i=1;i<=5;i++){ //begin for loop
			if(myScanner.hasNextInt()){ //check if the input is an integer
				x = myScanner.nextInt(); //read the integer
				if(x>=0){ //check if the integer is non-negative
					sum+=x; //sum equal to sum plus x
				}
				else{ //if the integer is negative
					System.out.println("Invalid input, enter again"); //tell the user that the input is invalid
					i--; //let i equal to i minus one
				}
			}
			else{ //if the input is not an integer
				System.out.println("Invalid input, enter again"); //tell the user that the input is invalid
				i--; //let i equal to i minus one
				myScanner.next(); //clean the scanner input which is not useful to avoid infinite loop
			}
		}
		System.out.println("Sum is "+sum); //print the sum
	}
}