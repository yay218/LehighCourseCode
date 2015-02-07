////////////////////////////////////////////
//Name: Yang Yi
//Date: 02/06/15
//Professor: Brian Chen 
//Class: CSE 002
import java.util.Scanner;
public class FourDigits {
    public static void main (String[] args) { // main method required for every Java program
    	Scanner myScanner;
        myScanner = new Scanner(System.in);
        System.out.print("Enter a double and I display the four digits to the right of the decimal point: "); //Let user enter the number
        double number = myScanner.nextDouble(); //Enter the number
        double number1; //the decimal number1
        int number2,digit1,digit2,digit3,digit4; //the four digits and the integer
        number1 = number*10000; //multiply number by 10000
        number2 = (int)number1; //Make the number which has been multiplied by 10000 be an integer
	    digit4=number2%10; //Take the remainder of number2 and get the forth digit
	    digit3=(number2%100)/10; //Divide the number2 by 100 and take the remainder and then divide by 10 thus get the third digit
	    digit2=(number2%1000)/100; //Divide the number2 by 1000 and take the remainder and then divide by 100 thus get the second digit
	    digit1=(number2%10000)/1000; //Divide the number2 by 10000 and take the remainder and then divide by 100 thus get the first digit
	    System.out.println("The four digits are "+digit1+""+digit2+""+digit3+""+digit4); //print the four digits
    }
}