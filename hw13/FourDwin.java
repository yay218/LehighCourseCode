////////////////////////////////////////////
//Name: Yang Yi
//Date: 04/24/15
//Professor: Brian Chen 
//Class: CSE 002
//
//hw13
//FourDwin Program
//Create a ragged 4D array containing random doubles between 0 and 30


//import scanner and public class
import java.util.Scanner;

public class FourDwin{
	
	
	//add the main method
	public static void main(String [] arg){
		Scanner in = new Scanner(System.in);
		int X = 0;
		int Y = 0;
		
		
		//Loops to ask and check for valid user input X
		while(true){
			System.out.print("Enter an integer X: ");
			if(!in.hasNextInt()){
				System.out.println("You did not enter an int, please enter again.");
				in.next();
				continue;
			}
			else{
				X = in.nextInt();
			}
			if(X <= 0){
				System.out.println("You did not enter an int>=0, please enter again.");
				continue;
			}
			else{
				break;
			}
		}
		
		
		//Loops to ask and check for valid user input Y
		while(true){
			System.out.print("Enter an integer Y where Y > X: ");
			if(!in.hasNextInt()){
				System.out.println("You did not enter an int, please enter again.");
				in.next();
				continue;
			}
			else{
				Y = in.nextInt();
			}
			if(Y <= 0){
				System.out.println("You did not enter an int>=0, please enter again.");
				continue;
			}
			else{
				if(Y<=X){
					System.out.println("Invalid input, please enter again.");
					continue;
				}
				else{
					break;
				}
			}
		}
		
		
		//Create a 4D ragged array and fill the 4D array with random doubles
		double fourD [][][][] = new double [3][][][];
		for(int i = 0; i < fourD.length; i++){
			fourD[i] = new double [X+(int)(Math.random() * (Y - X + 1))][][];
			for(int j = 0; j < fourD[i].length; j++){
				fourD[i][j] = new double [X+(int)(Math.random() * (Y - X + 1))][];
				for(int k = 0; k < fourD[i][j].length; k++){
					fourD[i][j][k] = new double [X+(int)(Math.random() * (Y - X + 1))];
					for(int l = 0; l < fourD[i][j][k].length; l++){
						fourD[i][j][k][l] = Math.random() * 30;
					}
				}
			}
		}
		
		
		//Prints out the initial array, the sorted array, and its statistics
		System.out.println();
		System.out.println("Original Array: ");
		printArray(fourD);
		sort4DArray(fourD);
		sort3DArray(fourD);
		System.out.println();
		System.out.println();
		System.out.println("Sorted Array: ");
		printArray(fourD);
		System.out.println();
		statArray(fourD);
	}
	
	
	//add the method called statArray
	public static void statArray(double[][][][] fourD){
		double sum = 0;
		int count = 0;
		
		//calculate the number and the sum
		for(int i = 0; i < fourD.length; i++){
			for(int j = 0; j < fourD[i].length; j++){
				for(int k = 0; k < fourD[i][j].length; k++){
					for(int l = 0; l < fourD[i][j][k].length; l++){
						sum += fourD[i][j][k][l];
						count ++;
					}
				}
			}
		}
		
		//caluculate the mean and print the number, sum and mean
		double mean = sum / count;
		System.out.println();
		System.out.println("Members: "+count);
		System.out.printf("Sum: %4.1f \n",sum);
		System.out.printf("Mean: %4.3f \n",mean);
	}
	
	
	//add the method called sort4DArray
	public static double[][][][] sort4DArray(double[][][][] array){
		for(int i = 0; i < array.length; i++){
    		for(int j = 0; j < array[i].length; j++){
            	for(int k = 0; k < array[i][j].length; k++){
            		for(int l = 0; l < array[i][j][k].length - 1; l++){
                    	int number = l;
                    	for(int m = l + 1; m < array[i][j][k].length; m++){
                        	if(array[i][j][k][m] < array[i][j][k][number]){
                            	number = m;
                        	}
                        	else{
                            	continue;
                        	}
                        }
                    	double temp = array[i][j][k][number]; 
                    	array[i][j][k][number] = array[i][j][k][l];
                    	array[i][j][k][l] = temp;
            		}
            	}
    		}
    	}
		return array;
	}
	
	
	//add the method called sort3DArray
	public static double[][][][] sort3DArray(double[][][][] array){
	    int number = 0;
	    int minNumber = 999999;
        for( int i = 0; i < array.length; i++ ){
            for( int j = 0; j < array[i].length; j++ ){
                for( int k = 0; k < array[i][j].length; k++ ){
                    minNumber = array[i][j][k].length;
                    for( int m = k; m < array[i][j].length; m++){
                        if( array[i][j][m].length < minNumber ){
                            minNumber = array[i][j][m].length;
                            number = m;
                        }
                        else if( array[i][j][m].length == minNumber ){
                            double minValue = 999999;
                            for( int n = k; n < array[i][j].length; n++){
                                for( int l = 0; l < array[i][j][n].length; l++ ){
                                    if( array[i][j][n][l] < minValue ){
                                        minValue = array[i][j][n][l];
                                        number = n;
                                    }
                                }
                            }
                        }
                        else{
                            continue;
                        } 
                    }
                    double[] temp = array[i][j][k];
                    array[i][j][k] = array[i][j][number];
                    array[i][j][number] = temp;
                }
            }
        }
        return array;
    }
	
	
	//add the method called printArray
	public static void printArray(double[][][][] array){
		System.out.print("{");
		for(int i = 0; i < array.length; i++){
			System.out.print("{");
			for(int j = 0; j < array[i].length; j++){
				System.out.print("{");
				for(int k = 0; k < array[i][j].length; k++){
					System.out.print("{");
					for(int l = 0; l < array[i][j][k].length; l++){
						System.out.printf("%2.1f",array[i][j][k][l]);
						if(array[i][j][k].length - 1 > l){
							System.out.print(",");
						}
					}
					if (array[i][j].length - 1 > k) {
						System.out.print("},");
					}
					else{
						System.out.print("}");
					}
				}
				if (array[i].length - 1 > j) {
					System.out.print("},");
				}
				else{
					System.out.print("}");
				}
			}
			if (array.length - 1 > i) {
				System.out.print("},");
			}
			else{
				System.out.print("}");
			}
		}
		System.out.print("}");
	}
}
