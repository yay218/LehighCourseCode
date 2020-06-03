////////////////////////////////////////////
//Name: Yang Yi
//Date: 02/06/15
//Professor: Brian Chen 
//Class: CSE 002
import java.util.Scanner; //Scanner is in the java.util package
public class Root {
    public static void main (String[] args) { // main method required for every Java program
    	Scanner myScanner = new Scanner(System.in); //Create a Scanner object
        System.out.print("Enter a double, and I print its cube root: "); //Let user enter a number
        double x = myScanner.nextDouble(); //Enter the number x
        double guess = x/3; //the first step begin the guess is to divide the number by 3
        double guess1,guess2,guess3,guess4,guess5; //do the guess step for five times
        guess1 = (2*guess*guess*guess+x)/(3*guess*guess); //do the guess step for the first time
        guess2 = (2*guess1*guess1*guess1+x)/(3*guess1*guess1); //do the guess step for the second time
        guess3 = (2*guess2*guess2*guess2+x)/(3*guess2*guess2); //do the guess step for the third time
        guess4 = (2*guess3*guess3*guess3+x)/(3*guess3*guess3); // do the guess step for the forth time
        guess5 = (2*guess4*guess4*guess4+x)/(3*guess4*guess4); // do the guess step for the fifth time
	    System.out.println("The cube root is "+guess5+": "+guess5+"*"+guess5+"*"+guess5+"="+(guess5*guess5*guess5)); //print the final answer
    }
}