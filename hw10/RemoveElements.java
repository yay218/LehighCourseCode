////////////////////////////////////////////
//Name: Yang Yi
//Date: 04/06/15
//Professor: Brian Chen 
//Class: CSE 002
//
//hw10
//RemoveElements Program
//Ask the user to write omitted methods and show the expected output

import java.util.Scanner;
public class RemoveElements{
	public static void main(String [] arg){
		Scanner scan=new Scanner(System.in);
		int num[]=new int[10];
		int newArray1[];
		int newArray2[];
		int index,target;
		String answer="";
		do{
		  	System.out.println("Random input 10 ints [0-9]");
		  	num = randomInput();
		  	String out = "The original array is:";
		  	out += listArray(num);
		  	System.out.println(out);
		 
		  	System.out.print("Enter the index: ");
		  	index = scan.nextInt();
		  	newArray1 = delete(num,index);
		  	String out1="The output array is ";
		  	out1+=listArray(newArray1);   
		  	System.out.println(out1);
		 
		    System.out.print("Enter the target value: ");
		  	target = scan.nextInt();
		  	newArray2 = remove(num,target);
		  	String out2="The output array is ";
		  	out2+=listArray(newArray2);  
		  	System.out.println(out2);
		  	 
		  	System.out.print("Go again? Enter 'y' or 'Y', anything else to quit-");
		  	answer=scan.next();
		}while(answer.equals("Y") || answer.equals("y"));
	}
	public static String listArray(int num[]){
		String out="{";
		for(int j=0;j<num.length;j++){
		  	if(j>0){
		    	out+=", ";
		  	}
		  	out+=num[j];
		}
		out+="} ";
		return out;
	}
	public static int[] randomInput(){ //add the randomInput method
		int num[] = new int[10];
		for(int i = 0; i<10;i++){
			num[i] = (int)(Math.random()*10);
		}
		return num;
	}
	public static int[] delete(int[]list, int pos){ //add the delete method
		int newArray1[] = new int[list.length-1];
		if (pos>=0&&pos<list.length-1){ //check if out of boundary
			for(int i = 0; i < newArray1.length;i++){ //use loop to delete the index number
				if(i>=pos){ 
					newArray1[i] = list[i+1];
				}
				else{
					newArray1[i] = list[i];
				}
			}
			System.out.println("Index "+pos+" element is removed.");
			return newArray1;
		}
		else{
			System.out.println("The index is not valid.");
			return list;
		}
	}
	public static int[] remove(int[]list, int target){ //add the remove method
		int targetNumbers = 0;
		int ValuesOfNewArray = 0;
		for (int i = 0; i<list.length;i++){ //check how many numbers are targets
			if(target == list[i]){
				targetNumbers++;
			}
		}
		int newArray2[] = new int[list.length-targetNumbers]; //define the new array
		for (int i = 0; i<list.length;i++){ //use loop to remove targets
			if(target != list[i]){
				newArray2[ValuesOfNewArray] = list[i];
				ValuesOfNewArray++;
			}
		}
		if(targetNumbers == 0){
			System.out.println("Element "+target+" was not found.");
			return newArray2;
		}
		else{
			System.out.println("Element "+target+" has been found.");
			return newArray2;
		}
	}
}
