////////////////////////////////////////////
//Name: Yang Yi
//Date: 04/19/15
//Professor: Brian Chen 
//Class: CSE 002
//
//hw12
//Multiply Program
//Implement matrix multiplication on random matrices

//import scanner and public class
import java.util.Scanner;
public class Multiply{
	
	//add the randomMatrix method
	public static int[][] randomMatrix(int width, int height){
		int[][] array = new int [height][width];
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				array[i][j] = (int)(Math.random()*21-10);
			}
		}
		return array;
	}
	
	//add the printMatrix method
	public static void printMatrix(int[][] array){
		for(int i = 0; i < array.length; i++){
			System.out.print("[ ");
			for (int j = 0; j < array[0].length; j++){
				System.out.print(array[i][j]+" ");
			}
			System.out.println("]");
		}
	}
	
	//add the matrixMultiply method
	public static int[][] matrixMultiply(int[][] array1, int[][]array2){
	    //define the width and heiht of array1 and array2
		int width1 = array1[0].length;
		int height1 = array1.length;
		int width2 = array2[0].length;
		int height2 = array2.length;
		//make sure the width of array1 is equal to the height of array2
		if(width1 == height2){
			int[][] array = new int [height1][width2];
			for(int i = 0; i < height1; i++){
				for(int j = 0; j < width2; j++){
					for(int k = 0; k < width1; k++){
						array[i][j] += array1[i][k]*array2[k][j];
					}
				}
			}
			return array;
		}
		//if the the width of array1 is not equal to the height of array2, print error and return null
		else{ 
			System.out.println("Error, multiplication is not possible.");
			return null;
		}
	}
	
	//add the main method
	public static void main(String [] arg){
	    //ask the user to enter the width and height of array1 and array2
		Scanner in = new Scanner(System.in);
		int width1, height1, width2, height2;
		//check if the user enter an integer and if the integer is greater than 0 for the width of array1
		while(true){
			System.out.print("Enter the width of array1: ");
			if(!in.hasNextInt()){
				System.out.println("You did not enter an int, please enter again.");
				in.next();
				continue;
			}
			else{
				width1 = in.nextInt();
			}
			if(width1 < 0){
				System.out.println("You did not enter an int>=0, please enter again.");
				continue;
			}
			else{
				break;
			}
		}
		
		//check the height of array1 like the way check the width of array1
		while(true){
			System.out.print("Enter the height of array1: ");
			if(!in.hasNextInt()){
				System.out.println("You did not enter an int, please enter again.");
				in.next();
				continue;
			}
			else{
				height1 = in.nextInt();
			}
			if(height1 < 0){
				System.out.println("You did not enter an int>=0, please enter again.");
				continue;
			}
			else{
				break;
			}
		}
		
		//check the width of array2 like the way check the width and height of array1
		while(true){
			System.out.print("Enter the width of array2: ");
			if(!in.hasNextInt()){
				System.out.println("You did not enter an int, please enter again.");
				in.next();
				continue;
			}
			else{
				width2 = in.nextInt();
			}
			if(width2 < 0){
				System.out.println("You did not enter an int>=0, please enter again.");
				continue;
			}
			else{
				break;
			}
		}
		
		//check the height of array2 and add one more step to check if the width of array1 is equal to the height of array2
		while(true){
			System.out.print("Enter the height of array2: ");
			if(!in.hasNextInt()){
				System.out.println("You did not enter an int, please enter again.");
				in.next();
				continue;
			}
			else{
				height2 = in.nextInt();
			}
			if(height2 < 0){
				System.out.println("You did not enter an int>=0, please enter again.");
				continue;
			}
			else{
				if(height2 == width1){
					break;
				}
				else{
					System.out.println("The width of the first array is not equal to the height of the second array, please enter again.");
					continue;
				}
			}
		}
		
		//define the two arrays and the product array and print them out
		int[][] array1 = randomMatrix(width1,height1);
		int[][] array2 = randomMatrix(width2,height2);
		int[][] productArray = matrixMultiply(array1,array2);
		System.out.println("The first matrix is: ");
		printMatrix(array1);
		System.out.println("The second matrix is: ");
		printMatrix(array2);
		System.out.println("The product matrix is: ");
		printMatrix(productArray);
	}
}
