////////////////////////////////////////////
//Name: Yang Yi
//Date: 02/13/15
//Professor: Brian Chen 
//Class: CSE 002
//
//WhichNumber Program

import java.util.Scanner; //Scanner is in the java.util package
	public class WhichNumber{

	public static void main(String[ ] args) { // main method required for every Java program
	Scanner myScanner = new Scanner(System.in); //Create a Scanner object
	System.out.println("Think of a number between 1 and 10 inclusive."); //tell user to think a number
	System.out.print("Is your number even? "); //ask user if the number is even
	String even = myScanner.next(); //if the number is even, user enter yes, or enter no 
	if(even.equals("Y")||even.equals("y")||even.equals("N")||even.equals("n")){ //judge if the user enter a valid number
		if(even.equals("Y")||even.equals("y")){ //if the user enter yes
			System.out.print("Is it divisible by 3? "); //then ask the user if the number is divisible by three
			String divisibleByThree = myScanner.next(); //if the number is divisible by three, user enter yes, or enter no 
			if(divisibleByThree.equals("Y")||divisibleByThree.equals("y")){ //if the user enter yes
				System.out.print("Is your number 6? "); //ask the user if the number is 6
				String checkNumber1= myScanner.next(); //if the number is 6, user enter yes 
				if(checkNumber1.equals("Y")||checkNumber1.equals("y")){ //if the user enter yes
					System.out.println("Yay!"); //print that the guess is right
				}
				else if(checkNumber1.equals("N")||checkNumber1.equals("n")){ //if the user enter no
					System.out.println("You are lying."); //tell the user that he/she is lying
				}
				else{
					System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
				}
			}
			else if(divisibleByThree.equals("N")||divisibleByThree.equals("n")){ //if the user enter no
				System.out.print("Is it divisible by 4? "); //ask the user if the number is divislble by 4
				String divisibleByFour = myScanner.next(); //if the number is divislble by 4, user enter yes, or enter no
				if(divisibleByFour.equals("Y")||divisibleByFour.equals("y")){ //if the user enter yes
					System.out.print("Is it divided by 4 greater than 1? "); //ask the user if the number divides by 4 greater than 1
					String divideFourGreaterOne = myScanner.next(); //if the number divides by 4 greater than 1,the user enter yes, or enter no
					if(divideFourGreaterOne.equals("Y")||divideFourGreaterOne.equals("y")){ //if the user enter yes
						System.out.print("Is your number 8? "); //ask the user if the number is 8
						String checkNumber2 = myScanner.next(); //if the number is 8, the user enter yes
						if(checkNumber2.equals("Y")||checkNumber2.equals("y")){ //if the user enter yes
							System.out.println("Yay!"); //print that the guess is right
						}
						else if(checkNumber2.equals("N")||checkNumber2.equals("n")){ //if the user enter no
							System.out.println("You are lying."); //tell the user that he/she is lying
						}
						else{
							System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
						}
					}
					else if(divideFourGreaterOne.equals("N")||divideFourGreaterOne.equals("n")){ //if the user enter no
						System.out.print("Is your number 4? "); //ask the user if the number is 4
						String checkNumber3 = myScanner.next(); //if the number is 4, the user enter yes
						if(checkNumber3.equals("Y")||checkNumber3.equals("y")){ //if the user enter yes
							System.out.println("Yay!"); //tell the user that the guess is right
						}
						else if(checkNumber3.equals("N")||checkNumber3.equals("n")){ //if the user enter no
							System.out.println("You are lying."); //tell the user that he/she is lying
						}
						else{
							System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
						}
					}
					else{
						System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
					}
				}
				else if(divisibleByFour.equals("N")||divisibleByFour.equals("n")){ //if the user enter no
					System.out.print("Is it divided by 5? "); //ask the user if the number is 5
					String divisibleByFive = myScanner.next(); //if the number is 5, the user enter yes
					if(divisibleByFive.equals("Y")||divisibleByFive.equals("y")){ //if the user enter yes
						System.out.print("Is your number 10? "); //ask the user if the number is 10
						String checkNumber4 = myScanner.next(); //if the number is 10, the user enter yes
						if(checkNumber4.equals("Y")||checkNumber4.equals("y")){ //if the user enter yes
							System.out.println("Yay!"); //tell the user that the guess is right
						}
						else if(checkNumber4.equals("N")||checkNumber4.equals("n")){ //if the user enter no
							System.out.println("You are lying."); //tell the user that he/she is lying
						}
						else{
							System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
						}
					}
					else if(divisibleByFive.equals("N")||divisibleByFive.equals("n")){ //if the user enter no
						System.out.print("Is your number 2? "); //ask the user if the number is 2
						String checkNumber5 = myScanner.next(); //if the number is 2, the user enter yes
						if(checkNumber5.equals("Y")||checkNumber5.equals("y")){ //if the user enter yes
							System.out.println("Yay!"); //tell the user that the guess is right
						}
						else if(checkNumber5.equals("N")||checkNumber5.equals("n")){ //if the user enter no
							System.out.println("You are lying."); //tell the user that he/she is lying
						}
						else{
							System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
						}
					}
					else{
						System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
					}
				}
				else{
					System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
				}
			}
			else{
				System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
			}
		}
		else if(even.equals("N")||even.equals("n")){ //if the user enter no
			System.out.print("Is it divisible by 3? "); //ask the user if the number is 3
			String divisibleByThree = myScanner.next(); //if the number is 3, the user enter yes
			if(divisibleByThree.equals("Y")||divisibleByThree.equals("y")){ //if the user enter yes
				System.out.print("When divided by 3 is the result greater than 1? "); //ask the user when divided by 3 is the result greater than 1
				String divideThreeGreaterOne = myScanner.next(); //when divided by 3 the result is greater than 1, the user enter yes, or enter no
				if(divideThreeGreaterOne.equals("Y")||divideThreeGreaterOne.equals("y")){ //if the user enter yes
					System.out.print("Is your number 9? "); //ask the user if the number is 9
					String checkNumber6 = myScanner.next(); //if the number is 9, the user enter yes
					if(checkNumber6.equals("Y")||checkNumber6.equals("y")){ //if the user enter yes
						System.out.println("Yay!"); //tell the user that the guess is right
					}
					else if(checkNumber6.equals("N")||checkNumber6.equals("n")){ //if the user enter no
						System.out.println("You are lying."); //tell the user that he/she is lying
					}
					else{
						System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
					}
				}
				else if(divideThreeGreaterOne.equals("N")||divideThreeGreaterOne.equals("n")){ //if the user enter no
					System.out.print("Is your number 3? "); //ask the user if the number is 3
					String checkNumber7 = myScanner.next(); //if the number is 3, the user enter yes
					if(checkNumber7.equals("Y")||checkNumber7.equals("y")){ //if the user enter yes
						System.out.println("Yay!"); //tell the user that the guess is right
					}
					else if(checkNumber7.equals("N")||checkNumber7.equals("n")){ //if the user enter no
						System.out.println("You are lying."); //tell the user that he/she is lying
					}
					else{
						System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
					}
				}
				else{
					System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
				}
			}
			else if(divisibleByThree.equals("N")||divisibleByThree.equals("n")){ //if the user enter no
				System.out.print("Is it greater than 6? ");	//ask the user if the number is grater than 6
				String greaterThanSix = myScanner.next(); //if the number is greater than 6, the user enter yes, or enter no
				if(greaterThanSix.equals("Y")||greaterThanSix.equals("y")){ //if the user enter yes
					System.out.print("Is your number 7? "); //ask the user if the number if 7
					String checkNumber8 = myScanner.next(); //if the number is 7, the user enter yes
					if(checkNumber8.equals("Y")||checkNumber8.equals("y")){ //if the user enter yes
						System.out.println("Yay!"); //tell the user that the guess is right
					}
					else if(checkNumber8.equals("N")||checkNumber8.equals("n")){ //if the user enter no
						System.out.println("You are lying."); //tell the user that he/she is lying
					}
					else{
						System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
					}
				}
				else if(greaterThanSix.equals("N")||greaterThanSix.equals("n")){ //if the user enter no
					System.out.print("Is it less than 3? "); //ask the user if the number is less than 3
					String lessThanThree = myScanner.next(); //if the number is less than 3, the user enter yes, or enter no
					if(lessThanThree.equals("Y")||lessThanThree.equals("y")){ //if the user enter yes
						System.out.print("Is your number 1? "); //ask the user if the number is 1
						String checkNumber9 = myScanner.next();  //if the number is 1, the user enter yes
						if(checkNumber9.equals("Y")||checkNumber9.equals("y")){ //if the user enter yes
							System.out.println("Yay!"); //tell the user that the guess is right
						}
						else if(checkNumber9.equals("N")||checkNumber9.equals("n")){ //if the user enter no
							System.out.println("You are lying."); //tell the user that he/she is lying
						}
						else{
							System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
						}
					}
					else if(lessThanThree.equals("N")||lessThanThree.equals("n")){ //if the user enter no
						System.out.print("Is your number5? "); //ask the user if the number is 5
						String checkNumber10 = myScanner.next(); //if the number is 5, the user enter yes
						if(checkNumber10.equals("Y")||checkNumber10.equals("y")){ //if the user enter yes
							System.out.println("Yay!"); //tell the user that the guess is right
						}
						else if(checkNumber10.equals("N")||checkNumber10.equals("n")){ //if the user enter no
							System.out.println("You are lying."); //tell the user that he/she is lying
						}
						else{
							System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
						}
					}
					else{
						System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
					}
				}
				else{
					System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
				}
			}
			else{
				System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
			}
		}
		else{
			System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
		}
	}
	else{
		System.out.println("Sorry, that is not a valid input."); //if the user enter neither N nor n, print that is not a valid input
	}
	}
	}