////////////////////////////////////////////
//Name: Yang Yi
//Date: 02/21/15
//Professor: Brian Chen 
//Class: CSE 002
//
//ToHex Program

import java.util.Scanner; //Scanner is in the java.util package

public class ToHex{

	public static void main(String[ ] args) { // main method required for every Java program
	Scanner myScanner = new Scanner(System.in); //Create a Scanner object
	System.out.print("Please enter three numbers representing RGB values: "); //ask the user to enter the values
	int x1=0,x2=0,x3=0; //create variable to represent the three values
	int remainder1,remainder2,remainder3; //create the variable "remainder"
	int quotient1,quotient2,quotient3; //create the variable "quotient"
	String digit1="00",digit2="00",digit3="00"; //create three digits to represent part of hexadecimal number
	String hexadecimalNumber="000000"; //the whole hexadecimal number
	if(!myScanner.hasNextInt()){ //check if the input is an integer
		System.out.println("Sorry, your input must consist of integers."); //print that the input must consist of integers
	}
	else{ //if the input is an integer
		x1 = myScanner.nextInt(); //enter the first value
	}
	if(!myScanner.hasNextInt()){ //check if the second input is an integer
		System.out.println("Sorry, your input must consist of integers."); //print that the input must consist of integers
	}
	else{
		x2 = myScanner.nextInt(); //enter the second value
	}
	if(!myScanner.hasNextInt()){ //check the third value
		System.out.println("Sorry, your input must consist of integers."); //print that the input must consist of integers
	}
	else{
		x3 = myScanner.nextInt(); //enter the third value
		if(x1>=0&&x1<=255&&x2>=0&&x2<=255&&x3>=0&&x3<=255){ //if three value are greater than 0 and less than 255
			quotient1=x1/16; //quotient1 is the quotient x1 divided by 16
			remainder1=x1%16; //remainder1 is the remiander of x1 devided by 16
			switch(quotient1){ //using the switch case
			case 0: //if the quotient is 0
				digit1="0"; //the digit 1 is 0
				break;
			case 1: //if the quotient is 1
				digit1="1"; //the digit 1 is 1
				break;
			case 2: //if the quotient is 2
				digit1="2"; //the digit 1 is 2
				break;
			case 3: //if the quotient is 3
				digit1="3"; //the digit 1 is 3
				break;
			case 4: //if the quotient is 4
				digit1="4"; //the digit 1 is 4
				break;
			case 5: //if the quotient is 5
				digit1="5"; //the digit 1 is 5
				break;
			case 6: //if the quotient is 6
				digit1="6"; //the digit 1 is 6
				break;
			case 7: //if the quotient is 7
				digit1="7"; //the digit 1 is 7
				break;
			case 8: //if the quotient is 8
				digit1="8"; //the digit 1 is 8
				break;
			case 9: //if the quotient is 9
				digit1="9"; //the digit 1 is 9
				break; 
			case 10: //if the quotient is 10
				digit1="A"; //the digit 1 is A
				break;
			case 11: //if the quotient is 11
				digit1="B"; //the digit 1 is B
				break;
			case 12: //if the quotient is 12
				digit1="C"; //the digit 1 is C
				break;
			case 13: //if the quotient is 13
				digit1="D"; //the digit 1 is D
				break;
			case 14: //if the quotient is 14
				digit1="E"; //the digit 1 is E
				break;
			case 15: //if the quotient is 15
				digit1="F"; //the digit 1 is F
				break;
			}
			switch(remainder1){ //the same action as the quotient1
			case 0:
				digit1=digit1+"0";
				break;
			case 1:
				digit1=digit1+"1";
				break;
			case 2:
				digit1=digit1+"2";
				break;
			case 3:
				digit1=digit1+"3";
				break;
			case 4:
				digit1=digit1+"4";
				break;
			case 5:
				digit1=digit1+"5";
				break;
			case 6:
				digit1=digit1+"6";
				break;
			case 7:
				digit1=digit1+"7";
				break;
			case 8:
				digit1=digit1+"8";
				break;
			case 9:
				digit1=digit1+"9";
				break;
			case 10:
				digit1=digit1+"A";
				break;
			case 11:
				digit1=digit1+"B";
				break;
			case 12:
				digit1=digit1+"C";
				break;
			case 13:
				digit1=digit1+"D";
				break;
			case 14:
				digit1=digit1+"E";
				break;
			case 15:
				digit1=digit1+"F";
				break;
			}
			quotient2=x2/16; //the same action as quotient1 and remainder1
			remainder2=x2%16;
			switch(quotient2){
			case 0:
				digit2="0";
				break;
			case 1:
				digit2="1";
				break;
			case 2:
				digit2="2";
				break;
			case 3:
				digit2="3";
				break;
			case 4:
				digit2="4";
				break;
			case 5:
				digit2="5";
				break;
			case 6:
				digit2="6";
				break;
			case 7:
				digit2="7";
				break;
			case 8:
				digit2="8";
				break;
			case 9:
				digit2="9";
				break;
			case 10:
				digit2="A";
				break;
			case 11:
				digit2="B";
				break;
			case 12:
				digit2="C";
				break;
			case 13:
				digit2="D";
				break;
			case 14:
				digit2="E";
				break;
			case 15:
				digit2="F";
				break;
			}
			switch(remainder2){
			case 0:
				digit2=digit2+"0";
				break;
			case 1:
				digit2=digit2+"1";
				break;
			case 2:
				digit2=digit2+"2";
				break;
			case 3:
				digit2=digit2+"3";
				break;
			case 4:
				digit2=digit2+"4";
				break;
			case 5:
				digit2=digit2+"5";
				break;
			case 6:
				digit2=digit2+"6";
				break;
			case 7:
				digit2=digit2+"7";
				break;
			case 8:
				digit2=digit2+"8";
				break;
			case 9:
				digit2=digit2+"9";
				break;
			case 10:
				digit2=digit2+"A";
				break;
			case 11:
				digit2=digit2+"B";
				break;
			case 12:
				digit2=digit2+"C";
				break;
			case 13:
				digit2=digit2+"D";
				break;
			case 14:
				digit2=digit2+"E";
				break;
			case 15:
				digit2=digit2+"F";
				break;
			}
			quotient3=x3/16; //the same action as quotient1 and remainder1
			remainder3=x3%16;
			switch(quotient3){
			case 0:
				digit3="0";
				break;
			case 1:
				digit3="1";
				break;
			case 2:
				digit3="2";
				break;
			case 3:
				digit3="3";
				break;
			case 4:
				digit3="4";
				break;
			case 5:
				digit3="5";
				break;
			case 6:
				digit3="6";
				break;
			case 7:
				digit3="7";
				break;
			case 8:
				digit3="8";
				break;
			case 9:
				digit3="9";
				break;
			case 10:
				digit3="A";
				break;
			case 11:
				digit3="B";
				break;
			case 12:
				digit3="C";
				break;
			case 13:
				digit3="D";
				break;
			case 14:
				digit3="E";
				break;
			case 15:
				digit3="F";
				break;
			}
			switch(remainder3){
			case 0:
				digit3=digit3+"0";
				break;
			case 1:
				digit3=digit3+"1";
				break;
			case 2:
				digit3=digit3+"2";
				break;
			case 3:
				digit3=digit3+"3";
				break;
			case 4:
				digit3=digit3+"4";
				break;
			case 5:
				digit3=digit3+"5";
				break;
			case 6:
				digit3=digit3+"6";
				break;
			case 7:
				digit3=digit3+"7";
				break;
			case 8:
				digit3=digit3+"8";
				break;
			case 9:
				digit3=digit3+"9";
				break;
			case 10:
				digit3=digit3+"A";
				break;
			case 11:
				digit3=digit3+"B";
				break;
			case 12:
				digit3=digit3+"C";
				break;
			case 13:
				digit3=digit3+"D";
				break;
			case 14:
				digit3=digit3+"E";
				break;
			case 15:
				digit3=digit3+"F";
				break;	
			}
			hexadecimalNumber=digit1+digit2+digit3;	//the whole hexadecimal number consists of digit1, digit2 and digit3
			System.out.println("The decimal numbers R:"+x1+", G:"+x2+", B:"+x3+", is represented in hexadecimal as: "+hexadecimalNumber); //print the hexadecimal number
		}
		else{ //if the input is not between 0 and 255
			System.out.println("Sorry,you must enter values between 0-255."); //print that the user must enter values between 0-255
		}
	}
	}
}