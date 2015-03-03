////////////////////////////////////////////
//Name: Yang Yi
//Date: 03/01/15
//Professor: Brian Chen 
//Class: CSE 002
//
//hw06
//MakeSymbols Program
//use do-while loops to print the symbols

public class MakeSymbols { 

	public static void main(String[] args) {// main method required for every Java program
		int x = (int)(Math.random()*101); //create a random number between 0 and 100
		String output1="*"; //output1 is string type 
		String output2="&"; //output2 is string type
		int count=1; //the initial value of count is 1
		System.out.println("Simple output:"); //print "simple output"
		System.out.println("Random number generated: "+x); //print the radomly generated number
		if(x%2==0){ //check if the number x is even
			do{ //begin do-while loop
				count++; //count equal to count plus one
				output1=output1+"*"; //output1 equal to output1 plus *
			}while(count<x); //check if count less than x
			System.out.println("The output pattern: "+output1); //print the value of output1
		}
		else{ //if the number is odd
			do{ //begin do-while loop
				count++; //count equal to count plus one
				output2=output2+"&"; //output2 equal to output2 plus &
			}while(count<x); //check if count less than x
			System.out.println("The output pattern: "+output2); //print the value of output1
		}
	}
}