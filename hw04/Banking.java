////////////////////////////////////////////
//Name: Yang Yi
//Date: 02/13/15
//Professor: Brian Chen 
//Class: CSE 002
//
//Banking Program

import java.util.Scanner; //Scanner is in the java.util package
	public class Practice {

		public static void main(String[] args) { // main method required for every Java program
		System.out.println("If you want to deposit money, please enter 1;"); //tell teh user if he/she want to deposit money, please enter 1
		System.out.println("if you want to withdraw money, please enter 2;"); //tell teh user if he/she want to withdraw money, please enter 2
		System.out.println("if you want to view your balance, please enter 3;"); //tell teh user if he/she want to view balance, please enter 3
		System.out.print("Now enter the number to tell me what you want to do: "); //Now ask the uer to enter what he/she wants to do
		Scanner myScanner = new Scanner(System.in); //Create a Scanner object
		int A = myScanner.nextInt(); //enter the number to show what the user wants to do
		int balance = (int)(Math.random()*4000+1000); //create the balance in a random number between 1000 and 5000 inclusive
		switch(A) //use swatch case
		{
		case 1: //if the user enter 1, which means that the user wants to deposit money
			System.out.print("Enter how much you want to deposit: "); //ask the user to enter how much he/she wants to deposit
			int x = myScanner.nextInt(); //the user enter the money he/she wants to deposit
			if(x<=0){ //if the money he/she wants to deposit is less than 0
				System.out.println("You should enter a positive number."); //tell the user that he/she should enter a positive number
			}
			else{ //if the money he/she wants to deposit is greater than 0
				balance +=x; //the balance is the old balance plus x(the money the user wants to deposit)
				System.out.println("Your resulting balance is "+balance+"."); //print the new balance the user has
			}
			break; //end of the case 1
		case 2: //if the user enter 2, which means that the user wants to withdraw money
			System.out.print("Enter how much you want to withdraw: "); //ask the user to enter how much he/she wants to withdraw
			int y = myScanner.nextInt(); //the user enter the money he.she wants to withdraw
			if(y<=0){ //if the money he/she wants to withdraw is less than 0
				System.out.println("You should enter a positive number."); //tell the user that he/she should enter a positive number
			}
			else if (y>balance){ //if the money he/she wants to withdraw is greater than balance
				System.out.println("You should withdraw money no more than the amount in your account."); //tell the user that he/she should withdraw money no more than the amount in your account
			}
			else{ //if the money he/she wants to wothdraw is greater than 0 but less than the amount in his/her account
				balance -=y; //the new balance is the old balance minus y(the money the user wants to withdraw)
				System.out.println("Your resulting balance is "+balance+"."); //print the new balance the user has
			}
			break; //end of the case 2
		case 3: //if the use enter 3, which means that the user wants to view his/her balance
			System.out.println("Your balance is "+balance+"."); //print the balance the user has
			break; //end of the case 3
		}
		
	}

}
