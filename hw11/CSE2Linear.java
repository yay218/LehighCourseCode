////////////////////////////////////////////
//Name: Yang Yi
//Date: 04/12/15
//Professor: Brian Chen 
//Class: CSE 002
//
//hw11
//CSE2Linear Program
//Ask the user to enter 15 ints for final grades of CSE2 and search scores

import java.util.Random;
import java.util.Scanner;
public class CSE2Linear{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int []grades = new int[15];
		int number = 0;
		int i = 0;
		System.out.println("Enter 15 ints for final grades in CSE2: "); //ask the user to enter 15 integer
		while(i<15){ //check if the user enter integer
			if(!in.hasNextInt()){
				System.out.println("You did not enter an integer, please enter again.");
				in.next();
				continue;
			}
			else{ //check if the integer is between 0 and 100
				number = in.nextInt();
				if(number<0||number>100){
					System.out.println("The number you enter is out of the range from 0-100, please enter again.");
					continue;
				}
				else{ //check if the number is greater than or equal to the last int
					if(i==0){
						grades[i] = number;
						i++;
					}
					else if(number<grades[i-1]){
						System.out.println("The number you enter is not greater than or equal to the last int, please enter again.");
						continue;
					}
					else{
						grades[i] = number;
						i++;
					}
				}
			}
		}
		System.out.print("The grades, sorted, are: "); //output the array
		for(int j = 0; j<15; j++){
			System.out.print(grades[j]+" ");
		}
		System.out.println();
		System.out.print("Enter a grade to search for: "); //ask the user to enter the grade to search for
		int searchNumber1 = 0;
		while(true){ //make sure the number is integer and is between 0 and 100
			if(!in.hasNextInt()){ 
				System.out.println("You did not enter an integer, please enter again.");
				in.next();
				continue;
			}
			else{
				searchNumber1 = in.nextInt();
				if(searchNumber1<0||searchNumber1>100){
					System.out.println("The number you enter is out of the range from 0-100, please enter again.");
					continue;
				}
				else{
					break;
				}
			}
		}
		binarySearch(searchNumber1, grades); //calling the binarySearch method
		scramble(grades); //calling the scramble method
		
		System.out.println();
		System.out.print("Enter a grade to search for: "); //ask the user to enter the grade to search for
		int searchNumber2 = 0;
		while(true){ //make sure the number is integer and is between 0 and 100
			if(!in.hasNextInt()){
				System.out.println("You did not enter an integer, please enter again.");
				in.next();
				continue;
			}
			else{
				searchNumber2 = in.nextInt();
				if(searchNumber2<0||searchNumber2>100){
					System.out.println("The number you enter is out of the range from 0-100, please enter again.");
					continue;
				}
				else{
					break;
				}
			}
		}
		linearSearch(searchNumber2, grades); //calling the linearSearch method
		
		
	}
	public static void binarySearch(int key, int []list){ //begin the binarySearch method
		int low = 0;
		int high = 14;
		int iteration = 0;
		int mid = 0;
		while(high>=low){ 
			iteration++; //increment iteration to calculate the number of iteration
			mid = (low + high)/2;
			if(key < list[mid]){
				high = mid - 1;
			}
			else if(key == list[mid]){
				System.out.println(key+" was found in the list with "+iteration+" iterations.");
				break;
			}
			else{
				low = mid + 1;
			}
		}
		if(key!=list[mid]){
			System.out.println(key+" was not found in the list with "+iteration+" iterations.");
		}
	}
	public static void scramble(int []list){ //begin the scramble method
		Random randomGenerator = new Random();
		
		for(int j = 0;j<15;j++){ //treat the scramble method like sorting
			int randomInt = randomGenerator.nextInt(15);
			int value = list[randomInt];
			list[randomInt] = list[j];
			list[j] = value;
		}
		System.out.println("Scrambled: ");
		for(int j = 0;j<15;j++){
			System.out.print(list[j]+" ");
		}
	}
	public static void linearSearch(int key, int []list){ //begin the linearSearch method
		int iteration = 0;
		int j;
		boolean found = false;
		for(j = 0;j<15;j++){
			iteration++;
			if(key == list[j]){
				System.out.println(key+" was found in the list with "+iteration+" iterations.");
				found = true;
				break;
			}
			
		}
		if(found == false){
			System.out.println(key+" was not found in the list with "+iteration+" iterations.");
		}
	}
}