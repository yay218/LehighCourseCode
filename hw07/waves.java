////////////////////////////////////////////
//Name: Yang Yi
//Date: 03/06/15
//Professor: Brian Chen 
//Class: CSE 002
//
//hw07
//waves Program
//Ask the user to enter an integer between 1 and 30, inclusive, and then prints out displays that depend on the value entered

import java.util.Scanner; //Scanner is in the java.util package
public class waves{

	public static void main(String[] args) { // main method required for every Java program
		Scanner myScanner = new Scanner(System.in); //Create a Scanner object
		System.out.print("Enter a number between 1 and 30: "); //ask the user to enter a number
		int number; //initialize the integer called number
		while(true){ //begin while loop to check if the user enter a valid number
			if(myScanner.hasNextInt()){ //check if the input is an integer
				number = myScanner.nextInt(); //read the integer
				if(number>=1&&number<=30){ //check if the integer is between 1 and 30, inclusive
					break; //if the input is between 1 and 30, inclusive, break the while loop
				}
				else{ //if the integer is not between 1 and 30, inclusive
					System.out.print("The input is not between 1 and 30, please enter again: "); //tell the user that the input is not between 1 and 30 and let the user enter again
				}
			}
			else{ //if the input is not an integer
				System.out.print("The input is invalid, please enter again: "); //Tell the user that the input is invalid and let the user enter again
				myScanner.next(); //clean the scanner input which is not useful to avoid infinite loop
			}
		} //end of while loop
		System.out.println(); //print a empty line
		System.out.println("FOR LOOP:"); //show the for loop
		for (int x=1;x<=number;x++){ //begin for loop
			if(x%2==0){ //check if x is an even number
				for (int count=1;count<=x;count++){ //begin the second for loop
					for (int k=1;k<count;k++){ //begin the third for loop
						System.out.print(x); //print the value of x
					}
					System.out.println(x); //print the value of x
				}
			}
			else{ //if x is odd
				for (int count=1;count<=x;count++){ //do the same loop as the even number
					for (int k=x;k>count;k--){
						System.out.print(x);
					}
					System.out.println(x);
				}
			}
		}
		System.out.println(); //print an empty line
		System.out.println("WHILE LOOP:"); //show the while loop
		int x=1; //the integer number x, whose initial value is 1
		while (x<=number){ //begin while loop
			if(x%2==0){ //check if x is even
				int count=1; //initialize integer called count equal to 1
				while (count<=x){ //begin the second while loop
					int k=1; //initialize integer called k equal to 1
					while (k<count){ //begin the third loop
						System.out.print(x); //print the value of x
						k++; //increment k
					}
					System.out.println(x); //print the value of x
					count++; //increment count
				}
			}
			else{ //if x is odd
				int count=1; //do the same thing as x is even
				while (count<=x){
					int k=x;
					while (k>count){
						System.out.print(x);
						k--;
					}
					System.out.println(x);
					count++;
				}
			}
			x++;
		}
		System.out.println(); //print an empty line
		System.out.println("FOR-WHILE LOOP:"); //show for-while loop
		x=1; //let x equal to 1
		do{ //begin for-while loop
			if(x%2==0){ //check if x is even
				int count=1; //initialize integer called count equal to 1
				do{ //begin the second for-while loop
					int k=1; //initialize integer called k equal to 1
					do{ //begin the third for-while loop
						k++; //increment k
						if(k==(count+1)){ //check if k is equal to count plus 1
							System.out.print(""); //print nothing
						}
						else{ //if k is not equal to count plus 1
							System.out.print(x); //print the value of x
						}
						
					}while (k<count); //check if k less than count
					System.out.println(x); //print the value of x
					count++; //increment count
				}while (count<=x); //check if count less or equal to x
			}
			else{ //if x is odd
				int count=1; //do the same thing as x is even
				do{
					int k=x;
					do{
						k--;
						if(k==(count-1)){
							System.out.print("");
						}
						else{
							System.out.print(x);
						}
					}while (k>count); 
					System.out.println(x);
					count++;
				}while (count<=x);
			}
			x++;
		}while (x<=number);
	}
}
